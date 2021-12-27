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
<div id="mainDiv" style="width: 99%">
    <g:link controller="account" action="create"><input type="button" class="btn btn-warning" value="Create Account"></g:link>
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
                <td><g:formatDate format="yyyy-MM-dd HH:mm:ss" date="${currentItem.dateCreated}"/></td>
                <td><g:formatDate format="yyyy-MM-dd HH:mm:ss" date="${currentItem.lastUpdated}"/></td>
                <td>${currentItem.status}</td>
                <g:if test="${currentItem.isActive}">
                    <td>Active</td>
                </g:if>
                <g:else>
                    <td>Inactive</td>
                </g:else>
                <g:if test="${currentItem.status== grailstestapp.Status.ACCEPTED}">
                    <td>${balanceList[index]}</td>
                </g:if>
                <g:else>
                    <td></td>
                </g:else>
                <g:if test="${currentItem.status == grailstestapp.Status.PENDING}">
                    <td>
                        <g:link controller="account" action="update"><input type="button" class="btn btn-info" onclick="accountUpdateButtonEvent('${currentItem.number}','${currentItem.currency}')" value="Update"></g:link>
                    </td>
                    <g:if test="${currentItem.isActive}">
                        <td>
                            <input type="button" class="btn btn-danger" value="Deactivate"
                                   onclick="deactivateAccount('${currentItem.id}',this)">
                        </td>
                    </g:if>
                    <g:else>
                        <td>
                            <input type="button" class="btn btn-success" value="Activate"
                                   onclick="activateAccount('${currentItem.id}',this)">
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