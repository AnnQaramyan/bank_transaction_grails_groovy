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
            <input type="text" class="form-control" placeholder="Enter First Name" name="firstName" value="${current.firstName}"><br>
        </div>
        <div class="form-group">
            <label for="lastName">Last Name</label><br>
            <input type="text" class="form-control" placeholder="Enter Last Name" name="lastName" value="${current.lastName}"><br>
        </div>
        <div class="form-group">
            <label for="email">Email</label><br>
            <input type="email" class="form-control" placeholder="Enter Email" name="email" value="${current.username}"><br>
        </div>
        <div class="form-group">
            <label for="birthDate">Birth Date</label><br>
            <input type="date" class="form-control" placeholder="Enter Birth Date" name="birthDate" value="${current.birthDate}"><br>
        </div>
        <div class="form-group">
            <label for="mobile">Mobile</label><br>
            <input type="text" class="form-control" placeholder="Enter Mobile" name="mobile" value="${current.mobile}"><br>
        </div>
        <div class="form-group">
            <label for="country">Country</label><br>
            <input type="text" class="form-control" placeholder="Enter Country" name="country" value="${current.address.country}"><br>
        </div>
        <div class="form-group">
            <label for="city">City</label><br>
            <input type="text" class="form-control" placeholder="Enter City" name="city" value="${current.address.city}"><br>
        </div>
        <div class="form-group">
            <label for="street">Street</label><br>
            <input type="text" class="form-control" placeholder="Enter Street" name="street" value="${current.address.street}"><br>
        </div>
        <div class="form-group">
            <label for="houseNumber">House Number</label><br>
            <input type="text" class="form-control" placeholder="Enter House Number" name="houseNumber" value="${current.address.houseNumber}"><br>
        </div>
        <div class="form-group">
            <label for="postalCode">Postal Code</label><br>
            <input type="text" class="form-control" placeholder="Enter Postal Code" name="postalCode" value="${current.address.postalCode}"><br>
        </div>
%{--        <input type="button" value="Update" class="btn btn-primary" onclick="updateUser()">--}%
        <button class="btn btn-primary" type="submit">Update</button>
        <input type="button" value="Cancel" class="btn btn-danger" onclick="location.href='../home/home'">
    </div>
</g:form>
</body>
</html>