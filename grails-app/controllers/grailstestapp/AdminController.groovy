package grailstestapp

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import grailstestapp.converter.UserConverter
import grailstestapp.dto.account.AccountAdminModel
import grailstestapp.dto.transaction.TransactionAdminModel
import grailstestapp.dto.user.UserAdminModel
import grailstestapp.dto.user.UserFilters

@Secured('ROLE_ADMIN')
class AdminController {
    def userService
    def accountService
    def transactionService
    def loanService
    SpringSecurityService springSecurityService
    long items_page = 5
    
    def accountRequests() {
        List<AccountAdminModel> requests = accountService.getAll(0)
        Integer count = accountService.getCount()
        if (count % items_page == 0) count = count / items_page else {
            count = count / items_page + 1
        }
        render view: 'accountRequests', model: ['requestsList': requests, 'count': count, page: 1]
    }

    def paginatedAccounts() {
        Integer pageNumber = Integer.valueOf(params.page)
        List<AccountAdminModel> accounts = accountService.getAll(pageNumber - 1)
        render template: 'accountRequestsContent', model: ['requestsList': accounts]
    }

    def accountsPaginationNavbar() {
        Integer pageNumber = Integer.valueOf(params.page)
        Integer count = accountService.getCount()
        if (count % items_page == 0) count = count / items_page else {
            count = count / items_page + 1
        }
        render template: '../accountsPaginationNavbar', model: ['page': pageNumber, 'count': count]
    }

    def get_user_list(Integer page_number) {
        List<UserAdminModel> users

        if (params.search != null) {

            users = UserConverter.usersToAdminModels(User.findAll(max:items_page, offset:page_number*items_page))
            def filters = new UserFilters( params.search, params.filter_by)
           // users = userService.getUsers(filters, page_number)
            users = userService.getUsers(filters, page_number)
        } else {
            users = userService.getAll(page_number)
        }
        return users
    }

    def users() {
        List<UserAdminModel> users = get_user_list(0)
        Integer count
        if(params.search!=null){
            count = users.size()
        }else{
            count = userService.getCount()
        }
        if (count % items_page == 0) count = count / items_page else {
            count = count / items_page + 1
        }
        render view: 'users', model: ['usersList': users, 'count': count, 'page': 1]
    }

    def paginatedUsers() {
        //Integer pageNumber = Integer.valueOf(params.page)
        List<UserAdminModel> users = get_user_list(0)
        render template: 'usersContent', model: ['usersList': users]
    }

    def usersPaginationNavbar() {
        //Integer pageNumber = Integer.valueOf(params.page)
        Integer count = userService.getCount()
        if (count % items_page == 0) count = count / items_page else {
            count = count/items_page+1
        }
        render template: '../usersPaginationNavbar', model: ['page':1, 'count':count]
    }

    def transactionRequests(){
        List<TransactionAdminModel> transactions = transactionService.getAll(0)
        Integer count = transactionService.getCount()
        if(count%items_page==0)
            count = count/items_page
        else{
            count = count/items_page+1
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
        if(count%items_page==0)
            count = count/items_page
        else{
            count = count/items_page+1
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

    def getAllLoans(){
        List<Loan> loans = loanService.getAll()
        render view: 'allLoans', model: [loanList: loans]
    }

    def loanRequest(){
        Loan loan  = Loan.findById(params.loanId)
        List<Account> potential_investors = loanService.getPotentialInvestors(params.loanId)
        render view: 'loan', model: [loan: loan, potential_investors: potential_investors]
    }

    @Secured('ROLE_ADMIN')
    def update(){
        loanService.update(params)
        List<Loan> loans = loanService.getAll()
        render view: 'allLoans', model: [loanList: loans]
    }

    @Secured('ROLE_ADMIN')
    def acceptLoan(){
        loanService.accept(params.id)
        List<Loan> loans = loanService.getAll()
        render view: 'allLoans', model: [loanList: loans]
    }

    @Secured('ROLE_ADMIN')
    def rejectLoan(){
        loanService.reject(params.id)
        List<Loan> loans = loanService.getAll()
        render view: 'allLoans', model: [loanList: loans]
    }
    @Secured('ROLE_ADMIN')
    def deactivateLoan(){
        loanService.deactivateLoan(params.id)
        List<Loan> loans = loanService.getAll()
        render view: 'allLoans', model: [loanList: loans]
    }
    @Secured('ROLE_ADMIN')
    def activateLoan(){
        loanService.activateLoan(params.id)
        List<Loan> loans = loanService.getAll()
        render view: 'allLoans', model: [loanList: loans]
    }
}
