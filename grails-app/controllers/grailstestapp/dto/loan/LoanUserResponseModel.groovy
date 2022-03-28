package grailstestapp.dto.loan

import grailstestapp.Account
import grailstestapp.Currency
import grailstestapp.Mortgage
import grailstestapp.Status
import grailstestapp.User

class LoanUserResponseModel {
    Long id
    Double amount
    Account account
    String description
    Mortgage mortgage
    Status status
    boolean isActive
    Date dueDate
    Date dateAccepted
}
