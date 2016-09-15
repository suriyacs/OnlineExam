<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
    <head>
    <c:if test="${ null == role }">
    <c:redirect url="loginpage"/>
</c:if>
<c:if test="${ role == 'User' }">
    <c:redirect url="gotouserpage"/>
</c:if>
        <title>Assign Question to Exam</title>
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" integrity="sha384-   
            BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous"/>
        <link rel="stylesheet" href="css/login.css">
        <link rel="stylesheet" href="css/bothtable.css">
        <link href='css/fonts.css' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="css/normalize.css">
        <script type="text/javascript" src="js/jssor.slider-21.1.5.mini.js"></script>
        <script src="js/sweetalert2.min.js"></script>
        <link rel="stylesheet" href="css/sweetalert2.min.css">
        <script src="js/sweetalert2.js"></script>
        <link rel="stylesheet" href="css/sweetalert2.css">
        <link rel="stylesheet" href="css/style.css">
        <script src="js/jquery-1.11.3.min.js"></script>
        <script type="text/javascript" src="js/jssor.slider-21.1.5.mini.js"></script>
        <script src="js/parallex.js"></script>
        <script>

        function validateAssignQuestionForm() {
        	var examId = document.forms["addQuestion"]["examId"].value;
        	var fromQuestionId = document.forms["addQuestion"]["fromQuestionId"].value;
        	var toQuestionId = document.forms["addQuestion"]["toQuestionId"].value;
        	var numbers = /^[0-9]+$/;
        	if(examId == null || examId == "" && fromQuestionId == null || fromQuestionId == "" && toQuestionId == null || toQuestionId == "") {
        		swal({ 
  		  		  title: "Error",
  		  		   text:"Please fillout all the fields",
  		  		    type: "error" 
  		        });
        		return false;
        	} else if(examId == null || examId == "") {
        		swal({ 
    		  		  title: "Error",
    		  		   text:"ExamId Can't be blank..!!",
    		  		    type: "error" 
    		    });
        		return false;
        	} else if(fromQuestionId == null || fromQuestionId == "") {
        		swal({ 
  		  		  title: "Error",
  		  		   text:"FromQuestionId Can't be blank..!!",
  		  		    type: "error" 
  		    });
        		return false;
        	} else if(toQuestionId == null || toQuestionId == "") {
        		swal({ 
  		  		  title: "Error",
  		  		   text:"ToQuestionId Can't be blank..!!",
  		  		    type: "error" 
  		    });
        		return false;
        	} else if ((!(examId.match(numbers)))) {
        		swal({ 
  		  		  title: "Error",
  		  		   text:"ExamId must be a number..!!",
  		  		    type: "error" 
  		    });
        		return false
        	} else if ((!(fromQuestionId.match(numbers)))) {
        		swal({ 
    		  		  title: "Error",
    		  		   text:"FromQuestionId must be a number..!!",
    		  		    type: "error" 
    		    });
        		return false
        	} else if ((!(toQuestionId.match(numbers)))) {
        		swal({ 
    		  		  title: "Error",
    		  		   text:"ToQuestionId must be a number..!!",
    		  		    type: "error" 
    		    });
        		return false
        	}
        }
        
    function scrolify(tblAsJQueryObject, height){
        var oTbl = tblAsJQueryObject;

        // for very large tables you can remove the four lines below
        // and wrap the table with <div> in the mark-up and assign
        // height and overflow property  
        var oTblDiv = $("<div/>");
        oTblDiv.css('height', height);
        oTblDiv.css('overflow','scroll');               
        oTbl.wrap(oTblDiv);

        // save original width
        oTbl.attr("data-item-original-width", oTbl.width());
        oTbl.find('thead tr td').each(function(){
            $(this).attr("data-item-original-width",$(this).width());
        }); 
        oTbl.find('tbody tr:eq(0) td').each(function(){
            $(this).attr("data-item-original-width",$(this).width());
        });                 


        // clone the original table
        var newTbl = oTbl.clone();

        // remove table header from original table
        oTbl.find('thead tr').remove();                 
        // remove table body from new table
        newTbl.find('tbody tr').remove();   

        oTbl.parent().parent().prepend(newTbl);
        newTbl.wrap("<div/>");

        // replace ORIGINAL COLUMN width                
        newTbl.width(newTbl.attr('data-item-original-width'));
        newTbl.find('thead tr td').each(function(){
            $(this).width($(this).attr("data-item-original-width"));
        });     
        oTbl.width(oTbl.attr('data-item-original-width'));      
        oTbl.find('tbody tr:eq(0) td').each(function(){
            $(this).width($(this).attr("data-item-original-width"));
        });                 
    }

    $(document).ready(function(){
        scrolify($('#tblNeedsScrolling'), 260); 
        scrolify($('#tbl1NeedsScrolling'), 260);// 160 is height
    });


    </script>
    </head>
    <body>
    <c:if test="${ErrorMessage != null }">
        <script>
        swal({ 
  		  title: "Error",
  		   text: "<c:out value="${ErrorMessage}"/>",
  		    type: "error" 
  		  },function(isConfirm){
                  alert('ok');
            });
            $('.swal2-confirm').click(function(){
                  window.location.href = 'adminpage';
            });
         </script>
    </c:if>
     <c:if test="${allocateMessage != null }">
        <script>
        swal({ 
    		  title: "GoodJob!",
    		   text: "<c:out value="${allocateMessage}"/>",
    		    type: "success" 
    		  },function(isConfirm){
                    alert('ok');
              });
              $('.swal2-confirm').click(function(){
                    window.location.href = 'allocatequestionpage';
              });
         </script>
    </c:if>
        <div id="grid"></div>
        <div class="content">
           <div class="header">
               <h1 class="title" style="color:black">Tech Assess</h1>
           </div>
            <div class="logout" style="float:left">
                <a href="adminpage"class="btn btn-success" title="logout">MainPage</a> 
            </div>
            <div class="logout" style="float:right">
                <a href="logout"class="btn btn-danger" title="logout"><span class="glyphicon glyphicon-log-out"></span></a> 
            </div><br><br>

		<div class="center">
			<div class="questiontable">
				<table border="1" width="100%" id="tblNeedsScrolling" height="20%";>
					<tr style="text-align: center">
						<th>QuestionId</th>
						<th>QuestionName</th>
						<th>QuestionTypeId</th>
					</tr>
					<tbody>
						<c:if test="${questionList != null}">
							<c:forEach items="${questionList}" var="question">
								<tr>
									<td><c:out value="${question.getQuestionId()}" /></td>
									<td><c:out value="${question.getQuestionName()}" /></td>
									<td><c:out value="${question.getTypeId()}" /></td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>
			<div class="allexams" style="float: right">
				<table border="1" width="100%" id="tbl1NeedsScrolling" height="20%">

					<tr style="text-align: center">
						<th>ExamId</th>
						<th>ExamName</th>
						<th>Duration</th>
						<th>TotalQuestions</th>
						<th>AllocatedQuestions</th>
					</tr>

					<tbody>
						<c:if test="${examList != null}">
							<c:forEach items="${examList}" var="exam">
								<tr>
									<td><c:out value="${exam.getExamId()}" /></td>
									<td><c:out value="${exam.getExamName()}" /></td>
									<td><c:out value="${exam.getExamDuration()}" /></td>
									<td><c:out value="${exam.getNoOfTotalQuestions()}" /></td>
									<td><c:choose>
											<c:when test="${exam.getNoOfAllocatedQuestions() != null}">
												<c:out value="${exam.getNoOfAllocatedQuestions()}" />
											</c:when>
											<c:otherwise>
												<c:out value="0" />
											</c:otherwise>
										</c:choose></td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>
			<br> <br> <br> <br> <br> <br> <br>
			<br> <br> <br> <br>
			<div class="form">
				<div class="tab-content" style="margin: 25px">
					<div id="signup">
						<h1 style="color: black">AllocateQuestion</h1>
						<form name="addQuestion" action="allocating" method="post" onsubmit="return validateAssignQuestionForm()">
							<div class="field-wrap">
								<label> EnterExamId<span class="req">*</span>
								</label> <input type="text" name="examId" autocomplete="off" />
							</div>
							<div class="field-wrap">
								<label> FormQuestionIdToAllocate<span class="req">*</span>
								</label> <input type="text" name="fromQuestionId"
									autocomplete="off" />
							</div>
							<div class="field-wrap">
								<label> ToQuestionIdToAllocate<span class="req">*</span>
								</label> <input type="text" name="toQuestionId"
									autocomplete="off" />
							</div>
							<button type="submit" class="button button-block">
							Allocate
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

	</div>
     </body>
   </html>