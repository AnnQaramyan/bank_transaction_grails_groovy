package grailstestapp.dto.account;


import grailstestapp.Currency;
import grailstestapp.Status;

import java.util.Date;

public class AccountUserResponseModel {

    Long id;
    Long permittedInvestmentAmount;
    String number;
    Currency currency;
    Date dateCreated;
    Date lastUpdated;
    Status status;
    boolean isActive;

    
}
