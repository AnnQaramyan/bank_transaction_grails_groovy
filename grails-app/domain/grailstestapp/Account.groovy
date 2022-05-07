package grailstestapp

import com.sun.istack.NotNull

import javax.persistence.ManyToOne

class Account {
    String number;
    Currency currency;
    Double permittedInvestmentAmount
    Date dateCreated;
    Date lastUpdated;
    Status status;
    User user;
    boolean isActive;


    static constraints = {
        number blank:false, nullable:false, unique:true
        currency blank:false, nullable:false
        status blank:false, nullable:false
        user blank:false, nullable:false
        isActive blank: false, nullable: false
    }

    Account() {
        this.permittedInvestmentAmount = 0
    }
}
