<nav aria-label="Page navigation example">
    <ul class="pagination">
        <li class="page-item" <g:if test="${page!=1}">onclick="fetchUserAccountsWithOffset(${page-1})</g:if>"><a class="page-link">Previous</a></li>
        <g:if test="${page>2}">
            <li class="page-item"><a class="page-link" onclick="fetchUserAccountsWithOffset(1)">1</a></li>
            <li><a class="page-link">...</a></li>
        </g:if>
        <g:if test="${page!=1}">
            <li class="page-item" onclick="fetchUserAccountsWithOffset(${page-1})"><a class="page-link">${page-1}</a></li>
        </g:if>
        <li class="page-item active" onclick="fetchUserAccountsWithOffset(${page})"><a class="page-link">${page}</a></li>
        <g:if test="${page!=count}">
            <li class="page-item" onclick="fetchUserAccountsWithOffset(${page+1})"><a class="page-link">${page+1}</a></li>
        </g:if>
        <g:if test="${page<count-1}">
            <li><a class="page-link">...</a></li>
            <li class="page-item"><a class="page-link" onclick="fetchUserAccountsWithOffset(${count})">${count}</a></li>
        </g:if>
        <li class="page-item" <g:if test="${page!=count}">onclick="fetchUserAccountsWithOffset(${page+1})"</g:if>><a class="page-link">Next</a></li>
    </ul>
</nav>