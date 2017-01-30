<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 30.01.2017
  Time: 10:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<f:setLocale value="${requestScope.language}"/>
<f:setBundle basename="locale" var="locale"/>
<f:message bundle="${locale}" key="locale.song" var="song"/>
<f:message bundle="${locale}" key="locale.commentary" var="commentary"/>
<f:message bundle="${locale}" key="locale.add" var="add"/>
<f:message bundle="${locale}" key="locale.randomSong" var="randSong"/>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<c:url value="/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/css/style.css" />" rel="stylesheet">
    <title>${song}</title>
</head>
<body>
<c:import url="headerAdmin.jsp" />
<div class="container-fluid text-center">
    <div class="row content">
        <div class="col-sm-3 sidenav">
            <c:import url="../additional/carousel.jsp" />
            <p><a href="#">Link</a></p>
        </div>
        <div class="col-sm-6 text-left">
            <section>
                <table class="table table-hover">
                    <tbody>
                        <tr>
                            <td><a href="controller?command=view_song&songId=${selected.id}">
                                <span class="glyphicon glyphicon-music-alt"></span> ${selectedSong.performance.name} - ${selectedSong.name}</a>
                            </td>
                            <td>
                                <audio controls>
                                        <%--<source src="audio/music.ogg" type="audio/ogg; codecs=vorbis">--%>
                                    <source src="/music/3.mp3" type="audio/mpeg">
                                        <%--<a href="3.mp3">Скачайте музыку</a>.--%>
                                </audio>
                            </td>

                            <td><c:out value="${ selectedSong.cost }$" /></td>

                            <td><c:import url="editSong.jsp" /></td>

                            <td><a href="controller?command=delete_song&songId=${selectedSong.id}"><span class="glyphicon glyphicon-minus"></span></a></td>
                        </tr>
                    </tbody>
                </table>
                <br>
                <ul class="list-group">
                    <c:forEach var="comment" items="${comments}" varStatus="status">
                        <li class="list-group-item">
                            <h5><b>${comment.user.login}, ${comment.date}</b></h5>
                                ${comment.content}<span class="badge"></span>
                        </li>
                    </c:forEach>
                </ul>
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
</html>
