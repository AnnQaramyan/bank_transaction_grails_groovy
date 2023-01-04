<%--
  Created by IntelliJ IDEA.
  User: boyaj
  Date: 12/22/2021
  Time: 3:34 AM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="layout" content="main"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Update Transaction</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <asset:javascript src="update-transaction.js"></asset:javascript>
</head>

<body>
<g:form name="updateTransactionForm" class="onboarding-form" id="updateTransactionForm" url="[controller: 'transaction', action: 'put']"
         method="POST" autocomplete="off">
    <div class="container col-md-6">
        <h2>Update Transaction</h2>
        <div class="form-group">
            <input type="text" id="hiddenIdInput" name="hiddenId" hidden>
        </div>
        <div class="form-group">
            <input type="text" id="hiddenPageNumber" name="hiddenPageNumber" hidden>
        </div>
        <div class="form-group">
            <label for="type">Types</label><br>
            <select name="type" class="form-select" onchange="updateToInput()">
                <option value="DEPOSIT">DEPOSIT</option>
                <option value="WITHDRAWAL">WITHDRAWAL</option>
                <option value="EXCHANGE">EXCHANGE</option>
            </select>
            <br>
        </div>
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
        <div class="form-group" id="hiddenTo" style="display: none">
            <label for="to">To</label><br>
            <input type="text" class="form-control" placeholder="To Account" name="to"><br>
        </div>
        <button class="btn btn-primary" type="submit">Update</button>
        <g:link controller="transaction" action="userTransactions"><input type="button" value="Cancel" class="btn btn-danger"></g:link>
        <br><br>
        <div class="alert alert-danger" role="alert" style="display: none;" id='myAlert'>

        </div>
    </div>
</g:form>
<script type="text/javascript">
    window.onload = ()=>{
        let type = document.getElementsByName('type')[0];
        type.value = localStorage.getItem('trType');
        let amount = document.getElementsByName('amount')[0];
        amount.value = localStorage.getItem('trAmount');
        let from = document.getElementsByName('from')[0];
        from.value = localStorage.getItem('trFrom');
        let to = document.getElementsByName('to')[0];
        to.value = localStorage.getItem('trTo');
        let hiddenId = document.getElementById('hiddenIdInput');
        hiddenId.value = localStorage.getItem('transactionId')
        let typeSelect = document.getElementsByName('type')[0];
        let toInput = document.getElementById('hiddenTo');
        if(typeSelect.value == 'EXCHANGE'){
            toInput.style.display = 'block';
        }
        let pageNumber = document.getElementById('hiddenPageNumber');
        pageNumber.value = localStorage.getItem('pageNumber')
    }
</script>
</body>
</html>