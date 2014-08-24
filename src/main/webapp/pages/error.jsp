
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8" />
    <title>Список контактов</title>
    <link rel="stylesheet" href="style/table_style.css">
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
            <li class="tab" ><a href="index?command=showSearchPage">Поиск</a></li>
            <li class="tab"><a href="">Отправить Письмо</a></li>
        </ul>
    </div>

    <div id="errorMessage">
        <p class="errorP">ERROR:<br></p>
        <c:out value="${requestScope.errorMessage}"/>
    </div>



</div>
</body>

</html>