package grailstestapp

import grails.gorm.transactions.Transactional
import grailstestapp.converter.AccountConverter
import grailstestapp.converter.LoanConverter
import grailstestapp.dto.loan.LoanUserRequestModel
import grailstestapp.dto.loan.LoanUserResponseModel
import grailstestapp.dto.mortgage.MortgageUserRequestModel

@Transactional
class LoanService {

   List<LoanUserResponseModel> getAllByUserId(Long id, Integer max, Integer pageNumber){
       User byId = User.findById(id)
       List<Loan> allByUserId = Loan.findAllByUser(byId,[max: max,offset: pageNumber*5])
       return LoanConverter.loansToResponses(allByUserId);
   }

    def add(LoanUserRequestModel loanUserRequestModel, User currentUser, Account account){
        Loan loan = new Loan();
        loan =  LoanConverter.loanUserRequestModelToLoan(loanUserRequestModel)
        loan.status = Status.IN_REVIEW
        loan.dateCreated = new Date()
        //TODO corrected
        loan.dueDate = new Date()
        loan.user = currentUser
        loan.account = account
       return  (loan.save(flush : true))
    }

    def getAll(){
        List<Loan> loans = Loan.findAll()
        return loans
    }

    def serviceMethod() {

    }

    def update(def params){
        Loan loan  = Loan.findById(params.loan.id)
        loan.mortgage.estimatedPrice = Double.valueOf(params.loan.mortgage.estimatedPrice)

        loan.mortgage.valid = params.loan.mortgage.valid == "on"
        if (params.loan.mortgage.valid == "on") {
            loan.mortgage.valid = true
            loan.status = Status.ACCEPTED
        }else {
            loan.mortgage.valid = false
            loan.status = Status.PENDING
        }

        Double sum = 0
        params.loan.investors.each {
            Account cur_investor = Account.findByNumber(it)
            Double curr_perc = Math.random()
            if (cur_investor.permittedInvestmentAmount / loan.amount < 0.001)
                curr_perc = 1.0
            loan.investors.put(it, curr_perc)
            sum += cur_investor.permittedInvestmentAmount * curr_perc
        }
        while (sum < loan.amount){
            sum = 0
            loan.investors.each {
                Account cur_investor = Account.findByNumber(it.key)
                it.value = (it.value + Math.random() / 10) % 1
                sum += cur_investor.permittedInvestmentAmount * it.value
            }
        }
        Double leftover = sum - loan.amount
        if (leftover > 0) {
            Double part = leftover/sum
            loan.investors.each {
                Account cur_investor = Account.findByNumber(it.key)
                Double investor_amount = it.value * cur_investor.permittedInvestmentAmount
                Double investor_leftover = part * investor_amount
                Double target_amount = investor_amount - investor_leftover
                it.value = target_amount
                cur_investor.permittedInvestmentAmount -= target_amount
            }
        }
        return (loan.save(flush : true))
    }

    def reject(def id){
        Loan loan = Loan.findById(id)
        loan.status = Status.REJECTED
        loan.isActive = false
        return (loan.save(flush : true))
    }

    def accept(def id){
        Loan loan = Loan.findById(id)
        loan.status = Status.ACCEPTED
        return (loan.save(flush : true))
    }

    def
    deactivateLoan(def id){
        Loan loan = Loan.findById(id)
        loan.isActive = false
        return (loan.save(flush : true))
    }
    def activateLoan(def id){
        Loan loan = Loan.findById(id)
        loan.isActive = true
        return (loan.save(flush : true))
    }


    List<Account> getPotentialInvestors(def loanId){
        Loan loan = Loan.findById(loanId)
        List<Account> investors = Account.findAll {permittedInvestmentAmount >= 0}
        return investors
    }
}
