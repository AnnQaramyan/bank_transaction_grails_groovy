package grailstestapp

import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.SpringSecurityService
import grailstestapp.converter.UserConverter
import grailstestapp.dto.account.AccountUserResponseModel
import grailstestapp.dto.address.AddressUserModel
import grailstestapp.dto.user.UserAdminModel
import grailstestapp.dto.user.UserRequestModel
import grailstestapp.dto.user.UserResponseModel
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

import java.text.SimpleDateFormat


@Transactional
class UserService {
    SpringSecurityService springSecurityService
    def add(Object params){
        UserRequestModel requestModel = new UserRequestModel()
        requestModel.setFirstName(params.firstName)
        requestModel.setLastName(params.lastName)
        requestModel.setEmail(params.email)
        requestModel.setPassword(params.password)
        def pattern = "yyyy-mm-dd"
        def birthDate = new SimpleDateFormat(pattern).parse(params.birthDate)
        requestModel.setBirthDate(birthDate)
        requestModel.setMobile(params.mobile)
        AddressUserModel addressUserModel = new AddressUserModel()
        addressUserModel.setCountry(params.country)
        addressUserModel.setCity(params.city)
        addressUserModel.setStreet(params.street)
        Integer houseNumber = Integer.valueOf(params.houseNumber)
        addressUserModel.setHouseNumber(houseNumber)
        addressUserModel.setPostalCode(params.postalCode)
        requestModel.setAddressUserModel(addressUserModel)

        User adding = UserConverter.requestToUser(requestModel);
        Date now = new Date();
        adding.setDateCreated(now);
        adding.setLastUpdated(now);
        adding.getAddress().setDateCreated(now);
        adding.getAddress().setLastUpdated(now);
        adding.setIsActive(true);
        User added = adding.save();
        UserRole.create(adding,Role.findByAuthority('ROLE_ADMIN'),true)
        return UserConverter.userToResponse(added);
    }
    def update(Object params){
        User currentUser = springSecurityService.currentUser as User
        User updating = User.findById(currentUser.id);

        Date now = new Date();
        updating.setFirstName(params.firstName);
        updating.setLastName(params.lastName);
        updating.setUsername(params.email);
        def pattern = "yyyy-mm-dd"
        def birthDate = new SimpleDateFormat(pattern).parse(params.birthDate)
        updating.setBirthDate(birthDate);
        updating.setMobile(params.mobile);
        updating.getAddress().setCountry(params.country);
        updating.getAddress().setCity(params.city);
        updating.getAddress().setStreet(params.street);
        Integer houseNumber = Integer.valueOf(params.houseNumber)
        updating.getAddress().setHouseNumber(houseNumber);
        updating.getAddress().setPostalCode(params.postalCode);
        updating.setLastUpdated(now);
        User updated = updating.save();
        return UserConverter.userToResponse(updated);

    }
    def updatePassword(Object params){
        User currentUser = springSecurityService.currentUser as User
        User byId = User.findById(currentUser.id)
        if(!springSecurityService?.passwordEncoder?.matches(params.oldPass ,
                currentUser.password )){
            throw new RuntimeException("Wrong old password");
        }
//        if(!PasswordValidation.isValid(params.newPass)){
//            throw new APIRequestException("The Password must contain Minimum eight characters, at least one uppercase letter, one lowercase letter and one number.");
//        }
        if(params.newPass != params.confirmPass){
            throw new RuntimeException("Confirm the password");
        }
        byId.setPassword(params.newPass);
        User save = byId.save();
        return UserConverter.userToResponse(save);
    }
    List<UserAdminModel> getAll(){
        List<UserAdminModel> users = UserConverter.usersToAdminModels(User.findAll())
        return users
    }
    UserAdminModel deactivate(Long id) {
        User byId = User.findById(id);
        byId.setIsActive(false);
        byId.setLastUpdated(new Date());
        byId.setEnabled(false)
        return UserConverter.userToAdminModel(byId.save());
    }

    UserAdminModel activate(Long id) {
        User byId = User.findById(id);
        byId.setIsActive(true);
        byId.setLastUpdated(new Date());
        byId.setEnabled(true)
        return UserConverter.userToAdminModel(byId.save());
    }

}
