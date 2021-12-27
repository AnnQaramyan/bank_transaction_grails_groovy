<%--
  Created by IntelliJ IDEA.
  User: boyaj
  Date: 12/21/2021
  Time: 11:57 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Update Account</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>

<body>
<g:form name="updateAccountForm" class="onboarding-form" id="updateAccountForm" url="[controller: 'account', action: 'put']"
        method="POST" autocomplete="off">
<div class="container col-md-6">
    <h2>Update Account</h2>
    <div class="form-group">
        <label for="number">Number</label><br>
        <input type="text" class="form-control" placeholder="Enter Account Number" name="number" readonly><br>
    </div>
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
%{--    <input type="button" value="Update" class="btn btn-primary" onclick="updateAccount()">--}%
    <button class="btn btn-primary" type="submit">Update</button>
    <g:link controller="account" action="userAccounts"><input type="button" value="Cancel" class="btn btn-danger"></g:link>

</div>
</g:form>
<script type="text/javascript">
    window.onload = ()=>{
        let accNum = document.getElementsByName('number')[0];
        accNum.value = localStorage.getItem('accNumber');
        let currency = document.getElementsByName('currencies')[0];
        currency.value = localStorage.getItem('accCurrency');

    }
</script>
</body>
</html>