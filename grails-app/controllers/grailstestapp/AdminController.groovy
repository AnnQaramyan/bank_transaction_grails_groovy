package grailstestapp

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import grailstestapp.dto.account.AccountAdminModel
import grailstestapp.dto.transaction.TransactionAdminModel
import grailstestapp.dto.user.UserAdminModel

@Secured('ROLE_ADMIN')
class AdminController {
    def userService
    def accountService
    def transactionService
    SpringSecurityService springSecurityService
    def accountRequests(){
        List<AccountAdminModel> requests = accountService.getAll()
        render view: 'accountRequests', model: ['requestsList': requests]
    }
    def users(){
        List<UserAdminModel> users = userService.getAll()
        render view: 'users', model: ['usersList':users]
    }
    def transactionRequests(){
        List<TransactionAdminModel> transactions = transactionService.getAll()
        render view: 'transactionRequests', model: ['transactionsList':transactions]
    }
    def userAccounts(){
        Long id = Long.valueOf(params.id)
        render template: 'accounts', model: [accountList:accountService.getUserAccounts(id)]
    }
    def deactivateUser(){
        Long id = Long.valueOf(params.id)
        render userService.deactivate(id)
    }
    def activateUser(){
        Long id = Long.valueOf(params.id)
        render userService.activate(id)
    }
    def rejectAccount(){
        Long id = Long.valueOf(params.id)
        render accountService.reject(id)
    }
    def acceptAccount(){
        Long id = Long.valueOf(params.id)
        render accountService.accept(id)
    }
}
