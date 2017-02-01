<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<f:setLocale value="${sessionScope.language}"/>
<f:setBundle basename="locale" var="locale"/>
<f:message bundle="${locale}" key="locale.password" var="password"/>
<f:message bundle="${locale}" key="locale.confirm" var="confirm"/>
<f:message bundle="${locale}" key="locale.registration" var="registration"/>
<f:message bundle="${locale}" key="locale.welcome" var="welcome"/>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<c:url value="/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/css/style.css" />" rel="stylesheet">
    <title>Registration</title>
</head>
<body>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="text-center">${welcome}</h1>
            </div>
            <div class="modal-body">
                <form name="RegistrationForm" class="modal-body" method="post" action="controller" onSubmit="return validateRegistrationForm();">

                    <input type="hidden" name="command" value="registration" />

                    <div class="form-group">
                        <input type="email" class="form-control input-lg" name="email" id="email"  placeholder="Email"/>
                        <span class="err" id="err-email"></span>
                    </div>

                    <div class="form-group">
                        <input type="text" class="form-control input-lg" name="login" id="login" placeholder="Login"/>
                        <span class="err" id="err-login"></span>
                    </div>

                    <div class="form-group">
                        <input type="password" class="form-control input-lg" name="password" id="password" placeholder="${password}"/>
                        <span class="err" id="err-password"></span>
                    </div>

                    <div class="form-group">
                        <input type="password" class="form-control input-lg" name="confirm" id="confirm"  placeholder="${confirm}"/>
                        <span class="err" id="err-confirm"></span>
                    </div>

                    <div class="form-group">
                        <input type="submit" class="btn btn-block btn-lg btn-primary" value="${registration}"/>
                    </div>
                    <div class="login-register">
                        <a href="controller?command=view_login"><span class="glyphicon glyphicon-user"></span> Login</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="js/registration.js"></script>
</body>
</html>