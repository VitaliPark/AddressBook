<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8" />
    <title>Add new contact</title>
    <link rel="stylesheet" href="style/table_style.css">
    <link rel="stylesheet" href="style/addContact.css">
    <script type="application/javascript" src="script/contactController.js"></script>
    <script type="text/javascript" src="script/multis.js"></script>
    <meta name="viewport" content="initial-scale=1.0; maximum-scale=1.0; width=device-width;">
</head>

<body>
<div id="index_wrapper">
<div class="toolbar">
    <ul>
        <li class="tab">
            <div id="index_title">
                <h1><a href="index?command=getAllContacts">Адресная книга</a></h1>
            </div>
        </li>
        <li class="tab activeTab"><a href="index?command=createContact">Добавить Контакт</a></li>
        <li class="tab" ><a href="index?command=showSearchPage">Поиск</a></li>
        <li class="tab"><a href="">Отправить Письмо</a></li>
    </ul>
</div>

<div class="inputFields">
    <c:set var="person" value="${requestScope.contact.getPerson()}" scope="request" />
    <c:set var="address" value="${requestScope.contact.getAddress()}" scope="request" />
    <c:set var="gender" value="${requestScope.person.getGender()}" scope="request" />
    <c:set var="status" value="${requestScope.person.getMaritalStatus()}" scope="request" />
    <form id="mainForm" enctype="multipart/form-data" action="index?command=updateContact&idPerson=${person.getIdPerson()}" method="post" onsubmit="onMainFormSubmit()">
        <ul>
            <li>
                <label class="inputLabel firstInput" for="firstName">Имя:</label>
                <input class="inputField firstInput" type="text" name="firstName" id="firstName" required
                    value='<c:out value="${requestScope.person.getFirstName()}"/>'
                >
            </li>
            <li>
                <label class="inputLabel" for="secondName">Фамилия:</label>
                <input class="inputField" type="text" name="secondName" id="secondName" required
                    value='<c:out value="${requestScope.person.getSecondName()}"/>'
                >
            </li>
            <li>
                <label class="inputLabel" for="patronymicName">Отчество:</label>
                <input class="inputField" type="text" name="patronymicName" id="patronymicName"
                    value='<c:out value="${requestScope.person.getPatronymicName()}"/>'
                >
            </li>
            <li>
                <label class="inputLabel" for="dateOfBirth">Дата Рождения:</label>
                <input class="inputField" name="dateOfBirth" id="dateOfBirth" type="date" required
                    value='<c:out value="${requestScope.contact.getBirthDate()}"/>'
                >
            </li>
            <li>

                <label>Пол:</label>
                <div class="register-switch">
                    <input type="radio" name="gender" value="G_F" id="gender_f" class="register-switch-input"
                        <c:if test= "${requestScope.gender.getValue() == 'G_F'}">CHECKED</c:if>
                    >
                    <label for="gender_f" class="register-switch-label">жен.</label>
                    <input type="radio" name="gender" value="G_M" id="gender_m" class="register-switch-input"
                        <c:if test="${requestScope.gender.getValue() eq 'G_M'}"> CHECKED </c:if>
                    >
                    <label for="gender_m" class="register-switch-label">муж.</label>
                    <input type="radio" name="gender" value="" id="gender_u" class="register-switch-input"
                        <c:if test="${empty requestScope.gender.getValue() or requestScope.gender.getValue() eq 'G_U'}"> CHECKED </c:if>
                    >
                    <label for="gender_u" class="register-switch-label">неопр.</label>
                </div>
            </li>
            <li>
                <label class="inputLabel" for="citizenship">Гражданство:</label>
                <input class="inputField" type="text" name="citizenship" id="citizenship"
                     value='<c:out value="${requestScope.person.getCitizenship()}"/>'
                >
            </li>
            <li>
                <label class="inputLabel"> Семейной положение:</label>
                <select name="maritalStatus" class="inputField">
                    <option  value = ""
                        <c:if test="${requestScope.status.getValue() eq 'MS_U'}">selected</c:if>
                    >Не важно
                    </option>
                    <option value = "MS_M"
                        <c:if test="${requestScope.status.getValue() eq 'MS_M'}">selected</c:if>
                    >В браке
                    </option>
                    <option value = "MS_S"
                        <c:if test="${requestScope.status.getValue() eq 'MS_S'}">selected</c:if>
                    >Не замужем/Не женат
                    </option>
                </select>
            </li>

            <li>
                <label class="inputLabel" for="webSite">Сайт:</label>
                <input class="inputField" type="text" name="website" id="webSite"
                    value='<c:out value="${requestScope.person.getWebSite()}"/>'
                >

            </li>
            <li>
                <label class="inputLabel" for="email">Почтовый адрес:</label>
                <input class="inputField" type="email" name="email" id="email"
                    value='<c:out value="${requestScope.person. getEmail()}"/>'
                >
            </li>
            <li>
                <label class="inputLabel" for="workplace">Место работы:</label>
                <input class="inputField" type="text" name="workplace" id="workplace"
                    value='<c:out value="${requestScope.person.getWorkplace()}"/>'
                >
            </li>
            <hr>
            <li>
                <label class="inputLabel" for="country">Страна:</label>
                <input class="inputField" type="text" name="country" id="country"
                    value='<c:out value="${requestScope.address.getCountry()}"/>'
                >
            </li>
            <li>
                <label class="inputLabel" for="city">Город:</label>
                <input class="inputField" type="text" name="city" id="city"
                    value='<c:out value="${requestScope.address.getCity()}"/>'
                >
            </li>
            <li>
                <label class="inputLabel" for="street">Улица:</label>
                <input class="inputField" type="text" name="street" id="street"
                    value='<c:out value="${requestScope.address.getStreet()}"/>'
                >
            </li>
            <li>
                <label class="inputLabel" for="houseNumber">Дом:</label>
                <input class="inputField" type="number" name="houseNumber" id="house"
                    value='<c:out value="${requestScope.address.getHouseNumberAsString()}"/>'
                >
            </li>
            <li>
                <label class="inputLabel" for="apartment">Квартира:</label>
                <input class="inputField" type="number" name="apartment" id="apartment"
                    value='<c:out value="${requestScope.address.getApartmentAsString()}"/>'
                >
            </li>
            <li>
                <label class="inputLabel" for="postIndex">Почтовый индекс:</label>
                <input class="inputField" type="text" name="postIndex" id="postIndex"
                    value='<c:out value="${requestScope.address.getIndex()}"/>'
                >
            </li>
        </ul>
        <input type="submit" name="Submit" value="Submit">
    </form>
