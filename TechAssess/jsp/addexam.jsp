<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
    <head>
        <title>CreatExam</title>
        <link rel="icon" href="img/c-finger-pointing.png">
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"
            integrity="sha384-   
            BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
            crossorigin="anonymous" />
        <link rel="stylesheet" href="css/login.css">
        <link rel="stylesheet" href="css/bothtable.css">
        <link href='css/fonts.css' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="css/normalize.css">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/sweetalert2.css">
        <link rel="stylesheet" href="css/sweetalert2.min.css">
        <script src="js/sweetalert2.min.js"></script>
        <script src="js/sweetalert2.js"></script>
        <script src="js/jquery-1.11.3.min.js"></script>
        <script type="text/javascript" src="js/jssor.slider-21.1.5.mini.js"></script>
        <script src="js/parallex.js"></script>
    </head>
    <c:if test="${null == sessionScope['role']}">
        <c:redirect url="loginpage" />
    </c:if>
    <c:if test="${'User' ==  sessionScope['role']}">
        <c:redirect url="gotouserpage" />
    </c:if>
    <body>
        <c:if test="${ null != SuccessMessage }">
            <script>
                swal({
                	title : "GoodJob!",
                	text : "<c:out value="${SuccessMessage}"/>",
                	type : "success"
                }, function(isConfirm) {
                	alert('ok');
                });
                $('.swal2-confirm').click(function() {
                	window.location.href = 'allocatequestionpage';
                });
            </script>
        </c:if>
        <c:if test="${null !=  InsertExamMessage  }">
            <script>
                swal({
                	title : "Error",
                	text : "<c:out value="${InsertExamMessage}"/>",
                	type : "error"
                });
            </script>
        </c:if>
        <div id="grid"></div>
        <div class="content">
        <div class="header">
            <h1 class="title" style="color: black">Tech Assess</h1>
        </div>
        <div class="logout" style="float: right">
            <a href="logout" class="btn btn-danger" title="logout"><span
                class="glyphicon glyphicon-log-out"></span></a>
        </div>
        <div class="logout" style="float: left">
            <a href="adminpage" class="btn btn-success" title="logout">MainPage</span></a>
        </div>
        <center>
        <div class="center">
            <div class="inputform">
                <div class="form">
                    <div class="tab-content" style="margin: 25px">
                        <div id="signup">
                            <h1 style="color: black">CreateNewExam</h1>
                            <form:form action="addingexam" method="post" role="form"
                                class="form" modelAttribute="exam">
                                <div class="top-row">
                                    <div class="field-wrap">
                                        <label> Exam Name<span class="req">*</span>
                                        </label>
                                        <form:input path="examName" name="examname"
                                            required="required" class="form-control"
                                            pattern='[A-Za-z\\s]*' title="Enter words only"></form:input>
                                    </div>
                                    <div class="field-wrap">
                                        <label> Duration<span class="req">*</span>
                                        </label>
                                        <form:input path="examDuration" name="duration"
                                            required="required" class="form-control"></form:input>
                                    </div>
                                </div>
                                <div class="field-wrap">
                                    <label> NoOfTotalQuestionsForThisExam<span class="req">*</span>
                                    </label>
                                    <form:input path="NoOfTotalQuestions" name="TotalQuestions"
                                        required="required" class="form-control"></form:input>
                                </div>
                                <button type="submit" class="button button-block" />CreateExam</button>
                            </form:form>
                        </div>
                        <div id="login"></div>
                    </div>
                    <!-- tab-content -->
                </div>
                <!-- /form -->
                <script src='js/form.js'></script>
                <script src="js/index.js"></script>
            </div>
            <!--- input-form -->
        </div>
        <!--center-->
    </body>
</html>

