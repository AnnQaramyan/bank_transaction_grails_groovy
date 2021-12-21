package grailstestapp

import grails.testing.gorm.DomainUnitTest
import grailstestapp.account.UserAccount
import spock.lang.Specification

class MyUserAccountSpec extends Specification implements DomainUnitTest<UserAccount> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }
}
