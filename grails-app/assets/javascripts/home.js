let transactions;
let accounts;
let users;

function drawOrderedUserTable(by){
    if(by.innerHTML == 'ID'){
        users.sort((a,b)=>{
            return a.id-b.id;
        });
    }else if(by.innerHTML == 'First Name'){
        users.sort((a,b)=>{
            return a.firstName.localeCompare(b.firstName);
        });
    }else if(by.innerHTML == 'Last Name'){
        users.sort((a,b)=>{
            return a.lastName.localeCompare(b.lastName);
        });
    }else if(by.innerHTML == 'Email'){
        users.sort((a,b)=>{
            return a.email.localeCompare(b.email);
        });
    }else if(by.innerHTML == 'Birth Date'){
        users.sort((a,b)=>{
            return a.birthDate.localeCompare(b.birthDate);
        });
    }else if(by.innerHTML == 'Mobile'){
        users.sort((a,b)=>{
            return a.mobile.localeCompare(b.mobile);
        });
    }else if(by.innerHTML == 'Date Created'){
        users.sort((a,b)=>{
            return a.dateCreated.localeCompare(b.dateCreated);
        });
    }else if(by.innerHTML == 'Last Updated'){
        users.sort((a,b)=>{
            return a.lastUpdated.localeCompare(b.lastUpdated);
        });
    }else if(by.innerHTML == 'Activation Status'){
        users.sort((a,b)=>{
            return -(a.active.toString().localeCompare(b.active.toString()));
        });
    }
    let tableBody = document.getElementsByTagName('tbody')[0];
    tableBody.remove();
    let table = document.getElementById('userTable');
    let tbody = document.createElement('tbody');
    for(var i = 0; i<users.length; i++){
        let user = users[i];
        let trBody = document.createElement('tr');

        let thBody = document.createElement('th')
        thBody.setAttribute('scope','row');
        thBody.innerHTML = user.id;
        let td1 = document.createElement('td');
        td1.innerHTML = user.firstName;
        let td2 = document.createElement('td');
        td2.innerHTML = user.lastName;
        let td3 = document.createElement('td');
        td3.innerHTML = user.email;
        let td4 = document.createElement('td');
        let formattedBirthDate = user.birthDate.split('T');
        td4.innerHTML = formattedBirthDate[0];
        let td5 = document.createElement('td');
        td5.innerHTML = user.mobile;
        let td6 = document.createElement('td');
        td6.innerHTML =user.addressAdminModel.country+", "
            + user.addressAdminModel.city + ", "
            + user.addressAdminModel.street + ", "
            + user.addressAdminModel.houseNumber + ", "
            + user.addressAdminModel.postalCode;
        let td7 = document.createElement('td');
        let formattedDateCreated = user.dateCreated.split('T');
        let formattedTimeCreated = formattedDateCreated[1].split('.');
        td7.innerHTML = formattedDateCreated[0] + " " + formattedTimeCreated[0];
        let td8 = document.createElement('td');
        let formattedLastUpdated = user.lastUpdated.split('T');
        let formattedTimeLastUpdated = formattedLastUpdated[1].split('.');
        td8.innerHTML = formattedLastUpdated[0] + " " + formattedTimeLastUpdated[0];
        let td9 = document.createElement('td');
        td9.innerHTML = user.authorities;
        let td10 = document.createElement('td');
        if(user.active == true){
            td10.innerHTML = 'Active';
        }
        else{
            td10.innerHTML = 'Inactive';
        }
        let td11 = document.createElement('td');
        let activationChanger = document.createElement('input');
        activationChanger.setAttribute('type','button');
        if(user.active == true){
            activationChanger.setAttribute('class','btn btn-danger');
            activationChanger.setAttribute('value', 'Deactivate');
            activationChanger.setAttribute('onclick',`deactivateUser(${user.id},this)`);
        }else{
            activationChanger.setAttribute('class','btn btn-success');
            activationChanger.setAttribute('value', 'Activate');
            activationChanger.setAttribute('onclick',`activateUser(${user.id},this)`);
        }
        td11.appendChild(activationChanger);
        trBody.appendChild(thBody);
        trBody.appendChild(td1);
        trBody.appendChild(td2);
        trBody.appendChild(td3);
        trBody.appendChild(td4);
        trBody.appendChild(td5);
        trBody.appendChild(td6);
        trBody.appendChild(td7);
        trBody.appendChild(td8);
        trBody.appendChild(td9);
        trBody.appendChild(td10);
        trBody.appendChild(td11);
        thBody.onclick = async()=>{
            let table;
            let modalMain = document.getElementById('myModal');
            let myModal = document.getElementById('myCont');
            let accUserId = trBody.children[0].innerHTML;
            const url = `http://localhost:8079/accounts/${accUserId}`;
            let token = localStorage.getItem("token");
            const response = await fetch(url, {
                method: 'GET',
                headers: {
                    'Authorization': token,
                    'Access-Control-Allow-Origin':'*'
                } // body data type must match "Content-Type" header
            }).then(res=>{
                if(res.status==200){
                    let tables = document.getElementsByTagName('table');
                    if(tables.length==2){
                        tables[1].remove();
                    }
                    let colNames =
                        ["ID","Number",
                            "Currency","Date Created",
                            "Last Updated","Status"];
                    table = document.createElement('table');
                    let thead = document.createElement('thead');
                    let tr = document.createElement('tr');

                    for(var i = 0;i<6;i++){
                        let th = document.createElement('th');
                        th.setAttribute('scope','col');
                        th.innerHTML = colNames[i];
                        tr.appendChild(th);
                    }
                    thead.appendChild(tr);
                    table.setAttribute('class','table table-bordered table-hover');
                    table.setAttribute('id','adminSideAccountsTable');
                    table.appendChild(thead);
                    myModal.appendChild(table);
                    return res.json();
                }
            }).then(data=>{
                let tbody = document.createElement('tbody');
                for(var j = 0; j<data.length;j++){
                    let trBody = document.createElement('tr');
                    let acc = data[j];
                    let thBody = document.createElement('th')
                    thBody.setAttribute('scope','row');
                    thBody.innerHTML = acc.id;
                    let td1 = document.createElement('td');
                    td1.innerHTML = acc.number;
                    let td2 = document.createElement('td');
                    td2.innerHTML = acc.currency;
                    let td3 = document.createElement('td');
                    let formattedDateCreated = acc.dateCreated.split('T');
                    let formattedTimeCreated = formattedDateCreated[1].split('.');
                    td3.innerHTML = formattedDateCreated[0] + " " + formattedTimeCreated[0];
                    let td4 = document.createElement('td');
                    let formattedLastUpdated = acc.lastUpdated.split('T');
                    let formattedTimeLastUpdated = formattedLastUpdated[1].split('.');
                    td4.innerHTML = formattedLastUpdated[0] + " " + formattedTimeLastUpdated[0];
                    let td5 = document.createElement('td');
                    td5.innerHTML = acc.status;
                    trBody.appendChild(thBody);
                    trBody.appendChild(td1);
                    trBody.appendChild(td2);
                    trBody.appendChild(td3);
                    trBody.appendChild(td4);
                    trBody.appendChild(td5);
                    tbody.appendChild(trBody);
                    table.appendChild(tbody);
                }
            });
            modalMain.style.display = "block";
        }
        tbody.appendChild(trBody);
        table.appendChild(tbody);
    }
}
// async function accountsTable(accUserId){
//     let table;
//     let modalMain = document.getElementById('myModal');
//     let myModal = document.getElementById('myCont');
//     const url = `http://localhost:8080/admin/userAccounts?id=${accUserId}`;
//     const response = await fetch(url, {
//         method: 'GET',
//         headers: {
//             'Access-Control-Allow-Origin':'*'
//         } // body data type must match "Content-Type" header
//     }).then(res=>{
//         if(res.status==200){
//             let tables = document.getElementsByTagName('table');
//             if(tables.length==2){
//                 tables[1].remove();
//             }
//             let colNames =
//                 ["ID","Number",
//                     "Currency","Date Created",
//                     "Last Updated","Status"];
//             table = document.createElement('table');
//             let thead = document.createElement('thead');
//             let tr = document.createElement('tr');
//
//             for(let i = 0;i<6;i++){
//                 let th = document.createElement('th');
//                 th.setAttribute('scope','col');
//                 th.innerHTML = colNames[i];
//                 tr.appendChild(th);
//             }
//             thead.appendChild(tr);
//             table.setAttribute('class','table table-bordered table-hover');
//             table.setAttribute('id','adminSideAccountsTable');
//             table.appendChild(thead);
//             myModal.appendChild(table);
//             return res.json();
//         }
//     }).then(data=>{
//         let tbody = document.createElement('tbody');
//         for(var j = 0; j<data.length;j++){
//             let trBody = document.createElement('tr');
//             let acc = data[j];
//             let thBody = document.createElement('th')
//             thBody.setAttribute('scope','row');
//             thBody.innerHTML = acc.id;
//             let td1 = document.createElement('td');
//             td1.innerHTML = acc.number;
//             let td2 = document.createElement('td');
//             td2.innerHTML = acc.currency;
//             let td3 = document.createElement('td');
//             let formattedDateCreated = acc.dateCreated.split('T');
//             let formattedTimeCreated = formattedDateCreated[1].split('.');
//             td3.innerHTML = formattedDateCreated[0] + " " + formattedTimeCreated[0];
//             let td4 = document.createElement('td');
//             let formattedLastUpdated = acc.lastUpdated.split('T');
//             let formattedTimeLastUpdated = formattedLastUpdated[1].split('.');
//             td4.innerHTML = formattedLastUpdated[0] + " " + formattedTimeLastUpdated[0];
//             let td5 = document.createElement('td');
//             td5.innerHTML = acc.status;
//             trBody.appendChild(thBody);
//             trBody.appendChild(td1);
//             trBody.appendChild(td2);
//             trBody.appendChild(td3);
//             trBody.appendChild(td4);
//             trBody.appendChild(td5);
//             tbody.appendChild(trBody);
//             table.appendChild(tbody);
//         }
//     });
//     modalMain.style.display = "block";
// }

