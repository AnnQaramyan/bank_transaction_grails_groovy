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

    <g:form name="updateLoanRequestForm" class="onboarding-form" id="updateLoanRequestForm" url="[controller: 'admin', action: 'update']"
            method="POST" autocomplete="off">
    <div class="row">
        <div class="col-md-4">
            <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQIsAYqLUZ-fKMM37Nk4LahvH_gLXxkZhPv4A&usqp=CAU">
        </div>
        <div class="col-md-8">
            <h4>Loan</h4>
            <h6>Loan ID : ${loan.id} </h6>
%{--            <input type="hidden" class="form-control" name="loan.id" id="loan.id" disabled value="${loan.id}"><br>--}%
            <div class="form-group">
                <label for="loan.id">Id</label>
                <input type="hidden" class="form-control"  name="loan.id" id="loan.id" value="${loan.id}"><br>
            </div>
            <h6>Amount : ${loan.amount}</h6>
            <h6>Description : ${loan.description}</h6>
            <hr>
            <h4>Loan User</h4> <br>
            <h6> ${loan.user.firstName}  ${loan.user.lastName} ,</h6> <br>
            <h6>Email: ${loan.user.username}, </h6>`
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
                <label for="loan.mortgage.estimatedPrice">Estimated price</label>
                <input type="text" class="form-control" placeholder="Enter Estimated Price" name="loan.mortgage.estimatedPrice" id="loan.mortgage.estimatedPrice" value="${loan.mortgage.estimatedPrice}"><br>
            </div>
            <div class="form-group">
                <label for="loan.mortgage.name">Is Validated</label>
                <g:if test="${loan.mortgage.valid}">
                    <input type="checkbox"  name="loan.mortgage.valid" id="loan.mortgage.valid" checked><br>
                </g:if>
                <g:else>
                    <input type="checkbox"  name="loan.mortgage.valid" id="loan.mortgage.valid"><br>
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
                    <th>Invested Amount</th>
                    <th>Invested Percent</th>
                    <th>Return Amount</th>
                    </thead>
                    <tbody>
                        <g:each in="${loan.investors}">
                            <tr>
                                <td>${it.key.username}</td>
                                <td>${it.value}</td>
                                <td>...</td>
                                <td>...</td>
                            </tr>
                        </g:each>
                    </tbody>
                </table>
        </div>
            <hr>
            <div class="form-group">
                <label for="investors">Potential Invsetors</label><br>
                <select name="investors" id="investors" class="form-select" multiple>
                    <g:each in="${potential_investors}">
                        <option value="${it.usename}"></option>
                    </g:each>
                </select>
            </div>
                <hr>
            <button type="submit" class="btn btn-primary">Update</button>
        </div>

    </div>
    </g:form>

        </div>

<div id="myModal" class="modal">
    <div id="myCont" class="modal-content">
        <span class="close">&times;</span>
    </div>
</div>
</body>
</html>