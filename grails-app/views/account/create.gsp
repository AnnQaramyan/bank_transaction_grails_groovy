<%--
  Created by IntelliJ IDEA.
  User: boyaj
  Date: 12/21/2021
  Time: 3:37 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Create Account</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <asset:javascript src="create-account.js"></asset:javascript>
</head>

<body>
<g:form name="createAccountForm" class="onboarding-form" id="createAccountForm" url="[controller: 'account', action: 'add']"
        method="POST" autocomplete="off">
<div class="container col-md-6">
    <h2>Create Account</h2>
    <div class="form-group">
        <label for="currencies">Currency</label><br>
        <select name="currencies" class="form-select">
            <option value="USD">USD</option>
            <option value="EUR">EUR</option>
            <option value="GBP">GBP</option>
            <option value="RUB">RUB</option>
            <option value="AMD">AMD</option>
        </select>
    </div>
    <br>
%{--    <input type="button" value="Create" class="btn btn-primary" onclick="createAccount()">--}%
    <button type="submit" class="btn btn-primary">Create</button>
    <input type="button" value="Cancel" class="btn btn-danger" onclick="location.href='../../home/home'">
</div>
</g:form>
</body>
</html>