</div>
<c:set var="phones" value="${requestScope.contact.getPhones()}" scope="request" />
<c:set var="attachments" value="${requestScope.contact.getAttachments()}" scope="request" />
<div id="tables">
    <article class="phone">
        <div class="tableButtons">
            <button type="button" onclick="showPhoneDialog('Создание телефона')">Создать</button>
            <button type="button" onclick="document.contactController.deletePhones()">Удалить</button>
        </div>
        <div class="table">
            <table id="phoneTable">
                <thead>
                <tr>
                    <th class="text-left smallFont">Телефон</th>
                    <th class="text-left smallFont">Тип телефона</th>
                    <th class="text-left smallFont">Комментрарий</th>
                </tr>
                </thead>
                <tbody class="table-hover" id="phoneBody">
                    <c:forEach items="${requestScope.phones}" var="phone">
                        <tr class = "row unmodified" ondblclick = "showPhoneDialog('Редактирование телефона', this, '${phone.getPhoneId()}')" >
                            <td class = "text-left smallFont"><c:out value="${phone.getFullPhone()}"></c:out></td>
                            <td class = "text-left smallFont"><c:out value="${phone.getPhoneType()}"></c:out></td>
                            <td class = "text-left smallFont"><c:out value="${phone.getComment()}"></c:out></td>
                            <td class = "hidden"><c:out value="${phone.getPhoneId()}"></c:out></td>
                            <td class = "hidden">unmodified</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

    </article>

    <article>
        <div class="tableButtons">
            <button type="button" onclick="showAttachmentDialog('Создание присоединения')">Создать</button>
            <button type="button" onclick="document.contactController.deleteAttachment()">Удалить</button>
        </div>
        <div class="table">
            <table id="attachmentTable">
                <thead>
                <tr>
                    <th class="text-left smallFont">Имя файла</th>
                    <th class="text-left smallFont">Дата загрузки</th>
                    <th class="text-left smallFont">Комментрарий</th>
                </tr>
                </thead>
                <tbody class="table-hover" id="attachmentBody">

                    <c:forEach items="${requestScope.attachments}" var="attach">
                        <tr class = "row unmodified" ondblclick = "showAttachmentDialog('Редактирование присоединения', this, '${attach.getIdAttachment()}')" >
                            <td class = "text-left smallFont"><c:out value="${attach.getFileName()}"></c:out></td>
                            <td class = "text-left smallFont"><c:out value="${attach.getUploadDateAsString()}"></c:out></td>
                            <td class = "text-left smallFont"><c:out value="${attach.getComment()}"></c:out></td>
                            <td class = "hidden"><c:out value="${attach.getIdAttachment()}"></c:out></td>
                            <td class = "hidden">unmodified</td>
                            <td class = "hidden">timestamp</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </article>
