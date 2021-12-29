package grailstestapp.dto.transaction

import grails.validation.Validateable
import grailstestapp.TransactionType;


class TransactionUserRequestModel implements Validateable{
    TransactionType type;
    Double amount;
    String from;
    String to;

    static constraints = {
        amount(nullable: true, validator: { val, TransactionUserRequestModel obj ->
            if (val<0) {
                return "validation.amount.negative"
            } else
                return null;
        })
        to(nullable: true)
    }
}
