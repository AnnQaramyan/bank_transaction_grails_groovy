package grailstestapp

import grails.gorm.PagedResultList
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import grailstestapp.converter.UserConverter
import grailstestapp.dto.PasswordRequestModel
import grailstestapp.dto.address.AddressUserModel
import grailstestapp.dto.user.UserFilters
import grailstestapp.dto.user.UserRequestModel
import grailstestapp.dto.user.UserResponseModel
import grailstestapp.dto.user.UserUpdateRequestModel
import org.springframework.web.bind.annotation.RequestBody

@Secured('permitAll')
class UserController {
    def userService
    SpringSecurityService springSecurityService
    def add(UserRequestModel userRequestModel){

        if (userRequestModel.hasErrors()) {
            println userRequestModel.errors
            render view: 'register', model: [userRequestModel: userRequestModel]
            return
        }
        def addedUser = userService.add(userRequestModel)
        if(addedUser!=null){
            redirect(controller: "login", action: "auth")
        }

    }
    def register(){
        if((springSecurityService.currentUser as User)!=null)
            redirect(controller: "home", action: "home")
        UserRequestModel userRequestModel = new UserRequestModel()
        render view: 'register', model: [userRequestModel:userRequestModel]
    }
    @Secured("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    def updateUser(UserUpdateRequestModel userUpdateRequestModel){
        User currentUser = springSecurityService.currentUser as User
        if (userUpdateRequestModel.hasErrors()) {

            render view: 'update', model: [userUpdateRequestModel: userUpdateRequestModel]
            return
        }

        def updatedUser = userService.update(userUpdateRequestModel)
        render view:'../home/home', model: [current: currentUser]
    }
    @Secured("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    def updateUserPassword(PasswordRequestModel passwordRequestModel){
        User currentUser = springSecurityService.currentUser as User
        if (passwordRequestModel.hasErrors()) {
            render view: 'updatePassword', model: [passwordRequestModel: passwordRequestModel]
            return
        }
        def updatedUserPassword = userService.updatePassword(passwordRequestModel)
        render view:'../home/home', model: [current: currentUser]
    }
    @Secured("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    def update(){
        User currentUser = springSecurityService.currentUser as User
        UserUpdateRequestModel userUpdateRequestModel = new UserUpdateRequestModel()
        userUpdateRequestModel.setFirstName(currentUser.firstName)
        userUpdateRequestModel.setLastName(currentUser.lastName)
        userUpdateRequestModel.setEmail(currentUser.username)
        userUpdateRequestModel.setBirthDate(currentUser.birthDate)
        userUpdateRequestModel.setMobile(currentUser.mobile)
        AddressUserModel addressUserModel = new AddressUserModel()
        addressUserModel.setCountry(currentUser.address.country)
        addressUserModel.setCity(currentUser.address.city)
        addressUserModel.setStreet(currentUser.address.street)
        addressUserModel.setHouseNumber(currentUser.address.houseNumber)
        addressUserModel.setPostalCode(currentUser.address.postalCode)
        userUpdateRequestModel.setAddressUserModel(addressUserModel)
        render view: 'update', model: [userUpdateRequestModel: userUpdateRequestModel]
    }
    @Secured("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    def updatePassword(){
        PasswordRequestModel passwordRequestModel = new PasswordRequestModel()
        render view: 'updatePassword', model: [passwordRequestModel:passwordRequestModel]
    }

    def autocomplete() {
        String usernameStart = params.email
        withFormat {
            json {
                respond(status: 200)
            }
        }
    }

    def getUsersFiltered(UserFilters filters) {
        if(filters == null) {
            session.userListFilters = null
            //TODO
        }
        session.userListFilters = filters
        final PagedResultList<User> users = userService.getUsers(filters)
        withFormat {
            json {
                respond(status: 200, users: users.collect { UserConverter.userToAdminModel(it)}, totalCount: users.totalCount)
            }
        }
    }
}