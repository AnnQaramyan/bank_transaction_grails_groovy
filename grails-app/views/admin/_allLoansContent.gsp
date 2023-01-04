
<g:each in="${loanList}" status="index" var="currentItem">
    <tr>
        <td>${currentItem.account.number}</td>
        <td>${currentItem.description}</td>
        <td>${currentItem.status}</td>
        <td>${currentItem.mortgage.description}</td>
        <td>${currentItem.amount}</td>
        <td>${currentItem.mortgage.estimatedPrice}</td>
        <td><g:formatDate format="yyyy-MM-dd HH:mm:ss" date="${currentItem.dateCreated}"/></td>
        <td><g:formatDate format="yyyy-MM-dd HH:mm:ss" date="${currentItem.dateAccepted}"/></td>
%{--        <td><g:formatDate format="yyyy-MM-dd HH:mm:ss" date="${currentItem.dueDate}"/></td>--}%
        <td>
            <g:if test="${currentItem.isActive}">ACTIVE</g:if>
            <g:else>INACTIVE</g:else>
        </td>
        <td style="text-align: center">
            <g:if test="${currentItem.status == grailstestapp.Status.IN_REVIEW}">
                <input type="button" class="btn btn-success" value="Accept"
                       onclick="acceptLoan(${currentItem.id},this)">
            </g:if>
            <g:elseif test="${currentItem.status == grailstestapp.Status.ACCEPTED}">
                <g:if  test="${!currentItem.isActive}">
                    <input type="button" class="btn btn-outline-success btn-light" value="Activate"
                               onclick="activateAcceptedLoan(${currentItem.id},this)">
                </g:if>
                <g:else> - </g:else>
            </g:elseif>
            <g:else>
                -
            </g:else>
        </td>
        <td style="text-align: center">
            <g:if test="${currentItem.status == grailstestapp.Status.IN_REVIEW}">
                <input type="button" class="btn btn-danger" value="Reject"
                           onclick="rejectLoan(${currentItem.id},this)">
            </g:if>
            <g:elseif test="${currentItem.status == grailstestapp.Status.ACCEPTED}">
                -
            </g:elseif>
            <g:else>
                 -
            </g:else>
        </td>
        <td><a href="loanRequest?loanId=${currentItem.id}" class="btn btn-outline-primary">Inspect</a></td>
    </tr>
</g:each>