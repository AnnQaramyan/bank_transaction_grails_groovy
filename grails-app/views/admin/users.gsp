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
        <tbody>
        <g:each in="${usersList}" var="currentItem">
            <tr>
                <td onclick="accountsTable('${currentItem.id}', '${createLink(controller: 'admin',action: 'userAccounts')}');
                document.getElementById('myModal').setAttribute('style','display:block')">${currentItem.id}</td>
                <td>${currentItem.firstName}</td>
                <td>${currentItem.lastName}</td>
                <td>${currentItem.email}</td>
                <td><g:formatDate format="yyyy-MM-dd" date="${currentItem.birthDate}"/></td>
                <td>${currentItem.mobile}</td>
                <td>${currentItem.addressAdminModel.country},
                    ${currentItem.addressAdminModel.city},
                    ${currentItem.addressAdminModel.street},
                    ${currentItem.addressAdminModel.houseNumber},
                    ${currentItem.addressAdminModel.postalCode}
                </td>
                <td><g:formatDate format="yyyy-MM-dd HH:mm:ss" date="${currentItem.dateCreated}"/></td>
                <td><g:formatDate format="yyyy-MM-dd HH:mm:ss" date="${currentItem.lastUpdated}"/></td>
                <td>${currentItem.authorities}</td>
                <g:if test="${currentItem.isActive}">
                    <td>Active</td>
                    <td><input type="button" class="btn btn-danger" value="Deactivate"
                               onclick="deactivateUser('${currentItem.id}',this)"></td>
                </g:if>
                <g:else>
                    <td>Inactive</td>
                    <td><input type="button" class="btn btn-success" value="Activate"
                               onclick="activateUser('${currentItem.id}',this)"></td>
                </g:else>

            </tr>
        </g:each>
        </tbody>
    </table>
</div>
<div id="myModal" class="modal">
    <div id="myCont" class="modal-content">
        <span class="close">&times;</span>
        <div id="tableCont">
        </div>
    </div>

</div>
<script type="text/javascript">
    let modal = document.getElementById("myModal");
    let span = document.getElementsByClassName("close")[0];
    span.onclick = function() {
        modal.style.display = "none";
    }
</script>
</body>
</html>