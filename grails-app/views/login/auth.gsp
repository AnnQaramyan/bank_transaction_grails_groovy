<%--
  Created by IntelliJ IDEA.
  User: boyaj
  Date: 12/20/2021
  Time: 6:55 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>

<body>
<g:form name="loginForm" class="onboarding-form" id="loginForm" url="[controller: 'login', action: 'authenticate']"
        method="POST" autocomplete="off">
    <div class="container col-md-6">
        <h2 class="text-center">Please Log In</h2>
        <div class="form-group">
%{--            <label for="email">Email</label><br>--}%
%{--            <input type="email" class="form-control" placeholder="Enter email" name="email"><br>--}%
            <label class="bmd-label-floating main-label">Email:</label>
<g:textField placeholder="Enter email" name="username" id="username" class="form-control main-input"
             value="${params.loginEmail}"/>

        </div>
        <br>
        <div class="form-group">
%{--            <label for="password">Password</label><br>--}%
%{--            <input type="password" class="form-control" placeholder="Enter password" name="password"><br>--}%
            <label class="bmd-label-floating main-label long-label">Password:</label>
            <input type="password" class="form-control main-input login-pass" name='password' id='password' placeholder="Enter password">

            <a href="#" class="show-pass-icon"><i class="zmdi zmdi-eye"></i></a>
        </div>
        <br>
        <button class="btn btn-primary" type="submit">Log in </button><br>
        <small>Don't have an account yet? <a class="text-decoration-none" href="../user/register">Register</a></small>
        <div class="alert alert-danger" role="alert" style="display: none;" id='myAlert'>

        </div>
    </div>
</g:form>
</body>
</html>