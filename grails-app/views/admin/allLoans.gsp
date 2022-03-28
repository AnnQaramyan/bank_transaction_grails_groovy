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
<div id="mainDiv" style="width: 99%">
  <table class="table table-bordered table-hover">
    <thead>
    <tr>
      <th scope="col">Account Number</th>
      <th scope="col">Descriptions</th>
      <th scope="col">Status</th>
      <th scope="col">Mortgage</th>
      <th scope="col">Loan Amount</th>
      <th scope="col">Mortgage Estimation</th>
      <th scope="col">Due Date</th>
      <th scope="col">Created Date</th>
      <th scope="col">Accepted Date</th>
      <th scope="col">Activation  Status</th>
      <th scope="col">Accept</th>
      <th scope="col">Reject</th>
      <th scope="col">Inspect</th>
    </tr>
    </thead>
    <tbody id="userLoansTableBody">
    <g:render template="allLoansContent" model="${loanList}"/>
    </tbody>
  </table>
</div>
%{--<div id="paginationNav">
    <g:if test="${count>1}">
        <g:render template="../userAccountsPaginationNavbar" model="${count}, ${page}"/>
    </g:if>
</div>--}%
</body>
</html>