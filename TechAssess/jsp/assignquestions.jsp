<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
    <head>
        <title>CreatExam</title>
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" integrity="sha384-   
            BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous"/>
        <link rel="stylesheet" href="css/login.css">
        <link rel="stylesheet" href="css/bothtable.css">
        <link href='css/fonts.css' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="css/normalize.css">
        <link rel="stylesheet" href="css/style.css">
        <script src="js/jquery-1.11.3.min.js"></script>
        <script type="text/javascript" src="js/jssor.slider-21.1.5.mini.js"></script>
        <script src="js/parallex.js"></script>
    </head>
    <c:if test="${ErrorMessage != null }">
        <script type="text/javascript" name="javascript">
            alert("<c:out value='${ErrorMessage}'/>");
            window.location="adminpage";
         </script>
    </c:if>
     <c:if test="${allocateMessage != null }">
        <script type="text/javascript" name="javascript">
            alert("<c:out value='${allocateMessage}'/>");
            window.location="allocatequestionpage";
         </script>
    </c:if>
    <body>
        <div id="grid"></div>
        <div class="content">
           <div class="header">
               <h1 class="title">Tech Assess</h1>
           </div>
            <div class="logout" style="float:right">
                <a href="logout"class="btn btn-danger" title="logout"><span class="glyphicon glyphicon-log-out"></span></a> 
            </div><br><br><br><br>
        <center>
        <div class="center">
            <div class="allquestions" style="float:left">
                 <table class="employee" id="employee">
                     <tr style="text-align:center">
                         <th>QuestionId</th>
                         <th>QuestionName</th>
                         <th>QuestionTypeId</th>
                     </tr>
                     <c:if test="${questionList != null}">
                          <c:forEach items="${questionList}" var="question" >
                               <tr>
                                   <td> <c:out value="${question.getQuestionId()}" /></td>
                                   <td> <c:out value="${question.getQuestionName()}" /></td>
                                   <td> <c:out value="${question.getTypeId()}" /></td>
                               </tr>	
                          </c:forEach>
                     </c:if> 
                 </table>
             </div>
             <div class="allexams" style="float:right">
                 <table class="employee" id="employee">
                     <tr style="text-align:center">
                         <th>ExamId</th>
                         <th>ExamName</th>
                         <th>Duration</th>
                         <th>TotalQuestions</th>
                         <th>AllocatedQuestions</th>
                     </tr>
                     <c:if test="${examList != null}">
                          <c:forEach items="${examList}" var="exam" >
                               <tr>
                                   <td> <c:out value="${exam.getExamId()}" /></td>
                                   <td> <c:out value="${exam.getExamName()}" /></td>
                                   <td> <c:out value="${exam.getExamDuration()}" /></td>
                                   <td> <c:out value="${exam.getNoOfTotalQuestions()}" /></td>
                                   <td>
                                    <c:choose>
                                        <c:when test="${exam.getNoOfAllocatedQuestions() != null}">
                                             <c:out value="${exam.getNoOfAllocatedQuestions()}" />
                                        </c:when>
                                        <c:otherwise>
                                             <c:out value="0" />
                                        </c:otherwise>
                                    </c:choose>
                                  </td>
                               </tr>	
                          </c:forEach>
                     </c:if> 
                 </table>
             </div><br><br><br><br><br><br>
              <div class="form">
                <div class="tab-content" style="margin:25px">
                    <div id="signup">
                        <h1 style="color:white">AllocateQuestion</h1>
                        <form action="allocating" method="post">
                            <div class="field-wrap">
                                <label>
                                EnterExamId<span class="req">*</span>
                                </label>
                                <input type="text" name="examId"required autocomplete="off"/>
                            </div>
                            <div class="field-wrap">
                                <label>
                                FormQuestionIdToAllocate<span class="req">*</span>
                                </label>
                                <input type="text" name="fromQuestionId"required autocomplete="off"/>
                            </div>
                             <div class="field-wrap">
                                <label>
                                ToQuestionIdToAllocate<span class="req">*</span>
                                </label>
                                <input type="text" name="toQuestionId"required autocomplete="off"/>
                            </div>
                            <button type="submit" class="button button-block"/>Allocate</button>          
                        </form>
                    </div>
                    <div id="login">

                    </div>
                </div>
                <!-- tab-content -->
            </div>
            <!-- /form -->
            <script src='js/form.js'></script>
            <script src="js/index.js"></script>
         </div>
         </center>
       </div>
     </body>
   </html>