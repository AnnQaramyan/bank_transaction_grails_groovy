<%--
  Created by IntelliJ IDEA.
  User: boyaj
  Date: 12/26/2021
  Time: 1:41 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Users</title>
</head>

<body>
<div id="mainDiv" style="width: 99%">
    <table class="table table-bordered table-hover">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">First Name</th>
            <th scope="col">Last Name</th>
            <th scope="col">Email</th>
            <th scope="col">Birth Date</th>
            <th scope="col">Mobile</th>
            <th scope="col">Address</th>
            <th scope="col">Date Created</th>
            <th scope="col">Last Updated</th>
            <th scope="col">Authorities</th>
            <th scope="col">Activation Status</th>
        </tr>
        </thead>
        <tbody id="usersTableBody">
            <g:render template="usersContent" model="${usersList}"/>
        </tbody>
    </table>
</div>
<div id="paginationNav">
    <g:if test="${count>1}">
        <g:render template="../usersPaginationNavbar" model="${count}, ${page}"/>
    </g:if>
</div>

</body>
</html>