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
<%@ taglib prefix="ctg" uri="customtags" %>
<f:setLocale value="${requestScope.language}"/>
<f:setBundle basename="locale" var="locale"/>
<f:message bundle="${locale}" key="locale.musicCenter" var="musicCenter"/>
<f:message bundle="${locale}" key="locale.search" var="search"/>
<f:message bundle="${locale}" key="locale.randomSong" var="randSong"/>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<c:url value="/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/css/style.css" />" rel="stylesheet">
    <title>${musicCenter}</title>
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
                    <form class="navbar-form navbar-left" action="controller" method="POST">
                        <input type="hidden" name="command" value="search" />
                        <input type="hidden" name="role" value="${sessionScope.currentUser.role}" />
                        <div class="input-group">
                            <input type="text" class="form-control" name="search" id="search" placeholder=${search}>
                            <div class="input-group-btn">
                                <button class="btn btn-default" type="submit">
                                    <i class="glyphicon glyphicon-search"></i>
                                </button>
                            </div>
                        </div>
                    </form>
                    <br><br><br><br>
                    <table class="table table-hover">
                        <tbody>
                        <c:forEach var="curSong" items="${sessionScope.songs}" varStatus="status">
                            <tr>
                                <td><a href="controller?command=view_song&songId=${curSong.id}">
                                    <span class="glyphicon glyphicon-music-alt"></span> ${curSong.performance.name} - ${curSong.name}</a>
                                </td>
                                <td>
                                    <audio controls>
                                        <%--<source src="audio/music.ogg" type="audio/ogg; codecs=vorbis">--%>
                                        <source src="/music/5.mp3" type="audio/mpeg">
                                        <%--<a href="3.mp3">Скачайте музыку</a>.--%>
                                    </audio>
                                </td>
                                <td><c:out value="${ curSong.cost }$" /></td>
                                <%--<td>  <img src = "img/plus.png" alt = "Add to basket." height="20" width="20"> </td>--%>
                                <td><a href="controller?command=add_to_basket&songId=${curSong.id}&userId=${sessionScope.currentUser.id}"><span class="glyphicon glyphicon-plus-sign"></span></a></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </section>
            </div>
            <div class="col-sm-3 sidenav">
                <h3>${randSong}</h3>
                <ctg:randomSong/>
            </div>
        </div>
    </div>

    <footer class="container-fluid text-center">
        <ctg:footer/>
    </footer>

</body>
</body>
</html>
