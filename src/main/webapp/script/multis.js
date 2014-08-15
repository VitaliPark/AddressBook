/**
 * Created with JetBrains WebStorm.
 * User: Anton.Nekrasov
 * Date: 7/17/14 11:11 AM
 */

window.onload = function () {
    "use strict";
    document.body.onclick = select;
};

function deleteContacts(path){
    var data = [];
    data = getIdData();
    if(data != null && data.length != 0) {
        sendData(path, data, "post");
    }
}

function getIdData(){
    var data = [];
    var i,
        collection = document.getElementsByClassName("selected");
    for(i = 0; i < collection.length; ++i){
        data[i] = collection[i].getAttribute("id");
    }
    return data;
}

function sendData(path, data, method) {
    method = method || "post";
    var i;
    var form = document.createElement("form");
    form.setAttribute("method", method);
    form.setAttribute("action", path);

    for(i = 0; i < data.length; ++i) {
        var inputField = document.createElement("input");
        inputField.setAttribute("type", "checkbox");
        inputField.setAttribute("name", "contactId");
        inputField.setAttribute("checked", "checked");
        inputField.setAttribute("value", data[i].toString());
        form.appendChild(inputField.cloneNode());
    }
    document.body.appendChild(form);
    form.submit();
    document.body.removeChild(form);
}

function editContact(personId){
    var form = document.createElement("form");
    form.setAttribute("method", "post");
    form.setAttribute("action", "index?command=editContact");
    var inputField = document.createElement("input");
    inputField.setAttribute("type", "number");
    inputField.setAttribute("name", "idPerson");
    inputField.setAttribute("value", personId);
    form.appendChild(inputField.cloneNode());
    document.body.appendChild(form);
    form.submit();
    document.body.removeChild(form);
}

function select(event) {
    "use strict";
    var element,
        tr;


    event = event || window.event;
    element = event.toElement || event.originalTarget;
    tr = getParent("row", element);

    if(!event.ctrlKey) {
        clearSelected();
    }
    if(tr) {
        if(hasClass("selected", tr)) {
            removeClass("selected", tr);
        } else {
            tr.className += " selected";
        }
    } else {

    }
}

function clearSelected() {
    var i,
        collection = document.getElementsByClassName("row");// be careful: getElementsByClassName was officially introduced only in HTML5 specification (won't work in IE8!)
    for(i = 0; i < collection.length; i++) {
        removeClass("selected", collection[i]);
    }

}

function getParent(className, tag) {
    var parent = tag.parentNode;
    if(!parent) {
        return null;
    }
    if(hasClass(className, parent)) {
        return parent;
    } else {
        return getParent(className, parent);
    }
}

function hasClass(className, tag) {
    if(!tag || !tag.className) return null;
    return tag.className.split(/\s/).indexOf(className) >= 0
}

function removeClass(className, tag) {
    var classes = tag.className.split(' '),
        i;

    for(i = 0; i < classes.length; i++) {
        if (classes[i] === className) {
            classes.splice(i, 1);
            i--;
        }
    }
    tag.className = classes.join(' ');
}/**
 * Created by Admin on 04.08.2014.
 */
