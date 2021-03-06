﻿<html lang="ru">
<head>
    <meta charset="utf-8" />
    <title>Add new contact</title>
    <link rel="stylesheet" href="style/table_style.css">
    <link rel="stylesheet" href="style/mail.css">
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
            <li class="tab"><a href="index?command=createContact">Добавить Контакт</a></li>
            <li class="tab" ><a href="index?command=showSearchPage">Поиск</a></li>
            <li class="tab  activeTab"><a href="">Отправить Письмо</a></li>
        </ul>
    </div>

    <div  id="emailForm">
        <form action="index?command=sendMail" method="post" >
        <ui>
            <li>
                <label class="mailLabel" for="mailTo" value="${requesScope.emails}">Кому:</label>
                <input class="mailInputField" type="text" name="mailTo" id="mailTo" value="${requestScope.emails}" readonly>
            </li>
            <li>
                <label class="mailLabel" for="mailSubject">Тема:</label>
                <input class="mailInputField" type="text" name="mailSubject" id="mailSubject">
            </li>
            <li>
                <label class="mailLabel" for="mailMessage">Сообщение:</label>
                <textarea class="messageArea" name="mailMessage" id="mailMessage"></textarea>
            </li>

            </li>
                <input type="submit" name="Submit" value="Отправить">
            </ul>
        </ui>
        </form>
    </div>




</div>
</body>

</html>