<%--
  Created by IntelliJ IDEA.
  User: boyaj
  Date: 12/21/2021
  Time: 7:51 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="layout" content="main"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Update Personal Info</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>

<body>
<g:form name="updateForm" class="onboarding-form" id="updateForm" url="[controller: 'user', action: 'updateUser']"
        method="PUT" autocomplete="off">
    <div class="container col-md-6">
        <h2>Update Your Profile</h2>
        <div class="form-group">
            <label for="firstName">First Name</label><br>
            <input type="text" class="form-control ${hasErrors(bean: userUpdateRequestModel, field: 'firstName', 'error')}" placeholder="Enter First Name" name="firstName" value="${userUpdateRequestModel.firstName}"><br>
            <bs:fieldErrors bean="${userUpdateRequestModel}" field="firstName"/>
        </div>
        <div class="form-group">
            <label for="lastName">Last Name</label><br>
            <input type="text" class="form-control ${hasErrors(bean: userUpdateRequestModel, field: 'lastName', 'error')}" placeholder="Enter Last Name" name="lastName" value="${userUpdateRequestModel.lastName}"><br>
            <bs:fieldErrors bean="${userUpdateRequestModel}" field="lastName"/>
        </div>
        <div class="form-group">
            <label for="email">Email</label><br>
            <input type="email" class="form-control" placeholder="Enter Email" name="email" value="${userUpdateRequestModel.email}"><br>
        </div>
        <div class="form-group">
            <label for="birthDate">Birth Date</label><br>
            <input type="date" class="form-control" placeholder="Enter Birth Date" name="birthDate" value="<g:formatDate format="yyyy-MM-dd" date="${userUpdateRequestModel.birthDate}"/>"><br>
        </div>
        <div class="form-group">
            <label for="mobile">Mobile</label><br>
            <input type="text" class="form-control" placeholder="Enter Mobile" name="mobile" value="${userUpdateRequestModel.mobile}"><br>
        </div>
        <div class="form-group">
            <label for="addressUserModel.country">Country</label><br>
            <input type="text" class="form-control" placeholder="Enter Country" name="addressUserModel.country" value="${userUpdateRequestModel.addressUserModel.country}"><br>
        </div>
        <div class="form-group">
            <label for="addressUserModel.city">City</label><br>
            <input type="text" class="form-control" placeholder="Enter City" name="addressUserModel.city" value="${userUpdateRequestModel.addressUserModel.city}"><br>
        </div>
        <div class="form-group">
            <label for="addressUserModel.street">Street</label><br>
            <input type="text" class="form-control" placeholder="Enter Street" name="addressUserModel.street" value="${userUpdateRequestModel.addressUserModel.street}"><br>
        </div>
        <div class="form-group">
            <label for="addressUserModel.houseNumber">House Number</label><br>
            <input type="text" class="form-control" placeholder="Enter House Number" name="addressUserModel.houseNumber" value="${userUpdateRequestModel.addressUserModel.houseNumber}"><br>
        </div>
        <div class="form-group">
            <label for="addressUserModel.postalCode">Postal Code</label><br>
            <input type="text" class="form-control" placeholder="Enter Postal Code" name="addressUserModel.postalCode" value="${userUpdateRequestModel.addressUserModel.postalCode}"><br>
        </div>
        <button class="btn btn-primary" type="submit">Update</button>
        <g:link controller="home" action="home"><input type="button" value="Cancel" class="btn btn-danger"></g:link>
    </div>
</g:form>
</body>
</html>