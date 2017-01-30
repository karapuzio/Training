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
<f:setLocale value="${requestScope.language}"/>
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
        <%--btn btn-info btn-lg--%>
        <!-- Modal -->
        <div class="modal fade" id="addSongModal" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h1 class="text-center">${addSong}</h1>
                    </div>
                    <div class="modal-body">
                        <form class="modal-body" name="AddSongForm" action="controller" method="POST">

                            <input type="hidden" name="command" value="add_song" />

                            <div class="form-group">
                                <input type="text" class="form-control input-lg" name="performance" id="performance" placeholder="${performance}"/>
                            </div>

                            <div class="form-group">
                                <input type="text" class="form-control input-lg" name="name" id="name" placeholder="${name}"/>
                            </div>

                            <div class="form-group">
                                <input type="text" class="form-control input-lg" name="genre" id="genre" placeholder="${genre}"/>
                            </div>

                            <div class="form-group">
                                <input type="file" accept="audio/*" class="form-control input-lg" name="pathToDemo" id="pathToDemo" placeholder="${selectDemo}"/>
                            </div>

                            <%--<div class="form-group">--%>
                                <%--<input type="file" accept="text/*" class="form-control input-lg" name="pathToText" id="pathToText" placeholder="${selectText}"/>--%>
                            <%--</div>--%>

                        <%--<div class="form-group">--%>
                                <%--<label class="control-label">${selectDemo}</label>--%>
                                <%--<input id="pathToDemo" name="pathToDemo[]" type="file" multiple class="file-loading">--%>
                                <%--<script>--%>
                                    <%--$(document).on('ready', function() {--%>
                                        <%--$("#pathToDemo").fileinput({showCaption: false});--%>
                                    <%--});--%>
                                <%--</script>--%>
                            <%--</div>--%>

                            <%--<div class="form-group">--%>
                                <%--<label class="control-label">${selectText}</label>--%>
                                <%--<input id="pathToText" name="pathToText[]" type="file" multiple class="file-loading">--%>
                                <%--<script>--%>
                                    <%--$(document).on('ready', function() {--%>
                                        <%--$("#pathToText").fileinput({showCaption: false});--%>
                                    <%--});--%>
                                <%--</script>--%>
                            <%--</div>--%>

                            <div class="form-group">
                                <input type="number" class="form-control input-lg" name="discount" id="discount" placeholder="${discount}"/>
                            </div>

                            <div class="form-group">
                                <input type="number" class="form-control input-lg" name="cost" id="cost" placeholder="${cost}"/>
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

</body>
</html>
