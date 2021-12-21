package grailstestapp.dto.user

import grailstestapp.dto.address.AddressUserModel

class UserResponseModel {
    String firstName;

    String lastName;

    String email;

    Date birthDate;

    String mobile;

    Set<String> authorities;

    AddressUserModel addressUserModel;
}
