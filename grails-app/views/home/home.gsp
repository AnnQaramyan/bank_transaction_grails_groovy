<%--
  Created by IntelliJ IDEA.
  User: boyaj
  Date: 12/20/2021
  Time: 10:32 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>My Page</title>
<style>
.Aligner {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 80vh;
}

.Aligner-item {
    width: 100%;
}

</style>
</head>

<body>

<div class="Aligner container-fluid">
    <div class="Aligner-item row">
        <div class="col-md-3"></div>
        <div class="col-md-3">
                <g:img dir="images" file="profile_pic.png" width="256"/>
        </div>
        <div class="col-md-3">
            <h5>${current.firstName} ${current.lastName}</h5>
            <h5>Username: ${current.username}</h5>
            <h5>Mobile: ${current.mobile}</h5>
            <h5>Birth Date: <g:formatDate format="yyyy-MM-dd" date="${current.birthDate}"/></h5>
            <h5 style="white-space: pre-line">${current.address.country}, ${current.address.city}
            ${current.address.street}, ${current.address.houseNumber}, ${current.address.postalCode}</h5>
            <hr>
            <g:link controller="user" action="update"><input type="button" class="btn btn-warning" value="Update personal info" style="margin-right:10px"></g:link>
            <g:link controller="user" action="updatePassword"><input type="button" class="btn btn-warning" value="Update password"></g:link>
        </div>
        <div class="col-md-3"></div>
    </div>
</div>

<div id="myModal" class="modal">
    <div id="myCont" class="modal-content">
        <span class="close">&times;</span>
    </div>
</div>
</body>
</html>