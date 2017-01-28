<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 24.01.2017
  Time: 18:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<f:setLocale value="${requestScope.language}"/>
<f:setBundle basename="locale" var="locale"/>
<f:message bundle="${locale}" key="locale.performance" var="performance"/>
<f:message bundle="${locale}" key="locale.addPerformance" var="addPerformance"/>
<f:message bundle="${locale}" key="locale.close" var="close"/>
<f:message bundle="${locale}" key="locale.add" var="add"/>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<c:url value="/css/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="/css/style.css" />" rel="stylesheet">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <title>${addPerformance}</title>
</head>
<body>

    <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#addPerformanceModal">${addPerformance}</button>

    <!-- Modal -->
    <div class="modal fade" id="addPerformanceModal" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h1 class="text-center">${addPerformance}</h1>
                </div>
                <div class="modal-body">
                    <form class="modal-body" name="AddGenreForm" action="controller" method="POST">

                        <input type="hidden" name="command" value="add_performer" />

                        <div class="form-group">
                            <input type="text" class="form-control input-lg" name="performer" id="performer" placeholder=${performance}/>
                        </div>

                        <div class="form-group">
                            <input type="submit" class="btn btn-block btn-lg btn-primary" value=${add}/>
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
