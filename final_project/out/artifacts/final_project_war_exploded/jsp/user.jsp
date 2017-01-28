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
<f:setLocale value="${requestScope.language}"/>
<f:setBundle basename="locale" var="locale"/>
<f:message bundle="${locale}" key="locale.numberOfOrders" var="numberOfOrders"/>
<f:message bundle="${locale}" key="locale.discount" var="discount"/>
<f:message bundle="${locale}" key="locale.cash" var="cash"/>
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
                <c:import url="carousel.jsp" />
                <p><a href="#">Link</a></p>
                <p><a href="#">Link</a></p>
                <p><a href="#">Link</a></p>
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
                            <td><c:out value="${sessionScope.currentUser.discount}" /></td>
                        </tr>
                        <tr>
                            <td>${cash} :  <a href="controller?command=add_funds"><span class="glyphicon glyphicon-plus-sign"></span></a></td>
                            <%--<td><img src = "img/plus.png" alt = "Add to basket." height="20" width="20"></td>--%>
                            <td><c:out value="${sessionScope.currentUser.cash}" /></td>
                        </tr>
                        </tbody>
                    </table>
                </section>
            </div>
            <div class="col-sm-3 sidenav">
                <c:import url="topsong.jsp"/>
                <div class="well">
                    <p>ADS</p>
                </div>
            </div>
        </div>
    </div>

    <footer class="container-fluid text-center">
        <p>Footer Text</p>
    </footer>
</body>
</html>
