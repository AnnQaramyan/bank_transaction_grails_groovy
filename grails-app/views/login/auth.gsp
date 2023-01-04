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

<body >
<div class="Aligner" style="background-image: url('/assets/login_background.png'); background-size: cover; color: whitesmoke">
    <div class="Aligner-item"><g:form name="loginForm" class="onboarding-form" id="loginForm" url="[controller: 'login', action: 'authenticate']"
                                      method="POST" autocomplete="off">
        <div class="container col-md-6">
            <h2 class="text-center">Log In</h2>
            <br>
            <div class="form-group" style="margin-bottom: 10px">
                <g:textField placeholder="Email" name="username" id="username" class="form-control main-input"
                             value="${params.loginEmail}"/>

            </div>
            <div class="form-group">
                <input type="password" class="form-control main-input login-pass" name='password' id='password' placeholder="Password">

                <a href="#" class="show-pass-icon"><i class="zmdi zmdi-eye"></i></a>
            </div>
            <br>
            <button class="btn btn-primary" type="submit">Log in </button><br>
            <small>Don't have an account yet? <a class="text-decoration-none" href="../user/register">Register</a></small>
            <div class="alert alert-danger" role="alert" style="display: none;" id='myAlert'>

            </div>
        </div>
    </g:form>
    </div>
</div>
</body>
</html>