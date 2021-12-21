package grailstestapp.converter

import grailstestapp.User
import grailstestapp.dto.user.UserAdminModel
import grailstestapp.dto.user.UserRequestModel
import grailstestapp.dto.user.UserResponseModel

import java.util.stream.Collectors

class UserConverter {
    static UserResponseModel userToResponse(User user){
        UserResponseModel response = new UserResponseModel();
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getUsername());
        response.setBirthDate(user.getBirthDate());
        response.setMobile(user.getMobile());
        response.setAuthorities(user.getAuthorities().stream().map(authority -> authority.getAuthority()).collect(Collectors.toSet()));
        response.setAddressUserModel(AddressConverter.addressToUserModel(user.getAddress()));
        return response;
    }
    static List<UserResponseModel> usersToResponses(List<User> users){
        List<UserResponseModel> responses = new ArrayList<>();
        for (User user:users){
            responses.add(userToResponse(user));
        }
        return responses;
    }
    static User requestToUser(UserRequestModel request){
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUsername(request.getEmail());
        user.setPassword(request.getPassword());
        user.setBirthDate(request.getBirthDate());
        user.setMobile(request.getMobile());
        user.setAddress(AddressConverter.requestToAddress(request.getAddressUserModel()));
        return user;
    }
    static UserAdminModel userToAdminModel(User user){
        UserAdminModel userAdminModel = new UserAdminModel();
        userAdminModel.setId(user.getId());
        userAdminModel.setFirstName(user.getFirstName());
        userAdminModel.setLastName(user.getLastName());
        userAdminModel.setEmail(user.getUsername());
        userAdminModel.setPassword(user.getPassword());
        userAdminModel.setBirthDate(user.getBirthDate());
        userAdminModel.setMobile(user.getMobile());
        userAdminModel.setAddressAdminModel(AddressConverter.addressToAdminModel(user.getAddress()));
        userAdminModel.setDateCreated(user.getDateCreated());
        userAdminModel.setLastUpdated(user.getLastUpdated());
        Set<String> authorityNames = user.getAuthorities().stream().map(authority -> authority.getAuthority()).collect(Collectors.toSet());
        userAdminModel.setAuthorities(authorityNames);
        userAdminModel.setIsActive(user.isActive);
        return userAdminModel;
    }
    static List<UserAdminModel> usersToAdminModels(List<User> users){
        List<UserAdminModel> adminModels = new ArrayList<>();
        for(User user:users){
            adminModels.add(userToAdminModel(user));
        }
        return adminModels;
    }
}
