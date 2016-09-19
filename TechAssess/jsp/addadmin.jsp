<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Create Admin</title>
<link rel="icon" href="img/c-finger-pointing.png">
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"
	integrity="sha384-   
            BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous" />
<link rel="stylesheet" href="css/login.css">
<link href='css/fonts.css' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/sweetalert2.min.css">
<link rel="stylesheet" href="css/sweetalert2.css">
<script src="js/sweetalert2.min.js"></script>
<script src="js/sweetalert2.js"></script>
<script src="js/loginvalidation.js"></script>
<script src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/jssor.slider-21.1.5.mini.js"></script>
<script src="js/parallex.js"></script>
</head>
<c:if test="${null == sessionScope['role']}">
            <c:redirect url="loginpage"/>
 </c:if>
 <c:if test="${'User' == sessionScope['role'] }">
            <c:redirect url="gotouserpage"/>
 </c:if>
<body>
	<c:if test="${null != LogInMessage }">
		<script>
		 swal({ 
			  title: "Error",
			   text: "<c:out value="${LogInMessage}"/>",
			    type: "error" 
			  },function(isConfirm){
	                alert('ok');
	          });
	          $('.swal2-confirm').click(function(){
	                window.location.href = 'adminpage';
	          });
		</script>
	</c:if>
	<c:if test="${null != SuccessMessage }">
		<script>
		 swal({ 
			  title: "GoodJob!",
			   text: "<c:out value="${SuccessMessage}"/>",
			    type: "success" 
			  },function(isConfirm){
	                alert('ok');
	          });
	          $('.swal2-confirm').click(function(){
	                window.location.href = 'adminpage';
	          });
		</script>
	</c:if>
	<div id="grid"></div>
	<div class="content">
		<h1 class="title">Tech Assess</h1>
		<div class="logout" style="float: right">
			<a href="logout" class="btn btn-danger" title="logout"><span
				class="glyphicon glyphicon-log-out"></span></a>
		</div>
		<div class="logout" style="float: left">
			<a href="adminpage" class="btn btn-success" title="logout">Main Page</span></a>
		</div>
		<center>
			<div class="center">
				<div class="inputform">
					<div class="form">
						<div class="tab-content" style="margin: 25px">
							<div id="signup">
								<h1 style="color: black">Admin Sign up</h1>
								<form name="user" action="adminRegisteration" method="post" onsubmit="return validateUserRegisterationForm()">
									<div class="top-row">
										<div class="field-wrap">
											<label> User Name<span class="req">*</span>
											</label> <input type="text" name="userName"
												autocomplete="off" />
										</div>
										<div class="field-wrap">
											<label> Mobile Number<span class="req">*</span>
											</label> <input type="text" name="mobileNumber"
												autocomplete="off" />
										</div>
									</div>
									<div class="field-wrap">
										<label> Email Address<span class="req">*</span>
										</label> <input type="email" name="emailId"
											autocomplete="off" />
									</div>
									<div class="field-wrap">
										<label> Password<span class="req">*</span>
										</label> <input type="password" name="password"
											autocomplete="off" />
									</div>
									<button type="submit" class="button button-block">
									Create Account
									</button>
								</form>
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
			</center>
			</div>
</body>
</html>


