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
<f:setLocale value="${requestScope.language}"/>
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
                <p><a href="#">Link</a></p>
                <p><a href="#">Link</a></p>
                <p><a href="#">Link</a></p>
            </div>
            <div class="col-sm-6 text-left">
                <section>
                    <form class="modal-body" name="OrderSongsForm" action="controller" method="POST">
                        <table class="table table-hover">
                            <tbody>
                            <c:forEach var="order" items="${userOrders}" varStatus="status">
                                <tr>
                                    <td>
                                        <div class="checkbox">
                                            <label><input type="checkbox" name="${order.song.id}" id="${order.song.id}" value="${order.song.id}"></label>
                                        </div>
                                    </td>
                                    <td><a href="controller?command=view_song&songId=${order.song.id}">
                                        <span class="glyphicon glyphicon-music-alt"></span> ${order.song.performance.name} - ${order.song.name}</a>
                                    </td>
                                    <td><c:out value="${ order.song.cost } $" /></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <div class="form-group">
                            <input type="submit" class="btn btn-block btn-lg btn-primary" value="${pay}"/>
                        </div>
                    </form>
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
