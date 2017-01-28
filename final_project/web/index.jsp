<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 08.12.2016
  Time: 6:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<f:setLocale value="en" scope="session"/>
<f:setBundle basename="locale" var="locale"/>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
    <c:redirect url="jsp/login.jsp"> </c:redirect>
    <%--<jsp:forward page="jsp/login.jsp"/>--%>
  </body>
</html>
