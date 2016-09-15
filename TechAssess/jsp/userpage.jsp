<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Exam Details</title>
<link href="css/userpagestyling.css" rel="stylesheet" type="text/css">
<link href="css/login.css" rel="stylesheet" type="text/css">
<link href="css/userpage.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"
	integrity="sha384-  
            BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<script src="js/jquery-1.11.3.min.js"></script>
<script src="js/sweetalert2.min.js"></script>
  <link rel="stylesheet" href="css/sweetalert2.min.css">
  <script src="js/sweetalert2.js"></script>
  <link rel="stylesheet" href="css/sweetalert2.css">
<script src="js/parallex.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<c:if test="${sessionScope['role'] == null}">
            <c:redirect url="loginpage"/>
 </c:if>
 <c:if test="${sessionScope['role'] != 'User'}">
            <c:redirect url="loginpage"/>
 </c:if>
<body>
	<c:if test="${ null != ExamMessage}">
		<script>
		 swal({ 
			  title: "Error",
			   text: "<c:out value="${ExamMessage}"/>",
			    type: "error" 
			  },function(isConfirm){
	                alert('ok');
	          });
	          $('.swal2-confirm').click(function(){
	                window.location.href = 'gotouserpage';
	       });
		</script>
	</c:if>
	<div id="grid"></div>
	<div class="content">
		<div class="page-header">
			<center>
				<h1 class="title">TechAssess</h1>
			</center>
		</div>
		<div class="usertab">
			<img src="img/userimage.png" alt="userimage">
			<h3 align="center">
				<c:out value="${userName}" />
			</h3>
		</div>
		<div class="logout" style="float: right">
			<a href="logout" class="btn btn-danger" title="logout"><span
				class="glyphicon glyphicon-log-out"></span></a>
		</div>
		<div id="header">
		    <h2 align="center">
					Welcome
					<c:out value="${userName}" />
		    </h2><br><br>
		</div>
		<h3 align="center">Exam to be Attended</h3> <br><br>
		<div class="content-container">
				<div id="content"></div>
				<c:set var="count" value="1"/>
				<div class="well">
				<table class="buttontable">
				    <c:forEach items="${exams}" var="exam">
						<tr><td><a id="load_home"
							href="#demo<c:out value="${Choice ? '' : count}"/>"
							class="btn btn-info btn-lg" data-toggle="collapse"><c:out
								value="${exam.getExamName()}" /></a></td></tr>
						<c:set var="count" value="${count + 1}"/>
				    </c:forEach>
				</table>
			    </div>
			    <c:set var="count" value="1"/>
			    <div id="form">
			        <c:forEach items="${exams}" var="exam">
						<form id="demo<c:out value="${Choice ? '' : count}"/>"
							action="taketest" class="collapse" method="post">
							<b>Instructions:</b><br> <p class="text-left">1.Examinations will be conducted during
							the allocated times shown in the examination timetable.<br>
							2.Handphones brought into the examination hall
							must be switched off at ALL times.
							<br> 3.Photography is NOT allowed in the examination hall at
							ALL times. <br> 4.All materials and/or devices which are
							found in violation of any examination regulations will be
							confiscated.<br> 5.The examination hall will be open for
							admission 10 minutes before the time scheduled for the
							commencement of the examination.<br> 6. You will not be
							admitted for the examination after one hour of the commencement
							of the examination.<br> 7.Do NOT continue to write after the
							examination has ended. You are to remain seated quietly while
							your answer scripts are being collected and counted. <br>
							8.No reference materials, in whatever format, are allowed.<br>
							9.You are to stay in the examination hall until the Chief
							Invigilator has given the permission to leave. Do NOT talk until
							you are outside of the examination hall.<br> 10. You may
							bring into the examination hall only those calculators that have
							been approved by the School.<br></p> <input
								type="checkbox" id="checkbox">Agreed and im ready to
								start the test<br> <label><input type="hidden"
								name="test" value="<c:out value="${exam.getExamId()}"/>" /></label><br>
							<input id="button" type="submit" value="START"
								class="btn btn-primary"/>
						</form>
						<c:set var="count" value="${count + 1}"/>
				</c:forEach>
			    </div>
		</div>
	</div>


</body>
</html>
