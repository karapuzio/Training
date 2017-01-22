<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 20.01.2017
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<f:setLocale value="${requestScope.language}"/>
<f:setBundle basename="locale" var="locale"/>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<c:url value="/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/css/style.css" />" rel="stylesheet">
    <title>Music center</title>
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
                    <form class="navbar-form navbar-left" action="/controller">
                        <input type="hidden" name="command" value="search" />
                        <div class="input-group">
                            <input type="text" class="form-control" name="search" id="search" placeholder="Search">
                            <div class="input-group-btn">
                                <button class="btn btn-default" type="submit">
                                    <i class="glyphicon glyphicon-search"></i>
                                </button>
                            </div>
                        </div>
                    </form>
                    <br><br><br><br>
                    <table class="table table-hover">
                        <%--<thead>--%>
                        <%--<tr>--%>
                            <%--<th></th>--%>
                            <%--<th></th>--%>
                            <%--<th>Song</th>--%>
                            <%--<th>Price</th>--%>
                            <%--<th>Add</th>--%>
                        <%--</tr>--%>
                        <%--</thead>--%>
                        <tbody>
                        <c:forEach var="song" items="${songs}" varStatus="status">
                            <tr>
                                <td>  <img src = "img/play.png" alt = "Play demo." height="20" width="20"> </td>
                                <td>  <img src = "img/pause.png" alt = "Pause demo." height="20" width="20"> </td>
                                <td><c:out value="${ song.name }" /></td>
                                <td><c:out value="${ song.cost }" /></td>
                                <td>  <img src = "img/plus.png" alt = "Add to basket." height="20" width="20"> </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </section>
            </div>
            <div class="col-sm-3 sidenav">
                <aside class = "top_chart">
                    <h3> Top songs.</h3>
                    <div class="list-group">
                        <a href="#" class="list-group-item">Anberlin - Feel The Good Drag</a>
                        <a href="#" class="list-group-item">Anberlin - True Fait</a>
                        <a href="#" class="list-group-item">Rise Agains - Savior</a>
                    </div>
                </aside>
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
</body>
</html>
