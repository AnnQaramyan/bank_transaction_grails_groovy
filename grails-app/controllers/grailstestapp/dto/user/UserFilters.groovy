package grailstestapp.dto.user

import grails.validation.Validateable

import java.text.SimpleDateFormat

class UserFilters implements Validateable {
    String username
    String firstName
    String lastName
    Date createdAfter
    Date lastUpdated
    Boolean isActive

    UserFilters(String value, String field) {
        switch (field) {
            case ("first_name"):
                this.firstName = value
                break
            case ("last_name"):
                this.lastName = value
                break
            case ("email"):
                this.username = value
                break
            case ("date_created"):
                def pattern = "yyyy-MM-dd"
                this.createdAfter = new SimpleDateFormat(pattern).parse(value)
                break
            case ("last_updated"):
                def pattern = "yyyy-MM-dd"
                this.lastUpdated = new SimpleDateFormat(pattern).parse(value)
                break
            case ("act_status"):
                this.isActive = (value.toLowerCase() == "active")
        }
    }
}
