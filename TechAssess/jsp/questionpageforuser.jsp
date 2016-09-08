<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>Employee Details</title>
        <link href="css/userpagestyling.css" rel="stylesheet" type="text/css">
         <link href="css/login.css" rel="stylesheet" type="text/css">
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
          <div class="logout" style="float:right">
                <a href="logout"class="btn btn-danger" title="logout"><span class="glyphicon glyphicon-log-out"></span></a> 
           </div>
		 <div class="row">
             <div class="col-sm-2">
                    <br><br>
                    <table class="table table-bordered table-condensed">
                        <thead>
                            <tr>
                                <th>Test Id</th>
                                <th>Mark</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>1</td>
                                <td>30</td>
                            </tr>
                            <tr>
                                <td>2</td>
                                <td>20</td>
                            </tr>
                            <tr>
                                <td>3</td>
                                <td>30</td>
                            </tr>
                        </tbody>
                    </table>
                  
             </div>
             <div class="rightfloat">
                 <h4 class="panel-title">
                     <a data-toggle="collapse" href="#collapse1">Your Account</a>
                 </h4>
                 <div id="collapse1" class="panel-collapse collapse">
                     <div class="panel-body"><a href="userdetails.jsp">Your Profile</a></div>
                     <div class="panel-footer"><a href="">Account</a></div>
                 </div>
             </div>
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
             
         </div>
         </div>
    </body>
</html>