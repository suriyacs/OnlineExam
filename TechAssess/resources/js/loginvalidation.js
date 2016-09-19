function validateUserRegisterationForm() {
		var userName = document.forms["user"]["userName"].value;
		var mobileNumber = document.forms["user"]["mobileNumber"].value;
		var phoneno = /^\d{10}$/;  
		var password = document.forms["user"]["password"].value;
		var emailId = document.forms["user"]["emailId"].value;
		var atpos = emailId.indexOf("@");
	    var dotpos = emailId.lastIndexOf(".");
	    if((emailId == null || emailId == "") && (password == null || password == "") && (mobileNumber == null || mobileNumber == "")&& (userName == null || userName == "")) {
	    	swal({ 
		  		  title: "Error",
		  		   text:"Please fillout all the fields",
		  		    type: "error" 
		            });
	    	return false;
	    }
	    else if (emailId == null || emailId == "") {
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
		    }
		 else if (mobileNumber == null || mobileNumber == "") {
		    	swal({ 
			  		 title: "Error",
			  		 text: "MobileNumber must be filled out",
			  		 type: "error"
			            });
		    	return false;
		    }
	    } 
	    function validateForm() {
	    var password = document.forms["myForm"]["password"].value;
	    var emailId = document.forms["myForm"]["emailId"].value;
	    var atpos = emailId.indexOf("@");
	    var dotpos = emailId.lastIndexOf(".");
	    if(emailId == null || emailId == "" && password == null || password == "") {
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
	    } else if (atpos < 1 || dotpos < (atpos + 2) || (dotpos + 2) >= emailId.length) {
	    	swal({ 
		  		 title: "Error",
		  		 text: "Not a valid e-mail address",
		  		 type: "error"
		         });
	        return false;InsertExamMessage
	    }
	}
