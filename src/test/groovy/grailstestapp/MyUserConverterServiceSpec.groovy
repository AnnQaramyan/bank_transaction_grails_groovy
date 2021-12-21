package grailstestapp

import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class MyUserConverterServiceSpec extends Specification implements ServiceUnitTest<UserConverterService>{

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }
}
