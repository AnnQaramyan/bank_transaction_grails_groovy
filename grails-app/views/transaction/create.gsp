<%--
  Created by IntelliJ IDEA.
  User: boyaj
  Date: 12/21/2021
  Time: 9:29 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="layout" content="main"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Create Transaction</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <asset:javascript src="create-transaction.js"></asset:javascript>
</head>

<body>
<g:form name="createTransactionForm" class="onboarding-form" id="createTransactionForm" url="[controller: 'transaction', action: 'add']"
        method="POST" autocomplete="off">
<div class="container col-md-6" id="create_tr_cont">
    <h2>Create Transaction</h2>
    <div class="form-group">
        <label for="type">Transaction type</label><br>
        <select name="type" class="form-select" onchange="createToInput()">
            <option value="DEPOSIT">DEPOSIT</option>
            <option value="WITHDRAWAL">WITHDRAWAL</option>
            <option value="EXCHANGE">EXCHANGE</option>
        </select>
    </div>
    <br>
    <div class="form-group">
        <label for="amount">Amount</label><br>
        <input type="text" class="form-control ${hasErrors(bean: transactionUserRequestModel, field: 'amount', 'error')}" placeholder="Enter Amount" name="amount"><br>
        <bs:fieldErrors bean="${transactionUserRequestModel}" field="amount"/>
    </div>
    <div class="form-group">
        <label for="from">From</label><br>
        <select name="from" class="form-select">
            <g:each in="${validAccountList}">
                <option value="${it.number}">${it.number}</option>
            </g:each>
        </select>
    </div>
    <br>

    <button id="create_btn" class="btn btn-primary" type="submit">Create</button>
    <g:link controller="transaction" action="userTransactions"><input type="button" value="Cancel" class="btn btn-danger"></g:link>
    <br><br>
    <div class="alert alert-danger" role="alert" style="display: none;" id='myAlert'>

    </div>
</div>
</g:form>
</body>
</html>