package grailstestapp

import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class MyUserServiceSpec extends Specification implements ServiceUnitTest<UserService>{

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }
}
