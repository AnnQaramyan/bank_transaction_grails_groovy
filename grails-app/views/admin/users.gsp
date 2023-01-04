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
    <asset:javascript src="filter.js"></asset:javascript>
</head>

<body>
<div class="container">
    <div class="row">
        <g:form>
            <button type="button" style="float: right" class="btn btn-primary" onclick="filter(document.getElementsByName('search')[0].value, document.getElementsByName('filter_by')[0].value)"><i class="fa fa-search fa-lg"></i>Filter</button>
            <label>Filter by anything: <g:textField name="search"  class="search-field" placeholder="search"/></label>
            <label>
                <select name="filter_by"  class="selectpicker" style="padding: 3px 5px" onchange="createToInput()">
                    <option value="first_name">First Name</option>
                    <option value="last_name">Last Name</option>
                    <option value="email">Email</option>
                    <option value="date_created">Date Created</option>
                    <option value="last_updated">Last Updated</option>
                    <option value="act_status">Activation Status</option>
                </select>
            </label>
            <hr>
        </g:form>

    </div>
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