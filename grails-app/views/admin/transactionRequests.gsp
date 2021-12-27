<%--
  Created by IntelliJ IDEA.
  User: boyaj
  Date: 12/28/2021
  Time: 1:36 AM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Account Requests</title>
</head>

<body>
<div id="mainDiv" style="width: 99%">
    <table class="table table-bordered table-hover">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Type</th>
            <th scope="col">Amount</th>
            <th scope="col">To Amount</th>
            <th scope="col">From</th>
            <th scope="col">To</th>
            <th scope="col">Date Created</th>
            <th scope="col">Last Updated</th>
            <th scope="col">Status</th>
            <th scope="col">Activation Status</th>
        </tr>
        </thead>
        <tbody>
        <g:each in="${transactionsList}" var="currentItem">
            <tr>
                <td>${currentItem.id}</td>
                <td>${currentItem.type}</td>
                <td>${currentItem.amount}</td>
                <td>${currentItem.toAmount}</td>
                <td>${currentItem.from.number},${currentItem.from.currency}</td>
                <td>${currentItem.to.number},${currentItem.to.currency}</td>
                <td><g:formatDate format="yyyy-MM-dd HH:mm:ss" date="${currentItem.dateCreated}"/></td>
                <td><g:formatDate format="yyyy-MM-dd HH:mm:ss" date="${currentItem.lastUpdated}"/></td>
                <td>${currentItem.status}</td>
                <g:if test="${currentItem.isActive}">
                    <td>Active</td>
                    <g:if test="${currentItem.status == grailstestapp.Status.PENDING}">
                        <td><input type="button" class="btn btn-success" value="Accept"
                                   onclick="acceptTransaction(${currentItem.id},this)"></td>
                        <td><input type="button" class="btn btn-danger" value="Reject"
                                   onclick="rejectTransaction(${currentItem.id},this)"></td>
                    </g:if>
                </g:if>
                <g:else>
                    <td>Inactive</td>
                </g:else>

            </tr>
        </g:each>
        </tbody>
    </table>
</div>
</body>
</html>