package grailstestapp.dto.user

import grails.validation.Validateable

class UserFilters implements Validateable
{
    String username
    String role
    String firstName
    String lastName
    Date createdAfter
    Date notBefore
}
