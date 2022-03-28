package grailstestapp

class Mortgage {
    CollateralType collateralType
    String description
    Double estimatedPrice
    Boolean valid

    static constraints = {
        estimatedPrice nullable: true
    }

    Mortgage() {
        this.valid = false
    }
}
