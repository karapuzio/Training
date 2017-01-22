<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<f:setLocale value="${requestScope.selectedLanguage}"/>
<f:setBundle basename="locale" var="locale"/>
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
                <h1 class="text-center">Welcome to Music Center</h1>
            </div>
            <div class="modal-body">
                <form name="RegistrationForm" class="modal-body" method="post" action="/controller">

                    <input type="hidden" name="command" value="registration" />

                    <div class="form-group">
                        <input type="text" class="form-control input-lg" name="email" id="email"  placeholder="Email"/>
                    </div>

                    <div class="form-group">
                        <input type="text" class="form-control input-lg" name="login" id="login" placeholder="Login"/>
                    </div>

                    <div class="form-group">
                        <input type="password" class="form-control input-lg" name="password" id="password" placeholder="Password"/>
                    </div>

                    <div class="form-group">
                        <input type="password" class="form-control input-lg" name="confirm" id="confirm"  placeholder="Confirm your Password"/>
                    </div>

                    <div class="form-group">
                        <input type="submit" class="btn btn-block btn-lg btn-primary" value="Registration"/>
                    </div>
                    <div class="login-register">
                        <a href="login.jsp">Login</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>