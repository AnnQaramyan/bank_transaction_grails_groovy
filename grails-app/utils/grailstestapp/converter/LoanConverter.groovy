package grailstestapp.converter


import grailstestapp.Loan
import grailstestapp.Mortgage
import grailstestapp.dto.loan.LoanUserRequestModel
import grailstestapp.dto.loan.LoanUserResponseModel

class LoanConverter {
    static List<LoanUserResponseModel> loansToResponses(List<Loan> loans) {
        List<LoanUserResponseModel> responses = new ArrayList<>();
        for (Loan loan : loans) {
            responses.add(loanToResponse(loan));
        }
        return responses;
    }

    static LoanUserResponseModel loanToResponse(Loan loan){
        LoanUserResponseModel loanUserResponseModel = new LoanUserResponseModel()
        loanUserResponseModel.id = loan.id
        loanUserResponseModel.amount = loan.amount
        loanUserResponseModel.account = loan.account
        loanUserResponseModel.mortgage = loan.mortgage
        loanUserResponseModel.description = loan.description
        loanUserResponseModel.dueDate = loan.dueDate
        loanUserResponseModel.dateAccepted = loan.dateAccepted
        loanUserResponseModel.status = loan.status
        loanUserResponseModel.isActive = loan.isActive
        return loanUserResponseModel
    }

    static Loan loanUserRequestModelToLoan(LoanUserRequestModel loanUserRequestModel){
        Loan loan = new Loan();
        loan.amount = loanUserRequestModel.amount
        loan.mortgage = new Mortgage()
        loan.mortgage.description = loanUserRequestModel.mortgageUserRequestModel.description
        loan.mortgage.collateralType = loanUserRequestModel.mortgageUserRequestModel.collateralType
        loan.description = loanUserRequestModel.description

        return  loan
    }
}
