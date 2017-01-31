<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 24.01.2017
  Time: 17:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<f:setLocale value="${sessionScope.language}"/>
<f:setBundle basename="locale" var="locale"/>
<f:message bundle="${locale}" key="locale.song" var="song"/>
<f:message bundle="${locale}" key="locale.addSong" var="addSong"/>
<f:message bundle="${locale}" key="locale.performance" var="performance"/>
<f:message bundle="${locale}" key="locale.close" var="close"/>
<f:message bundle="${locale}" key="locale.add" var="add"/>
<f:message bundle="${locale}" key="locale.name" var="name"/>
<f:message bundle="${locale}" key="locale.release" var="release"/>
<f:message bundle="${locale}" key="locale.selectDemo" var="selectDemo"/>
<f:message bundle="${locale}" key="locale.selectText" var="selectText"/>
<f:message bundle="${locale}" key="locale.discount" var="discount"/>
<f:message bundle="${locale}" key="locale.cost" var="cost"/>
<f:message bundle="${locale}" key="locale.genre" var="genre"/>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<c:url value="/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/css/style.css" />" rel="stylesheet">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <title>${addSong}</title>
</head>
<body>
        <button type="button" class="btn btn-block btn-mg btn-link" data-toggle="modal" data-target="#addSongModal">${addSong}</button>
        <!-- Modal -->
        <div class="modal fade" id="addSongModal" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h1 class="text-center">${addSong}</h1>
                    </div>
                    <div class="modal-body">
                        <form class="modal-body" name="AddSongForm" action="controller" method="POST" onsubmit="validateAddSongForm();">

                            <input type="hidden" name="command" value="add_song" />

                            <div class="form-group">
                                <input type="text" class="form-control input-lg" name="performance" id="performance" placeholder="${performance}"/>
                                <span class="err" id="err-performance"></span>
                            </div>

                            <div class="form-group">
                                <input type="text" class="form-control input-lg" name="name" id="name" placeholder="${name}"/>
                                <span class="err" id="err-name"></span>
                            </div>

                            <div class="form-group">
                                <input type="text" class="form-control input-lg" name="genre" id="genre" placeholder="${genre}"/>
                                <span class="err" id="err-genre"></span>
                            </div>

                            <div class="form-group">
                                <input type="file" accept="audio/*" class="form-control input-lg" name="pathToDemo" id="pathToDemo" placeholder="${selectDemo}"/>
                            </div>

                            <div class="form-group">
                                <input type="number" step="0.01" min="0.01" max="30" class="form-control input-lg" name="cost" id="cost" placeholder="${cost}"/>
                                <span class="err" id="err-cost"></span>
                            </div>

                            <div class="form-group">
                                <input type="submit" class="btn btn-block btn-lg btn-primary" value="${add}"/>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-block btn-lg btn-primary" data-dismiss="modal">${close}</button>
                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript" src="js/song.js"></script>
</body>
</html>
