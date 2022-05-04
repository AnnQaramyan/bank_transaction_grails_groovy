package grailstestapp

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import grailstestapp.dto.account.AccountUserResponseModel
import grailstestapp.dto.loan.LoanUserRequestModel
import grailstestapp.dto.loan.LoanUserResponseModel
import grailstestapp.dto.transaction.TransactionUserRequestModel
import grailstestapp.dto.user.UserAdminModel

class LoanController {

    SpringSecurityService springSecurityService
    def loanService
    def accountService
    def userService

    @Secured('ROLE_USER')
    def userLoans(){
        User currentUser = springSecurityService.currentUser as User
        List<LoanUserResponseModel> loans = loanService.getAllByUserId(currentUser.id,5,0)
        render view: 'userLoans', model: [loanList: loans,  current: currentUser]
    }
    @Secured('ROLE_USER')
    def loanCreation(){
        User currentUser = springSecurityService.currentUser as User
        List<AccountUserResponseModel> validAccounts =  accountService.getValidByUserId(currentUser.id)
        render view: 'createLoan', model: [validAccountList:validAccounts]
    }

    @Secured('ROLE_USER')
    def add(LoanUserRequestModel loanUserRequestModel){
        User currentUser = springSecurityService.currentUser as User
        Account account = accountService.getByNumber(loanUserRequestModel.accountUserRequestModel.number)
        def addedLoan = loanService.add(loanUserRequestModel, currentUser,account)
        List<LoanUserResponseModel> loans = loanService.getAllByUserId(currentUser.id,5,0)
        render view: 'userLoans', model: [loanList: loans, current: currentUser]
    }
    def index() { }

    @Secured('ROLE_USER')
    def loanDetails(){
        Loan loan  = Loan.findById(params.loanId)
        render view: 'loanDetails', model: [loan : loan]
    }

}
