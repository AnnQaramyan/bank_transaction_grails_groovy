package grailstestapp.dto.transaction

import grailstestapp.Status
import grailstestapp.TransactionType
import grailstestapp.dto.account.AccountUserResponseModel;


class TransactionUserResponseModel {
    Long id;
    TransactionType type;
    Double amount;
    AccountUserResponseModel from;
    AccountUserResponseModel to;
    Date dateCreated;
    Date lastUpdated;
    Status status;
    boolean isActive;



    @Override
    String toString() {
        return "TransactionUserResponseModel{" +
                "id=" + id +
                ", type=" + type +
                ", amount=" + amount +
                ", from=" + from +
                ", to=" + to +
                ", dateCreated=" + dateCreated +
                ", lastUpdated=" + lastUpdated +
                ", status=" + status +
                ", isActive=" + isActive +
                '}';
    }
}
