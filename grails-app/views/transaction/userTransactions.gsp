<%--
  Created by IntelliJ IDEA.
  User: boyaj
  Date: 12/21/2021
  Time: 9:06 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Transactions</title>
</head>

<body>
<div class="container">
    <table class="table table-bordered table-hover">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Type</th>
            <th scope="col">Amount</th>
            <th scope="col">From</th>
            <th scope="col">To</th>
            <th scope="col">Date Created</th>
            <th scope="col">Last Updated</th>
            <th scope="col">Status</th>
            <th scope="col">Activation Status</th>
        </tr>
        </thead>
        <tbody id="userTransactionsTableBody">
            <g:render template="userTransactionsContent" model="${transactionList}, ${page}"/>
        </tbody>
    </table>
    <hr>
    <g:link controller="transaction" action="create"><input type="button" class="btn btn-warning" value="Create Transaction"></g:link>

</div>
    <g:if test="${count>1}">
        <g:render template="../userTransactionsPaginationNavbar" model="${count}, ${page}"/>
    </g:if>
</body>
</html>