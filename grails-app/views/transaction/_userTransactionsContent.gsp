<g:each in="${transactionList}" status="index" var="currentItem">
    <tr>
        <td>${currentItem.id}</td>
        <td>${currentItem.type}</td>
        <td>${currentItem.amount}</td>
        <td>${currentItem.from.number},${currentItem.from.currency}</td>
        <td>${currentItem.to.number},${currentItem.to.currency}</td>
        <td><g:formatDate format="yyyy-MM-dd HH:mm:ss" date="${currentItem.dateCreated}"/></td>
        <td><g:formatDate format="yyyy-MM-dd HH:mm:ss" date="${currentItem.lastUpdated}"/></td>
        <td>${currentItem.status}</td>
        <g:if test="${currentItem.isActive}">
            <td>Active</td>
        </g:if>
        <g:else>
            <td>Inactive</td>
        </g:else>

        <g:if test="${currentItem.status == grailstestapp.Status.PENDING}">
            <td>
                <g:link controller="transaction" action="update"><input type="button" class="btn btn-info" value="Update" onclick="localStorage.setItem('transactionId','${currentItem.id}');localStorage.setItem('pageNumber','1');transactionUpdateButtonEvent('${currentItem.type}', '${currentItem.amount}', '${currentItem.from.number}','${currentItem.to.number}')"></g:link>
            </td>
            <g:if test="${currentItem.isActive}">
                <td>
                    <input type="button" class="btn btn-danger" value="Deactivate"
                           onclick="deactivateTransaction('${currentItem.id}',this)">
                </td>
            </g:if>
            <g:else>
                <td>
                    <input type="button" class="btn btn-success" value="Activate"
                           onclick="activateTransaction('${currentItem.id}',this)">
                </td>
            </g:else>
        </g:if>
    </tr>
</g:each>