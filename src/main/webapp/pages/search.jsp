
    <%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
    <html lang="ru">
        <head>
            <meta charset="utf-8" />
            <title>Search</title>
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
                            <h1><a href="index?command=getAllContacts">Адресная Книга</a></h1>
                        </div>
                    </li>
                    <li class="tab"><a href="index?command=createContact">Добавить Контакт</a></li>
                    <li class="tab activeTab" ><a href="index?command=showSearchPage">Поиск</a></li>
                    <li class="tab"><a href="">Отправить Письмо</a></li>
                </ul>
            </div>


        <div class="inputFields">
            <form action="index?command=searchContacts" method="post" accept-charset=«utf-8>
            <ul>
                <li>
                    <label class="inputLabel firstInput" for="firstName">Имя:</label>
                    <input class="inputField firstInput" type="text" name="firstName" id="firstName" required>
                </li>
                <li>
                    <label class="inputLabel" for="secondName">Фамилия:</label>
                    <input class="inputField" type="text" name="secondName" id="secondName" required>
                </li>
                <li>
                    <label class="inputLabel" for="patronymicName">Отчество:</label>
                    <input class="inputField" name="patronymicName" id="patronymicName">
                </li>
                <li>
                    <label class="inputLabel" for="dateOfBirth">Дата Рождения:</label>
                    <input class="inputField" name="dateOfBirth" id="dateOfBirth" type="date">
                </li>
            <li>
                <label>Пол:</label>
                <div class="register-switch">
                    <input type="radio" name="sex" value="female" id="sex_f" class="register-switch-input" checked>
                    <label for="sex_f" class="register-switch-label">жен.</label>
                    <input type="radio" name="sex" value="male" id="sex_m" class="register-switch-input">
                    <label for="sex_m" class="register-switch-label">муж.</label>
                    <input type="radio" name="sex" value="unknown" id="sex_u" class="register-switch-input">
                    <label for="sex_u" class="register-switch-label">неопр.</label>
                </div>
            </li>
            <li>
                <label class="inputLabel" for="citizenship">Гражданство:</label>
                <input class="inputField" type="text" name="citizenship" id="citizenship">
            </li>
                <li>
                <label class="inputLabel"> Семейной положение:</label>
                <select class="inputField">
                    <option name="maritalStatus" value = "notImportant">Не важно</option>
                    <option name="maritalStatus" value = "married">В браке</option>
                    <option name="maritalStatus" value = "single">Не замужем/Не женат</option>
                </select>
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
                <label class="inputLabel" for="houseNumber">Дом:</label>
                <input class="inputField" type="number" name="houseNumber" id="houseNumber">
            </li>
            <li>
                <label class="inputLabel" for="apartment">Квартира:</label>
                <input class="inputField" type="number" name="apartment" id="apartment">
            </li>
            <li>
                <label class="inputLabel" for="postIndex">Город:</label>
                <input class="inputField" type="text" name="postIndex" id="postIndex">
            </li>
                <input type="submit" name="Submit" value="Поиск">
            </ul>
        </form>
        </div>

    </div>
</body>
</html>