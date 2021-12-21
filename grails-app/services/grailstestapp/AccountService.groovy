package grailstestapp

import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import grailstestapp.converter.AccountConverter
import grailstestapp.dto.account.AccountUserRequestModel
import grailstestapp.dto.account.AccountUserResponseModel

@Transactional
class AccountService {
    SpringSecurityService springSecurityService
    List<AccountUserResponseModel> getAllByUserId(Long id){
        User byId = User.findById(id)
        List<Account> allByUserId = Account.findAllByUser(byId)
        return AccountConverter.accountsToResponses(allByUserId);
    }
//    AccountUserResponseModel add(AccountUserRequestModel request, Long userId) {
//        Account adding = AccountConverter.requestToAccount(request);
//        adding.setIsActive(true);
//        Date now = new Date();
//        adding.setDateCreated(now);
//        adding.setLastUpdated(now);
//        adding.setStatus(Status.PENDING);
//        adding.setUser(User.findById(userId));
//        Account added = adding.save();
//        return AccountConverter.accountToResponse(added);
//    }
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
    def update(Object params){

    }
}
