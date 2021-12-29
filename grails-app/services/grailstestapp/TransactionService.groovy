package grailstestapp

import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.SpringSecurityService
import grailstestapp.converter.TransactionConverter
import grailstestapp.dto.account.AccountUserResponseModel
import grailstestapp.dto.transaction.TransactionAdminModel
import grailstestapp.dto.transaction.TransactionUserRequestModel
import grailstestapp.dto.transaction.TransactionUserResponseModel

@Transactional
class TransactionService {
    SpringSecurityService springSecurityService
    def exchangeService

    List<Double> balance(Long id) {

        List<Account> accounts =
                Account.executeQuery("select acc from Account acc where acc.user.id=(:userId)", [userId: id]) as List<Account>
        List<Transaction> transactions = Transaction.executeQuery("SELECT t FROM Transaction t WHERE t.from.user.id=(:userId) OR t.to.user.id=(:userId)", [userId: id]) as List<Transaction>


        return getBalance(transactions, accounts);
    }


    List<Double> getBalance(List<Transaction> transactions, List<Account> accounts) {
        List<Double> balances = new ArrayList<>();
        for (Account account : accounts) {
            double balance = 0.0;
            List<Transaction> addingByAccount = Transaction.executeQuery("select  t From Transaction t where (t.from.number = (:accNum) and t.type = 'DEPOSIT') or (t.to.number = (:accNum) and t.type = 'EXCHANGE')",
                    [accNum: account.getNumber()]) as List<Transaction>;
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
            }
            balances.add(balance);
        }


