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
%{--    <asset:stylesheet href="home.css"></asset:stylesheet>--}%
%{--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">--}%
%{--    <asset:javascript src="home.js"></asset:javascript>--}%
</head>

<body>

<div id="mainDiv" style="width: 60%">
    <div class="row">
        <div class="col-md-4">
            <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQRr0YlatAy-hrNCQjzZ7fqDzNiXt7HGmzVaA&usqp=CAU">
        </div>
        <div class="col-md-8">
            <h5>${current.firstName} ${current.lastName}</h5>
            <h5>Username: ${current.username}</h5>
            <h5>Mobile: ${current.mobile}</h5>
            <h5>Birth Date: ${current.birthDate}</h5>
            <h5 style="white-space: pre-line">${current.address.country},${current.address.city}
            ${current.address.street}, ${current.address.houseNumber},${current.address.postalCode}</h5>
            <input type="button" class="btn btn-warning" value="Update personal info" style="margin-right:10px" onclick="location.href='../user/update'">
            <input type="button" class="btn btn-warning" value="Update password" onclick="location.href='../user/updatePassword'">
        </div>
    </div>
</div>

<div id="myModal" class="modal">
    <div id="myCont" class="modal-content">
        <span class="close">&times;</span>
    </div>
</div>

%{--<script type="text/javascript">--}%
%{--    window.onload = ()=>loadData('${current.id}', '${current.firstName}', '${current.lastName}',--}%
%{--        '${current.username}', '${current.mobile}','${current.birthDate}', '${current.address.country}',--}%
%{--        '${current.address.city}', '${current.address.street}', '${current.address.houseNumber}'--}%
%{--        ,'${current.address.postalCode}');--}%
%{--    let modal = document.getElementById("myModal");--}%
%{--    let span = document.getElementsByClassName("close")[0];--}%
%{--    span.onclick = function() {--}%
%{--        modal.style.display = "none";--}%
%{--    }--}%
%{--</script>--}%
</body>
</html>