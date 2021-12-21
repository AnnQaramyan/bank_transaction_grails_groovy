package grailstestapp

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import grailstestapp.dto.account.AccountUserResponseModel

@Secured('permitAll')
class AccountController {
    SpringSecurityService springSecurityService
    def accountService
    def transactionService
    def userAccounts(){
        User currentUser = springSecurityService.currentUser as User
        List<AccountUserResponseModel> accounts = accountService.getAllByUserId(currentUser.id)
        List<Double> balances = transactionService.balance(currentUser.id)
        render view: 'userAccounts', model: [accountList: accounts, balanceList:balances, current: currentUser]
    }
    def add(){
        User currentUser = springSecurityService.currentUser as User
        def addedAccount = accountService.add(params)
        render view:'../home/home', model:[current:currentUser]
    }
    def create(){

    }
    def put(){
        User currentUser = springSecurityService.currentUser as User
        def updatedAccount = accountService.(params)
    }
    def update(){
        User currentUser = springSecurityService.currentUser as User
        render view: 'update', model: [currentAccount:c]
    }
}
