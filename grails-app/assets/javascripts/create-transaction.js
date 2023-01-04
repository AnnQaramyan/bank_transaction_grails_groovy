function createToInput(){
    let typeSelect = document.getElementsByName('type')[0];
    if(typeSelect.value == 'EXCHANGE'){
        let mainDiv = document.getElementById('create_tr_cont');
        let btn = document.getElementById('create_btn');

        let toDiv = document.createElement('div');
        toDiv.setAttribute('id','toAcc');
        toDiv.setAttribute('class','form-group');

        let toLabel = document.createElement('label');
        toLabel.setAttribute('for','to');
        toLabel.innerHTML = 'To';

        let br1 = document.createElement('br');

        let toInput = document.createElement('input');
        toInput.setAttribute('type','text');
        toInput.setAttribute('class','form-control');
        toInput.setAttribute('placeholder','To Who');
        toInput.setAttribute('name','to');

        let br2 = document.createElement('br');

        toDiv.appendChild(toLabel);
        toDiv.appendChild(br1);
        toDiv.appendChild(toInput);
        toDiv.appendChild(br2)

        mainDiv.insertBefore(toDiv,btn);
    }else{
        let probToDiv = document.getElementById('toAcc');
        if(probToDiv!=null){
            probToDiv.remove();
        }
    }
}