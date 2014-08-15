(function() {



    var Phone = function(id, countryCode, operatorCode, phoneNumber, phoneType, comment, status) {
        this.id = id;
        this.countyrCode = countryCode;
        this.operatorCode = operatorCode;
        this.phoneNumber = phoneNumber;
        this.phoneType = phoneType;
        this.comment = comment;
        this.status = status;
        this.serialize = function() {
            return id + '|' + countryCode + '|' + operatorCode;
        };
    };

        document.contactController = {

        // container for Pairs
        //phones: {},
        //phones: {},
        lastUsedId: -1,
        arrayIndex: 1,

        submitPhone: function (){
            var type = document.getElementById("phoneTitle")
            if(type.innerHTML == "Создание телефона"){
                this.addPhone();
            } else if(type.innerHTML == "Редактирование телефона"){
                this.updatePhone();
            }
        },

        // CRUD methods & helpers...

        addPhone: function () {
            var countryCode = document.getElementById("countryCode").value;
            var operatorCode = document.getElementById("operatorCode").value;
            var phoneNumber = document.getElementById("phoneNumber").value;
            var phoneType = document.getElementById("phoneType").value;
            var phoneComment = document.getElementById("phoneComment").value;
            var phoneId = --this.lastUsedId;

            var tr = document.createElement("tr");
            tr.className += "row create";
            tr.ondblclick = function(){showPhoneDialog("Редактирование телефона", this)};

            var td0 = document.createElement("td");
            td0.innerHTML = phoneId;
            td0.className = "ident";

            var td1 = document.createElement("td");
            td1.className += "text-left smallFont";
            td1.innerHTML = countryCode + "-" + operatorCode + "-" + phoneNumber;

            var td2 = document.createElement("td");
            td2.className += "text-left smallFont";
            td2.innerHTML = phoneType;

            var td3 = document.createElement("td");
            td3.className += "text-left smallFont";
            td3.innerHTML = phoneComment;



            tr.appendChild(td1);
            tr.appendChild(td2);
            tr.appendChild(td3);
            tr.appendChild(td0);
            //tr.appendChild(td4);
            var element = document.getElementById("phoneBody");
            element.appendChild(tr);
            closeDialog("phone");
        },

        deletePhones: function (){
            var element = document.getElementById("phoneBody");
            var rows = element.getElementsByClassName("selected");
            for(var i=0; i < rows.length; i++) {
                rows[i].className = "row deleted selected";
                rows[i].style.display = "none";
            }
        },

        updatePhone: function() {
            var countryCode = document.getElementById("countryCode").value;
            var operatorCode = document.getElementById("operatorCode").value;
            var phoneNumber = document.getElementById("phoneNumber").value;
            var phoneType = document.getElementById("phoneType").value;
            var phoneComment = document.getElementById("phoneComment").value;
            var phoneId = document.getElementById("phoneId");

            var changedRow = this.getChangedRow(phoneId.innerHTML);
            changedRow.className = "row update";

            var dataCells = changedRow.getElementsByTagName("td");
            dataCells[0].innerHTML = countryCode + "-" + operatorCode + "-" + phoneNumber;
            dataCells[1].innerHTML = phoneType;
            dataCells[2].innerHTML = phoneComment;
            document.getElementById("phonePopup").removeChild(phoneId);
            closeDialog("phone");
        },

        getChangedRow: function(phoneId){
            var tableBody = document.getElementById("phoneBody");
            var rows = tableBody.getElementsByTagName("tr");
            for(var i = 0; i < rows.length; i++){
                var identTd = rows[i].getElementsByTagName("td")[3];
                var id = identTd.innerHTML;
                if(id == phoneId){
                    return rows[i];
                }
            }
        },

        createInput: function (){

        },

        deleteRow: function() {
            var id = prompt("Enter id");
            this.rows[id] = null;
        },

        updateRow: function(id, key, value) {
            var row = this.rows[id];
            row.key = key;
            row.value = value;
        },

        redraw: function(containerId) {
            var element = document.getElementById(containerId);
            element.innerHTML = "";
            var tr, td, obj;
            for(var e in this.rows) {
                obj = this.rows[e];
                console.info(obj.id);
                tr = document.createElement("tr");
                tr.id = obj.id;
                td = document.createElement("td");
                td.innerHTML = obj.id;
                tr.appendChild(td);
                td = document.createElement("td");
                td.innerHTML = obj.key;
                tr.appendChild(td);
                td = document.createElement("td");
                td.innerHTML = obj.value;
                tr.appendChild(td);
                element.appendChild(tr);
            }
        },

        createRow: function() {
            var key = prompt("Please enter key");
            var value = prompt("Please enter value");
            this.addRow(key, value);
        },

        showSerialized: function() {
            var id = prompt("Enter id");
            var pair = this.rows[id];
            alert(pair.serialize());
        },

			hello: function(){
			var p;
			alert("hello");
			p = document.createElement("p");
			p.innerHTML="hello";
			p.style.display="none";
			document.body.appendChild(p);
		}

    };
})();

