package grailstestapp

import com.sun.istack.Nullable

class Rate {
    Currency currency;
    Double amount;
    Date start_date;
    Date end_date;
    static constraints = {
        end_date blank:true, nullable: true
    }
}
