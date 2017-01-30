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
<f:setLocale value="${sessionScope.language}"/>
<f:setBundle basename="locale" var="locale"/>
<f:message bundle="${locale}" key="locale.musicCenter" var="musicCenter"/>
<f:message bundle="${locale}" key="locale.welcome" var="welcome"/>
<f:message bundle="${locale}" key="locale.chooseLanguage" var="chooseLanguage"/>
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<c:url value="/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/css/style.css" />" rel="stylesheet">
    <title>${musicCenter}</title>
  </head>
  <body>
    <%--<c:redirect url="jsp/login.jsp"> </c:redirect>--%>
    <%--<jsp:forward page="jsp/login.jsp"/>--%>
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="text-center">${welcome}</h1>
          <h2 class="text-center">${chooseLanguage}</h2>
        </div>
        <div class="modal-body">
          <form class="modal-body" method="POST" action="/controller">
            <input type="hidden" name="command" value="language_change"/>
            <input type="hidden" name="language" value="EN"/>
            <input type="submit" class="btn btn-block btn-mg btn-link" value="ENGLISH"/>
            </form>
          <form class="modal-body" method="POST" action="/controller">
            <input type="hidden" name="command" value="language_change"/>
            <input type="hidden" name="language" value="NL"/>
            <input type="submit" class="btn btn-block btn-mg btn-link" value="NEDERLANDS"/>
          </form>
        </div>
      </div>
    </div>
  </body>
</html>
