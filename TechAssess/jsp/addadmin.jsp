<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>CreatAdmin</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"
	integrity="sha384-   
            BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous" />
<link rel="stylesheet" href="css/login.css">
<link href='css/fonts.css' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/style.css">
<script src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/jssor.slider-21.1.5.mini.js"></script>
<script src="js/parallex.js"></script>
</head>
<body>
	<c:if test="${LogInMessage != null}">
		<script type='text/javascript' language='javascript'>
			alert("<c:out value='${LogInMessage}' />");
			window.location = "adminpage";
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
			<a href="adminpage" class="btn btn-success" title="logout">MainPage</span></a>
		</div>
		<center>
			<div class="center">
				<div class="inputform">
					<div class="form">
						<div class="tab-content" style="margin: 25px">
							<div id="signup">
								<h1 style="color: black">Admin Sign up</h1>
								<form action="adminRegisteration" method="post">
									<div class="top-row">
										<div class="field-wrap">
											<label> User Name<span class="req">*</span>
											</label> <input type="text" name="userName" required
												autocomplete="off" />
										</div>
										<div class="field-wrap">
											<label> MobileNumber<span class="req">*</span>
											</label> <input type="text" name="mobileNumber" required
												autocomplete="off" />
										</div>
									</div>
									<div class="field-wrap">
										<label> Email Address<span class="req">*</span>
										</label> <input type="email" name="emailId" required
											autocomplete="off" />
									</div>
									<div class="field-wrap">
										<label> Password<span class="req">*</span>
										</label> <input type="password" name="password" required
											autocomplete="off" />
									</div>
									<button type="submit" class="button button-block" />
									CreateAccount
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
</body>
</html>


