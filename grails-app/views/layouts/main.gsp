    <!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <link rel="icon" type="image/x-icon" href="/assets/logo.png" />

    <title>
        Distributed Banking
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <asset:stylesheet href="home.css"></asset:stylesheet>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <asset:javascript src="home.js"></asset:javascript>
    <asset:javascript src="jquery-3.3.1.min.js"></asset:javascript>
    <asset:javascript src="pagination-navbar.js"></asset:javascript>
    <g:layoutHead/>
    <style>
    body{
        background-color: aliceblue;
    }
</style>
</head>

<body>

<div id="navDiv" style="margin-top: 70px;">
    <nav class="navbar navbar-dark navbar-expand-lg fixed-top bg-dark" id="mainNav">
        <div class="container">
            <div class="collapse navbar-collapse" id="navbarResponsive">
                <ul class="nav navbar-nav ml-auto">
                    <li class="nav-item"><g:link controller="home" action="home"><input type="button" class="btn btn-dark" value="My Page"></g:link></li>
                    <sec:ifAnyGranted roles="ROLE_USER">
                        <li class="nav-item"><g:link controller="account" action="userAccounts"><input type="button" name="secondInNav" class="btn btn-dark" value="Accounts"></g:link></li>
                    </sec:ifAnyGranted>
                    <sec:ifAnyGranted roles="ROLE_ADMIN">
                        <li class="nav-item"><g:link controller="admin" action="users"><input type="button" name="secondInNav" class="btn btn-dark" value="Users"></g:link></li>
                    </sec:ifAnyGranted>
                    <sec:ifAnyGranted roles="ROLE_USER">
                        <li class="nav-item"><g:link controller="transaction" action="userTransactions"><input type="button" name="thirdInNav" class="btn btn-dark" value="Transactions"></g:link></li>
                    </sec:ifAnyGranted>
                    <sec:ifAnyGranted roles="ROLE_ADMIN">
                        <li class="nav-item"><g:link controller="admin" action="accountRequests"><input type="button" name="thirdInNav" class="btn btn-dark" value="Account Requests"></g:link></li>
                    </sec:ifAnyGranted>
                    <sec:ifAnyGranted roles="ROLE_ADMIN">
                        <li class="nav-item"><g:link controller="admin" action="transactionRequests"><input type="button" name="fourthInNav" class="btn btn-dark" value="Transaction Requests"></g:link></li>
                    </sec:ifAnyGranted>
                    <sec:ifAnyGranted roles="ROLE_USER">
                        <li class="nav-item"><g:link controller="loan" action="userLoans"><input type="button" name="secondInNav" class="btn btn-dark" value="My Loans"></g:link></li>
                    </sec:ifAnyGranted>
                    <sec:ifAnyGranted roles="ROLE_ADMIN">
                        <li class="nav-item"><g:link controller="admin" action="getAllLoans"><input type="button" name="secondInNav" class="btn btn-dark" value="Loans"></g:link></li>
                    </sec:ifAnyGranted>
                    <li class="nav-item"><g:link controller="logout" action="logout"><input type="button" class="btn btn-dark" value="Log Out"></g:link></li>

                </ul>
            </div>
        </div>
    </nav>
</div>

<g:layoutBody/>

</body>
</html>