</div>
<div id="buttons">
    <a href='#' onclick='addAttachmentInputToForm()'>Click here to show the overlay</a>
</div>

<div id="phoneOverlay" class="overlay">
    <div id="phonePopup">
        <div id="phoneTitle" class="popupHeader">PhoneDialog</div>
        <div id="phoneFields">
            <ul>
                <li>
                    <label class="inputLabel" for="countryCode">Код страны:</label>
                    <input class="inputField" type="number" name="countryCode" id="countryCode" required>
                </li>
                <li>
                    <label class="inputLabel" for="operatorCode">Код оператора:</label>
                    <input class="inputField" type="number" name="operatorCode" id="operatorCode" required>
                </li>
                <li>
                    <label class="inputLabel" for="phoneNumber">Телефон:</label>
                    <input class="inputField" type="text" name="phoneNumber" id="phoneNumber" required>
                </li>
                <li>
                    <label class="inputLabel" for="phoneType">Тип телефона:</label>
                    <input class="inputField" type="text" name="phoneType" id="phoneType">
                </li>
                <li>
                    <label class="inputLabel" for="phoneComment">Комментарий:</label>
                    <input class="inputField" type="text" name="phoneComment" id="phoneComment">
                </li>
            </ul>
        </div>
        <button type="button" onclick="document.contactController.submitPhone()">Применить</button>
        <button type="button" onclick="closeDialog('phone')">Закрыть</button>
    </div>
</div>

<div id="attachmentOverlay" class="overlay">
    <div id="attachmentPopup">
        <div id="attachmentTitle" class="popupHeader">AttachmentDialog</div>
        <div id="attachmentFields">
            <ul>
                <li>
                    <label class="inputLabel" for="attachChooser">Выберите файл:</label>
                    <input class="inputField" type="file" onchange="setFileName(this.value)" name="attachChooser" id="attachChooser">
                </li>
                <li>
                    <label class="inputLabel" for="fileName">Имя файла:</label>
                    <input class="inputField" type="text" name="fileName" id="fileName">
                </li>
                <li>
                    <label class="inputLabel" for="attachComment">Комментарий:</label>
                    <input class="inputField" type="text" name="attachComment" id="attachComment">
                </li>
            </ul>
        </div>
        <button type="button" onclick="document.contactController.submitAttachment()">Применить</button>
        <button type="button" onclick="closeDialog('attachment')">Закрыть</button>
    </div>
</div>



</div>
</body>
</html>