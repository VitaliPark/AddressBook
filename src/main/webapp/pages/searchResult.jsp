
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
		  <li class="tab activeTab">
			<div id="index_title">
				<h1><a href="index?command=getAllContacts">Адресная Книга</a></h1>
			</div>
		  </li>
		  <li class="tab"><a href="index?command=createContact">Добавить Контакт</a></li>
		  <li class="tab" ><a href="index?command=showSearchPage">Поиск</a></li>
		  <li class="tab"><a href="">Отправить Письмо</a></li>
		</ul>
	</div>



	<div class="table">



	<table class="table-fill">
		<thead>
			<tr>
				<th class="text-left">Полное имя</th>
				<th class="text-left">Дата рождения</th>
				<th class="text-left">Адресс</th>
				<th class="text-left">Компания</th>
			</tr>
		</thead>
		<tbody class="table-hover">
            <c:forEach items="${requestScope.result}" var="contact">
            <tr ondblclick = "editContact(${contact.getPersonId()})" class = "row" id = "${contact.getPersonId()}">
                <td>
				<c:out value="${contact.getFullName()}"></c:out>
                </td>
                <td class = "text-left"><c:out value="${contact.getBirthDate()}"></c:out></td>
                <td class = "text-left"><c:out value="${contact.getAddress()}"></c:out></td>
                <td class = "text-left"><c:out value="${contact.getWorkplace()}"></c:out></td>
            </tr>
            </c:forEach>
		</tbody>
	</table>
	  
	</div>

	<input type = "button" onclick = "deleteContacts('index?command=deleteContacts')" value = "Удалить">
	
</div>
 </body>
 
 </html>