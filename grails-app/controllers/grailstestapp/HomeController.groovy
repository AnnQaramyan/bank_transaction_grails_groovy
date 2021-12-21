package grailstestapp

import grails.plugin.springsecurity.SpringSecurityService
import org.springframework.security.access.annotation.Secured

@Secured('permitAll')
class HomeController {
    SpringSecurityService springSecurityService;
    def index() {
        User currentUser = springSecurityService.currentUser as User
        render "<h1>${currentUser.firstName}</h1>"
    }
    def home(){
        User currentUser = springSecurityService.currentUser as User
        render view:'home', model:[current:currentUser]
    }
}
