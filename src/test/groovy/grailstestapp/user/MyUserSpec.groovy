package grailstestapp.user

import grails.testing.gorm.DomainUnitTest
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
