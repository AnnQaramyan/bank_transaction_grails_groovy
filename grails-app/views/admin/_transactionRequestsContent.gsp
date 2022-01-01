<g:each in="${transactionsList}" var="currentItem">
    <tr>
        <td>${currentItem.id}</td>
        <td>${currentItem.type}</td>
        <td>${currentItem.amount}</td>
        <td>${currentItem.toAmount}</td>
        <td>${currentItem.from.number},${currentItem.from.currency}</td>
        <td>${currentItem.to.number},${currentItem.to.currency}</td>
        <td><g:formatDate format="yyyy-MM-dd HH:mm:ss" date="${currentItem.dateCreated}"/></td>
        <td><g:formatDate format="yyyy-MM-dd HH:mm:ss" date="${currentItem.lastUpdated}"/></td>
        <td>${currentItem.status}</td>
        <g:if test="${currentItem.isActive}">
            <td>Active</td>
            <g:if test="${currentItem.status == grailstestapp.Status.PENDING}">
                <td><input type="button" class="btn btn-success" value="Accept"
                           onclick="acceptTransaction(${currentItem.id},this)"></td>
                <td><input type="button" class="btn btn-danger" value="Reject"
                           onclick="rejectTransaction(${currentItem.id},this)"></td>
            </g:if>
        </g:if>
        <g:else>
            <td>Inactive</td>
        </g:else>

    </tr>
</g:each>