function accountsTable(accUserId, accountsUrl){

    jQuery.ajax({
        url: accountsUrl,
        type: "GET",
        dataType: 'text',
        data: {id:accUserId},
        success: function (result) {

            $("#tableCont").html(result);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            $("#emErrorMessageAmount").html(XMLHttpRequest.responseText);

        }
    });


}

// async function fetchUsers(){
//     let table;
//     let mainDiv = document.getElementById('mainDiv');
//     mainDiv.innerHTML = "";
//     mainDiv.setAttribute('style','width:100%');
//     const url = 'http://localhost:8079/user';
//     let token = localStorage.getItem("token");
//     const response = await fetch(url, {
//         method: 'GET',
//         headers: {
//             'Authorization': token,
//             'Access-Control-Allow-Origin':'*'
//         } // body data type must match "Content-Type" header
//     }).then(res=>{
//         if(res.status==200){
//             //console.log(res.json());
//             let colNames =
//                 ["ID","First Name",
//                     "Last Name","Email","Birth Date",
//                     "Mobile","Address",
//                     "Date Created","Last Updated","Authorities","Activation Status"];
//
//             table = document.createElement('table');
//             let thead = document.createElement('thead');
//             let tr = document.createElement('tr');
//
//             for(var i = 0;i<11;i++){
//                 let th = document.createElement('th');
//                 th.setAttribute('scope','col');
//                 th.setAttribute('onclick', 'drawOrderedUserTable(this)');
//                 th.innerHTML = colNames[i];
//                 tr.appendChild(th);
//             }
//             thead.appendChild(tr);
//             table.setAttribute('class','table table-bordered table-hover');
//             table.setAttribute('id','userTable');
//             table.appendChild(thead);
//             mainDiv.appendChild(table);
//             return res.json();
//         }
//     }).then(data=>
//     {
//         users=data;
//         let tbody = document.createElement('tbody');
//         for(var i = 0; i<data.length; i++){
//             let user = data[i];
//             let trBody = document.createElement('tr');
//
//             let thBody = document.createElement('th')
//             thBody.setAttribute('scope','row');
//             thBody.innerHTML = user.id;
//             let td1 = document.createElement('td');
//             td1.innerHTML = user.firstName;
//             let td2 = document.createElement('td');
//             td2.innerHTML = user.lastName;
//             let td3 = document.createElement('td');
//             td3.innerHTML = user.email;
//             let td4 = document.createElement('td');
//             let formattedBirthDate = user.birthDate.split('T');
//             td4.innerHTML = formattedBirthDate[0];
//             let td5 = document.createElement('td');
//             td5.innerHTML = user.mobile;
//             let td6 = document.createElement('td');
//             td6.innerHTML =user.addressAdminModel.country+", "
//                 + user.addressAdminModel.city + ", "
//                 + user.addressAdminModel.street + ", "
//                 + user.addressAdminModel.houseNumber + ", "
//                 + user.addressAdminModel.postalCode;
//             let td7 = document.createElement('td');
//             let formattedDateCreated = user.dateCreated.split('T');
//             let formattedTimeCreated = formattedDateCreated[1].split('.');
//             td7.innerHTML = formattedDateCreated[0] + " " + formattedTimeCreated[0];
//             let td8 = document.createElement('td');
//             let formattedLastUpdated = user.lastUpdated.split('T');
//             let formattedTimeLastUpdated = formattedLastUpdated[1].split('.');
//             td8.innerHTML = formattedLastUpdated[0] + " " + formattedTimeLastUpdated[0];
//             let td9 = document.createElement('td');
//             td9.innerHTML = user.authorities;
//             let td10 = document.createElement('td');
//             if(user.active == true){
//                 td10.innerHTML = 'Active';
//             }
//             else{
//                 td10.innerHTML = 'Inactive';
//             }
//             let td11 = document.createElement('td');
//             let activationChanger = document.createElement('input');
//             activationChanger.setAttribute('type','button');
//             if(user.active == true){
//                 activationChanger.setAttribute('class','btn btn-danger');
//                 activationChanger.setAttribute('value', 'Deactivate');
//                 activationChanger.setAttribute('onclick',`deactivateUser(${user.id},this)`);
//             }else{
//                 activationChanger.setAttribute('class','btn btn-success');
//                 activationChanger.setAttribute('value', 'Activate');
//                 activationChanger.setAttribute('onclick',`activateUser(${user.id},this)`);
//             }
//             td11.appendChild(activationChanger);
//             trBody.appendChild(thBody);
//             trBody.appendChild(td1);
//             trBody.appendChild(td2);
//             trBody.appendChild(td3);
//             trBody.appendChild(td4);
//             trBody.appendChild(td5);
//             trBody.appendChild(td6);
//             trBody.appendChild(td7);
//             trBody.appendChild(td8);
//             trBody.appendChild(td9);
//             trBody.appendChild(td10);
//             trBody.appendChild(td11);
//             thBody.onclick = async()=>{
//
//             }
//             tbody.appendChild(trBody);
//             table.appendChild(tbody);
//         }
//     });
//
// }
async function deactivateUser(user_id,bt){
    const url = `http://localhost:8080/admin/deactivateUser?id=${user_id}`
    const response = await fetch(url, {
        method: 'PUT',
        headers: {
            'Access-Control-Allow-Origin':'*'
        } // body data type must match "Content-Type" header
    }).then(res=>{
        if(res.status==200){
            let parTr = bt.parentElement.parentElement;
            let activationTd = parTr.children[10];
            activationTd.innerHTML = 'Inactive';
            bt.setAttribute('class','btn btn-success');
            bt.setAttribute('value', 'Activate');
            bt.setAttribute('onclick', `activateUser(${user_id},this)`);
            let lUpd = parTr.children[8];
            let now = new Date()
            lUpd.innerHTML=now.getFullYear() + '-' + (now.getMonth()+1) + '-' + now.getDate()
                + ' ' + now.getHours() + ":"
                + now.getMinutes() + ":" + now.getSeconds();
            return res.json();
        }
    });
}
async function activateUser(user_id,bt){
    const url = `http://localhost:8080/admin/activateUser?id=${user_id}`
    //let token = localStorage.getItem("token");
    const response = await fetch(url, {
        method: 'PUT',
        headers: {
            //'Authorization': token,
            'Access-Control-Allow-Origin':'*'
        } // body data type must match "Content-Type" header
    }).then(res=>{
        if(res.status==200){
            let parTr = bt.parentElement.parentElement;
            let activationTd = parTr.children[10];
            activationTd.innerHTML = 'Active';
            bt.setAttribute('class','btn btn-danger');
            bt.setAttribute('value', 'Deactivate');
            bt.setAttribute('onclick', `deactivateUser(${user_id},this)`);
            let lUpd = parTr.children[8];
            let now = new Date()
            lUpd.innerHTML=now.getFullYear() + '-' + (now.getMonth()+1) + '-' + now.getDate()
                + ' ' + now.getHours() + ":"
                + now.getMinutes() + ":" + now.getSeconds();
            return res.json();
        }
    });
}

