function fetchUsersWithOffset(page_number){
    const usersUrl = "http://localhost:8080/admin/paginatedUsers"
    const paginationNavbarUrl = "http://localhost:8080/admin/usersPaginationNavbar"
    jQuery.ajax({
        url: usersUrl,
        type: "GET",
        dataType: 'text',
        data: {page:page_number},
        success: function (result) {

            $("#usersTableBody").html(result);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            $("#emErrorMessageAmount").html(XMLHttpRequest.responseText);

        }
    });
    jQuery.ajax({
        url: paginationNavbarUrl,
        type: "GET",
        dataType: 'text',
        data: {page:page_number},
        success: function (result) {

            $("#paginationNav").html(result);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            $("#emErrorMessageAmount").html(XMLHttpRequest.responseText);

        }
    });
}
function fetchAccountsWithOffset(page_number){
    const accountsUrl = "http://localhost:8080/admin/paginatedAccounts"
    const paginationNavbarUrl = "http://localhost:8080/admin/accountsPaginationNavbar"
    jQuery.ajax({
        url: accountsUrl,
        type: "GET",
        dataType: 'text',
        data: {page:page_number},
        success: function (result) {

            $("#accountsTableBody").html(result);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            $("#emErrorMessageAmount").html(XMLHttpRequest.responseText);

        }
    });
    jQuery.ajax({
        url: paginationNavbarUrl,
        type: "GET",
        dataType: 'text',
        data: {page:page_number},
        success: function (result) {

            $("#paginationNav").html(result);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            $("#emErrorMessageAmount").html(XMLHttpRequest.responseText);

        }
    });
}
function fetchTransactionsWithOffset(page_number){
    const transactionsUrl = "http://localhost:8080/admin/paginatedTransactions"
    const paginationNavbarUrl = "http://localhost:8080/admin/transactionsPaginationNavbar"
    jQuery.ajax({
        url: transactionsUrl,
        type: "GET",
        dataType: 'text',
        data: {page:page_number},
        success: function (result) {

            $("#transactionsTableBody").html(result);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            $("#emErrorMessageAmount").html(XMLHttpRequest.responseText);

        }
    });
    jQuery.ajax({
        url: paginationNavbarUrl,
        type: "GET",
        dataType: 'text',
        data: {page:page_number},
        success: function (result) {

            $("#paginationNav").html(result);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            $("#emErrorMessageAmount").html(XMLHttpRequest.responseText);

        }
    });
}
function fetchUserAccountsWithOffset(page_number){
    const userAccountsUrl = "http://localhost:8080/account/paginatedUserAccounts"
    const paginationNavbarUrl = "http://localhost:8080/account/userAccountsPaginationNavbar"
    jQuery.ajax({
        url: userAccountsUrl,
        type: "GET",
        dataType: 'text',
        data: {page:page_number},
        success: function (result) {

            $("#userAccountsTableBody").html(result);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            $("#emErrorMessageAmount").html(XMLHttpRequest.responseText);

        }
    });
    jQuery.ajax({
        url: paginationNavbarUrl,
        type: "GET",
        dataType: 'text',
        data: {page:page_number},
        success: function (result) {

            $("#paginationNav").html(result);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            $("#emErrorMessageAmount").html(XMLHttpRequest.responseText);

        }
    });
}
function fetchUserTransactionsWithOffset(page_number){
    const userTransactionsUrl = "http://localhost:8080/transaction/paginatedUserTransactions"
    const paginationNavbarUrl = "http://localhost:8080/transaction/userTransactionsPaginationNavbar"
    jQuery.ajax({
        url: userTransactionsUrl,
        type: "GET",
        dataType: 'text',
        data: {page:page_number},
        success: function (result) {

            $("#userTransactionsTableBody").html(result);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            $("#emErrorMessageAmount").html(XMLHttpRequest.responseText);

        }
    });
    jQuery.ajax({
        url: paginationNavbarUrl,
        type: "GET",
        dataType: 'text',
        data: {page:page_number},
        success: function (result) {

            $("#paginationNav").html(result);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            $("#emErrorMessageAmount").html(XMLHttpRequest.responseText);

        }
    });
}