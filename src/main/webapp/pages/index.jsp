<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html lang="ru">
<head>
	<meta charset="UTF-8" />
	<title>Список контактов</title>
	<link rel="stylesheet" href="pages/table_style.css">
	<meta name="viewport" content="initial-scale=1.0; maximum-scale=1.0; width=device-width;">
</head>

<body>

<div id="index_wrapper">
	<div class="toolbar">
		<ul>
		  <li class="tab activeTab">
			<div id="index_title">
				<h1><a href="index.html">Contact Book</a></h1>
			</div>
		  </li>
		  <li class="tab"><a href="addContact.html">Add Contact</a></li>
		  <li class="tab" ><a href="">Search</a></li>
		  <li class="tab"><a href="">Send Email</a></li>
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
            <c:forEach items="${requestScope.allContacts}" var="contact">
            <tr>
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

	
	
</div>
 </body>
 
 </html>