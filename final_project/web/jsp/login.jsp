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
<%@ taglib prefix="ctg" uri="customtags" %>
<f:setLocale value="${sessionScope.language}"/>
<f:setBundle basename="locale" var="locale"/>
<f:message bundle="${locale}" key="locale.password" var="password"/>
<f:message bundle="${locale}" key="locale.registration" var="registration"/>
<f:message bundle="${locale}" key="locale.welcome" var="welcome"/>
<f:message bundle="${locale}" key="locale.notCorrect" var="notCorrect"/>
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
    <section>
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="text-center">${welcome}</h1>
                </div>
                <div class="modal-body">
                    <form class="modal-body" name="LoginForm" action="controller" method="POST" onsubmit="return validateLoginForm();">

                        <input type="hidden" name="command" value="login" />

                        <div class="form-group">
                            <input type="text" class="form-control input-lg" name="login" id="login" placeholder="Login"/>
                            <span class="err" id="err-login"></span>
                        </div>

                        <div class="form-group">
                            <input type="password" class="form-control input-lg" name="password" id="password" placeholder="${password}"/>
                            <span class="err" id="err-password"></span>
                        </div>

                        <div class="form-group">
                            <input type="submit" class="btn btn-block btn-lg btn-primary" value="Login"/>
                        </div>

                        <div class="login-register">
                            <a href="controller?command=view_registration"><span class="glyphicon glyphicon-user"></span> ${registration}</a>
                        </div>
                    </form>
                    <c:if test="${isCorrect eq '0'}">
                        <h3 class="err text-center">${notCorrect}</h3>
                    </c:if>
                </div>
            </div>
        </div>
    </section>

    <script type="text/javascript" src="js/login.js"></script>
</body>
</html>
