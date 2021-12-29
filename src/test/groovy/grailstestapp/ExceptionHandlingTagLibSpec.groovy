package grailstestapp

import grails.testing.web.taglib.TagLibUnitTest
import spock.lang.Specification

class ExceptionHandlingTagLibSpec extends Specification implements TagLibUnitTest<ExceptionHandlingTagLib> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }
}
