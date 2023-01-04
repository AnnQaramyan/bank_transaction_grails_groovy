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
    <title>Create Loan Request</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <asset:javascript src="create-transaction.js"></asset:javascript>
</head>

<body>
<g:form name="createLoanRequestForm" class="onboarding-form" id="createLoanRequestForm"
        url="[controller: 'loan', action: 'add']"
        method="POST" autocomplete="off">
    <div class="container col-md-6">
        <h2>Create Loan Request</h2>

        <div class="form-group">
            <label for="accountUserRequestModel.number">Account Number</label><br>
            <select id="accountUserRequestModel.number" name="accountUserRequestModel.number" class="form-select">
                <g:each in="${validAccountList}">
                    <option value="${it.number}">${it.number}</option>
                </g:each>
            </select>
        </div>
        <br>

        <div class="form-group">
            <label for="amount">Amount</label><br>
            <input id="amount" type="text"
                   class="form-control ${hasErrors(bean: transactionUserRequestModel, field: 'amount', 'error')}"
                   placeholder="Enter Amount" name="amount"><br>
            <bs:fieldErrors bean="${transactionUserRequestModel}" field="amount"/>
        </div>

        <div class="form-group">
            <label for="description">Description</label><br>
            <textarea id="description" type="text" class="form-control " placeholder="Enter Description"
                      name="description"></textarea><br>
            <bs:fieldErrors bean="${transactionUserRequestModel}" field="description"/>
        </div>

        <div class="form-group">
            <label for="mortgageUserRequestModel.collateralType">Collateral Type</label><br>
            <select id="mortgageUserRequestModel.collateralType" name="mortgageUserRequestModel.collateralType" class="form-select">
                <option value="REAL_ESTATE">Real Estate</option>
                <option value="MOVABLE_ESTATE">Movable Estate</option>
                <option value="PROPERTY">Property</option>
            </select>
        </div>
        <br>

        <div class="form-group">
            <label for="mortgageUserRequestModel.description">Collateral Description</label><br>
            <textarea id="mortgageUserRequestModel.description" type="text" class="form-control " placeholder="Enter Description"
                      name="mortgageUserRequestModel.description"></textarea><br>
            <bs:fieldErrors bean="${transactionUserRequestModel}" field="description"/>
        </div>
        <button class="btn btn-primary" type="submit">Create</button>
        <g:link controller="loan" action="userLoans"><input type="button" value="Cancel"
                                                            class="btn btn-danger"></g:link>
        <br><br>

        <div class="alert alert-danger" role="alert" style="display: none;" id='myAlert'>

        </div>
    </div>
</g:form>
</body>
</html>