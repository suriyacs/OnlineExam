<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    <body>
        <c:if test="${null != insertQuestionMessage}">
           <c:out value="${insertQuestionMessage}"/>
        </c:if>
         <div id="grid"></div>
         <div class="content">
         <div class="page-header">
         <center><h1 class="title">TechAssess</h1></center>
         </div>
          <div class="user">
              <img src="img/userimage.png" alt="userimage" style="width:18%;height:10%;"><h3><c:out value="${userName}"/></h3>
             </div>
             <div class="logout" style="float:right">
                 <a href="logout"class="btn btn-danger" title="logout"><span class="glyphicon glyphicon-log-out"></span></a> 
             </div>
<h4><c:out value="${examName}"/></h4>
                 <div class="center-container">
                     <form action = "taketest" method = "post">
                         <c:set var="count" value="1" scope="page" />
                         <c:forEach items="${questions}" var="question">
                             <c:if test="${question.getTypeId().getTypeName() == 'fillup'}">
                             <div class="row">
                                 <div class="col-sm-4">
                                     <h4><c:out value="${count}"/>. <c:out value="${ question.getQuestionName()}"/></h4><c:set var="count" value="${count + 1}" scope="page"/>
                                     <label>Answer:<input type="text" name="answer"/></label></td>
                                 </div>
                             </div><br>
                             </c:if>
                             <c:if test="${question.getTypeId().getTypeName() == 'Choose the correct answer'}">
                             <div class="row">
                                 <div class="col-sm-4">
                                     <h4><c:out value="${count}"/>. <c:out value="${ question.getQuestionName()}"/></h4><c:set var="count" value="${count + 1}" scope="page"/>
                                     <c:forEach items="${question.getChoices()}" var="choice">
                                         <label class="radio-inline"><input type="radio" name="radio"/> <c:out value="${choice.getChoiceName()}"/></label>
                                     </c:forEach>
                                 </div>
                             </div><br>
                             </c:if>
                             <c:if test="${question.getTypeId().getTypeName() == 'Multiple Answers'}">
                             <div class="row">
                                  <div class="col-sm-4">
                                      <h4><c:out value="${count}"/>. <c:out value="${question.getQuestionName()}"/></h4><c:set var="count" value="${count + 1}" scope="page"/>
                                      <c:forEach items="${question.getChoices()}" var="choice">
                                          <label class="checkbox-inline"><input type="checkbox" name="check"/> <c:out value="${choice.getChoiceName()}"/></label>
                                      </c:forEach>
                                  </div>
                                  </div><br>
                             </c:if>
                         </c:forEach>
                         <br><br><input id="button" type="submit" value="next" class="btn btn-primary"/>
                     </form>
                 </div>
             </div>

    </body>
</html>