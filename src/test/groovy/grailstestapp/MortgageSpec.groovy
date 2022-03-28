package grailstestapp

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class MortgageSpec extends Specification implements DomainUnitTest<Mortgage> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }
}
