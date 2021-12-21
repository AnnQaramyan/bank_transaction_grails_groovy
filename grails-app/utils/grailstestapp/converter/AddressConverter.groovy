package grailstestapp.converter

import grailstestapp.Address
import grailstestapp.dto.address.AddressAdminModel
import grailstestapp.dto.address.AddressUserModel

class AddressConverter {
    static AddressUserModel addressToUserModel(grailstestapp.Address address){
        AddressUserModel addressUserModel = new AddressUserModel();
        addressUserModel.setCountry(address.getCountry());
        addressUserModel.setCity(address.getCity());
        addressUserModel.setStreet(address.getStreet());
        addressUserModel.setHouseNumber(address.getHouseNumber());
        addressUserModel.setPostalCode(address.getPostalCode());
        return addressUserModel;
    }
    static Address requestToAddress(AddressUserModel request){
        Address address = new Address();
        address.setCountry(request.getCountry());
        address.setCity(request.getCity());
        address.setStreet(request.getStreet());
        address.setHouseNumber(request.getHouseNumber());
        address.setPostalCode(request.getPostalCode());
        return address;
    }
    static AddressAdminModel addressToAdminModel(Address address) {
        AddressAdminModel addressAdminModel = new AddressAdminModel();
        addressAdminModel.setId(address.getId());
        addressAdminModel.setCountry(address.getCountry());
        addressAdminModel.setCity(address.getCity());
        addressAdminModel.setStreet(address.getStreet());
        addressAdminModel.setHouseNumber(address.getHouseNumber());
        addressAdminModel.setPostalCode(address.getPostalCode());
        addressAdminModel.setDateCreated(address.getDateCreated());
        addressAdminModel.setLastUpdated(address.getLastUpdated());
        return addressAdminModel;
    }
}
