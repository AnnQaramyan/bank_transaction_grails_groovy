package grailstestapp

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import grailstestapp.dto.user.UserRequestModel
import grailstestapp.dto.user.UserResponseModel
import org.springframework.web.bind.annotation.RequestBody

@Secured('permitAll')
class UserController {
    def userService
    SpringSecurityService springSecurityService
    def add(){
        def addedUser = userService.add(params)
        if(addedUser!=null){
            redirect(controller: "login", action: "auth")
        }
    }
    def register(){
        if((springSecurityService.currentUser as User)!=null)
            redirect(controller: "home", action: "home")
    }
    @Secured('ROLE_USER')
    def updateUser(){
        User currentUser = springSecurityService.currentUser as User
        def updatedUser = userService.update(params)
        render view:'../home/home', model: [current: currentUser]
    }
    @Secured('ROLE_USER')
    def updateUserPassword(){
        User currentUser = springSecurityService.currentUser as User
        def updatedUserPassword = userService.updatePassword(params)
        render view:'../home/home', model: [current: currentUser]
    }
    @Secured('ROLE_USER')
    def update(){
        User currentUser = springSecurityService.currentUser as User
        render view: 'update', model: [current:currentUser]
    }
    @Secured('ROLE_USER')
    def updatePassword(){

    }
}