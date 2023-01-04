<style>
.flex-container {
    height: 100%;
    padding: 0;
    margin: 0;
    display: flex;
    align-items: center;
    justify-content: center;
}
</style>

<div class="flex-container"><nav aria-label="Page navigation example">
    <ul class="pagination">
        <li class="page-item" <g:if test="${page!=1}">onclick="fetchAccountsWithOffset(${page-1})"</g:if>><a class="page-link">Previous</a></li>
        <g:if test="${page>2}">
            <li class="page-item"><a class="page-link" onclick="fetchAccountsWithOffset(1)">1</a></li>
            <li><a class="page-link">...</a></li>
        </g:if>
        <g:if test="${page!=1}">
            <li class="page-item" onclick="fetchAccountsWithOffset(${page-1})"><a class="page-link">${page-1}</a></li>
        </g:if>
        <li class="page-item active" onclick="fetchAccountsWithOffset(${page})"><a class="page-link">${page}</a></li>
        <g:if test="${page!=count}">
            <li class="page-item" onclick="fetchAccountsWithOffset(${page+1})"><a class="page-link">${page+1}</a></li>
        </g:if>
        <g:if test="${page<count-1}">
            <li><a class="page-link">...</a></li>
            <li class="page-item"><a class="page-link" onclick="fetchAccountsWithOffset(${count})">${count}</a></li>
        </g:if>
        <li class="page-item" <g:if test="${page!=count}">onclick="fetchAccountsWithOffset(${page+1})"</g:if>><a class="page-link">Next</a></li>
    </ul>
</nav></div>