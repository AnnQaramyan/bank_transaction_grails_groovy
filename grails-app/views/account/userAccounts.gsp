<%--
  Created by IntelliJ IDEA.
  User: boyaj
  Date: 12/21/2021
  Time: 7:20 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Accounts</title>
</head>

<body>
<div id="mainDiv" style="width: 80%">
    <input type="button" class="btn btn-warning" value="Create Account" onclick="location.href='../account/create'">
    <table class="table table-bordered table-hover">
        <thead>
        <tr>
            <th scope="col">Number</th>
            <th scope="col">Currency</th>
            <th scope="col">Date Created</th>
            <th scope="col">Last Updated</th>
            <th scope="col">Status</th>
            <th scope="col">Activation Status</th>
            <th scope="col">Balance</th>
        </tr>
        </thead>
        <tbody>
        <g:each in="${accountList}" status="index" var="currentItem">
            <tr>
                <td>${currentItem.number}</td>
                <td>${currentItem.currency}</td>
                <td>${currentItem.dateCreated}</td>
                <td>${currentItem.lastUpdated}</td>
                <td>${currentItem.status}</td>
                <td>${currentItem.isActive}</td>
                <td>${balanceList[index]}</td>
                <g:if test="${currentItem.status == grailstestapp.Status.PENDING}">
                    <td>
                        <input type="button" class="btn btn-info" value="Update" onclick="location.href=''">
                    </td>
                </g:if>

                <td>

                </td>
            </tr>
        </g:each>
        </tbody>
    </table>
</div>

</body>
</html>