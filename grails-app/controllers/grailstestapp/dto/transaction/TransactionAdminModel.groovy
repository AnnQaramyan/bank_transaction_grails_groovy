package grailstestapp.dto.transaction

import grailstestapp.Status
import grailstestapp.TransactionType
import grailstestapp.dto.account.AccountAdminModel;

import java.util.Date;

class TransactionAdminModel {
    Long id;
    TransactionType type;
    Double amount;
    Double toAmount;
    AccountAdminModel from;
    AccountAdminModel to;
    Date dateCreated;
    Date lastUpdated;
    Status status;
    boolean isActive;


}
