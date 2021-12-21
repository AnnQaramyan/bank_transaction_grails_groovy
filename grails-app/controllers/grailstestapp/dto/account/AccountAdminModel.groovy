package grailstestapp.dto.account;

import grailstestapp.Currency;
import grailstestapp.Status;
import grailstestapp.dto.user.UserAdminModel;

import java.util.Date;

class AccountAdminModel {
    Long id;
    String number;
    Currency currency;
    Date dateCreated;
    Date lastUpdated;
    Status status;
    UserAdminModel userAdminModel;
    boolean isActive;
}
