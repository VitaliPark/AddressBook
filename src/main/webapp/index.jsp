<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="constants.StringConstants"%>
<%@page import="constants.CommandTypes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
    <title>index</title>
</head>
<body>
<a href = "index?<%=StringConstants.command %>=<%= CommandTypes.GET_ALL_CONTACTS.getValue() %>">blabla</a>
<c:redirect url="index?command=getAllContacts"/>
</body>
</html>
