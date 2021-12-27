package grailstestapp

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured

@Secured("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
class HomeController {
    SpringSecurityService springSecurityService

    def home(){
        User currentUser = springSecurityService.currentUser as User
        render view:'home', model:[current:currentUser]
    }
}
