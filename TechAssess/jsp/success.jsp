<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>Employee Details</title>
        <link href="css/userpagestyling.css" rel="stylesheet" type="text/css">
    </head>
    <body>
       <c:if test="${null != insertQuestionMessage }">
           <c:out value="${insertQuestionMessage}"/>
       </c:if>
		 
             <div class="center-container">
                 <form action = "values" method = "post" modelAttribute="testId">
                     <input type="hidden" name="test" value="<c:out value="${value}"/>"/>
                     <c:out value="${question.getQuestionName()}"/><br>
                     <c:forEach items="${choices}" var="choice">
                         <c:out value="${ choice.getChoiceName()}"/>
                     </c:forEach>
                     <c:if test="${null == value }">
                      Instructions:<br>
                              1.aswertfeawfasdfasdfasdasdvcasdfasd.<br>
                              2.sADfAWFRASDFASDVASVASDGFAWRFASDVASDV<br>
                              3.ASDGAWFZSCBZCVBNDXHGGVNBXBHDGHSDFGDFGADFV<br>
                              4.aswertfeawfasdfasdfasdasdvcasdfasd.<br>
                              5.sADfAWFRASDFASDVASVASDGFAWRFASDVASDV<br>
                              6.ASDGAWFZSCBZCVBNDXHGGVNBXBHDGHSDFGDFGADFV<br>
                              7.aswertfeawfasdfasdfasdasdvcasdfasd.<br>
                              8.sADfAWFRASDFASDVASVASDGFAWRFASDVASDV<br>
                              9.ASDGAWFZSCBZCVBNDXHGGVNBXBHDGHSDFGDFGADFV<br>
                              10.aswertfeawfasdfasdfasdasdvcasdfasd.<br>
                             <label><input type="checkbox" id="checkbox">Agreed and im ready to start the test</label><br>
                        <label><input type="hidden" name="test" value="<c:out value="1"/>"/></label><br>
                        </c:if>
                        <input id="button" type="submit" value="next" class="btn btn-primary"/>
                 </form>
             </div>
    </body>
</html>
