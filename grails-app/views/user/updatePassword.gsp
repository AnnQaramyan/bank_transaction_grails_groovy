<%--
  Created by IntelliJ IDEA.
  User: boyaj
  Date: 12/21/2021
  Time: 8:28 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Update Password</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>

<body>
<g:form name="updatePassForm" class="onboarding-form" id="updatePassForm" url="[controller: 'user', action: 'updateUserPassword']"
        method="POST" autocomplete="off">
<div class="container col-md-6">
    <h2>Update Password</h2>
    <div class="form-group">
        <label for="oldPassword">Old Password</label><br>
        <input type="password" class="form-control" placeholder="Enter Your Old Password" name="oldPassword" value="${passwordRequestModel.oldPassword}"><br>
    </div>
    <div class="form-group">
        <label for="newPassword">New Password</label><br>
        <input type="password" class="form-control ${hasErrors(bean: passwordRequestModel, field: 'newPassword', 'error')}" placeholder="Enter Your New Password" name="newPassword" value="${passwordRequestModel.newPassword}"><br>
        <bs:fieldErrors bean="${passwordRequestModel}" field="newPassword"/>
    </div>
    <div class="form-group">
        <label for="confirmation">Confirmation</label><br>
        <input type="password" class="form-control ${hasErrors(bean: passwordRequestModel, field: 'confirmation', 'error')}" placeholder="Confirm Your New Password" name="confirmation" value="${passwordRequestModel.confirmation}"><br>
        <bs:fieldErrors bean="${passwordRequestModel}" field="confirmation"/>
    </div>
    <button class="btn btn-primary" type="submit">Update</button>
    <g:link controller="home" action="home"><input type="button" value="Cancel" class="btn btn-danger"></g:link>
    <br><br>
    <div class="alert alert-danger" role="alert" style="display: none;" id='myAlert'>

    </div>
</div>
</g:form>
</body>
</html>