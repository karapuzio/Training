<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 22.01.2017
  Time: 17:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<f:setLocale value="${requestScope.language}"/>
<f:setBundle basename="locale" var="locale"/>
<f:message bundle="${locale}" key="locale.song" var="song"/>
<f:message bundle="${locale}" key="locale.commentary" var="commentary"/>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<c:url value="/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/css/style.css" />" rel="stylesheet">
    <title>${song}</title>
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
                                <td>  <img src = "img/play.png" alt = "Play demo." height="20" width="20"> </td>
                                <td>  <img src = "img/pause.png" alt = "Pause demo." height="20" width="20"> </td>
                                <td><c:out value="${selectedSong.name}" /></td>
                                <td><c:out value="${selectedSong.cost}" /></td>
                                <td>  <img src = "img/plus.png" alt = "Add to basket." height="20" width="20"> </td>
                            </tr>
                        </tbody>
                    </table>
                    <form action="/controller" method="POST">
                        <input type="hidden" name="command" value="add_comment" />
                        <div class="form-group">
                            <label for="comment">${commentary}:</label>
                            <textarea class="form-control" rows="5" name="comment" id="comment"></textarea>
                        </div>
                    </form>
                    <ul class="list-group">
                        <c:forEach var="comment" items="${comments}" varStatus="status">
                            <li class="list-group-item">
                                 ${comment.content}<span class="badge">${comment.like}</span>
                            </li>
                        </c:forEach>
                    </ul>
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
