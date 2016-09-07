<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <title>add question</title>
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" integrity="sha384-   
            BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous"/>
	      <link rel="stylesheet" href="css/form-basic.css">
        <script src="js/jquery-1.11.3.min.js"></script>
        <script>
        $(function() {
        $('#colorselector').change(function(){
            $('.colors').hide();
            $('#' + $(this).val()).show();
        });
        });
        </script>
        <c:if test="${insertQuestionMessage != null }">
            <script type="text/javascript" language = "javascript">
                alert("<c:out value='${insertQuestionMessage}'/>");
                window.location = 'insertquestion';
            </script>
        </c:if>
</head>
<body>
        <div class="content">
        <div class="heading">
            <div class="head">
                 <h1 class="title">TechAssess</h1>
            </div>
        </div>
        <br>
        <center>
        <div class="select">
              <Select id="colorselector">
              <option value="fill">Fill In The Blanks</option>
              <option value="choose">Choose The Best Answer</option>
              <option value="Multiple">Multiple Answer Question</option>
              </Select>  
         </div>
         </center>
                  <div id="fill" class="colors" style="display:none">  
                   <form class="form-basic" method="post" action="fillintheblanks" name="fill">  
                        <div class="form-row">
                              <label>
                                <span>Question</span>
                                <textarea name="questionname"></textarea>
                              </label>
                           </div>
                         <div class="form-row">
                            <div class="answer"> 
                                 <label>
                                     <span>Answer</span>
                                     <input type="text" name="answer">
                                 </label>
                            </div> 
                            <div class="form-row">
                               <label>
                                  <span>IfCorrect</span>
                                 <input type="checkbox" name="checkbox">
                              </label>
                            </div>
                         </div>
                          <div class="form-row">
                              <button type="submit" class="btn btn-primary">Add</button>
                          </div>
                     </form>
            </div>
              <div id="choose" class="colors" style="display:none"> 
                 <form:form method="post" action="choosethebest" modelAttribute="Question">  
                      <div class="form-row">
                              <label>
                                <span>Question</span>
                                <form:textarea path ="question"></form:textarea>
                              </label>
                           </div>
                         <c:forEach items="${Question.choices}" var="choice" varStatus="status">
                         <div class="form-row">
                            <div class="answer"> 
                                 <label>
                                     <span>Choice1</span>
                                     <input name ="choices[${status.index}].choiceName" value="${choice.choiceName}" >
                                 </label>
                            </div> 
                            <div class="form-row">
                               <label>
                                  <span>IfCorrect</span>
                                 <input name="choices[${status.index}].isCorrect" value="${choice.isCorrect}">
                              </label>
                            </div>
                         </div>
                         </c:forEach>
                          <div class="form-row">
                               <button type="submit" class="btn btn-primary">Add</button>
                         </div>
                    </form:form>
                 </div>
               <div id="Multiple" class="colors" style="display:none"> 
               <form:form method="post" action="multiple" modelAttribute="Question">  
                    <div class="form-row">
                              <label>
                                <span>Question</span>
                                <form:textarea path ="question"></form:textarea>
                              </label>
                           </div>
                         <c:forEach items="${Question.choices}" var="choice" varStatus="status">
                         <div class="form-row">
                            <div class="answer"> 
                                 <label>
                                     <span>Choice1</span>
                                     <input name ="choices[${status.index}].choiceName" value="${choice.choiceName}" >
                                 </label>
                            </div> 
                            <div class="form-row">
                               <label>
                                  <span>IfCorrect</span>
                                 <input name="choices[${status.index}].isCorrect" value="${choice.isCorrect}">
                              </label>
                            </div>
                         </div>
                         </c:forEach>                         
                         <div class="form-row">
                               <button type="submit" class="btn btn-primary">Add</button>
                         </div>
                     </form:form>
              </div>
          </div>
     </body>
   </html>
