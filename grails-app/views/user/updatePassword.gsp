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
        <label for="oldPass">Old Password</label><br>
        <input type="password" class="form-control" placeholder="Enter Your Old Password" name="oldPass"><br>
    </div>
    <div class="form-group">
        <label for="newPass">New Password</label><br>
        <input type="password" class="form-control" placeholder="Enter Your New Password" name="newPass"><br>
    </div>
    <div class="form-group">
        <label for="confirmPass">Confirmation</label><br>
        <input type="password" class="form-control" placeholder="Confirm Your New Password" name="confirmPass"><br>
    </div>
%{--    <input type="button" value="Update" class="btn btn-primary" onclick="updatePassword()">--}%
    <button class="btn btn-primary" type="submit">Update</button>
    <input type="button" value="Cancel" class="btn btn-danger" onclick="location.href='../home/home'">
    <br><br>
    <div class="alert alert-danger" role="alert" style="display: none;" id='myAlert'>

    </div>
</div>
</g:form>
</body>
</html>