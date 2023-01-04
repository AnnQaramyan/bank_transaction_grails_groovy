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
<div class="container">
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
            <th scope="col">Permitted Investment</th>
        </tr>
        </thead>
        <tbody id="userAccountsTableBody">
            <g:render template="userAccountsContent" model="${accountList}, ${balanceList}, ${page}"/>
        </tbody>
    </table>
    <hr>
    <g:link controller="account" action="create"><input type="button" class="btn btn-warning" value="Create Account"></g:link>
</div>
<div id="paginationNav">
    <g:if test="${count>1}">
        <g:render template="../userAccountsPaginationNavbar" model="${count}, ${page}"/>
    </g:if>
</div>
</body>
</html>