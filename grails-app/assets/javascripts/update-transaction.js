function updateToInput(){
    let typeSelect = document.getElementsByName('type')[0];
    let toInput = document.getElementById('hiddenTo');
    if(typeSelect.value == 'EXCHANGE'){
        toInput.style.display = 'block'
    }else{
        toInput.style.display = 'none'
    }
}