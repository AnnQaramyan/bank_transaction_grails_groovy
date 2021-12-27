
    <table class="table table-bordered table-hover">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Number</th>
            <th scope="col">Currency</th>
            <th scope="col">Date Created</th>
            <th scope="col">Last Updated</th>
            <th scope="col">Status</th>
        </tr>
        </thead>
        <tbody>
            <g:each in="${accountList}" var="currentAccount">
                <tr>
                    <td>${currentAccount.id}</td>
                    <td>${currentAccount.number}</td>
                    <td>${currentAccount.currency}</td>
                    <td>${currentAccount.dateCreated}</td>
                    <td>${currentAccount.lastUpdated}</td>
                    <td>${currentAccount.status}</td>
                </tr>
            </g:each>
        </tbody>
    </table>
