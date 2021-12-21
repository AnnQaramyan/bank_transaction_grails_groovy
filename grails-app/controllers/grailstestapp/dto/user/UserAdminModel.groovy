package grailstestapp.dto.user

import grailstestapp.dto.address.AddressAdminModel

class UserAdminModel {
    Long id;


    String firstName;


    String lastName;


    String email;


    String password;

    Date birthDate;
    String mobile;
    AddressAdminModel addressAdminModel;
    Date dateCreated;
    Date lastUpdated;
    Set<String> authorities;
    boolean isActive;
}
