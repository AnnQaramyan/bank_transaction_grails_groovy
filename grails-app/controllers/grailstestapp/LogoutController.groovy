package grailstestapp

import grails.plugin.springsecurity.SpringSecurityUtils
import grails.plugin.springsecurity.annotation.Secured

@Secured("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
class LogoutController {
    static defaultAction = "logout"
    def logout() {
        redirect uri: SpringSecurityUtils.securityConfig.logout.filterProcessesUrl
    }
}
