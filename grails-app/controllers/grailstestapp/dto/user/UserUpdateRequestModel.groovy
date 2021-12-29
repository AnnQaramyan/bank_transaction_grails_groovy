package grailstestapp.dto.user

import grails.validation.Validateable
import grailstestapp.dto.address.AddressUserModel

class UserUpdateRequestModel implements Validateable{
    String firstName;

    String lastName;

    String email;

    Date birthDate;
    String mobile;

    AddressUserModel addressUserModel;
    static constraints = {
        firstName(nullable: true, validator: { val, UserUpdateRequestModel obj ->
            if (val.length()<3) {
                return "validation.name.short"
            } else
                return null;
        })
        lastName(nullable: true, validator: { val, UserUpdateRequestModel obj ->
            if (val.length()<3) {
                return "validation.lastname.short"
            } else
                return null;
        })
    }
}