function closeDialog(type) {
    if(type == "phone"){
        var phoneId = document.getElementById("phoneId");
        if(phoneId != null){
            document.getElementById("phonePopup").removeChild(phoneId);
        }
        document.getElementById("phoneOverlay").style.visibility = 'hidden';
    } else if(type == "attachment"){
        var attachId = document.getElementById("attachId");
        if(attachId != null) {
            document.getElementById("attachmentPopup").removeChild(phoneId);
        }
        document.getElementById("attachmentOverlay").style.visibility = 'hidden';
    }
}

function showElement(element){
	element.style.visibility = (element.style.visibility == "visible") ? "hidden" : "visible";
}

	function showPhoneDialog(title, editedRow, phoneId){
	    var el = document.getElementById("phoneOverlay");
	    var headerElement = document.getElementById("phoneTitle");
	    headerElement.innerHTML = title;

        if(editedRow != null){
            var idCell = editedRow.getElementsByTagName("td")[3];
            var phoneId = idCell.innerHTML;
            var phoneFields = [];
            var tr = editedRow;
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

    function showAttachmentDialog(title, attachId){
        var element = document.getElementById("attachmentOverlay");
        var headerElement = document.getElementById("attachmentTitle");
        headerElement.innerHTML = title;

        showElement(element);

    }

	function fillPhoneFields(phoneFields){
	    //document.getElementById("countryCode").setAttribute("value", phoneFields[0]);
	    //document.getElementById("operatorCode").setAttribute("value", phoneFields[1]);
	   // document.getElementById("phoneNumber").setAttribute("value", phoneFields[2]);
	   // document.getElementById("phoneType").setAttribute("value", phoneFields[3]);
	   // document.getElementById("phoneComment").setAttribute("value", phoneFields[4]);
        document.getElementById("countryCode").value = phoneFields[0];
        document.getElementById("operatorCode").value = phoneFields[1];
        document.getElementById("phoneNumber").value = phoneFields[2];
        document.getElementById("phoneType").value = phoneFields[3];
        document.getElementById("phoneComment").value = phoneFields[4];

	}

	function setPhoneId(id){
	    var element = document.createElement("label");
	    element.setAttribute("id", "phoneId");
	    element.style.display = "none";
	    element.innerHTML = id;
	    var phoneDiv = document.getElementById("phonePopup");
	    phoneDiv.appendChild(element);
	}

    function setFileName(fileName){
        var element = document.getElementById("fileName");
        var name = fileName;
        var shortName = name.match(/[^\/\\]+$/);
        element.setAttribute("value", shortName);
    }

function submitAttachment(){
    var fileInput = document.getElementById("fileChooser");
    var form = document.getElementById("mainForm");
    var newFileInput = fileInput.cloneNode(true);
    form.appendChild(newFileInput);
}

