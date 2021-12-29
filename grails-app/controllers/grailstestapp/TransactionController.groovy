package grailstestapp

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import grailstestapp.dto.account.AccountUserRequestModel
import grailstestapp.dto.account.AccountUserResponseModel
import grailstestapp.dto.transaction.TransactionUserRequestModel
import grailstestapp.dto.transaction.TransactionUserResponseModel
import io.micronaut.http.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

@Secured('permitAll')
class TransactionController {
    def transactionService
    def accountService
    SpringSecurityService springSecurityService
    @Secured('ROLE_USER')
    def userTransactions(){
        User currentUser = springSecurityService.currentUser as User
        List<TransactionUserResponseModel> transactions = transactionService.getAllByUserId(currentUser.id)
        render view: 'userTransactions', model: [transactionList: transactions,current: currentUser]
    }
    @Secured('ROLE_USER')
    def create(){
        User currentUser = springSecurityService.currentUser as User
        List<AccountUserResponseModel> validAccounts =  accountService.getValidByUserId(currentUser.id)
        TransactionUserRequestModel transactionUserRequestModel = new TransactionUserRequestModel()
        render view: 'create', model: [validAccountList:validAccounts,transactionUserRequestModel:transactionUserRequestModel]
    }
    @Secured('ROLE_USER')
    def add(TransactionUserRequestModel transactionUserRequestModel){
        User currentUser = springSecurityService.currentUser as User
        if (transactionUserRequestModel.hasErrors()) {
            List<AccountUserResponseModel> validAccounts =  accountService.getValidByUserId(currentUser.id)
            render view: 'create', model: [validAccountList:validAccounts,transactionUserRequestModel:transactionUserRequestModel]
            return
        }
        def addedTransaction = transactionService.add(transactionUserRequestModel)
        List<TransactionUserResponseModel> transactions = transactionService.getAllByUserId(currentUser.id)
        render view: 'userTransactions', model: [transactionList: transactions,current: currentUser]
    }
    @Secured("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    def deactivate(){
        User currentUser = springSecurityService.currentUser as User
        Long id = Long.valueOf(params.id)
        render transactionService.deActivate(id,currentUser.id)
    }
    @Secured("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    def activate(){
        User currentUser = springSecurityService.currentUser as User
        Long id = Long.valueOf(params.id)
        render transactionService.activate(id,currentUser.id)
    }
    @Secured('ROLE_ADMIN')
    def reject(){
        Long id = Long.valueOf(params.id)
        render transactionService.reject(id)
    }
    @Secured('ROLE_ADMIN')
    def accept(){
        Long id = Long.valueOf(params.id)
        render transactionService.accept(id)
    }
    @Secured('ROLE_USER')
    def put(TransactionUserRequestModel transactionUserRequestModel){
        User currentUser = springSecurityService.currentUser as User
        if (transactionUserRequestModel.hasErrors()) {
            List<AccountUserResponseModel> validAccounts =  accountService.getValidByUserId(currentUser.id)
            render view: 'update', model: [validAccountList:validAccounts,transactionUserRequestModel:transactionUserRequestModel]
            return
        }
        Long hiddenId = Long.valueOf(params.hiddenId)
        def updatedTransaction = transactionService.update(transactionUserRequestModel,currentUser.id,hiddenId)
        List<TransactionUserResponseModel> transactions = transactionService.getAllByUserId(currentUser.id)
        render view: 'userTransactions', model: [transactionList: transactions,current: currentUser]
    }
    @Secured('ROLE_USER')
    def update(){
        User currentUser = springSecurityService.currentUser as User
        List<AccountUserResponseModel> validAccounts =  accountService.getValidByUserId(currentUser.id)
        TransactionUserRequestModel transactionUserRequestModel = new TransactionUserRequestModel()
        render view: 'update', model: [validAccountList:validAccounts, transactionUserRequestModel: transactionUserRequestModel]
    }
}
