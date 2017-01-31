<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 22.01.2017
  Time: 20:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<f:setLocale value="${sessionScope.language}"/>
<f:setBundle basename="locale" var="locale"/>
<f:message bundle="${locale}" key="locale.numberOfOrders" var="numberOfOrders"/>
<f:message bundle="${locale}" key="locale.discount" var="discount"/>
<f:message bundle="${locale}" key="locale.cash" var="cash"/>
<f:message bundle="${locale}" key="locale.randomSong" var="randSong"/>
<f:message bundle="${locale}" key="locale.addFunds" var="addFunds"/>
<f:message bundle="${locale}" key="locale.close" var="close"/>
<f:message bundle="${locale}" key="locale.add" var="add"/>
<f:message bundle="${locale}" key="locale.cost" var="cost"/>
<f:message bundle="${locale}" key="locale.card" var="card"/>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<c:url value="/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/css/style.css" />" rel="stylesheet">
    <title>User</title>
</head>
<body>
    <c:import url="header.jsp" />
    <div class="container-fluid text-center">
        <div class="row content">
            <div class="col-sm-3 sidenav">
                <c:import url="../additional/carousel.jsp" />
            </div>
            <div class="col-sm-6 text-left">
                <section>
                    <table class="table table-hover">
                        <tbody>
                        <tr>
                            <td>Login :</td>
                            <td><c:out value="${sessionScope.currentUser.login}" /></td>
                        </tr>
                        <tr>
                            <td>Email :</td>
                            <td><c:out value="${sessionScope.currentUser.email}" /></td>
                        </tr>
                        <tr>
                            <td>${numberOfOrders} : :</td>
                            <td><c:out value="${sessionScope.currentUser.numberOfOrders}" /></td>
                        </tr>
                        <tr>
                            <td>${discount} :</td>
                            <td><c:out value="${sessionScope.currentUser.discount} %" /></td>
                        </tr>
                        <tr>
                            <td>${cash} :  <c:import url="addFunds.jsp" /></a></td>
                            <td><c:out value="${sessionScope.currentUser.cash} $"/></td>
                        </tr>
                        </tbody>
                    </table>
                    <%--<div class="modal-content">--%>
                        <%--<div class="modal-header">--%>
                            <%--<button type="button" class="close" data-dismiss="modal">&times;</button>--%>
                            <%--<h1 class="text-center">${addFunds}</h1>--%>
                        <%--</div>--%>
                        <%--<div class="modal-body">--%>
                            <%--<form class="modal-body" name="AddFundsForm" action="controller" method="POST" onsubmit="validateFundsForm();">--%>

                                <%--<input type="hidden" name="command" value="add_funds" />--%>

                                <%--<div class="form-group">--%>
                                    <%--<input type="number" class="form-control input-lg" name="card" id="card" placeholder="${card}"/>--%>
                                    <%--<span class="err" id="err-card"></span>--%>
                                <%--</div>--%>

                                <%--<div class="form-group">--%>
                                    <%--<input type="number" class="form-control input-lg" name="cvv" id="cvv" placeholder="CVV"/>--%>
                                    <%--<span class="err" id="err-cvv"></span>--%>
                                <%--</div>--%>

                                <%--<div class="form-group">--%>
                                    <%--<input type="number" step="0.01" min="0.01" max="100" class="form-control input-lg" name="cash" id="cash" placeholder="${cash}"/>--%>
                                    <%--<span class="err" id="err-cash"></span>--%>
                                <%--</div>--%>

                                <%--<div class="form-group">--%>
                                    <%--<input type="submit" class="btn btn-block btn-lg btn-primary" value="${add}"/>--%>
                                <%--</div>--%>
                            <%--</form>--%>
                        <%--</div>--%>
                        <%--<div class="modal-footer">--%>
                            <%--<button type="button" class="btn btn-block btn-lg btn-primary" data-dismiss="modal">${close}</button>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                </section>
            </div>
            <div class="col-sm-3 sidenav">
                <p>${randSong}</p>
                <ctg:randomSong/>
            </div>
        </div>
    </div>

    <footer class="container-fluid text-center">
        <ctg:footer/>
    </footer>
    <script type="text/javascript" src="js/funds.js"></script>
</body>
</html>
