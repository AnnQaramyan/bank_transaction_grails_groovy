package grailstestapp

import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import grailstestapp.converter.AccountConverter
import grailstestapp.dto.account.AccountAdminModel
import grailstestapp.dto.account.AccountUserRequestModel
import grailstestapp.dto.account.AccountUserResponseModel

import java.util.stream.Collectors

@Transactional
class AccountService {
    SpringSecurityService springSecurityService
    List<AccountUserResponseModel> getAllByUserId(Long id, Integer max, Integer pageNumber){
        User byId = User.findById(id)
        List<Account> allByUserId = Account.findAllByUser(byId,[max: max,offset: pageNumber*5])
        return AccountConverter.accountsToResponses(allByUserId);
    }
    List<AccountAdminModel> getUserAccounts(Long id){
        User byId = User.findById(id)
        List<Account> allByUserId = Account.findAllByUser(byId)
        return AccountConverter.accountsToAdminModels(allByUserId)
    }
    def add(Object params){
        AccountUserRequestModel requestModel = new AccountUserRequestModel()
        requestModel.setNumber(Generator.generateRandomAccountNumber())
        Currency currency = Currency.valueOf(params.currencies)
        requestModel.setCurrency(currency)
        Account adding = AccountConverter.requestToAccount(requestModel);
        adding.setIsActive(true);
        Date now = new Date();
        adding.setDateCreated(now);
        adding.setLastUpdated(now);
        adding.setStatus(Status.PENDING);
        User currentUser = springSecurityService.currentUser as User
        adding.setUser(currentUser);
        Account added = adding.save();
        return AccountConverter.accountToResponse(added);
    }
    List<AccountUserResponseModel> getValidByUserId(Long user_id){
        List<Account> validByUserId = Account.executeQuery("SELECT acc FROM Account acc WHERE acc.user.id=(:user_id) AND acc.status='ACCEPTED' AND acc.isActive=true",[user_id:user_id]);
        return AccountConverter.accountsToResponses(validByUserId);
    }

    Account getByNumber(String number){
        return  Account.findByNumber(number)
    }
    def update(Object params){
        User currentUser = springSecurityService.currentUser as User
        Account accountByNumber = Account.findByNumber(params.number);
        if(accountByNumber.getUser().getId()!=currentUser.id){
            throw new RuntimeException("You can update only your accounts");
        }else if(accountByNumber.getStatus()!=Status.PENDING){
            throw new RuntimeException("You can't update accepted/rejected account");
        }
        else {
            accountByNumber.setNumber(params.number);
            Currency currency = Currency.valueOf(params.currencies)
            accountByNumber.setCurrency(currency);
            accountByNumber.setLastUpdated(new Date());
            Account updated = accountByNumber.save();
            return AccountConverter.accountToResponse(updated);
        }
    }
    AccountUserResponseModel deActivate(Long id, Long userId){
        Account account = Account.findById(id);
        if(!account.isActive){
            throw new RuntimeException("Already InActive ! ");
        }
        if(User.findById(userId).getAuthorities().stream().map(authority -> authority.getAuthority()).collect(Collectors.toSet()).contains('ROLE_ADMIN')
                && account.getStatus() == Status.ACCEPTED){
            account.setIsActive(false);
            account.setLastUpdated(new Date());
        }else if(User.findById(userId).getAuthorities().stream().map(authority -> authority.getAuthority()).collect(Collectors.toSet()).contains('ROLE_USER')
                && account.getStatus() == Status.PENDING
                && account.getUser().getId() == userId){
            account.setIsActive(false);
            account.setLastUpdated(new Date());
        }
        else{
            throw new RuntimeException("Please check if you can deActive this Account ((");
        }
        return AccountConverter.accountToResponse(account.save());
    }

    AccountUserResponseModel activate(Long id, Long userId){
        Account account = Account.findById(id);
        if(account.isActive){
            throw new RuntimeException("Already Active !!");
        }
        if(User.findById(userId).getAuthorities().stream().map(authority -> authority.getAuthority()).collect(Collectors.toSet()).contains('ROLE_ADMIN')
                && account.getStatus() == Status.ACCEPTED){
            account.setIsActive(true);
            account.setLastUpdated(new Date());
        }else if(User.findById(userId).getAuthorities().stream().map(authority -> authority.getAuthority()).collect(Collectors.toSet()).contains('ROLE_USER')
                && account.getStatus() == Status.PENDING && account.getUser().getId() == userId){
            account.setIsActive(true);
            account.setLastUpdated(new Date());
        }

        return AccountConverter.accountToResponse(account.save());

    }
    def reject(Long id){
        Account byId = Account.findById(id);
        byId.setStatus(Status.REJECTED);
        byId.setIsActive(false);
        byId.setLastUpdated(new Date());
        return AccountConverter.accountToAdminModel(byId.save());
    }
    def accept(Long id){
        Account byId = Account.findById(id);
        byId.setStatus(Status.ACCEPTED);
        byId.setLastUpdated(new Date());
        return AccountConverter.accountToAdminModel(byId.save());
    }
    def getAll(Integer pageNumber){
        List<Account> requests = Account.findAll(max:5,offset:pageNumber*5);
        return AccountConverter.accountsToAdminModels(requests);
    }
    Integer getCount(){
        return Account.findAll().size()
    }
}
