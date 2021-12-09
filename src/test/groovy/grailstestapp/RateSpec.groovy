package grailstestapp

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class RateSpec extends Specification implements DomainUnitTest<Rate> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }
}
