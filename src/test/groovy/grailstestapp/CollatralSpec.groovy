package grailstestapp

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class CollatralSpec extends Specification implements DomainUnitTest<Collatral> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }
}
