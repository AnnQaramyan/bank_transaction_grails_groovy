package grailstestapp.dto

import grails.validation.Validateable

class PasswordRequestModel implements Validateable{
    String oldPassword
    String newPassword
    String confirmation

    static constraints = {
        newPassword(nullable: true, validator: { val, PasswordRequestModel obj ->
            if (val.length()<8) {
                return "validation.password.short"
            }
            else
                return null
        })
        confirmation(nullable: true, validator: {val,PasswordRequestModel obj->
            if(val!=obj.newPassword){
                return "validation.password.confirm"
            }else{
                return null
            }
        })
    }
}
