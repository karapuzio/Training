<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 26.01.2017
  Time: 0:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<f:setLocale value="${sessionScope.language}"/>
<f:setBundle basename="locale" var="locale"/>
<f:message bundle="${locale}" key="locale.addFunds" var="addFunds"/>
<f:message bundle="${locale}" key="locale.close" var="close"/>
<f:message bundle="${locale}" key="locale.add" var="add"/>
<f:message bundle="${locale}" key="locale.cost" var="cost"/>
<f:message bundle="${locale}" key="locale.card" var="card"/>
<f:message bundle="${locale}" key="locale.cash" var="cash"/>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<c:url value="/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/css/style.css" />" rel="stylesheet">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <title>${addFunds}</title>
</head>
<body>
        <button type="button" class="btn btn-block btn-mg btn-link" data-toggle="modal" data-target="#addFundsModal"><span class="glyphicon glyphicon-plus-sign"></span></button>

        <!-- Modal -->
        <div class="modal fade" id="addFundsModal" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h1 class="text-center">${addFunds}</h1>
                    </div>
                    <div class="modal-body">
                        <form class="modal-body" name="AddFundsForm" action="controller" method="POST" onsubmit="return validateFundsForm();">

                            <input type="hidden" name="command" value="add_funds" />

                            <div class="form-group">
                                <input type="number" class="form-control input-lg" name="card" id="card" placeholder="${card}"/>
                                <span class="err" id="err-card"></span>
                            </div>

                            <div class="form-group">
                                <input type="number" class="form-control input-lg" name="cvv" id="cvv" placeholder="CVV"/>
                                <span class="err" id="err-cvv"></span>
                            </div>

                            <div class="form-group">
                                <input type="number" step="0.01" min="0.01" max="100" class="form-control input-lg" name="cash" id="cash" placeholder="${cash}"/>
                                <span class="err" id="err-cash"></span>
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
        <script type="text/javascript" src="js/funds.js"></script>
</body>
</html>