function drawOrderedAccountsTable(by){
    if(by.innerHTML == 'ID'){
        accounts.sort((a,b)=>{
            return a.id-b.id;
        });
    }else if(by.innerHTML == 'Number'){
        accounts.sort((a,b)=>{
            return a.number.localeCompare(b.number);
        });
    }else if(by.innerHTML == 'Currency'){
        accounts.sort((a,b)=>{
            return a.currency.localeCompare(b.currency);
        });
    }else if(by.innerHTML == 'Date Created'){
        accounts.sort((a,b)=>{
            return a.dateCreated.localeCompare(b.dateCreated);
        });
    }else if(by.innerHTML == 'Last Updated'){
        accounts.sort((a,b)=>{
            return a.lastUpdated.localeCompare(b.lastUpdated);
        });
    }else if(by.innerHTML == 'User ID'){
        accounts.sort((a,b)=>{
            return a.userAdminModel.id - b.userAdminModel.id;
        });
    }else if(by.innerHTML == 'Status'){
        accounts.sort((a,b)=>{
            return a.status.localeCompare(b.status);
        });
    }else if(by.innerHTML == 'Activation Status'){
        accounts.sort((a,b)=>{
            return -(a.active.toString().localeCompare(b.active.toString()));
        });
    }
    let tableBody = document.getElementsByTagName('tbody')[0];
    tableBody.remove();
    let table = document.getElementById('accountRequestsTable');

    let tbody = document.createElement('tbody');
    for(var i = 0; i<accounts.length; i++){
        let account = accounts[i];
        let trBody = document.createElement('tr');
        let thBody = document.createElement('th')
        thBody.setAttribute('scope','row');
        thBody.innerHTML = account.id;
        let td1 = document.createElement('td');
        td1.innerHTML = account.number;
        let td2 = document.createElement('td');
        td2.innerHTML = account.currency;
        let td3 = document.createElement('td');
        let formattedDateCreated = account.dateCreated.split('T');
        let formattedTimeCreated = formattedDateCreated[1].split('.');
        td3.innerHTML = formattedDateCreated[0] + " " + formattedTimeCreated[0];
        let td4 = document.createElement('td');
        let formattedLastUpdated = account.lastUpdated.split('T');
        let formattedTimeLastUpdated = formattedLastUpdated[1].split('.');
        td4.innerHTML = formattedLastUpdated[0]+ " " +formattedTimeLastUpdated[0];
        let td5 = document.createElement('td');
        td5.innerHTML = account.userAdminModel.id;
        let td6 = document.createElement('td');
        td6.innerHTML = account.status;
        let td7 = document.createElement('td');
        if(account.active==true){
            td7.innerHTML = 'Active';
        }else{
            td7.innerHTML = 'Inactive';
        }

        trBody.appendChild(thBody);
        trBody.appendChild(td1);
        trBody.appendChild(td2);
        trBody.appendChild(td3);
        trBody.appendChild(td4);
        trBody.appendChild(td5);
        trBody.appendChild(td6);
        trBody.appendChild(td7);
        if(account.status == 'PENDING' && account.active==true){
            let td8 = document.createElement('td');
            let acceptButton = document.createElement('input');
            acceptButton.setAttribute('type','button');
            acceptButton.setAttribute('class','btn btn-success');
            acceptButton.setAttribute('value', 'Accept');
            acceptButton.setAttribute('onclick', `acceptAccount(${account.id},this)`);
            td8.appendChild(acceptButton);
            let td9 = document.createElement('td');
            let rejectButton = document.createElement('input');
            rejectButton.setAttribute('type','button');
            rejectButton.setAttribute('class','btn btn-danger');
            rejectButton.setAttribute('value', 'Reject');
            rejectButton.setAttribute('onclick',`rejectAccount(${account.id},this)`);
            td9.appendChild(rejectButton);
            trBody.appendChild(td8);
            trBody.appendChild(td9);
        }else if(account.status == 'ACCEPTED'){
            let td8 = document.createElement('td');
            if(account.active == true){
                let accDeactBt = document.createElement('input');
                accDeactBt.setAttribute('type','button');
                accDeactBt.setAttribute('class','btn btn-secondary');
                accDeactBt.setAttribute('value','Deactivate');
                accDeactBt.setAttribute('onclick',`deactivateAcceptedAccount(${account.id},this)`);
                td8.appendChild(accDeactBt);
            }else{
                let accActBt = document.createElement('input');
                accActBt.setAttribute('type','button');
                accActBt.setAttribute('class','btn btn-light');
                accActBt.setAttribute('value','Activate');
                accActBt.setAttribute('onclick',`activateAcceptedAccount(${account.id},this)`);
                td8.appendChild(accActBt);
            }
            trBody.appendChild(td8);
        }
        tbody.appendChild(trBody);
        table.appendChild(tbody);
    }

}
async function fetchAccountRequests(){
    let mainDiv = document.getElementById('mainDiv');
    mainDiv.setAttribute('style','width:100%');
    mainDiv.innerHTML="";
    const url = 'http://localhost:8079/account';
    let token = localStorage.getItem("token");
    const response = await fetch(url, {
        method: 'GET',
        headers: {
            'Authorization': token,
            'Access-Control-Allow-Origin':'*'
        } // body data type must match "Content-Type" header
    }).then(res=>{
        if(res.status==200){
            let colNames =
                ["ID","Number",
                    "Currency","Date Created",
                    "Last Updated","User ID","Status", "Activation Status"];
            table = document.createElement('table');
            let thead = document.createElement('thead');
            let tr = document.createElement('tr');

            for(var i = 0;i<8;i++){
                let th = document.createElement('th');
                th.setAttribute('scope','col');

                //trial
                th.setAttribute('onclick',`drawOrderedAccountsTable(this)`);
                //

                th.innerHTML = colNames[i];
                tr.appendChild(th);
            }
            thead.appendChild(tr);
            table.setAttribute('class','table table-bordered table-hover');
            table.setAttribute('id','accountRequestsTable');
            table.appendChild(thead);
            mainDiv.appendChild(table);
            return res.json();
        }
    }).then(data=>
    {
        //trial
        accounts = data;
        //console.log(accounts);
        //


        let tbody = document.createElement('tbody');
        for(var i = 0; i<data.length; i++){
            let account = data[i];
            let trBody = document.createElement('tr');
            let thBody = document.createElement('th')
            thBody.setAttribute('scope','row');
            thBody.innerHTML = account.id;
            let td1 = document.createElement('td');
            td1.innerHTML = account.number;
            let td2 = document.createElement('td');
            td2.innerHTML = account.currency;
            let td3 = document.createElement('td');
            let formattedDateCreated = account.dateCreated.split('T');
            let formattedTimeCreated = formattedDateCreated[1].split('.');
            td3.innerHTML = formattedDateCreated[0] + " " + formattedTimeCreated[0];
            let td4 = document.createElement('td');
            let formattedLastUpdated = account.lastUpdated.split('T');
            let formattedTimeLastUpdated = formattedLastUpdated[1].split('.');
            td4.innerHTML = formattedLastUpdated[0]+ " " +formattedTimeLastUpdated[0];
            let td5 = document.createElement('td');
            td5.innerHTML = account.userAdminModel.id;
            let td6 = document.createElement('td');
            td6.innerHTML = account.status;
            let td7 = document.createElement('td');
            if(account.active==true){
                td7.innerHTML = 'Active';
            }else{
                td7.innerHTML = 'Inactive';
            }

            trBody.appendChild(thBody);
            trBody.appendChild(td1);
            trBody.appendChild(td2);
            trBody.appendChild(td3);
            trBody.appendChild(td4);
            trBody.appendChild(td5);
            trBody.appendChild(td6);
            trBody.appendChild(td7);
            if(account.status == 'PENDING' && account.active==true){
                let td8 = document.createElement('td');
                let acceptButton = document.createElement('input');
                acceptButton.setAttribute('type','button');
                acceptButton.setAttribute('class','btn btn-success');
                acceptButton.setAttribute('value', 'Accept');
                acceptButton.setAttribute('onclick', `acceptAccount(${account.id},this)`);
                td8.appendChild(acceptButton);
                let td9 = document.createElement('td');
                let rejectButton = document.createElement('input');
                rejectButton.setAttribute('type','button');
                rejectButton.setAttribute('class','btn btn-danger');
                rejectButton.setAttribute('value', 'Reject');
                rejectButton.setAttribute('onclick',`rejectAccount(${account.id},this)`);
                td9.appendChild(rejectButton);
                trBody.appendChild(td8);
                trBody.appendChild(td9);
            }else if(account.status == 'ACCEPTED'){
                let td8 = document.createElement('td');
                if(account.active == true){
                    let accDeactBt = document.createElement('input');
                    accDeactBt.setAttribute('type','button');
                    accDeactBt.setAttribute('class','btn btn-secondary');
                    accDeactBt.setAttribute('value','Deactivate');
                    accDeactBt.setAttribute('onclick',`deactivateAcceptedAccount(${account.id},this)`);
                    td8.appendChild(accDeactBt);
                }else{
                    let accActBt = document.createElement('input');
                    accActBt.setAttribute('type','button');
                    accActBt.setAttribute('class','btn btn-light');
                    accActBt.setAttribute('value','Activate');
                    accActBt.setAttribute('onclick',`activateAcceptedAccount(${account.id},this)`);
                    td8.appendChild(accActBt);
                }
                trBody.appendChild(td8);
            }
            tbody.appendChild(trBody);
            table.appendChild(tbody);
        }
    });
    //console.log(response.json());
}
async function deactivateAcceptedLoan(loan_id,bt){
    const url = `http://localhost:8080/admin/deactivateLoan?id=${loan_id}`;
    const response = await fetch(url, {
        method: 'PUT',
        headers: {
            'Access-Control-Allow-Origin':'*'
        } // body data type must match "Content-Type" header
    }).then(res=>{
        if(res.status==200){
            let parTr = bt.parentElement.parentElement;
            let deactivationTd = parTr.children[7];
            deactivationTd.innerHTML = 'Inactive';
            bt.setAttribute('class','btn btn-light');
            bt.setAttribute('value', 'Activate');
            bt.setAttribute('onclick', `activateAcceptedLoan(${loan_id},this)`);
            let lUpd = parTr.children[4];
            let now = new Date()
            lUpd.innerHTML=now.getFullYear() + '-' + (now.getMonth()+1) + '-' + now.getDate()
                + ' ' + now.getHours() + ":"
                + now.getMinutes() + ":" + now.getSeconds();
        }
    });
}
async function activateAcceptedLoan(loan_id,bt) {
    const url = `http://localhost:8080/admin/activateLoan?id=${loan_id}`;
    const response = await fetch(url, {
        method: 'PUT',
        headers: {
            'Access-Control-Allow-Origin':'*'
        } // body data type must match "Content-Type" header
    }).then(res=>{
        if(res.status==200){
            let parTr = bt.parentElement.parentElement;
            let deactivationTd = parTr.children[7];
            deactivationTd.innerHTML = 'Active';
            bt.setAttribute('class','btn btn-secondary');
            bt.setAttribute('value', 'Deactivate');
            let lUpd = parTr.children[4];
            let now = new Date()
            lUpd.innerHTML=now.getFullYear() + '-' + (now.getMonth()+1) + '-' + now.getDate()
                + ' ' + now.getHours() + ":"
                + now.getMinutes() + ":" + now.getSeconds();
        }
    });
} // TODO
async function acceptLoan(loan_id, bt) {
    const url = `http://localhost:8080/admin/acceptLoan?id=${loan_id}`;
    const response = await fetch(url, {
        method: 'PUT',
        headers: {
            'Access-Control-Allow-Origin':'*'
        } // body data type must match "Content-Type" header
    }).then(res=>{
        if(res.status==200){
            let parentTr = bt.parentElement.parentElement;
            parentTr.children[6].innerHTML = 'ACCEPTED';
            let nextBt = parentTr.children[9].children[0];
            let lUpd = parentTr.children[4];
            bt.remove();
            nextBt.remove();
            let actStatBt = document.createElement('input');
            actStatBt.setAttribute('type','button');
            actStatBt.setAttribute('class','btn btn-secondary');
            actStatBt.setAttribute('value','Deactivate');
            actStatBt.setAttribute('onclick',`deactivateAcceptedLoan(${loan_id},this)`);
            parentTr.children[8].appendChild(actStatBt);
            let now = new Date()
            lUpd.innerHTML=now.getFullYear() + '-' + (now.getMonth()+1) + '-' + now.getDate()
                + ' ' + now.getHours() + ":"
                + now.getMinutes() + ":" + now.getSeconds();
        }
    });
} // TODO
async function rejectLoan(loan_id, bt) {
    const url = `http://localhost:8080/admin/rejectLoan?id=${loan_id}`;
    const response = await fetch(url, {
        method: 'PUT',
        headers: {
            'Access-Control-Allow-Origin':'*'
        } // body data type must match "Content-Type" header
    }).then(res=>{
        if(res.status==200){
            let parentTr = bt.parentElement.parentElement;
            parentTr.children[6].innerHTML = 'REJECTED';
            parentTr.children[7].innerHTML = 'Inactive';
            let nextBt = parentTr.children[8].children[0];
            let lUpd = parentTr.children[4];
            bt.remove();
            nextBt.remove();
            let now = new Date()
            lUpd.innerHTML=now.getFullYear() + '-' + (now.getMonth()+1) + '-' + now.getDate()
                + ' ' + now.getHours() + ":"
                + now.getMinutes() + ":" + now.getSeconds();
        }
    });
} // TODO implement backend

