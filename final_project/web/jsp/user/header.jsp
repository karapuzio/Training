<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 22.01.2017
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<f:setLocale value="${sessionScope.language}"/>
<f:setBundle basename="locale" var="locale"/>
<f:message bundle="${locale}" key="locale.home" var="home"/>
<f:message bundle="${locale}" key="locale.user" var="user"/>
<f:message bundle="${locale}" key="locale.logout" var="logout"/>
<f:message bundle="${locale}" key="locale.basket" var="basket"/>
<f:message bundle="${locale}" key="locale.contact" var="contact"/>
<f:message bundle="${locale}" key="locale.topSong" var="topSong"/>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<c:url value="/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/css/style.css" />" rel="stylesheet">
    <title>Header</title>
</head>
<body>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#"><span class="glyphicon glyphicon-music"></span></a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li class="active"><a href="controller?command=view_home"><span class="glyphicon glyphicon-home"></span> ${home}</a></li>
                <li><a href="controller?command=view_contact">${contact}</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="controller?command=view_basket&userId=${sessionScope.currentUser.id}"><span class="glyphicon glyphicon-shopping-cart"></span> ${basket}</a></li>
                <li><a href="controller?command=view_user"><span class="glyphicon glyphicon-user"></span> ${user} ${sessionScope.currentUser.login}</a></li>
                <li><a href="controller?command=logout"><span class="glyphicon glyphicon-log-out"></span> ${logout}</a></li>
            </ul>
        </div>
    </div>
</nav>
</body>
</html>
