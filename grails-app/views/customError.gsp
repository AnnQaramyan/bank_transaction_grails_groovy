<%--
  Created by IntelliJ IDEA.
  User: boyaj
  Date: 12/29/2021
  Time: 2:20 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Exception</title>
</head>

<body>
    <div class="alert alert-danger" role="alert" id='myAlert'>
        <bs:exH exception="${exception}"/>
    </div>
</body>
</html>