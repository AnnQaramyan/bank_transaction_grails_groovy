<%--
  Created by IntelliJ IDEA.
  User: boyaj
  Date: 12/27/2021
  Time: 8:38 PM
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
<div class="container">
    <table class="table table-bordered table-hover">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Number</th>
            <th scope="col">Currency</th>
            <th scope="col">Date Created</th>
            <th scope="col">Last Updated</th>
            <th scope="col">User ID</th>
            <th scope="col">Status</th>
            <th scope="col">Activation Status</th>
        </tr>
        </thead>
        <tbody id="accountsTableBody">
            <g:render template="accountRequestsContent" model="${requestsList}"/>
        </tbody>
    </table>
</div>
<div id="paginationNav">
    <g:if test="${count>1}">
        <g:render template="../accountsPaginationNavbar" model="${count}, ${page}"/>
    </g:if>
</div>
</body>
</html>