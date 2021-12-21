package grailstestapp

import grails.testing.gorm.DomainUnitTest
import grailstestapp.user.Address
import spock.lang.Specification

class AddressSpec extends Specification implements DomainUnitTest<Address> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }
}
