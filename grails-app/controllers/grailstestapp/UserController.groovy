package grailstestapp

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import grailstestapp.dto.user.UserRequestModel
import grailstestapp.dto.user.UserResponseModel
import org.springframework.web.bind.annotation.RequestBody

class UserController {
    def userService
    SpringSecurityService springSecurityService
//    @Secured('permitAll')
//    def add(@RequestBody UserRequestModel requestModel){
//        def addedUser = userService.add(requestModel)
//        render view:'home'
//    }
    @Secured('permitAll')
    def add(){
        User currentUser = springSecurityService.currentUser as User
        def addedUser = userService.add(params)
        render view:'../home/home', model: [current: currentUser]
    }
    @Secured('permitAll')
    def register(){
        if((springSecurityService.currentUser as User)!=null)
            redirect(controller: "home", action: "home")
    }
    @Secured('permitAll')
    def updateUser(){
        User currentUser = springSecurityService.currentUser as User
        def updatedUser = userService.update(params)
        render view:'../home/home', model: [current: currentUser]
    }
    @Secured('permitAll')
    def updateUserPassword(){
        User currentUser = springSecurityService.currentUser as User
        def updatedUserPassword = userService.updatePassword(params)
        render view:'../home/home', model: [current: currentUser]
    }
    @Secured('permitAll')
    def update(){
        User currentUser = springSecurityService.currentUser as User
        render view: 'update', model: [current:currentUser]
    }
    @Secured('permitAll')
    def updatePassword(){

    }
}