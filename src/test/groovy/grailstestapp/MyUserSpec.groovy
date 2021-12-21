package grailstestapp

import grails.testing.gorm.DomainUnitTest
import grailstestapp.user.MyUser
import spock.lang.Specification

class MyUserSpec extends Specification implements DomainUnitTest<MyUser> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }
}
