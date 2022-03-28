package grailstestapp.dto.loan

import grailstestapp.Currency
import grailstestapp.Mortgage
import grailstestapp.Status
import grailstestapp.dto.account.AccountUserRequestModel
import grailstestapp.dto.account.AccountUserResponseModel
import grailstestapp.dto.mortgage.MortgageUserRequestModel
import org.hibernate.validator.internal.metadata.facets.Cascadable
import org.hibernate.validator.internal.metadata.facets.Validatable

class LoanUserRequestModel implements Validatable {
    Double amount
    AccountUserRequestModel accountUserRequestModel
    String description
    MortgageUserRequestModel mortgageUserRequestModel

    @Override
    Iterable<Cascadable> getCascadables() {
        return null
    }

    @Override
    boolean hasCascadables() {
        return false
    }
}