async function deactivateAcceptedAccount(acc_id,bt){
    const url = `http://localhost:8080/account/deactivate?id=${acc_id}`;
    const response = await fetch(url, {
        method: 'PUT',
        headers: {
            'Access-Control-Allow-Origin':'*'
        } // body data type must match "Content-Type" header
    }).then(res=>{
        if(res.status==200){
            let parTr = bt.parentElement.parentElement;
            let deactivationTd = parTr.children[7];
            deactivationTd.innerHTML = 'Inactive';
            bt.setAttribute('class','btn btn-light');
            bt.setAttribute('value', 'Activate');
            bt.setAttribute('onclick', `activateAcceptedAccount(${acc_id},this)`);
            let lUpd = parTr.children[4];
            let now = new Date()
            lUpd.innerHTML=now.getFullYear() + '-' + (now.getMonth()+1) + '-' + now.getDate()
                + ' ' + now.getHours() + ":"
                + now.getMinutes() + ":" + now.getSeconds();
        }
    });
}
async function activateAcceptedAccount(acc_id,bt) {
    const url = `http://localhost:8080/account/activate?id=${acc_id}`;
    const response = await fetch(url, {
        method: 'PUT',
        headers: {
            'Access-Control-Allow-Origin':'*'
        } // body data type must match "Content-Type" header
    }).then(res=>{
        if(res.status==200){
            let parTr = bt.parentElement.parentElement;
            let deactivationTd = parTr.children[7];
            deactivationTd.innerHTML = 'Active';
            bt.setAttribute('class','btn btn-secondary');
            bt.setAttribute('value', 'Deactivate');
            bt.setAttribute('onclick', `deactivateAcceptedAccount(${acc_id},this)`);
            let lUpd = parTr.children[4];
            let now = new Date()
            lUpd.innerHTML=now.getFullYear() + '-' + (now.getMonth()+1) + '-' + now.getDate()
                + ' ' + now.getHours() + ":"
                + now.getMinutes() + ":" + now.getSeconds();
        }
    });
}
async function acceptAccount(acc_id, bt) {
    const url = `http://localhost:8080/admin/acceptAccount?id=${acc_id}`;
    const response = await fetch(url, {
        method: 'PUT',
        headers: {
            'Access-Control-Allow-Origin':'*'
        } // body data type must match "Content-Type" header
    }).then(res=>{
        if(res.status==200){
            let parentTr = bt.parentElement.parentElement;
            parentTr.children[6].innerHTML = 'ACCEPTED';
            let nextBt = parentTr.children[9].children[0];
            let lUpd = parentTr.children[4];
            bt.remove();
            nextBt.remove();
            let actStatBt = document.createElement('input');
            actStatBt.setAttribute('type','button');
            actStatBt.setAttribute('class','btn btn-secondary');
            actStatBt.setAttribute('value','Deactivate');
            actStatBt.setAttribute('onclick',`deactivateAcceptedAccount(${acc_id},this)`);
            parentTr.children[8].appendChild(actStatBt);
            let now = new Date()
            lUpd.innerHTML=now.getFullYear() + '-' + (now.getMonth()+1) + '-' + now.getDate()
                + ' ' + now.getHours() + ":"
                + now.getMinutes() + ":" + now.getSeconds();
        }
    });
}
async function rejectAccount(acc_id, bt) {
    const url = `http://localhost:8080/admin/rejectAccount?id=${acc_id}`;
    const response = await fetch(url, {
        method: 'PUT',
        headers: {
            'Access-Control-Allow-Origin':'*'
        } // body data type must match "Content-Type" header
    }).then(res=>{
        if(res.status==200){
            let parentTr = bt.parentElement.parentElement;
            parentTr.children[6].innerHTML = 'REJECTED';
            parentTr.children[7].innerHTML = 'Inactive';
            let nextBt = parentTr.children[8].children[0];
            let lUpd = parentTr.children[4];
            bt.remove();
            nextBt.remove();
            let now = new Date()
            lUpd.innerHTML=now.getFullYear() + '-' + (now.getMonth()+1) + '-' + now.getDate()
                + ' ' + now.getHours() + ":"
                + now.getMinutes() + ":" + now.getSeconds();
        }
    });
}
function drawOrderedTransactionsTable(by){
    if(by.innerHTML == 'ID'){
        transactions.sort((a,b)=>{
            return a.id-b.id;
        });
    }else if(by.innerHTML == 'Type'){
        transactions.sort((a,b)=>{
            return a.type.localeCompare(b.type);
        });
    }else if(by.innerHTML == 'Amount'){
        transactions.sort((a,b)=>{
            return a.amount-b.amount;
        });
    }else if(by.innerHTML == 'From'){
        transactions.sort((a,b)=>{
            return a.from.number.localeCompare(b.from.number);
        });
    }else if(by.innerHTML == 'To'){
        transactions.sort((a,b)=>{
            return a.to.number.localeCompare(b.to.number);
        });
    }else if(by.innerHTML == 'Date Created'){
        transactions.sort((a,b)=>{
            return a.dateCreated.localeCompare(b.dateCreated);
        });
    }else if(by.innerHTML == 'Last Updated'){
        transactions.sort((a,b)=>{
            return a.lastUpdated.localeCompare(b.lastUpdated);
        });
    }else if(by.innerHTML == 'Status'){
        transactions.sort((a,b)=>{
            return a.status.localeCompare(b.status);
        });
    }else if(by.innerHTML == 'Activation Status'){
        transactions.sort((a,b)=>{
            return -(a.active.toString().localeCompare(b.active.toString()));
        });
    }
    let tableBody = document.getElementsByTagName('tbody')[0];
    tableBody.remove();
    let table = document.getElementById('allTransactionsTable');
    let tbody = document.createElement('tbody');
    for(var i = 0; i<transactions.length; i++){
        let transaction = transactions[i];
        let trBody = document.createElement('tr');
        let thBody = document.createElement('th');
        thBody.setAttribute('scope','row');
        thBody.innerHTML = transaction.id;
        let td1 = document.createElement('td');
        td1.innerHTML = transaction.type;
        let td2 = document.createElement('td');
        td2.innerHTML = transaction.amount;
        let tdToAm = document.createElement('td');
        tdToAm.innerHTML = transaction.toAmount;
        let td3 = document.createElement('td');
        td3.innerHTML = transaction.from.number;
        let td4 = document.createElement('td');
        td4.innerHTML = transaction.to.number;
        let td5 = document.createElement('td');
        let formattedDateCreated = transaction.dateCreated.split('T');
        let formattedTimeCreated = formattedDateCreated[1].split('.');
        td5.innerHTML = formattedDateCreated[0] + " " + formattedTimeCreated[0];
        let td6 = document.createElement('td');
        let formattedLastUpdated = transaction.lastUpdated.split('T');
        let formattedTimeLastUpdated = formattedLastUpdated[1].split('.');
        td6.innerHTML = formattedLastUpdated[0]+ " " +formattedTimeLastUpdated[0];
        let td7 = document.createElement('td');
        td7.innerHTML = transaction.status;
        let td8 = document.createElement('td');
        if(transaction.active==true){
            td8.innerHTML = 'Active';
        }else{
            td8.innerHTML = 'Inactive';
        }

        trBody.appendChild(thBody);
        trBody.appendChild(td1);
        trBody.appendChild(td2);
        trBody.appendChild(tdToAm);
        trBody.appendChild(td3);
        trBody.appendChild(td4);
        trBody.appendChild(td5);
        trBody.appendChild(td6);
        trBody.appendChild(td7);
        trBody.appendChild(td8);
        if(transaction.status=="PENDING" && transaction.active==true){
            let td9 = document.createElement('td');
            let acceptButton = document.createElement('input');
            acceptButton.setAttribute('type','button');
            acceptButton.setAttribute('class','btn btn-success');
            acceptButton.setAttribute('value', 'Accept');
            acceptButton.setAttribute('onclick', `acceptTransaction(${transaction.id},this)`);
            td9.appendChild(acceptButton);
            let td10 = document.createElement('td');
            let rejectButton = document.createElement('input');
            rejectButton.setAttribute('type','button');
            rejectButton.setAttribute('class','btn btn-danger');
            rejectButton.setAttribute('value', 'Reject');
            rejectButton.setAttribute('onclick',`rejectTransaction(${transaction.id},this)`);
            td10.appendChild(rejectButton);
            trBody.appendChild(td9);
            trBody.appendChild(td10);
        }
        tbody.appendChild(trBody);
        table.appendChild(tbody);
    }
}
async function fetchAllTransactions(){
    let table;
    let mainDiv = document.getElementById('mainDiv');
    mainDiv.innerHTML="";
    const url = 'http://localhost:8079/transaction/all';
    let token = localStorage.getItem("token");
    const response = await fetch(url, {
        method: 'GET',
        headers: {
            'Authorization': token,
            'Access-Control-Allow-Origin':'*'
        } // body data type must match "Content-Type" header
    }).then(res=>{
        if(res.status==200){
            let colNames =
                ["ID",
                    "Type","Amount","To Amount","From","To","Date Created",
                    "Last Updated","Status", "Activation Status"];
            table = document.createElement('table');
            let thead = document.createElement('thead');
            let tr = document.createElement('tr');

            for(var i = 0;i<10;i++){
                let th = document.createElement('th');
                th.setAttribute('scope','col');
                th.setAttribute('onclick','drawOrderedTransactionsTable(this)');
                th.innerHTML = colNames[i];
                tr.appendChild(th);
            }
            thead.appendChild(tr);
            table.setAttribute('class','table table-bordered table-hover');
            table.setAttribute('id','allTransactionsTable');
            table.appendChild(thead);
            mainDiv.setAttribute('style','width:100%');
            mainDiv.appendChild(table);
            return res.json();
        }
    }).then(data=>
    {
        transactions = data;
        let tbody = document.createElement('tbody');
        for(var i = 0; i<data.length; i++){
            let transaction = data[i];
            let trBody = document.createElement('tr');
            let thBody = document.createElement('th');
            thBody.setAttribute('scope','row');
            thBody.innerHTML = transaction.id;
            let td1 = document.createElement('td');
            td1.innerHTML = transaction.type;
            let td2 = document.createElement('td');
            td2.innerHTML = transaction.amount;
            let tdToAm = document.createElement('td');
            tdToAm.innerHTML = transaction.toAmount;
            let td3 = document.createElement('td');
            td3.innerHTML = transaction.from.number;
            let td4 = document.createElement('td');
            td4.innerHTML = transaction.to.number;
            let td5 = document.createElement('td');
            let formattedDateCreated = transaction.dateCreated.split('T');
            let formattedTimeCreated = formattedDateCreated[1].split('.');
            td5.innerHTML = formattedDateCreated[0] + " " + formattedTimeCreated[0];
            let td6 = document.createElement('td');
            let formattedLastUpdated = transaction.lastUpdated.split('T');
            let formattedTimeLastUpdated = formattedLastUpdated[1].split('.');
            td6.innerHTML = formattedLastUpdated[0]+ " " +formattedTimeLastUpdated[0];
            let td7 = document.createElement('td');
            td7.innerHTML = transaction.status;
            let td8 = document.createElement('td');
            if(transaction.active==true){
                td8.innerHTML = 'Active';
            }else{
                td8.innerHTML = 'Inactive';
            }

            trBody.appendChild(thBody);
            trBody.appendChild(td1);
            trBody.appendChild(td2);
            trBody.appendChild(tdToAm);
            trBody.appendChild(td3);
            trBody.appendChild(td4);
            trBody.appendChild(td5);
            trBody.appendChild(td6);
            trBody.appendChild(td7);
            trBody.appendChild(td8);
            if(transaction.status=="PENDING" && transaction.active==true){
                let td9 = document.createElement('td');
                let acceptButton = document.createElement('input');
                acceptButton.setAttribute('type','button');
                acceptButton.setAttribute('class','btn btn-success');
                acceptButton.setAttribute('value', 'Accept');
                acceptButton.setAttribute('onclick', `acceptTransaction(${transaction.id},this)`);
                td9.appendChild(acceptButton);
                let td10 = document.createElement('td');
                let rejectButton = document.createElement('input');
                rejectButton.setAttribute('type','button');
                rejectButton.setAttribute('class','btn btn-danger');
                rejectButton.setAttribute('value', 'Reject');
                rejectButton.setAttribute('onclick',`rejectTransaction(${transaction.id},this)`);
                td10.appendChild(rejectButton);
                trBody.appendChild(td9);
                trBody.appendChild(td10);
            }
            tbody.appendChild(trBody);
            table.appendChild(tbody);
        }
    });
}
async function acceptTransaction(tr_id, bt) {
    const url = `http://localhost:8080/transaction/accept?id=${tr_id}`;
    const response = await fetch(url, {
        method: 'PUT',
        headers: {
            'Access-Control-Allow-Origin':'*'
        } // body data type must match "Content-Type" header
    }).then(res=>{
        if(res.status==200){
            let parentTr = bt.parentElement.parentElement;
            parentTr.children[8].innerHTML = 'ACCEPTED';
            parentTr.children[9].innerHTML = 'Active';
            let nextBt = parentTr.children[11].children[0];
            let lUpd = parentTr.children[7];
            bt.remove();
            nextBt.remove();
            let now = new Date()
            lUpd.innerHTML=now.getFullYear() + '-' + (now.getMonth()+1) + '-' + now.getDate()
                + ' ' + now.getHours() + ":"
                + now.getMinutes() + ":" + now.getSeconds();
        }
    });
}
async function rejectTransaction(tr_id, bt) {
    const url = `http://localhost:8080/transaction/reject?id=${tr_id}`;
    const response = await fetch(url, {
        method: 'PUT',
        headers: {
            'Access-Control-Allow-Origin':'*'
        } // body data type must match "Content-Type" header
    }).then(res=>{
        if(res.status==200){
            let parentTr = bt.parentElement.parentElement;
            parentTr.children[8].innerHTML = 'REJECTED';
            parentTr.children[9].innerHTML = 'Inactive';
            let nextBt = parentTr.children[10].children[0];
            let lUpd = parentTr.children[7];
            bt.remove();
            nextBt.remove();
            let now = new Date()
            lUpd.innerHTML=now.getFullYear() + '-' + (now.getMonth()+1) + '-' + now.getDate()
                + ' ' + now.getHours() + ":"
                + now.getMinutes() + ":" + now.getSeconds();
        }
    });
}
function drawOrderedUserAccountTable(by){
    if(by.innerHTML == 'Number'){
        accounts.sort((a,b)=>{
            return a.number.localeCompare(b.number);
        });
    }else if(by.innerHTML == 'Currency'){
        accounts.sort((a,b)=>{
            return a.currency.localeCompare(b.currency);
        });
    }else if(by.innerHTML == 'Date Created'){
        accounts.sort((a,b)=>{
            return a.dateCreated.localeCompare(b.dateCreated);
        });
    }else if(by.innerHTML == 'Last Updated'){
        accounts.sort((a,b)=>{
            return a.lastUpdated.localeCompare(b.lastUpdated);
        });
    }else if(by.innerHTML == 'Status'){
        accounts.sort((a,b)=>{
            return a.status.localeCompare(b.status);
        });
    }else if(by.innerHTML == 'Activation Status'){
        accounts.sort((a,b)=>{
            return -(a.active.toString().localeCompare(b.active.toString()));
        });
    }
    let tableBody = document.getElementsByTagName('tbody')[0];
    tableBody.remove();
    let table = document.getElementById('userAccountsTable');

    let tbody = document.createElement('tbody');
    for(var i = 0; i<accounts.length; i++){
        let account = accounts[i];
        let trBody = document.createElement('tr');
        let td1 = document.createElement('td');
        td1.innerHTML = account.number;
        let td2 = document.createElement('td');
        td2.innerHTML = account.currency;
        let td3 = document.createElement('td');
        let formattedDateCreated = account.dateCreated.split('T');
        let formattedTimeCreated = formattedDateCreated[1].split('.');
        td3.innerHTML = formattedDateCreated[0] + " " + formattedTimeCreated[0];
        let td4 = document.createElement('td');
        let formattedLastUpdated = account.lastUpdated.split('T');
        let formattedTimeLastUpdated = formattedLastUpdated[1].split('.');
        td4.innerHTML = formattedLastUpdated[0]+ " " +formattedTimeLastUpdated[0];
        let td5 = document.createElement('td');
        td5.innerHTML = account.status;
        let td6 = document.createElement('td');
        if(account.active==true){
            td6.innerHTML = 'Active';
        }else{
            td6.innerHTML = 'Inactive';
        }

        trBody.appendChild(td1);
        trBody.appendChild(td2);
        trBody.appendChild(td3);
        trBody.appendChild(td4);
        trBody.appendChild(td5);
        trBody.appendChild(td6);
        if(account.status == 'PENDING'){
            let td7 = document.createElement('td');
            let accUpdBt = document.createElement('input');
            accUpdBt.setAttribute('type','button');
            accUpdBt.setAttribute('class','btn btn-info');
            accUpdBt.setAttribute('value', 'Update');
            accUpdBt.setAttribute('onclick','accountUpdateButtonEvent(this)');
            td7.appendChild(accUpdBt);
            trBody.appendChild(td7);
            let td8 = document.createElement('td');
            if(account.active==true){
                let accInactBt = document.createElement('input');
                accInactBt.setAttribute('type','button');
                accInactBt.setAttribute('class','btn btn-danger');
                accInactBt.setAttribute('value', 'Deactivate');
                accInactBt.setAttribute('onclick',`deactivateAccount(${account.id},this)`);
                td8.appendChild(accInactBt);
            }else{
                let accActBt = document.createElement('input');
                accActBt.setAttribute('type','button');
                accActBt.setAttribute('class','btn btn-success');
                accActBt.setAttribute('value', 'Activate');
                accActBt.setAttribute('onclick',`activateAccount(${account.id},this)`);
                td8.appendChild(accActBt);
            }
            trBody.appendChild(td8);
        }

        tbody.appendChild(trBody);
        table.appendChild(tbody);

    }
}

