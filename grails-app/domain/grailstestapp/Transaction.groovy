package grailstestapp

import javax.persistence.ManyToOne

class Transaction {

    TransactionType type;
    Double amount;
    Double toAmount;
    Account from;
    Account to;
    Date dateCreated;
    Date lastUpdated;
    Status status;

    boolean isActive;
    static constraints = {
        type blank:false, nullable: false
        amount blank:false, nullable: false
        toAmount nullable: true
        from blank:false, nullable: false
        to blank:false, nullable: false
        status blank:false, nullable: false
        isActive blank:false, nullable: false
    }
}
