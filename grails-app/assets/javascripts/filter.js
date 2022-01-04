function filter(search, filter_by){
    const filterUrl = "http://localhost:8080/admin/paginatedUsers"
    const paginationNavbarUrl = "http://localhost:8080/admin/usersPaginationNavbar"
    jQuery.ajax({
        url: filterUrl,
        type: "GET",
        dataType: 'text',
        data:{search:search, filter_by:filter_by},
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
        data: {page:0},
        success: function (result) {

            $("#paginationNav").html(result);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            $("#emErrorMessageAmount").html(XMLHttpRequest.responseText);

        }
    });
}