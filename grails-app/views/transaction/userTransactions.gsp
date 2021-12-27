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
<g:link controller="transaction" action="create"><input type="button" class="btn btn-warning" value="Create Transaction"></g:link>
<div id="mainDiv" style="width: 99%">
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
                <td><g:formatDate format="yyyy-MM-dd HH:mm:ss" date="${currentItem.dateCreated}"/></td>
                <td><g:formatDate format="yyyy-MM-dd HH:mm:ss" date="${currentItem.lastUpdated}"/></td>
                <td>${currentItem.status}</td>
                <g:if test="${currentItem.isActive}">
                    <td>Active</td>
                </g:if>
                <g:else>
                    <td>Inactive</td>
                </g:else>

            <g:if test="${currentItem.status == grailstestapp.Status.PENDING}">
                <td>
                    <g:link controller="transaction" action="update"><input type="button" class="btn btn-info" value="Update" onclick="localStorage.setItem('transactionId','${currentItem.id}');transactionUpdateButtonEvent('${currentItem.type}', '${currentItem.amount}', '${currentItem.from.number}','${currentItem.to.number}')"></g:link>
                </td>
                <g:if test="${currentItem.isActive}">
                    <td>
                        <input type="button" class="btn btn-danger" value="Deactivate"
                               onclick="deactivateTransaction('${currentItem.id}',this)">
                    </td>
                </g:if>
                <g:else>
                    <td>
                        <input type="button" class="btn btn-success" value="Activate"
                               onclick="activateTransaction('${currentItem.id}',this)">
                    </td>
                </g:else>
            </g:if>
            </tr>
        </g:each>
        </tbody>
    </table>
</div>
</body>
</html>