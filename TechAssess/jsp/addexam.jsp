<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Create Exam</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"
	integrity="sha384-   
            BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous" />
<link rel="stylesheet" href="css/login.css">
<link rel="stylesheet" href="css/bothtable.css">
<link href='css/fonts.css' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/style.css">
<script src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/jssor.slider-21.1.5.mini.js"></script>
<script src="js/parallex.js"></script>
</head>
<c:if test="${InsertExamMessage != null }">
	<script type="text/javascript" name="javascript">
		alert("<c:out value='${InsertExamMessage}'/>");
		window.location = "allocatequestionpage";
	</script>
</c:if>
<body>
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


