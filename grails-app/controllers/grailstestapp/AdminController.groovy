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
        List<AccountAdminModel> requests = accountService.getAll(0)
        Integer count = accountService.getCount()
        if(count%5==0)
            count = count/5
        else{
            count = count/5+1
        }
        render view: 'accountRequests', model: ['requestsList': requests, 'count':count, page:1]
    }
    def paginatedAccounts(){
        Integer pageNumber = Integer.valueOf(params.page)
        List<AccountAdminModel> accounts = accountService.getAll(pageNumber-1)
        render template: 'accountRequestsContent', model: ['requestsList':accounts]
    }
    def accountsPaginationNavbar(){
        Integer pageNumber = Integer.valueOf(params.page)
        Integer count = accountService.getCount()
        if(count%5==0)
            count = count/5
        else{
            count = count/5+1
        }
        render template: '../accountsPaginationNavbar', model: ['page':pageNumber, 'count':count]
    }
    def users(){
//        def filters = session.userFilters
//        if(filters != null) {
//            render view: 'users', model: ['usersList': userService.getUsers((UserFilters)filters).collect { UserConverter.userToAdminModel(it)}]
//        }
        List<UserAdminModel> users = userService.getAll(0)
        Integer count = userService.getCount()
        if(count%5==0)
            count = count/5
        else{
            count = count/5+1
        }
        render view: 'users', model: ['usersList':users, 'count':count, 'page':1]
    }
    def paginatedUsers(){
        Integer pageNumber = Integer.valueOf(params.page)
        List<UserAdminModel> users = userService.getAll(pageNumber-1)
        render template: 'usersContent', model: ['usersList':users]
    }
    def usersPaginationNavbar(){
        Integer pageNumber = Integer.valueOf(params.page)
        Integer count = userService.getCount()
        if(count%5==0)
            count = count/5
        else{
            count = count/5+1
        }
        render template: '../usersPaginationNavbar', model: ['page':pageNumber, 'count':count]
    }
    def transactionRequests(){
        List<TransactionAdminModel> transactions = transactionService.getAll(0)
        Integer count = transactionService.getCount()
        if(count%5==0)
            count = count/5
        else{
            count = count/5+1
        }
        render view: 'transactionRequests', model: ['transactionsList':transactions, 'count':count, 'page':1]
    }
    def paginatedTransactions(){
        Integer pageNumber = Integer.valueOf(params.page)
        List<TransactionAdminModel> transactions = transactionService.getAll(pageNumber-1)
        render template: 'transactionRequestsContent', model: ['transactionsList':transactions]
    }
    def transactionsPaginationNavbar(){
        Integer pageNumber = Integer.valueOf(params.page)
        Integer count = transactionService.getCount()
        if(count%5==0)
            count = count/5
        else{
            count = count/5+1
        }
        render template: '../transactionsPaginationNavbar', model: ['page':pageNumber, 'count':count]
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
