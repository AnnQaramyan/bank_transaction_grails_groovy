<%--
  Created by IntelliJ IDEA.
  User: boyaj
  Date: 12/20/2021
  Time: 10:25 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Registration</title>
    <link rel="icon" type="image/x-icon" href="/assets/logo.png" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <style>
    .Aligner {
        display: flex;
        align-items: center;
        justify-content: center;
        height: 100vh;
    }

    .Aligner-item {
        width: 100%;
        max-width: 768px;
    }

    </style>
</head>

<body>
<div class="Aligner" style="background-image: url('/assets/login_background.png'); background-size: cover; color: whitesmoke">
    <div class="Aligner-item">
    <g:form name="registrationForm" class="onboarding-form" id="registrationForm" url="[controller: 'user', action: 'add']"
            method="POST" autocomplete="off">
        <h2 class="text-center">Register</h2>
        <div class="container-fluid">
            <div class="row">
            <div class="form-group col-md-4">
                <label for="firstName">First Name</label><br>
                <input type="text" class="form-control ${hasErrors(bean: userRequestModel, field: 'firstName', 'error')}" placeholder="Enter First Name" name="firstName"><br>
                <bs:fieldErrors bean="${userRequestModel}" field="firstName"/>
            </div>
            <div class="form-group col-md-4">
                <label for="lastName">Last Name</label><br>
                <input type="text" class="form-control ${hasErrors(bean: userRequestModel, field: 'lastName', 'error')}" placeholder="Enter Last Name" name="lastName"><br>
                <bs:fieldErrors bean="${userRequestModel}" field="lastName"/>
            </div>
            <div class="form-group col-md-4">
                <label for="email">Email</label><br>
                <input type="email" class="form-control" placeholder="Enter Email" name="email"><br>
            </div>
            <div class="form-group col-md-4">
                <label for="password">Password</label><br>
                <input type="password" class="form-control ${hasErrors(bean: userRequestModel, field: 'password', 'error')}" placeholder="Enter Password" name="password"><br>
                <bs:fieldErrors bean="${userRequestModel}" field="password"/>
            </div>
            <div class="form-group col-md-4">
                <label for="birthDate">Birth Date</label><br>
                <input type="date" class="form-control" placeholder="Enter Birth Date" name="birthDate"><br>
            </div>
            <div class="form-group col-md-4">
                <label for="mobile">Mobile</label><br>
                <input type="text" class="form-control" placeholder="Enter Mobile" name="mobile"><br>
            </div>
            <div class="form-group col-md-4">
                <label for="addressUserModel.country">Country</label><br>
                <input type="text" class="form-control" placeholder="Enter Country" name="addressUserModel.country"><br>
            </div>
            <div class="form-group col-md-4">
                <label for="addressUserModel.city">City</label><br>
                <input type="text" class="form-control" placeholder="Enter City" name="addressUserModel.city"><br>
            </div>
            <div class="form-group col-md-4">
                <label for="addressUserModel.street">Street</label><br>
                <input type="text" class="form-control" placeholder="Enter Street" name="addressUserModel.street"><br>
            </div>
            <div class="form-group col-md-4">
                <label for="addressUserModel.houseNumber">House Number</label><br>
                <input type="text" class="form-control" placeholder="Enter House Number" name="addressUserModel.houseNumber"><br>
            </div>
            <div class="form-group col-md-4">
            </div>
            <div class="form-group col-md-4">
                <label for="addressUserModel.postalCode">Postal Code</label><br>
                <input type="text" class="form-control" placeholder="Enter Postal Code" name="addressUserModel.postalCode"><br>
            </div>
            </div>
            <div class="row">
            <div class="form-group col-md-4">
            </div>
            <div class="form-group col-md-4" style="text-align: center">
                <button type="submit" class="btn btn-success" style="width: 100%">Register</button>
            </div>
            <div class="form-group col-md-4">
            </div>
            </div>
            <br>
            <small>Already have an account? <g:link controller="login" action="auth">Log In</g:link></small>
            <div class="alert alert-danger" role="alert" style="display: none;" id='myAlert'>

            </div>
        </div>
    </g:form>
    </div>
</div>
</body>
</html>