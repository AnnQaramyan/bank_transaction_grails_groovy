<g:each in="${usersList}" var="currentItem">
    <tr>
        <td onclick="accountsTable('${currentItem.id}', '${createLink(controller: 'admin',action: 'userAccounts')}');
        document.getElementById('myModal').setAttribute('style','display:block')">${currentItem.id}</td>
        <td>${currentItem.firstName}</td>
        <td>${currentItem.lastName}</td>
        <td>${currentItem.email}</td>
        <td><g:formatDate format="yyyy-MM-dd" date="${currentItem.birthDate}"/></td>
        <td>${currentItem.mobile}</td>
        <td>${currentItem.addressAdminModel.country},
        ${currentItem.addressAdminModel.city},
        ${currentItem.addressAdminModel.street},
        ${currentItem.addressAdminModel.houseNumber},
        ${currentItem.addressAdminModel.postalCode}
        </td>
        <td><g:formatDate format="yyyy-MM-dd HH:mm:ss" date="${currentItem.dateCreated}"/></td>
        <td><g:formatDate format="yyyy-MM-dd HH:mm:ss" date="${currentItem.lastUpdated}"/></td>
        <td>${currentItem.authorities}</td>
        <g:if test="${currentItem.isActive}">
            <td>Active</td>
            <td><input type="button" class="btn btn-danger" value="Deactivate"
                       onclick="deactivateUser('${currentItem.id}',this)"></td>
        </g:if>
        <g:else>
            <td>Inactive</td>
            <td><input type="button" class="btn btn-success" value="Activate"
                       onclick="activateUser('${currentItem.id}',this)"></td>
        </g:else>

    </tr>
</g:each>