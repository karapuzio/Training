<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 27.01.2017
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<f:setLocale value="${sessionScope.language}"/>
<f:setBundle basename="locale" var="locale"/>
<f:message bundle="${locale}" key="locale.song" var="song"/>
<f:message bundle="${locale}" key="locale.editSong" var="editSong"/>
<f:message bundle="${locale}" key="locale.performance" var="performance"/>
<f:message bundle="${locale}" key="locale.close" var="close"/>
<f:message bundle="${locale}" key="locale.add" var="add"/>
<f:message bundle="${locale}" key="locale.edit" var="edit"/>
<f:message bundle="${locale}" key="locale.name" var="name"/>
<f:message bundle="${locale}" key="locale.release" var="release"/>
<f:message bundle="${locale}" key="locale.selectDemo" var="selectDemo"/>
<f:message bundle="${locale}" key="locale.selectText" var="selectText"/>
<f:message bundle="${locale}" key="locale.discount" var="discount"/>
<f:message bundle="${locale}" key="locale.cost" var="cost"/>
<f:message bundle="${locale}" key="locale.genre" var="genre"/>
<f:message bundle="${locale}" key="locale.search" var="search"/>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<c:url value="/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/css/style.css" />" rel="stylesheet">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <title>${editSong}</title>
</head>
<body>
    <c:import url="headerAdmin.jsp" />
    <!-- Modal -->
    <section>
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="text-center">${editSong}</h1>
                </div>
                <div class="modal-body">
                    <form class="modal-body" name="EditSongForm" action="controller" method="POST">

                        <input type="hidden" name="command" value="edit_song" />
                        <input type="hidden" name="editSongId" value="${selectedSong.id}" />

                        <div class="form-group">
                            <input type="text" class="form-control input-lg" name="performance" id="performance" placeholder="${selectedSong.performance.name}"/>
                        </div>

                        <div class="form-group">
                            <input type="text" class="form-control input-lg" name="name" id="name" placeholder="${selectedSong.name}"/>
                        </div>

                        <div class="form-group">
                            <input type="file" accept="audio/*" class="form-control input-lg" name="pathToDemo" id="pathToDemo" placeholder="${selectDemo}"/>
                        </div>

                        <div class="form-group">
                            <input type="number" step="0.01" class="form-control input-lg" name="cost" id="cost" placeholder="${selectedSong.cost}"/>
                        </div>

                        <div class="form-group">
                            <input type="submit" class="btn btn-block btn-lg btn-primary" value="${edit}"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>>

</body>
</html>
