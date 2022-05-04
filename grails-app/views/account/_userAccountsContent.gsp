<g:each in="${accountList}" status="index" var="currentItem">
    <tr>
        <td>${currentItem.number}</td>
        <td>${currentItem.currency}</td>
        <td><g:formatDate format="yyyy-MM-dd HH:mm:ss" date="${currentItem.dateCreated}"/></td>
        <td><g:formatDate format="yyyy-MM-dd HH:mm:ss" date="${currentItem.lastUpdated}"/></td>
        <td>${currentItem.status}</td>
        <g:if test="${currentItem.isActive}">
            <td>Active</td>
        </g:if>
        <g:else>
            <td>Inactive</td>
        </g:else>
        <g:if test="${currentItem.status == grailstestapp.Status.ACCEPTED}">
            <td>${balanceList[index]}</td>
        </g:if>
        <g:else>
            <td></td>
        </g:else>
        <td>

            <g:form name="updateAccount" class="onboarding-form" id="updateAccount"
                    url="[controller: 'account', action: 'updatePermitted']"
                    method="POST" autocomplete="off">
                <div class="form-group" style="display: inline-flex; ">

                    <input type="hidden" name="account.id" id="account.id"
                           value="${currentItem.id}">
                    <input type="number" name="account.permittedInvestmentAmount" id="account.permittedInvestmentAmount"
                           value="${currentItem.permittedInvestmentAmount}"><br>
                    <button style="margin-left: 5px" type="submit" class="btn btn-primary">Update</button>
                </div>

            </g:form>

        </td>
        <g:if test="${currentItem.status == grailstestapp.Status.PENDING}">
            <td>
                <g:link controller="account" action="update"><input type="button" class="btn btn-info"
                                                                    onclick="accountUpdateButtonEvent('${currentItem.number}', '${currentItem.currency}', 1)"
                                                                    value="Update"></g:link>
            </td>
            <g:if test="${currentItem.isActive}">
                <td>
                    <input type="button" class="btn btn-danger" value="Deactivate"
                           onclick="deactivateAccount('${currentItem.id}', this)">
                </td>
            </g:if>
            <g:else>
                <td>
                    <input type="button" class="btn btn-success" value="Activate"
                           onclick="activateAccount('${currentItem.id}',this)">
                </td>
            </g:else>

        </g:if>

    </tr>
</g:each>