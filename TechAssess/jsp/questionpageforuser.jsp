<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
    <head>
        <title>Exam</title>
        <link href="css/userpagestyling.css" rel="stylesheet" type="text/css">
         <link href="css/login.css" rel="stylesheet" type="text/css">
         <link href="css/row.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" integrity="sha384-  
            BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <script src="js/jquery-1.11.3.min.js"></script>
        <script src="js/parallex.js"></script>
         <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
  <body onload="updateClock(); setInterval('updateClock()', 1000 )">
        <c:if test="${null != insertQuestionMessage}">
           <c:out value="${insertQuestionMessage}"/>
        </c:if>
         <div id="grid"></div>
         <div class="content">
         <div class="page-header">
             <center><h1 class="title">TechAssess</h1></center>
         </div>
         <h5>Time Remaining:</h5><div id="countdown"></div>
         <div id="notifier"></div>
<script type="text/javascript">

  function display( notifier, str ) {
    document.getElementById(notifier).innerHTML = str;
  }
  
  function myFunction() {
	    if (confirm("Oops!!!Time Up!! your response were submitted.") == true) {
	       window.location = "gotouserpage";
	    }
	}
 
  function toMinuteAndSecond( x ) {
    return Math.floor(x/60) + ":" + x%60;
  }
        
  function setTimer( remain, actions ) {
    (function countdown() {
       display("countdown", toMinuteAndSecond(remain));         
       actions[remain] && actions[remain]();
       (remain -= 1) >= 0 && setTimeout(arguments.callee, 1000);
    })();
  }

  setTimer(1500, {
    10: function () { display("notifier", "Just 10 seconds to go"); },
     5: function () { display("notifier", "5 seconds left");        },
     0: function () { myFunction();      }
  });   

</script>
          <div class="usertab">
              <img src="img/userimage.png" alt="userimage"><h3><c:out value="${userName}"/></h3>
             </div>
             <div class="logout" style="float:right">
                 <a href="logout"class="btn btn-danger" title="logout"><span class="glyphicon glyphicon-log-out"></span></a> 
             </div>
              <center><h4><c:out value="${examName}"/></h4></center>
                 <div class="questiondiv">
                     <form:form action = "resultcalulation" method = "post" modelAttribute="exam">
                         <c:set var="count" value="1" scope="page" />
                         <c:forEach items="${exam.answers}" var="answer"  varStatus="status" >
                                 <c:set value="${exam.questions[status.index]}" var="que" />        
                                   <c:out value="${que.questionName}" /><br><br>
                                   <c:set value="${que.typeId}" var="type" />
                                   <c:forEach items="${que.getChoices()}" var="choice" >
                                          <c:if test="${type.typeId==1}" >
                                          <label>Answer:<input type="text" name="answer[${status.index}].choices.choiceId" /></label>
                                          </c:if>
                                          <c:if test="${type.typeId==2}" >
                                          <input type="radio" name="answer[${status.index}].choices.choiceId" /> <c:out value="${choice.getChoiceName()}" /> <br>
                                          </c:if>
                                          <c:if test="${type.typeId==3}" >
                                          <input type="checkBox" name="answer[${status.index}].choices.choiceId" /> <c:out value="${choice.getChoiceName()}" /> <br>
                                          </c:if>
                                     </c:forEach>
                                     <br>
                             </c:forEach>
                         <br><br><input id="button" type="submit" value="submit answers" class="btn btn-primary"/>
                     </form:form>
                 </div>
             </div>

    </body>
</html>