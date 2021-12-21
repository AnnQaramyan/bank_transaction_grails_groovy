package grailstestapp.dto.transaction

import grailstestapp.TransactionType;


class TransactionUserRequestModel {
    TransactionType type;
    Double amount;
    String from;
    String to;
}
