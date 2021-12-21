package grailstestapp

import grails.compiler.GrailsCompileStatic
import grails.validation.Validateable
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@GrailsCompileStatic
@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class User implements Serializable, Validateable {

    private static final long serialVersionUID = 1

    String firstName
    String lastName
    String username
    String password
    Date birthDate
    String mobile
    Address address
    Date dateCreated
    Date lastUpdated

//    static hasMany = [roles: Role]
    boolean isActive
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    Set<Role> getAuthorities() {
        (UserRole.findAllByUser(this) as List<UserRole>)*.role as Set<Role>
    }


    static constraints = {
        firstName blank: false, nullable: false
        lastName blank: false, nullable: false
        username blank: false, nullable: false, unique: true
        password blank: false, nullable: false
        isActive blank: false, nullable: false
    }

    static mapping = {
        password column: '`password`'
    }

    String getFullName() {
        return this.firstName + " " + this. lastName
    }

//    Boolean getIsDelivery() {
//        return this?.authorities?.contains(Role.findByAuthority("ROLE_DELIVERY"))
//    }
//
//    Boolean getIsTherapist() {
//        return this?.authorities?.contains(Role.findByAuthority("ROLE_THERAPIST"))
//    }
//
//    Boolean getIsRegistration() {
//        return this?.authorities?.contains(Role.findByAuthority("ROLE_REGISTRATION"))
//    }
//
//
//    Boolean getIsBox() {
//        return this?.authorities?.contains(Role.findByAuthority("ROLE_BOX"))
//    }
//
//
//    Boolean getIsSerology() {
//        return this?.authorities?.contains(Role.findByAuthority("ROLE_SEROLOGY"))
//    }
//
//    Boolean getIsIsoserology() {
//        return this?.authorities?.contains(Role.findByAuthority("ROLE_ISOSEROLOGY"))
//    }
//
//    Boolean getIsPcr() {
//        return this?.authorities?.contains(Role.findByAuthority("ROLE_PCR"))
//    }
    Boolean getIsAdmin() {
        return this?.authorities?.contains(Role.findByAuthority("ROLE_ADMIN"))
    }
    Boolean getIsUser(){
        return this?.authorities?.contains(Role.findByAuthority("ROLE_USER"))
    }

    String getAuthority() {
        return UserRole.findByUser(this)?.role?.authority
    }
}
