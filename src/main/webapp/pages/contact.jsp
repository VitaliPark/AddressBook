﻿<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html lang="ru">
<head>
    <meta charset="utf-8" />
    <title>Add new contact</title>
    <link rel="stylesheet" href="style/table_style.css">
    <link rel="stylesheet" href="style/addContact.css">
    <script type="text/javascript" src="script/addContact.js"></script>
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
    <form action="">
        <c:set var="person" value="${requestScope.contact.getPerson()}" scope="request" />
        <c:set var="address" value="${requestScope.contact.getAddress()}" scope="request" />
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
                <input class="inputField" name="dateOfBirth" id="dateOfBirth" type="date"
                    value='<c:out value="${requestScope.contact.getBirthDate()}"/>'
                >
            </li>
            <li>
                <label>Пол:</label>
                <div class="register-switch">
                    <input type="radio" name="sex" value="F" id="sex_f" class="register-switch-input" checked>
                    <label for="sex_f" class="register-switch-label">жен.</label>
                    <input type="radio" name="sex" value="M" id="sex_m" class="register-switch-input">
                    <label for="sex_m" class="register-switch-label">муж.</label>
                    <input type="radio" name="sex" value="U" id="sex_u" class="register-switch-input">
                    <label for="sex_u" class="register-switch-label">неопр.</label>
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
                <select class="inputField">
                    <option value = "maried">В браке</option>
                    <option value = "single">Не замужем/Не женат</option>
                </select>
            </li>

            <li>
                <label class="inputLabel" for="webSite">Сайт:</label>
                <input class="inputField" type="text" name="webSite" id="webSite"
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
                    value='<c:out value="${requestScope.person. getWorkplace()}"/>'
                >
            </li>
            <hr>
            <li>
                <label class="inputLabel" for="country">Страна:</label>
                <input class="inputField" type="text" name="country" id="country">
            </li>
            <li>
                <label class="inputLabel" for="city">Город:</label>
                <input class="inputField" type="text" name="city" id="city">
            </li>
            <li>
                <label class="inputLabel" for="street">Улица:</label>
                <input class="inputField" type="text" name="street" id="street">
            </li>
            <li>
                <label class="inputLabel" for="house">Дом:</label>
                <input class="inputField" type="number" name="house" id="house">
            </li>
            <li>
                <label class="inputLabel" for="apartment">Квартира:</label>
                <input class="inputField" type="number" name="apartment" id="apartment">
            </li>
            <li>
                <label class="inputLabel" for="index">Город:</label>
                <input class="inputField" type="text" name="index" id="index">
            </li>
            <input type="submit" name="Submit" value="Submit">
        </ul>
    </form>
</div>

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

                <tr class = "row" id="3" ondblclick="showPhoneDialog('Редактирование телефона', 3)">
                    <td class = "text-left smallFont">375-29-7223343</td>
                    <td class = "text-left smallFont">мобильный</td>
                    <td class = "text-left smallFont">не звонить</td>

                <tr class = "row">
                    <td class = "text-left smallFont">375-29-7223343</td>
                    <td class = "text-left smallFont">мобильный</td>
                    <td class = "text-left smallFont">не звонить</td>
                </tr>


                </tbody>
            </table>
        </div>

    </article>

    <article>
        <div class="tableButtons">
            <button type="button" onclick="showPhoneDialog('Создание присоединения')">Создать</button>
            <button type="button">Удалить</button>
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
                <tbody class="table-hover">

                <tr class = "row">
                    <td class = "text-left smallFont">hello.scp</td>
                    <td class = "text-left smallFont">07.08.2014</td>
                    <td class = "text-left smallFont">DANGER</td>
                </tr>

                <tr class = "row">
                    <td class = "text-left smallFont">hello.pdf</td>
                    <td class = "text-left smallFont">07.08.2014</td>
                    <td class = "text-left smallFont">DANGER</td>
                </tr>

                </tbody>
            </table>
        </div>
    </article>
</div>
<div id="buttons">
    <a href='#' onclick='showPhoneDialog("Создание телефона")'>Click here to show the overlay</a>
</div>

<div id="phoneOverlay" class="overlay">
    <div id="phonePopup">
        <div id="phoneTitle" class="popupHeader">PhoneDialog</div>
        <div id="phoneFields">
            <ul>
                <li>
                    <label class="inputLabel" for="countryCode">Код страны:</label>
                    <input class="inputField" type="number" name="countryCode" id="countryCode">
                </li>
                <li>
                    <label class="inputLabel" for="operatorCode">Код оператора:</label>
                    <input class="inputField" type="number" name="operatorCode" id="operatorCode">
                </li>
                <li>
                    <label class="inputLabel" for="phoneNumber">Телефон:</label>
                    <input class="inputField" type="text" name="phoneNumber" id="phoneNumber">
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
        <button type="button" onclick="closeDialog()">Закрыть</button>
    </div>
</div>



</div>
</body>
</html>