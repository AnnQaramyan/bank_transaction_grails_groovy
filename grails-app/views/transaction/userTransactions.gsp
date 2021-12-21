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
<input type="button" class="btn btn-warning" value="Create Transaction" onclick="location.href='../transaction/create'">
<div id="mainDiv" style="width: 80%">
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
        <tbody>
        <g:each in="${transactionList}" status="index" var="currentItem">
            <tr>
                <td>${currentItem.id}</td>
                <td>${currentItem.type}</td>
                <td>${currentItem.amount}</td>
                <td>${currentItem.from.number},${currentItem.from.currency}</td>
                <td>${currentItem.to.number},${currentItem.to.currency}</td>
                <td>${currentItem.dateCreated}</td>
                <td>${currentItem.lastUpdated}</td>
                <td>${currentItem.status}</td>
                <td>${currentItem.isActive}</td>
            </tr>
        </g:each>
        </tbody>
    </table>
</div>
</body>
</html>