<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 22.01.2017
  Time: 19:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<f:setLocale value="${requestScope.language}"/>
<f:setBundle basename="locale" var="locale"/>
<f:message bundle="${locale}" key="locale.genre" var="genre"/>
<f:message bundle="${locale}" key="locale.addGenre" var="addGenre"/>
<f:message bundle="${locale}" key="locale.performance" var="performance"/>
<f:message bundle="${locale}" key="locale.addPerformance" var="addPerformance"/>
<f:message bundle="${locale}" key="locale.song" var="song"/>
<f:message bundle="${locale}" key="locale.addSong" var="addSong"/>
<f:message bundle="${locale}" key="locale.close" var="close"/>
<f:message bundle="${locale}" key="locale.add" var="add"/>
<f:message bundle="${locale}" key="locale.administration" var="administration"/>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<c:url value="/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/css/style.css" />" rel="stylesheet">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <title>${administration}</title>

</head>
<body>
    <c:import url="header.jsp" />
    <br/>
    <c:import url="addSong.jsp" />
    <br/>
    <c:import url="addGenre.jsp" />
    <br/>
    <c:import url="addPerformer.jsp" />
    <br/>
    <c:import url="addFunds.jsp" />
</body>
</html>
