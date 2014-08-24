(function() {

        document.contactController = {


        lastUsedId: -1,
        lastAttachId: -1,

        submitPhone: function (){
            var type = document.getElementById("phoneTitle")
            if(type.innerHTML == "Создание телефона"){
                this.addPhone();
            } else if(type.innerHTML == "Редактирование телефона"){
                this.updatePhone();
            }
        },

        submitAttachment: function (){
            var type = document.getElementById("attachmentTitle");
            if(type.innerHTML == "Создание присоединения"){
                this.addAttachment();
            } else if(type.innerHTML == "Редактирование присоединения"){
                this.updateAttachment();
            }
        },

        downloadAttachment: function(){
            var element = document.getElementById("attachmentBody");
            var rows = element.getElementsByClassName("selected");
            var localFileName;
            var path = "static/attachments/";
            var fullPath;
            if(rows != null && rows.length != 0){
                localFileName = rows[0].id;
                fullPath = path + localFileName;
                var aLink = document.createElement("a");
                aLink.setAttribute("href", fullPath);
                aLink.click();
                document.removeChild(aLink);
            }
        },

        submitImage: function (){
            var imageId = document.getElementById("imageId").innerHTML.toString();
            var status = "created";
            if(imageId != null && imageId.length != 0){
                status = "updated";
            }

            var mainFormImageFileInput = document.getElementById("mainFormImageFileInput");
            var mainForm = document.getElementById("mainForm");
            if(mainFormImageFileInput != null){
                var parent = mainFormImageFileInput.parentNode;
                parent.removeChild(mainFormImageFileInput);
            }
            var popupImageInput = document.getElementById("imageChooser");
            var newImageFileInput = popupImageInput.cloneNode();
            popupImageInput.id = "mainFormImageFileInput";
            var timeStamp = getTimeStamp();
            popupImageInput.setAttribute("name", "contact_image" + timeStamp);
            popupImageInput.className = 'hidden';
            switchInputs(newImageFileInput, popupImageInput);
            mainForm.appendChild(popupImageInput);

            var mainFormImageFileName = document.getElementById("mainFormImageFileName");
            var imageFileName = document.getElementById("imageFileName");
            if(mainFormImageFileName == null){
                mainFormImageFileName = document.createElement("input");
                mainFormImageFileName.setAttribute("name", "mainFormImageFileName");
                mainFormImageFileName.id = "mainFormImageFileName";
                mainFormImageFileName.value = imageFileName.value;
                mainForm.appendChild(mainFormImageFileName);
            }
            mainFormImageFileName.value =  "contact_image" + timeStamp + ";" + imageFileName.value + ";" + status;
            closeDialog("image");
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
            tr.className = "row";
            tr.ondblclick = function(){showPhoneDialog("Редактирование телефона", this)};

            var td0 = document.createElement("td");
            td0.innerHTML = phoneId;
            td0.className = "hidden";

            var td1 = document.createElement("td");
            td1.className += "text-left smallFont";
            td1.innerHTML = countryCode + "-" + operatorCode + "-" + phoneNumber;

            var td2 = document.createElement("td");
            td2.className += "text-left smallFont";
            td2.innerHTML = phoneType;

            var td3 = document.createElement("td");
            td3.className += "text-left smallFont";
            td3.innerHTML = phoneComment;

            var td4 = document.createElement("td");
            td4.innerHTML = "created";
            td4.className = "hidden";

            tr.appendChild(td1);
            tr.appendChild(td2);
            tr.appendChild(td3);
            tr.appendChild(td0);
            tr.appendChild(td4);
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
                var statusTd = rows[i].getElementsByTagName("td")[4];
                statusTd.innerHTML = "deleted";
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

            var changedRow = this.getChangedRow(phoneId.innerHTML, "phoneBody");
            changedRow.className = "row update";

            var dataCells = changedRow.getElementsByTagName("td");
            dataCells[0].innerHTML = countryCode + "-" + operatorCode + "-" + phoneNumber;
            dataCells[1].innerHTML = phoneType;
            dataCells[2].innerHTML = phoneComment;
            if(phoneId.innerHTML < 0){
                dataCells[4].innerHTML = "created";
            }
            else {
                dataCells[4].innerHTML = "updated";
            }
            document.getElementById("phonePopup").removeChild(phoneId);
            closeDialog("phone");
        },

        getChangedRow: function(elementId, bodyType){
            var tableBody = document.getElementById(bodyType);
            var rows = tableBody.getElementsByTagName("tr");
            for(var i = 0; i < rows.length; i++){
                var identTd = rows[i].getElementsByTagName("td")[3];
                var id = identTd.innerHTML;
                if(id == elementId){
                    return rows[i];
                }
            }
        },

        addAttachment: function(){
            var fileName = document.getElementById("fileName").value;
            var comment = document.getElementById("attachComment").value;
            var uploadDate = getCurrentDate();
            var attachId = --this.lastAttachId;

            var fileInput = document.getElementById("attachChooser");
            var form = document.getElementById("mainForm");
            var newFileInput = fileInput.cloneNode(false);
            var timeStamp = getTimeStamp();

            fileInput.id = timeStamp;

            //fileInput.id = "attachment" + attachId;
            fileInput.className = "hidden";
            //fileInput.setAttribute("name", "attachment" + attachId);
            fileInput.setAttribute("name", timeStamp);
            switchInputs(newFileInput, fileInput);
            form.appendChild(fileInput);

            var tr = document.createElement("tr");
            tr.className = "row";
            tr.ondblclick = function(){showAttachmentDialog("Редактирование присоединения", this)};

            var td1 = document.createElement("td");
            td1.className += "text-left smallFont";
            td1.innerHTML = fileName;

            var td2 = document.createElement("td");
            td2.className += "text-left smallFont";
            td2.innerHTML = uploadDate;

            var td3 = document.createElement("td");
            td3.className += "text-left smallFont";
            td3.innerHTML = comment;

            var td4 = document.createElement("td");
            td4.innerHTML = attachId;
            td4.className = "hidden";

            var td5 = document.createElement("td");
            td5.innerHTML = "created";
            td5.className = "hidden";

            var td6 = document.createElement("td");
            td6.innerHTML = timeStamp;
            td6.className = "hidden";

            tr.appendChild(td1);
            tr.appendChild(td2);
            tr.appendChild(td3);
            tr.appendChild(td4);
            tr.appendChild(td5);
            tr.appendChild(td6);
            var element = document.getElementById("attachmentBody");
            element.appendChild(tr);

            closeDialog("attachment");
        },

        deleteAttachment: function(){
            var element = document.getElementById("attachmentBody");
            var rows = element.getElementsByClassName("selected");
            for(var i=0; i < rows.length; i++) {
                rows[i].className = "row deleted selected";
                var statusTd = rows[i].getElementsByTagName("td")[4];
                var idTd = rows[i].getElementsByTagName("td")[3];
                var timeStamp = rows[i].getElementsByTagName("td")[5];
                statusTd.innerHTML = "deleted";

                //var fileInput = document.getElementById("attachment" + idTd.innerHTML);
                var fileInput = document.getElementById(timeStamp.innerHTML);
                if(fileInput != null){
                    var parent = fileInput.parentNode;
                    parent.removeChild(fileInput);
                }
                rows[i].style.display = "none";
            }
        },

         updateAttachment: function() {
            var fileName = document.getElementById("fileName").value;
            var comment = document.getElementById("attachComment").value;
            var date = getCurrentDate();
            var attachId = document.getElementById("attachmentId");
             var attachTimeStamp = document.getElementById("attachmentTimeStamp");
            var fileChooser = document.getElementById("attachChooser");
             var newFileInput = fileChooser.cloneNode(false);
             var form = document.getElementById("mainForm");
             //var fileInput = document.getElementById("attachment" + attachId.innerHTML);
             var fileInput = document.getElementById(attachTimeStamp.innerHTML);
             if(fileInput != null){
                 var parent = fileInput.parentNode;
                 parent.removeChild(fileInput);
             }

             var timeStamp = getTimeStamp();
            // fileChooser.id = "attachment" + attachId.innerHTML;
             fileChooser.id = timeStamp;
             fileChooser.className = "hidden";
             //fileChooser.setAttribute("name", "attachment" + attachId.innerHTML);
             fileChooser.setAttribute("name", timeStamp);


             switchInputs(newFileInput, fileChooser);
             form.appendChild(fileChooser);
             var changedRow = this.getChangedRow(attachId.innerHTML, "attachmentBody");
             changedRow.className = "row update";

             var dataCells = changedRow.getElementsByTagName("td");
             dataCells[0].innerHTML = fileName;
             dataCells[1].innerHTML = date;
             dataCells[2].innerHTML = comment;
             if(attachId.innerHTML < 0){
                 dataCells[4].innerHTML = "created";
             }
             else {
                 dataCells[4].innerHTML = "updated";
             }
             dataCells[5].innerHTML = timeStamp;
             document.getElementById("attachmentPopup").removeChild(attachId);
             closeDialog("attachment");
         }
    };
})();

    function showImageDialog(){
        var element = document.getElementById("imageOverlay");
        var mainFormImageName = document.getElementById("mainFormImageFileName");
        var imageFileNameInput = document.getElementById("imageFileName");
        if(mainFormImageName != null){
            imageFileNameInput.value = mainFormImageName.value;
        }

        showElement(element);

    }

    function switchInputs(newInput, oldInput){
        oldInput.parentNode.replaceChild(newInput, oldInput);
    }

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
                document.getElementById("attachmentPopup").removeChild(attachId);
            }
            var attachTimeStamp = document.getElementById("attachmentTimeStamp");
            if(attachTimeStamp != null){
                document.getElementById("attachmentPopup").removeChild(attachTimeStamp);
            }
            document.getElementById("attachmentOverlay").style.visibility = 'hidden';
        } else if(type == "image"){
            document.getElementById("imageOverlay").style.visibility = 'hidden';
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

    function showAttachmentDialog(title, editedRow){
        var element = document.getElementById("attachmentOverlay");
        var headerElement = document.getElementById("attachmentTitle");
        headerElement.innerHTML = title;

        if(editedRow != null){
            var idCell = editedRow.getElementsByTagName("td")[3];
            var attachTimeStamp = editedRow.getElementsByTagName("td")[5];
            var attachmentId = idCell.innerHTML;
            var attachmentFields = [];
            var tr = editedRow;
            var dataCells = tr.getElementsByTagName("td");
            attachmentFields[0] = dataCells[0].innerHTML;
            attachmentFields[1] = dataCells[2].innerHTML;
            setAttachmentId(attachmentId);
            setAttachmentTimeStamp(attachTimeStamp.innerHTML);
            fillAttachmentFields(attachmentFields);
        }
        showElement(element);
    }



	function fillPhoneFields(phoneFields){
        document.getElementById("countryCode").value = phoneFields[0];
        document.getElementById("operatorCode").value = phoneFields[1];
        document.getElementById("phoneNumber").value = phoneFields[2];
        document.getElementById("phoneType").value = phoneFields[3];
        document.getElementById("phoneComment").value = phoneFields[4];
	}

    function setAttachmentTimeStamp(attachTimeStamp){
        var element = document.createElement("label");
        element.setAttribute("id", "attachmentTimeStamp");
        element.style.display = "none";
        element.innerHTML = attachTimeStamp;
        var attachmentDiv = document.getElementById("attachmentPopup");
        attachmentDiv.appendChild(element);

    }

    function fillAttachmentFields(attachmentFields){
        document.getElementById("fileName").value = attachmentFields[0];
        document.getElementById("attachComment").value = attachmentFields[1];

    }

    function setAttachmentId(attachmentId){
        var element = document.createElement("label");
        element.setAttribute("id", "attachmentId");
        element.style.display = "none";
        element.innerHTML = attachmentId;
        var attachmentDiv = document.getElementById("attachmentPopup");
        attachmentDiv.appendChild(element);
    }

	function setPhoneId(id){
	    var element = document.createElement("label");
	    element.setAttribute("id", "phoneId");
	    element.style.display = "none";
	    element.innerHTML = id;
	    var phoneDiv = document.getElementById("phonePopup");
	    phoneDiv.appendChild(element);
	}

    function setFileName(fileName, fileNamefield){
        var element = document.getElementById(fileNamefield);
        var name = fileName;
        var shortName = name.match(/[^\/\\]+$/);
        element.value = shortName;
    }

    function getCurrentDate(){
        var today = new Date();
        var dd = today.getDate();
        var mm = today.getMonth()+1; //January is 0!
        var yyyy = today.getFullYear();

        if(dd<10) {
            dd='0'+dd
        }

        if(mm<10) {
            mm='0'+mm
        }
        today = yyyy+'-'+mm+'-'+dd;
        return today;
    }

    function onMainFormSubmit(){
        addPhonesInputToForm();
        addAttachmentInputToForm();
    }

    function addPhonesInputToForm(){
        var phoneBody = document.getElementById("phoneBody");
        var phoneRows = phoneBody.getElementsByTagName("tr");
        var phoneInputValue;
        for(var i = 0; i < phoneRows.length; i++){
            var phoneDataCells = phoneRows[i].getElementsByTagName("td");
            var status = phoneDataCells[4].innerHTML;
            var phoneId = phoneDataCells[3].innerHTML;
            if(isChanged(phoneId, status)){
                createPhoneInput(phoneDataCells);
            }
        }
    }

    function addAttachmentInputToForm(){
        var attachmentBody = document.getElementById("attachmentBody");
        var attachmentRows = attachmentBody.getElementsByTagName("tr");
        for(var i = 0; i < attachmentRows.length; i++){
            var attachDataCells = attachmentRows[i].getElementsByTagName("td");
            var status = attachDataCells[4].innerHTML;
            var attachId = attachDataCells[3].innerHTML;
            if(isChanged(attachId, status)){
                createAttachInput(attachDataCells);
            }
        }
    }

    function isChanged(phoneId, status){
        return !(phoneId < 0 && status == "deleted") && status != "unmodified"
    }

    function createPhoneInput(phoneDataCells){
        var input = document.createElement("input");
        var inputValue ="";
        input.className = "hidden";
        input.setAttribute("type", "checkbox")
        input.setAttribute("checked", "checked");
        input.setAttribute("name", "phones");
        for(var i = 0; i < phoneDataCells.length; i++){
            inputValue += phoneDataCells[i].innerHTML + ";";
        }
        input.setAttribute("value", inputValue);
        document.getElementById("mainForm").appendChild(input);
    }

    function createAttachInput(attachDataCells){
        var input = document.createElement("input");
        var inputValue ="";
        input.className = "hidden";
        input.setAttribute("type", "checkbox")
        input.setAttribute("checked", "checked");
        input.setAttribute("name", "attachments");
            for(var i = 0; i < attachDataCells.length; i++){
                inputValue += attachDataCells[i].innerHTML + ";";
            }
        input.setAttribute("value", inputValue);
        document.getElementById("mainForm").appendChild(input);
    }

    function getFile(){
        var input = document.getElementById("1");
        alert(input.value);
    }

    function getTimeStamp (){
        var time = new Date().getTime();
        var timeStamp = time.toString().slice(0, 10);
        return timeStamp;
    }

    function formImagePath(localFileName, imageElement){
        var pathToFile = new String();
        if(localFileName != null && localFileName.length != 0){
            pathToFile = "static/images/" + localFileName;
        }else {
            pathToFile = "static/images/avatar.jpg";
        }

        imageElement.setAttribute("src", pathToFile);

    }



