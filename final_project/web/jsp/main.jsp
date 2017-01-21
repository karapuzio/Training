<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 20.01.2017
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<f:setLocale value="${requestScope.selectedLanguage}"/>
<f:setBundle basename="locale" var="locale"/>
<html>
<head>
    <title>Music center</title>
</head>
<body>
    <h3> Hello! </h3>
    <a href="controller?command=logout">Logout</a>
</body>
</html>
