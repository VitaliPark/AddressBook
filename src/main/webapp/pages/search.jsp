
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
                    <input class="inputField firstInput" type="text" name="firstName" id="firstName">
                </li>
                <li>
                    <label class="inputLabel" for="secondName">Фамилия:</label>
                    <input class="inputField" type="text" name="secondName" id="secondName">
                </li>
                <li>
                    <label class="inputLabel" for="patronymicName">Отчество:</label>
                    <input class="inputField" name="patronymicName" id="patronymicName">
                </li>

                <li>
                    <label class="inputLabel" for="citizenship">Гражданство:</label>
                    <input class="inputField" type="text" name="citizenship" id="citizenship">
                </li>

                <li>
                    <label class="inputLabel" for="dateOfBirth">Дата Рождения:</label>
                    <input class="inputField" name="dateOfBirth" id="dateOfBirth" type="date" required>
                </li>

                <li>
                    <div class="register-switch">
                        <input type="radio" name="operatorType" value=">" id="more" class="register-switch-input">
                        <label for="more" class="register-switch-label">больше</label>
                        <input type="radio" name="operatorType" value="<" id="less" class="register-switch-input">
                        <label for="less" class="register-switch-label">меньше</label>
                        <input type="radio" name="operatorType" value="=" id="equal" class="register-switch-input" checked>
                        <label for="equal" class="register-switch-label">равно</label>
                    </div>
                </li>
            <li>
                <label>Пол:</label>
                <div class="register-switch">
                    <input type="radio" name="gender" value="G_F" id="gender_f" class="register-switch-input">
                    <label for="gender_f" class="register-switch-label">жен.</label>
                    <input type="radio" name="gender" value="G_M" id="gender_m" class="register-switch-input">
                    <label for="gender_m" class="register-switch-label">муж.</label>
                    <input type="radio" name="gender" value="" id="gender_u" class="register-switch-input" checked>
                    <label for="gender_u" class="register-switch-label">не важно</label>
                </div>
            </li>

             <li>
                <label> Семейной положение:</label>
                <div class="register-switch">
                    <input type="radio" name="maritalStatus" value="MS_M" id="ms_m" class="register-switch-input">
                    <label for="ms_m" class="register-switch-label">в браке</label>
                    <input type="radio" name="maritalStatus" value="MS_S" id="ms_s" class="register-switch-input">
                    <label for="ms_s" class="register-switch-label">не в браке</label>
                    <input type="radio" name="maritalStatus" value="" id="ms_u" class="register-switch-input" checked>
                    <label for="ms_u" class="register-switch-label">не важно</label>
                </div>
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
                <label class="inputLabel" for="postIndex">Индекс:</label>
                <input class="inputField" type="text" name="postIndex" id="postIndex">
            </li>
                <input type="submit" name="Submit" value="Поиск">
            </ul>
        </form>
        </div>

    </div>
</body>
</html>