
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
        <td><g:formatDate format="yyyy-MM-dd HH:mm:ss" date="${currentItem.dueDate}"/></td>
        <td>
            <g:if test="${currentItem.isActive}">ACTIVE</g:if>
            <g:else>DEACTIVE</g:else>
        </td>
        <g:if test="${currentItem.status == grailstestapp.Status.IN_REVIEW}">
            <td><input type="button" class="btn btn-success" value="Accept"
                       onclick="acceptLoan(${currentItem.id},this)"></td>
            <td><input type="button" class="btn btn-danger" value="Reject"
                       onclick="rejectLoan(${currentItem.id},this)"></td>
        </g:if>
        <g:elseif test="${currentItem.status == grailstestapp.Status.ACCEPTED}">
            <g:if  test="${!currentItem.isActive}">
                <td><input type="button" class="btn btn-light" value="Activate"
                           onclick="activateAcceptedLoan(${currentItem.id},this)"></td>
            </g:if>
            <td>   </td>
        </g:elseif>
        <g:elseif test="${currentItem.status == grailstestapp.Status.REJECTED}">
            <td> - </td>
            <td> - </td>
        </g:elseif>
        <g:elseif test="${currentItem.status == grailstestapp.Status.ACCEPTED}">
            <g:if test="${currentItem.isActive}">
                <td><input type="button" class="btn btn-secondary" value="Deactivate"
                           onclick="deactivateAcceptedLoan(${currentItem.id},this)"></td>
            </g:if>
            <td> - </td>
        </g:elseif>
        <g:else>
            <td> - </td>
            <td> - </td>
        </g:else>


        <td><a href="loanRequest?loanId=${currentItem.id}">Inspect</a></td>
        %{-- <g:if test="${currentItem.status == grailstestapp.Status.PENDING}">
         --}%%{--  <td>
 --}%%{----}%%{--
               <g:link controller="account" action="update"><input type="button" class="btn btn-info" onclick="accountUpdateButtonEvent('${currentItem.number}','${currentItem.currency}',1)" value="Update"></g:link>
 --}%%{----}%%{--
           </td>--}%%{--
             <g:if test="${currentItem.isActive}">--}%
            %{-- <td>
                 <input type="button" class="btn btn-danger" value="Deactivate"
                        onclick="deactivateAccount('${currentItem.id}',this)">
             </td>
            </g:if>--}%
          %{--  <g:else>
                <td>
                    <input type="button" class="btn btn-success" value="Activate"
                           onclick="activateAccount('${currentItem.id}',this)">
                </td>
            </g:else>

        </g:if>--}%

    </tr>
</g:each>