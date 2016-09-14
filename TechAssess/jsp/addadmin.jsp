<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Create Admin</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"
	integrity="sha384-   
            BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous" />
<link rel="stylesheet" href="css/login.css">
<link href='css/fonts.css' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="css/normalize.css">
<link rel="stylesheet" href="css/style.css">
<script src="js/sweetalert2.min.js"></script>
  <link rel="stylesheet" href="css/sweetalert2.min.css">
  <script src="js/sweetalert2.js"></script>
  <link rel="stylesheet" href="css/sweetalert2.css">
<script src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/jssor.slider-21.1.5.mini.js"></script>
<script src="js/parallex.js"></script>
<script type='text/javascript' language='javascript'>
function validateUserRegisterationForm() {
	var userName = document.forms["user"]["userName"].value;
	var mobileNumber = document.forms["user"]["mobileNumber"].value;
	var phoneno = /^\d{10}$/;  
	var password = document.forms["user"]["password"].value;
	var emailId = document.forms["user"]["emailId"].value;
	var atpos = emailId.indexOf("@");
    var dotpos = emailId.lastIndexOf(".");
    if(emailId == null || emailId == "" && password == null || password == "" && mobileNumber == null || mobileNumber == "" && userName == null || userName == "") {
    	swal({ 
	  		  title: "Error",
	  		   text:"Please fillout all the fields",
	  		    type: "error" 
	        });
    	return false;
    } else if (emailId == null || emailId == "") {
    	swal({ 
	  		 title: "Error",
	  		 text: "EmailId must be filled out",
	  		 type: "error"
	        });
        return false;
    } else if (password == null || password == "") {
    	swal({ 
	  		 title: "Error",
	  		 text: "Password must be filled out",
	  		 type: "error"
	        });
    	return false;
    } else if (mobileNumber == null || mobileNumber == "") {
    	swal({ 
	  		 title: "Error",
	  		 text: "Mobile Number must be filled out",
	  		 type: "error"
	        });
    	return false;
    } else if (userName == null || userName == "") {
    	swal({ 
	  		 title: "Error",
	  		 text: "user name must be filled out",
	  		 type: "error"
	         });
    	return false;
    } else if (atpos < 1 || dotpos < (atpos + 2) || (dotpos + 2) >= emailId.length) {
    	swal({ 
	  		 title: "Error",
	  		 text: "Invalid Email Id..!!Try Again!!",
	  		 type: "error"
	         });
        return false;
    } else if (!(mobileNumber.match(phoneno))) {
    	swal({ 
	  		 title: "Error",
	  		 text: "Invalid Mobile Number..!!Try Again!!",
	  		 type: "error"
	       });
        return false;  
    }
}
</script>
</head>
<body>
	<c:if test="${LogInMessage != null}">
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
	<c:if test="${SuccessMessage != null}">
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
			<a href="adminpage" class="btn btn-success" title="logout">MainPage</span></a>
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
											<label> MobileNumber<span class="req">*</span>
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
			</center>
			</div>
</body>
</html>


