

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>Welcome Admin</title>
        <link rel="icon" href="img/c-finger-pointing.png">
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"
            integrity="sha384-   
            BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
            crossorigin="anonymous" />
        <link rel="stylesheet" href="css/index.css">
    </head>
    <body>
        <c:if test="${null == sessionScope['role']}">
            <c:redirect url="loginpage"/>
        </c:if>
        <c:if test="${'User' == sessionScope['role']}">
            <c:redirect url="gotouserpage"/>
        </c:if>
        <div class="logout" style="float: right">
            <a href="logout" class="btn btn-danger" title="logout"><span
                class="glyphicon glyphicon-log-out"></span></a>
        </div>
        <div class="header">
            <h1 class="title">Tech Assess</h1>
        </div>
        <br>
        <br>
        <br>
        <div class="container">
            <div class="row">
                <a href="insertadmin" class="firstcolumn">
                    <div class="col-xs-2 row-height">
                        <h3>Add Admin</h3>
                    </div>
                </a>
                <div class="col-xs-1"></div>
                <a href="insertquestion" class="firstcolumn">
                    <div class="col-xs-2 middle">
                        <h3>Add Question</h3>
                    </div>
                </a>
                <div class="col-xs-1"></div>
                <a href="insertexamdetails" class="firstcolumn">
                    <div class="col-xs-2 row-height">
                        <h3>Add NewTest</h3>
                    </div>
                </a>
                <div class="col-xs-1"></div>
                <a href="allocatequestionpage" class="firstcolumn">
                    <div class="col-xs-3 row-height">
                        <h3>Assign Questions</h3>
                    </div>
                </a>
            </div>
        </div>
        <div>
    </body>
</html>

