package grailstestapp

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import grailstestapp.converter.AccountConverter
import grailstestapp.dto.account.AccountUserResponseModel
import grailstestapp.dto.transaction.TransactionAdminModel

@Secured('permitAll')
class AccountController {
    SpringSecurityService springSecurityService
    def accountService
    def transactionService
    @Secured('ROLE_USER')
    def userAccounts(){
        User currentUser = springSecurityService.currentUser as User
        List<AccountUserResponseModel> accounts = accountService.getAllByUserId(currentUser.id,5,0)
        List<Double> balances = transactionService.balance(currentUser.id,0)
        Integer count = accountService.getAllByUserId(currentUser.id,Account.findAll().size(),0).size()
        if(count%5==0)
            count = count/5
        else{
            count = count/5+1
        }
        render view: 'userAccounts', model: [accountList: accounts, balanceList:balances, current: currentUser, 'count':count, 'page':1]
    }
    def paginatedUserAccounts(){
        User currentUser = springSecurityService.currentUser as User
        Integer pageNumber = Integer.valueOf(params.page)
        List<AccountUserResponseModel> userAccounts = accountService.getAllByUserId(currentUser.id,5,pageNumber-1)
        List<Double> balances = transactionService.balance(currentUser.id,pageNumber-1)
        render template: 'userAccountsContent', model: ['accountList':userAccounts, 'balanceList':balances]
    }
    def userAccountsPaginationNavbar(){
        User currentUser = springSecurityService.currentUser as User
        Integer pageNumber = Integer.valueOf(params.page)
        Integer count = accountService.getAllByUserId(currentUser.id,Account.findAll().size(),0).size()
        if(count%5==0)
            count = count/5
        else{
            count = count/5+1
        }
        render template: '../userAccountsPaginationNavbar', model: ['page':pageNumber, 'count':count]
    }
    @Secured('ROLE_USER')
    def add(){
        User currentUser = springSecurityService.currentUser as User
        def addedAccount = accountService.add(params)
        Integer count = accountService.getAllByUserId(currentUser.id,Account.findAll().size(),0).size()
        if(count%5==0)
            count = count/5
        else{
            count = count/5+1
        }
        List<AccountUserResponseModel> accounts = accountService.getAllByUserId(currentUser.id,5,count-1)
        List<Double> balances = transactionService.balance(currentUser.id,0)
        render view: 'userAccounts', model: [accountList: accounts, balanceList:balances, current: currentUser, 'count':count, page:count]
    }
    @Secured('ROLE_USER')
    def create(){

    }
    @Secured('ROLE_USER')
    def put(){
        User currentUser = springSecurityService.currentUser as User
        def updatedAccount = accountService.update(params)
        Integer count = accountService.getAllByUserId(currentUser.id,Account.findAll().size(),0).size()
        if(count%5==0)
            count = count/5
        else{
            count = count/5+1
        }
        Integer pageNumber = Integer.valueOf(params.hiddenPageNumberInput)
        List<AccountUserResponseModel> accounts = accountService.getAllByUserId(currentUser.id,5,pageNumber-1)
        List<Double> balances = transactionService.balance(currentUser.id,pageNumber-1)

        render view: 'userAccounts', model: [accountList: accounts, balanceList:balances, current: currentUser,count:count,page:pageNumber]
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
