package grailstestapp

import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.SpringSecurityService
import grailstestapp.converter.TransactionConverter
import grailstestapp.dto.transaction.TransactionUserRequestModel
import grailstestapp.dto.transaction.TransactionUserResponseModel

@Transactional
class TransactionService {
    SpringSecurityService springSecurityService
    def exchangeService
    List<Double> balance(Long id) {

        List<Account> accounts =
                Account.executeQuery("select acc from Account acc where acc.user.id=(:userId)", [userId:id]) as List<Account>
        List<Transaction> transactions = Transaction.executeQuery("SELECT t FROM Transaction t WHERE t.from.user.id=(:userId) OR t.to.user.id=(:userId)", [userId:id]) as List<Transaction>


        return getBalance(transactions, accounts);
    }


    List<Double> getBalance(List<Transaction> transactions, List<Account> accounts) {
        List<Double> balances = new ArrayList<>();
        for (Account account : accounts) {
            double balance = 0.0;
            List<Transaction> addingByAccount = Transaction.executeQuery("select  t From Transaction t where (t.from.number = (:accNum) and t.type = 'DEPOSIT') or (t.to.number = (:accNum) and t.type = 'EXCHANGE')",
            [accNum:account.getNumber()]) as List<Transaction>;
            for (Transaction tr : addingByAccount) {
                if (tr.getStatus() != Status.ACCEPTED) {
                    continue;
                }
                if (tr.getType() == TransactionType.DEPOSIT) {
                    balance += tr.getAmount();
                } else {
                    balance += tr.getToAmount();
                }
            }
            for (Transaction transaction : transactions) {
                if (transaction.getStatus() != Status.ACCEPTED) {
                    continue;
                }
                if (transaction.getType() == TransactionType.DEPOSIT) {
                    continue;
                }
                if (account.getNumber() == transaction.getFrom().getNumber()) {
                    balance -= transaction.getAmount();
                }
//                else {
//                    if (transaction.getType().equals(TransactionType.EXCHANGE)) {
//                        balance += transaction.getToAmount();
//                    }
//                }
            }
            balances.add(balance);
        }


        return balances;
    }
    //SELECT t FROM Transaction t WHERE t.from.user.id=?1 OR t.to.user.id=?1
    List<TransactionUserResponseModel> getAllByUserId(Long id) {
        List<Transaction> allByUserId =
                Transaction.executeQuery("SELECT t FROM Transaction t WHERE t.from.user.id=(:userId) OR t.to.user.id=(:userId)",[userId: id]);
        return TransactionConverter.transactionsToResponses(allByUserId);
    }
    def add(Object params){
        Double requestAmount
        User currentUser = springSecurityService.currentUser as User
        String paramsTo
        if(params.to==null)
            paramsTo = params.accs
        else
            paramsTo = params.to
        if(Account.findByNumber(params.accs).getUser().getId()!=currentUser.id){
            throw new RuntimeException("You can use only your accounts");
        }
        else if(Account.findByNumber(params.accs).getStatus()!=Status.ACCEPTED
                || Account.findByNumber(params.accs).getStatus()!=Status.ACCEPTED
                || !Account.findByNumber(params.accs).isActive
                || !Account.findByNumber(params.accs).isActive){
            throw new RuntimeException("You can use only active accepted accounts");
        }else if(params.types == 'EXCHANGE' && params.accs == paramsTo){
            throw new RuntimeException("Choose different accounts as sender and receiver");
        }else if(params.types != 'EXCHANGE' && params.accs != paramsTo){
            throw new RuntimeException("Choose the same account as sender and receiver");
        }else if(params.types == 'WITHDRAWAL' || params.types == 'EXCHANGE'){
            List<Transaction> allByUserId = Transaction.executeQuery("SELECT t FROM Transaction t WHERE t.from.user.id=(:userId) OR t.to.user.id=(:userId)", [userId:currentUser.id]) as List<Transaction>;
            List<Account> curAccount = new ArrayList<>();
            curAccount.add(Account.findByNumber(params.accs));
            requestAmount = Double.valueOf(params.amount)
            if(getBalance(allByUserId,curAccount).get(0)-requestAmount<0){
                throw new RuntimeException("Not enough balance");
            }
        }
        TransactionUserRequestModel requestModel = new TransactionUserRequestModel()
        TransactionType type = TransactionType.valueOf(params.types)
        requestModel.setType(type)
        requestAmount = Double.valueOf(params.amount)
        requestModel.setAmount(requestAmount)
        requestModel.setFrom(params.accs)
        requestModel.setTo(paramsTo)

        Transaction adding = TransactionConverter.requestToTransaction(requestModel);
        adding.setFrom(Account.findByNumber(requestModel.getFrom()));
        adding.setTo(Account.findByNumber(requestModel.getTo()));
        if(requestModel.getType() == TransactionType.EXCHANGE){
//            String grailsUrl = "http://localhost:8080/exchange/exchange?fromCur="
//            +
//                    adding.getFrom().getCurrency().name()
//            +
//                    "&toCur="
//            +
//                    adding.getTo().getCurrency().name()
//            +
//                    "&amount="
//            +
//                    request.getAmount();
//            String toAmountString = restTemplate.getForObject(grailsUrl, String.class);

//            Currency from = Currency.valueOf(params.fromCur)
//            Currency to = Currency.valueOf(params.toCur)
            Double amount = Double.valueOf(params.amount)
            Currency from = Account.findByNumber(params.accs).currency
            Currency to = Account.findByNumber(paramsTo).currency

            def exchange = exchangeService.exchange(from,to,amount)
            adding.setToAmount(Double.valueOf(exchange));
        }
        adding.setIsActive(true);
        Date now = new Date();
        adding.setDateCreated(now);
        adding.setLastUpdated(now);
        adding.setStatus(Status.PENDING);
        Transaction transaction = adding.save(failOnError:true);
        return TransactionConverter.transactionToResponse(transaction);
    }
}
