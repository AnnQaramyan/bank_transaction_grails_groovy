
<g:each in="${loanList}" status="index" var="currentItem">
        <tr>
        <td>${currentItem.id}</td>
        <td>${currentItem.amount}</td>
        <td>${currentItem.account.number}</td>
        <td>${currentItem.description}</td>
        <td>${currentItem.mortgage}</td>
        <td>${currentItem.status}</td>
        <td><g:formatDate format="yyyy-MM-dd HH:mm:ss" date="${currentItem.dueDate}"/></td>
        <td><g:formatDate format="yyyy-MM-dd HH:mm:ss" date="${currentItem.dateAccepted}"/></td>
        <g:if test="${currentItem.isActive}">
            <td>Active</td>
        </g:if>
        <g:else>
            <td>Inactive</td>
        </g:else>

        <g:if test="${currentItem.status == grailstestapp.Status.PENDING}">
          %{--  <td>
--}%%{--
                <g:link controller="account" action="update"><input type="button" class="btn btn-info" onclick="accountUpdateButtonEvent('${currentItem.number}','${currentItem.currency}',1)" value="Update"></g:link>
--}%%{--
            </td>--}%
            <g:if test="${currentItem.isActive}">
               %{-- <td>
                    <input type="button" class="btn btn-danger" value="Deactivate"
                           onclick="deactivateAccount('${currentItem.id}',this)">
                </td>--}%
            </g:if>
            <g:else>
                <td>
                    <input type="button" class="btn btn-success" value="Activate"
                           onclick="activateAccount('${currentItem.id}',this)">
                </td>
            </g:else>

        </g:if>
            <td><a href="loanDetails?loanId=${currentItem.id}">Inspect</a></td>

    </tr>
</g:each>