        return balances;
    }
    List<TransactionUserResponseModel> getAllByUserId(Long id) {
        List<Transaction> allByUserId =
                Transaction.executeQuery("SELECT t FROM Transaction t WHERE t.from.user.id=(:userId) OR t.to.user.id=(:userId)", [userId: id]);
        return TransactionConverter.transactionsToResponses(allByUserId);
    }

    def add(TransactionUserRequestModel transactionUserRequestModel) {
        User currentUser = springSecurityService.currentUser as User
        String paramsTo
        if (transactionUserRequestModel.to == null) {
            paramsTo = transactionUserRequestModel.from
            transactionUserRequestModel.to=transactionUserRequestModel.from
        }
        else
            paramsTo = transactionUserRequestModel.to
        if (Account.findByNumber(transactionUserRequestModel.from).getUser().getId() != currentUser.id) {
            throw new RuntimeException("You can use only your accounts");
        } else if (Account.findByNumber(transactionUserRequestModel.from).getStatus() != Status.ACCEPTED
                || Account.findByNumber(paramsTo).getStatus() != Status.ACCEPTED
                || !Account.findByNumber(transactionUserRequestModel.from).isActive
                || !Account.findByNumber(paramsTo).isActive) {
            throw new RuntimeException("You can use only active accepted accounts");
        } else if (transactionUserRequestModel.type == TransactionType.EXCHANGE && transactionUserRequestModel.from == paramsTo) {
            throw new RuntimeException("Choose different accounts as sender and receiver");
        } else if (transactionUserRequestModel.type != TransactionType.EXCHANGE && transactionUserRequestModel.from != paramsTo) {
            throw new RuntimeException("Choose the same account as sender and receiver");
        } else if (transactionUserRequestModel.type == TransactionType.WITHDRAWAL || transactionUserRequestModel.type == TransactionType.EXCHANGE) {
            List<Transaction> allByUserId = Transaction.executeQuery("SELECT t FROM Transaction t WHERE t.from.user.id=(:userId) OR t.to.user.id=(:userId)", [userId: currentUser.id]) as List<Transaction>;
            List<Account> curAccount = new ArrayList<>();
            curAccount.add(Account.findByNumber(transactionUserRequestModel.from));
            if (getBalance(allByUserId, curAccount).get(0) - transactionUserRequestModel.amount < 0) {
                throw new RuntimeException("Not enough balance");
            }
        }
//        TransactionUserRequestModel requestModel = new TransactionUserRequestModel()
//        TransactionType type = TransactionType.valueOf(params.types)
//        requestModel.setType(type)
//        requestAmount = Double.valueOf(params.amount)
//        requestModel.setAmount(requestAmount)
//        requestModel.setFrom(params.accs)
//        requestModel.setTo(paramsTo)

        Transaction adding = TransactionConverter.requestToTransaction(transactionUserRequestModel);
        adding.setFrom(Account.findByNumber(transactionUserRequestModel.getFrom()));
        adding.setTo(Account.findByNumber(transactionUserRequestModel.getTo()));
        if (transactionUserRequestModel.getType() == TransactionType.EXCHANGE) {
            Currency from = Account.findByNumber(transactionUserRequestModel.from).currency
            Currency to = Account.findByNumber(paramsTo).currency

            def exchange = exchangeService.exchange(from, to, transactionUserRequestModel.amount)
            adding.setToAmount(Double.valueOf(exchange));
        }
        adding.setIsActive(true);
        Date now = new Date();
        adding.setDateCreated(now);
        adding.setLastUpdated(now);
        adding.setStatus(Status.PENDING);
        Transaction transaction = adding.save(failOnError: true);
        return TransactionConverter.transactionToResponse(transaction);
    }

    TransactionUserResponseModel deActivate(Long id, Long userId) {
        Transaction transaction = Transaction.findById(id);
        if (!transaction.isActive) {
            throw new RuntimeException("Already inactive");
        }
        if (transaction.getStatus() == Status.ACCEPTED || transaction.getStatus() == Status.REJECTED) {
            throw new RuntimeException("You can deActive only  transactions with PENDING status");
        }
        if (transaction.getFrom().getUser().getId() != userId) {
            throw new RuntimeException("You can delete only your transactions");
        } else {
            transaction.setIsActive(false);
            transaction.setLastUpdated(new Date());
        }
        return TransactionConverter.transactionToResponse(transaction.save());
    }

    TransactionUserResponseModel activate(Long id, Long userId) {
        Transaction transaction = Transaction.findById(id);
        if (transaction.isActive) {
            throw new RuntimeException("Already Active");
        }
        if (transaction.getFrom().getUser().getId() != userId) {
            throw new RuntimeException("You can activate only your transactions");
        } else {
            transaction.setIsActive(true);
            transaction.setLastUpdated(new Date());
        }
        return TransactionConverter.transactionToResponse(transaction.save());
    }

    TransactionUserResponseModel update(TransactionUserRequestModel transactionUserRequestModel, Long userId, Long hiddenId) {
        String paramsTo
        if(transactionUserRequestModel.type == TransactionType.EXCHANGE)
        {
            paramsTo = transactionUserRequestModel.to
        }
        else{
            paramsTo = transactionUserRequestModel.from
            transactionUserRequestModel.to = transactionUserRequestModel.from
        }
        if (userId == Transaction.findById(hiddenId).getFrom().getUser().getId()) {
            if (Account.findByNumber(transactionUserRequestModel.from).getStatus() != Status.ACCEPTED
                    || Account.findByNumber(paramsTo).getStatus() != Status.ACCEPTED
                    || !Account.findByNumber(transactionUserRequestModel.from).isActive
                    || !Account.findByNumber(paramsTo).isActive) {
                throw new RuntimeException("You can use only active accepted accounts");
            } else if (transactionUserRequestModel.type == TransactionType.EXCHANGE && transactionUserRequestModel.from==paramsTo) {
                throw new RuntimeException("Choose different accounts as sender and receiver");
            } else if (transactionUserRequestModel.type != TransactionType.EXCHANGE && transactionUserRequestModel.from!=paramsTo) {
                throw new RuntimeException("Choose the same account as sender and receiver");
            } else if (transactionUserRequestModel.type == TransactionType.WITHDRAWAL || transactionUserRequestModel.type == TransactionType.EXCHANGE) {
                List<Transaction> allByUserId = Transaction.executeQuery("SELECT t FROM Transaction t WHERE t.from.user.id=(:userId) OR t.to.user.id=(:userId)", [userId: userId]) as List<Transaction>;
                List<Account> curAccount = new ArrayList<>();
                curAccount.add(Account.findByNumber(transactionUserRequestModel.to));
                if (getBalance(allByUserId, curAccount).get(0) - transactionUserRequestModel.amount < 0) {
                    throw new RuntimeException("Not enough balance");
                }
            }
            Transaction transaction = Transaction.findById(hiddenId);
            if (transaction.getStatus() == Status.PENDING) {
                if (Account.findByNumber(paramsTo).getStatus() == Status.ACCEPTED) {
                    transaction.setAmount(transactionUserRequestModel.amount);
                    if (transactionUserRequestModel.type == TransactionType.EXCHANGE && transaction.getType() != TransactionType.EXCHANGE) {
                        Currency from = Account.findByNumber(transactionUserRequestModel.from).currency
                        Currency to = Account.findByNumber(paramsTo).currency
                        def exchange = exchangeService.exchange(from, to, transactionUserRequestModel.amount)
                        transaction.setToAmount(Double.valueOf(exchange));
                    } else if (transactionUserRequestModel.type != TransactionType.EXCHANGE && transaction.getType() == TransactionType.EXCHANGE) {
                        transaction.setToAmount(null);
                    }
                    transaction.setType(transactionUserRequestModel.type);
                    transaction.setTo(Account.findByNumber(paramsTo));

                    Date now = new Date();
                    transaction.setLastUpdated(now);
                    return TransactionConverter.transactionToResponse(transaction.save());
                } else {
                    throw new RuntimeException("You can use only accepted accounts");
                }

            }
            else{
                throw new RuntimeException("You can update only  transactions with PENDING status");
            }
        }
        else{
            throw new RuntimeException("You can update only your transactions");
        }
    }
    List<TransactionAdminModel> getAll(){
        List<Transaction> all = Transaction.findAll();
        return TransactionConverter.transactionsToAdminModels(all);
    }

    TransactionAdminModel accept(Long id){
        Transaction byId = Transaction.findById(id);
        byId.setStatus(Status.ACCEPTED);
        byId.setLastUpdated(new Date());
        return TransactionConverter.transactionToAdminModel(byId.save());
    }
    TransactionAdminModel reject(Long id){
        Transaction byId = Transaction.findById(id);
        byId.setStatus(Status.REJECTED);
        byId.setIsActive(false)
        byId.setLastUpdated(new Date());
        return TransactionConverter.transactionToAdminModel(byId.save());
    }
}
