package grailstestapp

import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class MyUserControllerSpec extends Specification implements ControllerUnitTest<UserController> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }
}
