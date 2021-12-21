package grailstestapp

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import grailstestapp.dto.account.AccountUserResponseModel
import grailstestapp.dto.transaction.TransactionUserResponseModel

@Secured('ROLE_USER')
class TransactionController {
    def transactionService
    def accountService
    SpringSecurityService springSecurityService
    def userTransactions(){
        User currentUser = springSecurityService.currentUser as User
        List<TransactionUserResponseModel> transactions = transactionService.getAllByUserId(currentUser.id)
        render view: 'userTransactions', model: [transactionList: transactions,current: currentUser]
    }
    def create(){
        User currentUser = springSecurityService.currentUser as User
        List<AccountUserResponseModel> validAccounts =  accountService.getValidByUserId(currentUser.id)
        render view: 'create', model: [validAccountList:validAccounts]
    }
    def add(){
        User currentUser = springSecurityService.currentUser as User
        def addedTransaction = transactionService.add(params)
        render view:'../home/home', model:[current:currentUser]
    }
}
