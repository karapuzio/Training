<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 22.01.2017
  Time: 19:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<f:setLocale value="${sessionScope.language}"/>
<f:setBundle basename="locale" var="locale"/>
<f:message bundle="${locale}" key="locale.contact" var="contact"/>
<f:message bundle="${locale}" key="locale.randomSong" var="randSong"/>
<f:message bundle="${locale}" key="locale.info" var="info"/>
<f:message bundle="${locale}" key="locale.contactEmail" var="contactEmail"/>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<c:url value="/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/css/style.css" />" rel="stylesheet">
    <title>${contact}</title>
</head>
<body>
    <c:import url="header.jsp" />
    <div class="container-fluid text-center">
        <div class="row content">
            <div class="col-sm-3 sidenav">
                <c:import url="../additional/carousel.jsp" />
            </div>
            <div class="col-sm-6 text-left">
                <section>
                    <h4 class="text-center">${info}</h4>
                    <br>
                    <h4 class="text-center">${contactEmail}</h4>
                </section>
            </div>
            <div class="col-sm-3 sidenav">
                <p>${randSong}</p>
                <ctg:randomSong/>
            </div>
        </div>
    </div>

    <footer class="container-fluid text-center">
        <ctg:footer/>
    </footer>
</body>
</html>
