<g:each in="${requestsList}" var="currentItem">
    <tr>
        <td>${currentItem.id}</td>
        <td>${currentItem.number}</td>
        <td>${currentItem.currency}</td>
        <td><g:formatDate format="yyyy-MM-dd HH:mm:ss" date="${currentItem.dateCreated}"/></td>
        <td><g:formatDate format="yyyy-MM-dd HH:mm:ss" date="${currentItem.lastUpdated}"/></td>
        <td>${currentItem.userAdminModel.id}</td>
        <td>${currentItem.status}</td>
        <g:if test="${currentItem.isActive}">
            <td>Active</td>
            <g:if test="${currentItem.status == grailstestapp.Status.PENDING}">
                <td><input type="button" class="btn btn-success" value="Accept"
                           onclick="acceptAccount(${currentItem.id},this)"></td>
                <td><input type="button" class="btn btn-danger" value="Reject"
                           onclick="rejectAccount(${currentItem.id},this)"></td>
            </g:if>
            <g:elseif test="${currentItem.status == grailstestapp.Status.ACCEPTED}">
                <td><input type="button" class="btn btn-secondary" value="Deactivate"
                           onclick="deactivateAcceptedAccount(${currentItem.id},this)"></td>
            </g:elseif>

        </g:if>
        <g:else>
            <td>Inactive</td>
            <g:if test="${currentItem.status == grailstestapp.Status.ACCEPTED}">
                <td><input type="button" class="btn btn-light" value="Activate"
                           onclick="activateAcceptedAccount(${currentItem.id},this)"></td>
            </g:if>

        </g:else>

    </tr>
</g:each>