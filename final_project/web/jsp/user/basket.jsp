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
<f:message bundle="${locale}" key="locale.basket" var="basket"/>
<f:message bundle="${locale}" key="locale.pay" var="pay"/>
<f:message bundle="${locale}" key="locale.randomSong" var="randSong"/>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<c:url value="/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/css/style.css" />" rel="stylesheet">
    <title>${basket}</title>
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
                    <form class="modal-body" name="OrderSongsForm" action="controller" method="POST">
                        <input type="hidden" name="command" value="song_order" />
                        <table class="table table-hover">
                            <tbody>
                            <c:forEach var="order" items="${userOrders}" varStatus="status">
                                <c:if test="${order.isPayed eq '0'}">
                                    <tr>
                                        <td>
                                            <div class="checkbox">
                                                <label><input type="checkbox" name="orderSong" id="${order.id}" value="${order.id}"></label>
                                            </div>
                                        </td>
                                        <td><a href="controller?command=view_song&songId=${order.song.id}">
                                            <span class="glyphicon glyphicon-music-alt"></span> ${order.song.performance.name} - ${order.song.name}</a>
                                        </td>
                                        <td><c:out value="${ order.song.cost } $" /></td>
                                    </tr>
                                </c:if>
                            </c:forEach>
                            </tbody>
                        </table>
                        <div class="form-group">
                            <input type="submit" class="btn btn-block btn-lg btn-primary" value="${pay}"/>
                        </div>
                    </form>
                    <br/>
                    <table class="table table-hover">
                        <tbody>
                        <c:forEach var="order" items="${userOrders}" varStatus="status">
                            <c:if test="${order.isPayed eq '1'}">
                                <tr>
                                    <td><a href="controller?command=view_song&songId=${order.song.id}">
                                        <span class="glyphicon glyphicon-music-alt"></span> ${order.song.performance.name} - ${order.song.name}</a>
                                    </td>
                                    <td>${order.dateOfOrder}</td>
                                    <td><c:out value="${ order.song.cost } $" /></td>
                                </tr>
                            </c:if>
                        </c:forEach>
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
</body>
</html>
