function showElement(element){
element.style.visibility = (element.style.visibility == "visible") ? "hidden" : "visible";
}

function showPhoneDialog(title, phoneId){
    var el = document.getElementById("phoneOverlay");
    var headerElement = document.getElementById("phoneTitle");
    headerElement.innerHTML = title;

    if(phoneId != null){
        var phoneFields = [];
        var tr = document.getElementById(phoneId);
        var dataCells = tr.getElementsByTagName("td");
        var fullPhone = dataCells[0].innerHTML;
        var res = fullPhone.split("-");
        for(var i = 0; i < res.length; i++){
            phoneFields[i] = res[i];
        }
        phoneFields[3] = dataCells[1].innerHTML;
        phoneFields[4] = dataCells[2].innerHTML;
        setPhoneId(phoneId);
        fillPhoneFields(phoneFields);
    }
    showElement(el);
}

function fillPhoneFields(phoneFields){
    document.getElementById("countryCode").setAttribute("value", phoneFields[0]);
    document.getElementById("operatorCode").setAttribute("value", phoneFields[1]);
    document.getElementById("phoneNumber").setAttribute("value", phoneFields[2]);
    document.getElementById("phoneType").setAttribute("value", phoneFields[3]);
    document.getElementById("phoneComment").setAttribute("value", phoneFields[4]);

}

function setPhoneId(id){
    var element = document.createElement("label");
    element.setAttribute("id", "phoneId");
    element.style.display = "none";
    element.innerHTML = id;
    var phoneDiv = document.getElementById("phonePopup");
    phoneDiv.appendChild(element);
}