package grailstestapp.dto.user

import grailstestapp.dto.address.AddressUserModel

class UserRequestModel {
    String firstName;

    String lastName;

    String email;

    String password;

    Date birthDate;
    String mobile;

    AddressUserModel addressUserModel;
}
