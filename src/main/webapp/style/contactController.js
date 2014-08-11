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
        phones: {},
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
            tr.setAttribute("id", phoneId);

            var td1 = document.createElement("td");
            td1.className += "text-left smallFont";
            td1.innerHTML = countryCode + "-" + operatorCode + "-" + phoneNumber;

            var td2 = document.createElement("td");
            td2.className += "text-left smallFont";
            td2.innerHTML = phoneType;

            var td3 = document.createElement("td");
            td3.className += "text-left smallFont";
            td3.innerHTML = phoneComment;

            //var td4 = document.createElement("td");
            //td4.innerHTML = "new";
            //td4.style.display = "none";

            tr.appendChild(td1);
            tr.appendChild(td2);
            tr.appendChild(td3);
            //tr.appendChild(td4);
            var element = document.getElementById("phoneBody");
            element.appendChild(tr);
            closeDialog();
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

            var changedRow = document.getElementById(phoneId.innerHTML);
            changedRow.className = "row update"
            var dataCells = changedRow.getElementsByTagName("td");
            dataCells[0].innerHTML = countryCode + "-" + operatorCode + "-" + phoneNumber;
            dataCells[1].innerHTML = phoneType;
            dataCells[2].innerHTML = phoneComment;
            document.getElementById("phonePopup").removeChild(phoneId);
            closeDialog();
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

function closeDialog() {
    var phoneId = document.getElementById("phoneId");
    if(phoneId != null){
        document.getElementById("phonePopup").removeChild(phoneId);
    }
    document.getElementById("phoneOverlay").style.visibility = 'hidden';
}

