package grailstestapp

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class MyUserRoleSpec extends Specification implements DomainUnitTest<UserRole> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }
}
