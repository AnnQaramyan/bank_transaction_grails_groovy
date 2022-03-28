package grailstestapp

class Loan {
    Double amount
    Account account
    User user
    String description
    Mortgage mortgage
    Map<User, Double> investors
    Status status
    boolean isActive
    Date dueDate
    Date dateAccepted
    Date dateCreated

    static constraints = {
        amount nullable: true
        account nullable: true
        description nullable: true
        mortgage nullable: true
        status nullable: true
        isActive nullable: true
        dateAccepted nullable: true
        dueDate nullable: true
        dateCreated nullable: true
        investors nullable: true
    }
}
