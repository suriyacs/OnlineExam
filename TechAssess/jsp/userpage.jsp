<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>Employee Details</title>
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
    <body>
    <c:if test="${ null != ExamMessage}">
        <script type="text/javascript">
            alert("<c:out value="${ExamMessage }"/>");
            window.location = "gotouserpage";
        </script>
   </c:if>
         <div id="grid"></div>
         <div class="content">
          <div class="page-header">
             <center><h1 class="title">TechAssess</h1></center>
         </div>
         <div class="usertab">
              <img src="img/userimage.png" alt="userimage"><h3><c:out value="${userName}"/></h3>
             </div>
          <div class="logout" style="float:right">
                <a href="logout"class="btn btn-danger" title="logout"><span class="glyphicon glyphicon-log-out"></span></a> 
        </div>
             <div class="center-container">
                <div class="container">
                    <h2> Welcome <c:out value="${userName}"/></h2>
                   
                    <c:set var="count" value="1" scope="page" />
                    <c:forEach items="${exams}" var="exam">
                     <div class="btn-group btn-group-lg">
                    <div class="well">
                    <a href="#demo<c:out value="${Choice ? '' : count}"/>" class="btn btn-info btn-lg" data-toggle="collapse"><c:out value="${exam.getExamName()}"/></a>
                    <form id="demo<c:out value="${Choice ? '' : count}"/>" action="taketest" class="collapse" method="post">
                        Instructions:<br>
                              1.Examinations will be conducted during the allocated times shown in the examination timetable.<br>
                              <block>2.Handphones brought into the examination hall must be switched off at ALL times.</block><br>
                              3.Photography is NOT allowed in the examination hall at ALL times. <br>
                              4.All materials and/or devices which are found in violation of any examination regulations will be confiscated.<br>
                              5.The examination hall will be open for admission 10 minutes before the time scheduled for the commencement of the examination.<br>
                              6. You will not be admitted for the examination after one hour of the commencement of the examination.<br>
                              7.Do NOT continue to write after the examination has ended. You are to remain seated quietly while your answer scripts are being collected and counted. <br>
                              8.No reference materials, in whatever format, are allowed.<br>
                              9.You are to stay in the examination hall until the Chief Invigilator has given the permission to leave. Do NOT talk until you are outside of the examination hall.<br>
                              10. You may bring into the examination hall only those calculators that have been approved by the School.<br>
                             <label><input type="checkbox" id="checkbox">Agreed and im ready to start the test</label><br>
                        <label><input type="hidden" name="test" value="<c:out value="${exam.getExamId()}"/>"/></label><br>
                        <input id="button" type="submit" value="START" class="btn btn-primary"/>
                    </form>
                     <c:set var="count" value="${count + 1}" scope="page"/>
                     </div>
                      </div>
                     </c:forEach>
                     </div>
                </div>
             </div>
        

    </body>
</html>
