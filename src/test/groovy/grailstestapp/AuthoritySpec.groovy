package grailstestapp

import grails.testing.gorm.DomainUnitTest
import grailstestapp.authority.Authority
import spock.lang.Specification

class AuthoritySpec extends Specification implements DomainUnitTest<Authority> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }
}
