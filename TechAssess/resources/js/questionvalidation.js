function validatefillupform() {
	var question = document.forms["fillup"]["questionname"].value;
	var answer = document.forms["fillup"]["answer"].value;
	var check = document.getElementById("checkbox").checked;
	var checkedValue = $('.checkbox:checked').val();
	if(question == null || question  == "" && answer == null || answer =="") {
		swal({ 
			  title: "Error",
			   text: "Please fillout all the fields",
			    type: "error" 
			});
		return false;
	} else if (question == null || question == "") {
		swal({ 
			  title: "Error",
			   text: "Please Provide Question",
			    type: "error" 
			});
		return false;
	} else if(!($("#checkbox").is(':checked'))){
		swal({ 
			  title: "Error",
			   text: "Please confirm the correct answer by clicking checkbox",
			    type: "error" 
			});
		return false;
	} else if(answer == null || answer =="") {
		swal({ 
			  title: "Error",
			   text: "Please provide answer",
			    type: "error" 
			});
	}
}

function checkChooseCheckBoxes() {
	var checkboxes = document.getElementsByName('checkboxofchoose');
	var vals = new Array();
	for (var i=0, n=checkboxes.length;i<n;i++) 
	{
	    if (checkboxes[i].checked) 
	    {
	        vals[i] = checkboxes[i].value;
	    }
	}
	if(vals.length <= 0) {
		swal({ 
			  title: "Error",
			   text: "Please Choose One Correct Answer",
			    type: "error" 
	   });
		return false;
	}
 }
function checkMultipleCheckBoxes() {
	var checkboxes = document.getElementsByName('multipleCheckBox');
	var vals = new Array();
	for (var i=0, n=checkboxes.length;i<n;i++) 
	{
	    if (checkboxes[i].checked) 
	    {
	        vals[i] = checkboxes[i].value;
	    }
	}
	if(vals.length <= 0) {
		swal({ 
			  title: "Error",
			   text: "Please Choose Atleast One Correct Answer",
			    type: "error" 
	   });
		return false;
	}
}
