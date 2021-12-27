package grailstestapp

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import grailstestapp.converter.AccountConverter
import grailstestapp.dto.account.AccountUserResponseModel

@Secured('permitAll')
class AccountController {
    SpringSecurityService springSecurityService
    def accountService
    def transactionService
    @Secured('ROLE_USER')
    def userAccounts(){
        User currentUser = springSecurityService.currentUser as User
        List<AccountUserResponseModel> accounts = accountService.getAllByUserId(currentUser.id)
        List<Double> balances = transactionService.balance(currentUser.id)
        render view: 'userAccounts', model: [accountList: accounts, balanceList:balances, current: currentUser]
    }
    @Secured('ROLE_USER')
    def add(){
        User currentUser = springSecurityService.currentUser as User
        def addedAccount = accountService.add(params)
        List<AccountUserResponseModel> accounts = accountService.getAllByUserId(currentUser.id)
        List<Double> balances = transactionService.balance(currentUser.id)
        render view: 'userAccounts', model: [accountList: accounts, balanceList:balances, current: currentUser]
    }
    @Secured('ROLE_USER')
    def create(){

    }
    @Secured('ROLE_USER')
    def put(){
        User currentUser = springSecurityService.currentUser as User
        def updatedAccount = accountService.update(params)
        List<AccountUserResponseModel> accounts = accountService.getAllByUserId(currentUser.id)
        List<Double> balances = transactionService.balance(currentUser.id)
        render view: 'userAccounts', model: [accountList: accounts, balanceList:balances, current: currentUser]
    }
    @Secured('ROLE_USER')
    def update(){
        User currentUser = springSecurityService.currentUser as User
        render view: 'update', model: [current: currentUser]
    }
    @Secured("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    def deactivate(){
        User currentUser = springSecurityService.currentUser as User
        Long id = Long.valueOf(params.id)
        render accountService.deActivate(id,currentUser.id)
    }
    @Secured("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    def activate(){
        User currentUser = springSecurityService.currentUser as User
        Long id = Long.valueOf(params.id)
        render accountService.activate(id,currentUser.id)
    }
}
