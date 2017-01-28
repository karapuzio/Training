<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 20.01.2017
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<f:setLocale value="${requestScope.language}" scope="session"/>
<f:setBundle basename="locale" var="locale"/>
<f:message bundle="${locale}" key="locale.password" var="password"/>
<f:message bundle="${locale}" key="locale.registration" var="registration"/>
<html>
<head>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="<c:url value="/css/bootstrap.css" />" rel="stylesheet">
        <link href="<c:url value="/css/style.css" />" rel="stylesheet">
        <title>Login</title>
    </head>
</head>
<body>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="text-center">Welcome to Music Center</h1>
            </div>
            <div class="modal-body">
                <form class="modal-body" name="LoginForm" action="/controller" method="POST">

                    <input type="hidden" name="command" value="login" />

                    <div class="form-group">
                        <input type="text" class="form-control input-lg" name="login" id="login" placeholder="Login"/>
                    </div>

                    <div class="form-group">
                        <input type="password" class="form-control input-lg" name="password" id="password" placeholder=${password}/>
                    </div>

                    <div class="form-group">
                        <input type="submit" class="btn btn-block btn-lg btn-primary" value="Login"/>
                    </div>
                    <div class="login-register">
                        <%--<a href="controller?command=registration"><span class="glyphicon glyphicon-user"></span> ${registration}</a>--%>
                        <a href="registration.jsp">${registration}</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