async function deactivateAccount(acc_id,bt){
    const url = `http://localhost:8080/account/deactivate?id=${acc_id}`;
    const response = await fetch(url, {
        method: 'PUT',
        headers: {
            'Access-Control-Allow-Origin':'*'
        }
    }).then(res=>{
            let parTr = bt.parentElement.parentElement;
            let deactivationTd = parTr.children[5];
            deactivationTd.innerHTML = 'Inactive';
            bt.setAttribute('class','btn btn-success');
            bt.setAttribute('value', 'Activate');
            bt.setAttribute('onclick', `activateAccount(${acc_id},this)`);
            let lUpd = parTr.children[3];
            let now = new Date()
            lUpd.innerHTML=now.getFullYear() + '-' + (now.getMonth()+1) + '-' + now.getDate()
                                             + ' ' + now.getHours() + ":"
                + now.getMinutes() + ":" + now.getSeconds();
    });
}
async function activateAccount(acc_id,bt){
    const url = `http://localhost:8080/account/activate?id=${acc_id}`;
    const response = await fetch(url, {
        method: 'PUT',
        headers: {
            'Access-Control-Allow-Origin':'*'
        }
    }).then(res=>{
            let parTr = bt.parentElement.parentElement;
            let deactivationTd = parTr.children[5];
            deactivationTd.innerHTML = 'Active';
            bt.setAttribute('class','btn btn-danger');
            bt.setAttribute('value', 'Deactivate');
            bt.setAttribute('onclick', `deactivateAccount(${acc_id},this)`);
            let lUpd = parTr.children[3];
            let now = new Date();
            lUpd.innerHTML=now.getFullYear() + '-' + (now.getMonth()+1) + '-' + now.getDate()
                + ' ' + now.getHours() + ":"
                + now.getMinutes() + ":" + now.getSeconds();
    });
}
function accountUpdateButtonEvent(accNumber, accCurrency, pageNumber){
    localStorage.setItem('accNumber',accNumber);
    localStorage.setItem('accCurrency',accCurrency);
    localStorage.setItem('pageNumber',pageNumber)
}
function transactionUpdateButtonEvent(trType, trAmount, trFrom, trTo){
    localStorage.setItem('trType',trType);
    localStorage.setItem('trAmount',trAmount);
    localStorage.setItem('trFrom',trFrom);
    localStorage.setItem('trTo',trTo);
}

