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
    <title>Loan</title>
</head>

<body>

<div id="mainDiv" style="width: 60%">

        <div class="row">
            <div class="col-md-4">
                <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQIsAYqLUZ-fKMM37Nk4LahvH_gLXxkZhPv4A&usqp=CAU">
            </div>
            <div class="col-md-8">
                <h4>Loan</h4>
                <div class="form-group">
                    <label for="loan.id">Id</label>
                    <input type="hidden" class="form-control"  name="loan.id" id="loan.id" value="${loan.id}"><br>
                </div>
                <h6>Amount : ${loan.amount}</h6>
                <h6>Description : ${loan.description}</h6>
                <hr>
                <h4>Mortgage</h4>
                <h6>Collateral Type : <g:if test="${loan.isActive}">
                    Active
                </g:if>
                <g:else>
                    Inactive
                </g:else>,</h6>
                <h6>Collateral Description : ${loan.mortgage.description},</h6>
                <div class="form-group">
                    <h4>Estimated price</h4>
                    <h5 class="form-control">${loan.mortgage.estimatedPrice}</h5><br>
                </div>
                <div class="form-group">
                    <h4>Is Validated</h4>
                    <g:if test="${loan.mortgage.valid}">
                        Validated
                    </g:if>
                    <g:else>
                        Not Valid
                    </g:else>
                    <br>
                </div>
                <h6>Created date : <g:formatDate format="yyyy-MM-dd" date="${loan.dateCreated}"/></h6>
                <h6>Due date : <g:formatDate format="yyyy-MM-dd" date="${loan.dueDate}"/></h6>
                <br>
                <h4>Investors</h4>
                <div class="form-group">
                    <table class="table table-bordered table-hover">
                        <thead>
                        <th>Investor</th>
                        <th>Payment remaining</th>
                        </thead>
                        <tbody>
                        <g:each in="${loan.investors}">
                            <tr>
                                <td>${it.key}</td>
                                <td>${it.value}</td>
                            </tr>
                        </g:each>
                        </tbody>
                    </table>
                </div>
                <hr>
            </div>

        </div>

</div>

</body>
</html>