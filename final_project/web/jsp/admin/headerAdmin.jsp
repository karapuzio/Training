<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 30.01.2017
  Time: 10:22
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
<f:message bundle="${locale}" key="locale.addSong" var="addSong"/>
<f:message bundle="${locale}" key="locale.addPerformance" var="addPerformance"/>
<f:message bundle="${locale}" key="locale.addGenre" var="addGenre"/>
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
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#"><span class="glyphicon glyphicon-music"></span></a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li class="active"><a href="controller?command=view_home"><span class="glyphicon glyphicon-home"></span> ${home}</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="controller?command=view_add_song">${addSong}</a></li>
                <li><a href="controller?command=view_add_genre">${addGenre}</a></li>
                <li><a href="controller?command=view_add_performance">${addPerformance}</a></li>
                <li><a href="controller?command=logout"><span class="glyphicon glyphicon-log-out"></span> ${logout}</a></li>
            </ul>
        </div>
    </div>
</nav>
</body>
</html>