function drawOrderedUserTransactionsTable(by){
    if(by.innerHTML == 'ID'){
        transactions.sort((a,b)=>{
            return a.id-b.id;
        });
    }else if(by.innerHTML == 'Type'){
        transactions.sort((a,b)=>{
            return a.type.localeCompare(b.type);
        });
    }else if(by.innerHTML == 'Amount'){
        transactions.sort((a,b)=>{
            return a.amount-b.amount;
        });
    }else if(by.innerHTML == 'From'){
        transactions.sort((a,b)=>{
            return a.from.number.localeCompare(b.from.number);
        });
    }else if(by.innerHTML == 'To'){
        transactions.sort((a,b)=>{
            return a.to.number.localeCompare(b.to.number);
        });
    }else if(by.innerHTML == 'Date Created'){
        transactions.sort((a,b)=>{
            return a.dateCreated.localeCompare(b.dateCreated);
        });
    }else if(by.innerHTML == 'Last Updated'){
        transactions.sort((a,b)=>{
            return a.lastUpdated.localeCompare(b.lastUpdated);
        });
    }else if(by.innerHTML == 'Status'){
        transactions.sort((a,b)=>{
            return a.status.localeCompare(b.status);
        });
    }else if(by.innerHTML == 'Activation Status'){
        transactions.sort((a,b)=>{
            return -(a.active.toString().localeCompare(b.active.toString()));
        });
    }
    let tableBody = document.getElementsByTagName('tbody')[0];
    tableBody.remove();
    let table = document.getElementById('userTransactionsTable');
    let tbody = document.createElement('tbody');
    for(var i = 0; i<transactions.length; i++){
        let transaction = transactions[i];
        let trBody = document.createElement('tr');
        let thBody = document.createElement('th');
        thBody.setAttribute('scope','row');
        thBody.innerHTML = transaction.id;
        let td1 = document.createElement('td');
        td1.innerHTML = transaction.type;
        let td2 = document.createElement('td');
        td2.innerHTML = transaction.amount;
        let td3 = document.createElement('td');
        td3.innerHTML = transaction.from.number + ", " + transaction.from.currency;
        let td4 = document.createElement('td');
        td4.innerHTML = transaction.to.number + ", " + transaction.to.currency;;
        let td5 = document.createElement('td');
        let formattedDateCreated = transaction.dateCreated.split('T');
        let formattedTimeCreated = formattedDateCreated[1].split('.');
        td5.innerHTML = formattedDateCreated[0] + " " + formattedTimeCreated[0];
        let td6 = document.createElement('td');
        let formattedLastUpdated = transaction.lastUpdated.split('T');
        let formattedTimeLastUpdated = formattedLastUpdated[1].split('.');
        td6.innerHTML = formattedLastUpdated[0]+ " " +formattedTimeLastUpdated[0];
        let td7 = document.createElement('td');
        td7.innerHTML = transaction.status;
        let td8 = document.createElement('td');
        if(transaction.active==true){
            td8.innerHTML = 'Active';
        }else{
            td8.innerHTML = 'Inactive';
        }

        trBody.appendChild(thBody);
        trBody.appendChild(td1);
        trBody.appendChild(td2);
        trBody.appendChild(td3);
        trBody.appendChild(td4);
        trBody.appendChild(td5);
        trBody.appendChild(td6);
        trBody.appendChild(td7);
        trBody.appendChild(td8);

        let accounts = localStorage.getItem('accounts').split(',');
        if(transaction.status == 'PENDING' && accounts.includes(transaction.from.number)){
            let td9 = document.createElement('td');
            let trUpdButton = document.createElement('input');
            trUpdButton.setAttribute('type','button');
            trUpdButton.setAttribute('class','btn btn-info');
            trUpdButton.setAttribute('value', 'Update');
            trUpdButton.setAttribute('onclick','transactionUpdateButtonEvent(this)');
            td9.appendChild(trUpdButton);
            trBody.appendChild(td9);

            let td10 = document.createElement('td');
            if(transaction.active==true){
                let trInactBt = document.createElement('input');
                trInactBt.setAttribute('type','button');
                trInactBt.setAttribute('class','btn btn-danger');
                trInactBt.setAttribute('value', 'Deactivate');
                trInactBt.setAttribute('onclick',`deactivateTransaction(${transaction.id},this)`);
                td10.appendChild(trInactBt);
            }else{
                let trActBt = document.createElement('input');
                trActBt.setAttribute('type','button');
                trActBt.setAttribute('class','btn btn-success');
                trActBt.setAttribute('value', 'Activate');
                trActBt.setAttribute('onclick',`activateTransaction(${transaction.id},this)`);
                td10.appendChild(trActBt);
            }
            trBody.appendChild(td10);
        }
        tbody.appendChild(trBody);
        table.appendChild(tbody);
    }
}
async function deactivateTransaction(tr_id,bt){
    const url = `http://localhost:8080/transaction/deactivate/?id=${tr_id}`;
    const response = await fetch(url, {
        method: 'PUT',
        headers: {
            'Access-Control-Allow-Origin':'*'
        }
    }).then(res=>{
            let parTr = bt.parentElement.parentElement;
            let deactivationTd = parTr.children[8];
            deactivationTd.innerHTML = 'Inactive';
            bt.setAttribute('class','btn btn-success');
            bt.setAttribute('value', 'Activate');
            bt.setAttribute('onclick', `activateTransaction(${tr_id},this)`);
        let lUpd = parTr.children[6];
        let now = new Date();
        lUpd.innerHTML=now.getFullYear() + '-' + (now.getMonth()+1) + '-' + now.getDate()
            + ' ' + now.getHours() + ":"
            + now.getMinutes() + ":" + now.getSeconds();
            return res.json();
    });
}
async function activateTransaction(tr_id,bt){
    const url = `http://localhost:8080/transaction/activate/?id=${tr_id}`;
    const response = await fetch(url, {
        method: 'PUT',
        headers: {
            'Access-Control-Allow-Origin':'*'
        }
    }).then(res=>{
            let parTr = bt.parentElement.parentElement;
            let deactivationTd = parTr.children[8];
            deactivationTd.innerHTML = 'Active';
            bt.setAttribute('class','btn btn-danger');
            bt.setAttribute('value', 'Deactivate');
            bt.setAttribute('onclick', `deactivateTransaction(${tr_id},this)`);
        let lUpd = parTr.children[6];
        let now = new Date();
        lUpd.innerHTML=now.getFullYear() + '-' + (now.getMonth()+1) + '-' + now.getDate()
            + ' ' + now.getHours() + ":"
            + now.getMinutes() + ":" + now.getSeconds();
            return res.json();
    });
}