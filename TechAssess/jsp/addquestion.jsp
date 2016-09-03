<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
</head>
<body>
        <div class="content">
        <div class="heading">
            <div class="head">
                 <h1 class="title">TechAssess</h1>
            </div>
            <div class="addtype">
                <form class="form-basic" method="post" action="addquestiontype">  
                        <div class="form-row">
                              <label>
                                <span>NewQuestionType</span>
                                <textarea name="questionname"></textarea>
                              </label>
                           </div>
                        <button class="btn btn-primary">AddType</button>
                 </form>
            </div>
        </div>
        <br>
        <center>
        <div class="select">
              <Select id="colorselector">
              <option selected="selected">Select Question Type</option>
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
                 <form class="form-basic" method="post" action="choosethebest" name="choose">   
                      <div class="form-row">
                              <label>
                                <span>Question</span>
                                <textarea name="questionname"></textarea>
                              </label>
                           </div>
                        <div class="form-row">
                            <div class="answer"> 
                                 <label>
                                     <span>Choice1</span>
                                     <input type="text" name="choiceone">
                                 </label>
                            </div> 
                            <div class="form-row">
                               <label>
                                  <span>IfCorrect</span>
                                 <input type="checkbox" name="checkboxone">
                              </label>
                            </div>
                         </div>
                         <div class="form-row">
                             <div class="answer"> 
                                 <label>
                                     <span>Choice2</span>
                                     <input type="text" name="choicetwo">
                                 </label>
                            </div> 
                            <div class="form-row">
                               <label>
                                  <span>IfCorrect</span>
                                 <input type="checkbox" name="checkboxtwo">
                              </label>
                            </div>
                         </div>
                         <div class="form-row">
                            <div class="answer"> 
                                 <label>
                                     <span>Choice3</span>
                                     <input type="text" name="choicethree">
                                 </label>
                            </div> 
                            <div class="form-row">
                               <label>
                                  <span>IfCorrect</span>
                                 <input type="checkbox" name="checkboxthree">
                              </label>
                            </div>
                         </div>
                         <div class="form-row">
                             <div class="answer"> 
                                 <label>
                                     <span>Choice4</span>
                                     <input type="text" name="choicefour">
                                 </label>
                            </div> 
                            <div class="form-row">
                               <label>
                                  <span>IfCorrect</span>
                                 <input type="checkbox" name="checkboxfour">
                              </label>
                            </div>
                         </div>
                         <div class="form-row">
                               <button type="submit" class="btn btn-primary">Add</button>
                         </div>
                 </form>
              </div>
               <div id="Multiple" class="colors" style="display:none"> 
              <form class="form-basic" method="post" action="Multipleanswer" name="multiple">   
                        <div class="form-row">
                              <label>
                                <span>Question</span>
                                <textarea name="questionname"></textarea>
                              </label>
                           </div>
                        <div class="form-row">
                            <div class="answer"> 
                                 <label>
                                     <span>Choice1</span>
                                     <input type="text" name="choiceone">
                                 </label>
                            </div> 
                            <div class="form-row">
                               <label>
                                  <span>IfCorrect</span>
                                 <input type="checkbox" name="checkboxone">
                              </label>
                            </div>
                         </div>
                         <div class="form-row">
                             <div class="answer"> 
                                 <label>
                                     <span>Choice2</span>
                                     <input type="text" name="choicetwo">
                                 </label>
                            </div> 
                            <div class="form-row">
                               <label>
                                  <span>IfCorrect</span>
                                 <input type="checkbox" name="checkboxtwo">
                              </label>
                            </div>
                         </div>
                         <div class="form-row">
                            <div class="answer"> 
                                 <label>
                                     <span>Choice3</span>
                                     <input type="text" name="choicethree">
                                 </label>
                            </div> 
                            <div class="form-row">
                               <label>
                                  <span>IfCorrect</span>
                                 <input type="checkbox" name="checkboxthree">
                              </label>
                            </div>
                         </div>
                         <div class="form-row">
                             <div class="answer"> 
                                 <label>
                                     <span>Choice4</span>
                                     <input type="text" name="choicefour">
                                 </label>
                            </div> 
                            <div class="form-row">
                               <label>
                                  <span>IfCorrect</span>
                                 <input type="checkbox" name="checkboxfour">
                              </label>
                            </div>
                         </div>
                         <div class="form-row">
                               <button type="submit" class="btn btn-primary">Add</button>
                         </div>
                     </form>
              </div>
          </div>
     </body>
   </html>
