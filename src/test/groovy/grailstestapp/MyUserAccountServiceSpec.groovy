package grailstestapp

import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class MyUserAccountServiceSpec extends Specification implements ServiceUnitTest<AccountService>{

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }
}
