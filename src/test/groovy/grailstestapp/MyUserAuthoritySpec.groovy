package grailstestapp

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class MyUserAuthoritySpec extends Specification implements DomainUnitTest<UserAuthority> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }
}
