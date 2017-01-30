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
    <%--<div class="container">--%>

        <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#editSongModal"><span class="glyphicon glyphicon-edit"></span></button>

        <!-- Modal -->
        <div class="modal fade" id="editSongModal" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h1 class="text-center">${editSong}</h1>
                    </div>
                    <div class="modal-body">
                        <form class="modal-body" name="EditSongForm" enctype="multipart/form-data" action="controller" method="POST">

                            <input type="hidden" name="command" value="edit_song" />
                            <input type="hidden" name="editSongId" value="${curSong.id}}" />

                            <div class="form-group">
                                <input type="text" class="form-control input-lg" name="performance" id="performance" placeholder="${curSong.performance.name}"/>
                            </div>

                            <div class="form-group">
                                <input type="text" class="form-control input-lg" name="name" id="name" placeholder="${curSong.name}"/>
                            </div>

                            <div class="form-group">
                                <input type="text" class="form-control input-lg" name="genre" id="genre" placeholder="${curSong.genre}"/>
                            </div>

                            <div class="form-group">
                                <input type="file" accept="audio/*" class="form-control input-lg" name="pathToDemo" id="pathToDemo" placeholder="${curSong.pathToDemo}"/>
                            </div>

                            <div class="form-group">
                                <input type="number" class="form-control input-lg" name="discount" id="discount" placeholder="${curSong.discount}"/>
                            </div>

                            <div class="form-group">
                                <input type="number" class="form-control input-lg" name="cost" id="cost" placeholder="${curSong.cost}"/>
                            </div>

                            <div class="form-group">
                                <input type="submit" class="btn btn-block btn-lg btn-primary" value="${edit}"/>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-block btn-lg btn-primary" data-dismiss="modal">${close}</button>
                    </div>
                </div>
            </div>
        </div>

    <%--</div>--%>
</body>
</html>
