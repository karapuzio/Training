<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 22.01.2017
  Time: 20:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<f:setLocale value="${sessionScope.language}"/>
<f:setBundle basename="locale" var="locale"/>
<f:message bundle="${locale}" key="locale.numberOfOrders" var="numberOfOrders"/>
<f:message bundle="${locale}" key="locale.discount" var="discount"/>
<f:message bundle="${locale}" key="locale.cash" var="cash"/>
<f:message bundle="${locale}" key="locale.randomSong" var="randSong"/>
<f:message bundle="${locale}" key="locale.addFunds" var="addFunds"/>
<f:message bundle="${locale}" key="locale.close" var="close"/>
<f:message bundle="${locale}" key="locale.add" var="add"/>
<f:message bundle="${locale}" key="locale.cost" var="cost"/>
<f:message bundle="${locale}" key="locale.card" var="card"/>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<c:url value="/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/css/style.css" />" rel="stylesheet">
    <title>User</title>
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
                    <table class="table table-hover">
                        <tbody>
                        <tr>
                            <td>Login :</td>
                            <td><c:out value="${sessionScope.currentUser.login}" /></td>
                        </tr>
                        <tr>
                            <td>Email :</td>
                            <td><c:out value="${sessionScope.currentUser.email}" /></td>
                        </tr>
                        <tr>
                            <td>${numberOfOrders} : :</td>
                            <td><c:out value="${sessionScope.currentUser.numberOfOrders}" /></td>
                        </tr>
                        <tr>
                            <td>${discount} :</td>
                            <td><c:out value="${sessionScope.currentUser.discount} %" /></td>
                        </tr>
                        <tr>
                            <td>${cash} :  <c:import url="addFunds.jsp" /></a></td>
                            <td><c:out value="${sessionScope.currentUser.cash} $"/></td>
                        </tr>
                        </tbody>
                    </table>
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
    <script type="text/javascript" src="js/funds.js"></script>
</body>
</html>
