<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 23.01.2017
  Time: 12:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<f:setLocale value="${requestScope.language}"/>
<f:setBundle basename="locale" var="locale"/>
<f:message bundle="${locale}" key="locale.topSong" var="topSong"/>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<c:url value="/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/css/style.css" />" rel="stylesheet">
    <title>${topSong}</title>
</head>
<body>
<aside class = "top_chart">
    <h3> ${topSong}.</h3>
    <div class="list-group">
        <a href="#" class="list-group-item">Anberlin - Feel The Good Drag</a>
        <a href="#" class="list-group-item">Anberlin - True Fait</a>
        <a href="#" class="list-group-item">Rise Agains - Savior</a>
    </div>
</aside>
</body>
</html>
