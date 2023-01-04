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
    <title>Loans</title>
</head>

<body>
<div class="container">
    <table class="table table-bordered table-hover">
        <thead>
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Amount</th>
            <th scope="col">Account Number</th>
            <th scope="col">Descriptions</th>
            <th scope="col">Status</th>
            <th scope="col">Activation Status</th>
            <th scope="col">Mortgages</th>
            <th scope="col">Due Date</th>
            <th scope="col">Accepted Date</th>
            <th scope="col">Inspect</th>
        </tr>
        </thead>
        <tbody id="userLoansTableBody">
        <g:render template="userLoansContent" model="${loanList}, ${page}"/>
        </tbody>
    </table>
<hr>
    <g:link controller="loan" action="loanCreation"><input type="button" class="btn btn-warning" value="Create Loan Request"></g:link>

</div>
%{--<div id="paginationNav">
    <g:if test="${count>1}">
        <g:render template="../userAccountsPaginationNavbar" model="${count}, ${page}"/>
    </g:if>
</div>--}%
</body>
</html>