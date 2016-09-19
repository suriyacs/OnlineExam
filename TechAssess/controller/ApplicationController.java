package controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;

import exception.DataException;
import model.User;
import model.Exam;
import model.Choice;
import model.Question;
import model.Answer;
import service.RoleService;
import service.UserService;
import service.ChoiceService;
import service.ExamService;
import service.QuestionService;
import service.ResultService;


/**
 * Controller which accept the request from Java server page and
 * call the service's of pojo's for processing the given request.
 * @author TechAssess
 *
 */
@Controller
public class ApplicationController {	
	private UserService userService = new UserService();
	private RoleService roleService = new RoleService();
	private ExamService examService = new ExamService();
	private QuestionService questionService = new QuestionService();
	private ChoiceService choiceService = new ChoiceService();
	 
	/**
	  * <p>
	  *   Method which redirect to loginpage.
	  * </p>
	  * 
	  * @return string
	  *     consist of name of the java server page to be loaded.
	  */
	@RequestMapping("loginpage")
	public String goToLoginPage() {
	    return "login";
	}
	
	/**
	 * <p>
	 *     Method gets invoked when the user clicks sign-up button
	 *     from the login page to register the user with given details.
	 * </p>
	 * 
	 * @param userName
	 *     consist of name of the user to be registered.
	 * @param emailId
	 *     contains email id of the user.
	 * @param password
	 *     contains password provided by the user.
	 * @param mobileNumber
	 *     contains mobile number of the user to be registered.
	 * @return object
	 *     return name of the java server page along with the 
	 *     message(i.e login success or failure message or database connection error message).
	 */
	@RequestMapping("/userRegisteration")
	public ModelAndView registerUser(@RequestParam("userName") String userName, @RequestParam("emailId") String emailId,
			@RequestParam("password") String password, @RequestParam("mobileNumber") String mobileNumber) {
		try {
			userService.addUser(userName, emailId, password, mobileNumber);
	        return new ModelAndView("login", "SuccessMessage","Welcome to Tech Assess..!!Please login to proceed..!!");
		} catch(DataException e) {
			return new ModelAndView("login", "LogInMessage", (e.getMessage().toString()));
		}
	}
	
	/**
	 * <p>
	 *     Method which gets invoked by the login button clicked by the user to
	 *     check the authorization such as (user or admin) and redirect the user to their desired page.
	 * </p>
	 * 
	 * @param emailId
	 *     consist of emailId of the user to be verified.
	 * @param password
	 *     contains password provided by the user.
	 * @param session
	 *     session is used to create a access level to the user.
	 * @param model
	 *     used for storing the messages related to database connection exception or login error message.
	 * @return string
	 *     contains name of the java server page.
	 */
	 @RequestMapping(value = "/AuthenticateLogin", method = RequestMethod.POST)
	   public String doGet(@RequestParam("emailId") String emailId, @RequestParam("password") String password,
			   HttpSession session, ModelMap model) {
	       try {
	           User user = userService.getUserByEmailId(emailId);
	           if(null == user) {
	               model.addAttribute("LogInMessage", "Entered Details does not Match. Kindly Enter Correct Details");
	           	   return "login";
	           } else if("Admin".equals(roleService.getRoleNameById(user.getRoleId())) && password.equals(user.getPassword())) {
	               session.setAttribute("role", "Admin");
	               return "redirect:adminpage";
	           } else if("User".equals(roleService.getRoleNameById(user.getRoleId())) && password.equals(user.getPassword())) {
	        	   session.setAttribute("role", "User");
	        	   session.setAttribute("user", user);
	        	   session.setAttribute("userName", user.getUserName());
	               return "redirect:gotouserpage";
	           } else {
	        	   model.addAttribute("LogInMessage", "Invalid Password");
	               return "login";
	           }
	       } catch (DataException e) {
	    	   model.addAttribute("LogInMessage", e.toString());
	       	   return "login";
	       }
	   }
	 
	 /**
	  * <p>
	  * method which add admin details by calling addAdmin method of userService class.
	  * </p>
	  * @param userName
	  *     contains name of the admin.
	  * @param emailId
	  *     contains emailId of the admin.
	  * @param password
	  *     contains password for the admin.
	  * @param mobileNumber
	  *     contains mobile number of  the admin.
	  * @return object
	  *     contains the name of the java server page along with the
	  *     message to be displayed such as insertion successful or failure.
	  */
	 @RequestMapping("/adminRegisteration")
		public ModelAndView createNewAdmin(@RequestParam("userName") String userName, @RequestParam("emailId") String emailId,
				@RequestParam("password") String password, @RequestParam("mobileNumber") String mobileNumber) {
			try {
				userService.addAdmin(userName, emailId, password, mobileNumber);
		        return new ModelAndView("addadmin", "SuccessMessage"," Admin Created Successfully..!!");
			} catch(DataException e) {
				return new ModelAndView("addadmin", "LogInMessage", (e.getMessage().toString()));
			}
		}
	 
	 /**
	  * <p>
	  * Method which accept request from the java server page whenever the
	  * admin wants to add a exam for the user and request the examService to insert the exam.
	  * </p>
	  * 
	  * @param exam
	  *     consist of instance of Exam type which needs to be inserted on the database.
	  * @param Message
	  *     contains message to be displayed on the java server page.
	  * @return string
	  *     contains name of the java server page in which the message
	  *     get's displayed and prompts the user to add another exam.
	  */
	@SuppressWarnings("finally")
	@RequestMapping(value="/addingexam",method = RequestMethod.POST)
	 public String insertExam(@ModelAttribute Exam exam,ModelMap Message) {
		 try {
			 examService.addExamDetails(exam);
			 Message.addAttribute("SuccessMessage","Added Successfully..!!");
		 } catch(DataException e) {
			 Message.addAttribute("InsertExamMessage",(e.getMessage().toString()));
		 } finally {
			 return "addexam";
		 }
	 }
	
	/**
	 * <p>
	 * method which redirect to jsp page named adminpage.jsp.
	 * </p>
	 * @return string
	 *     contains name of the Java Server page to be redirected.
	 */
	 @RequestMapping(value = "/adminpage")
	 public String goToAdminPage() {
		 return "adminpage";
	 }
	 
	 /**
	  * <p>
	  * Method which gets invoked when the admin add question to the exam, and
	  * it request service's of exam and question to allocated question for that specific exam.
	  * </p>
	  * 
	  * @param model
	  *     contains messages to be displayed on the java server page
	  *     such as allocation success or database connection failure message.
	  * @param examId
	  *     contains id of the exam to be tracked for allocating the question.
	  * @param fromQuestionId
	  *     contains starting id of the question to be allocated for the given exam.
	  * @param toQuestionId
	  *     represents id of the question to be allocated.
	  * @return string
	  *     contains name of the java server page to be loaded.
	  */
	 @RequestMapping(value="/allocating",method=RequestMethod.POST)
	 public String allocateQuestionsToExam(ModelMap model,@RequestParam("examId") int examId,@RequestParam("fromQuestionId")int fromQuestionId,@RequestParam("toQuestionId") int toQuestionId) {
		 try {
		     examService.checkIfExamExist(examId);
		     questionService.checkIfQuestionExist(fromQuestionId);
		     questionService.checkIfQuestionExist(toQuestionId);
		     examService.allocateQuestionsToExam(examId, fromQuestionId,toQuestionId);
		     model.addAttribute("allocateMessage","AllocatedSuccessfully..!!");
		 } catch(DataException e) {
			 model.addAttribute("ErrorMessage",e.getMessage().toString());
		 }
		 return("assignquestions");
	 }   
	 
	 /**
	  * <p>
	  *      Method which gets invoked when the user wants to allocate question, it request the question
	  *      and exam service for retrieving the entire details of the question and exam finally passes
	  *      those list to assign question java server page.
	  * </p>
	  * 
	  * @param model
	  *     contains list type of question and exam or contains message of database connection failure.
	  * @return string
	  *     contains name of the java server page which needs to be loaded.
	  */
	 @RequestMapping(value="/allocatequestionpage")
	 public String redirectToAssignQuestionPage(ModelMap model) {
		 try {
		     model.addAttribute("questionList", questionService.getAllQuestions());		    
		     model.addAttribute("examList",examService.getAllExamDetails());
		 } catch(DataException e) {
			 model.addAttribute("ErrorMessage",e.getMessage().toString());
		 }
		 return("assignquestions");		 
	 }
	 
	 /**
	  * <p>
	  *     Method which gets invoked when the user click login from the login page.gets details of allexams from examService class
	  *     and pass it to jsp page named userpage.jsp.
	  * </p>
	  * 
	  * @param model
	  *     used to store details of all the exam or to store the error message raised at the time of database connection.
	  * @return string
	  *     contains name of the java server page to be loaded.
	  */
	 @RequestMapping(value = "/gotouserpage")
	 public String goToUserPage(ModelMap model) {
		 try {
		     model.addAttribute("exams", examService.getAllExamDetails());
		 } catch (DataException e) {
			 model.addAttribute("ExamMessage", e.toString());
		 }
		 return "userpage";
	 }
	 
	 /**
	  * <p>
	  *     Method which redirects to add admin page when the admin clicks the addadmin button on admin page.
	  * </p>
	  * 
	  * @return string
	  *     contains name of the java server page to be loaded.
	  */
	 @RequestMapping(value = "/insertadmin")
	 public String redirectToInsertAdminPage() {
		 return "addadmin";
	 }
	 
	 /**
	  * <p>
	  *     Method which add number of choice object to the list created in the form of choice type and 
	  *     redirect to add question page once the admin click insert question button.
	  * </p>
	  * 
	  * @param model
	  *     contains instance of question.
	  * @return string
	  *     contains name of the java server page which need's to be loaded.
	  */
	 @RequestMapping(value="/insertquestion") 
     public String redirectToInsertQuestionPage(ModelMap model) {
		 Question question = new Question();
		 question.add(new Choice());
		 question.add(new Choice());
		 question.add(new Choice());
		 question.add(new Choice());
		 model.addAttribute("Question",question);
    	 return "addquestion";
         }
	 
	 /**
	  * <p>
	  *     Method which redirect to add exam java server page when the admin click add exam button in admin page.
	  * </p>
	  * 
	  * @param model
	  *     contains an instance of exam type.
	  * @return string
	  *     contains name of the java server page which needs to be loaded.
	  */
	 @RequestMapping(value="/insertexamdetails")
	 public String redirectToInsertTestPage(ModelMap model){
		 model.addAttribute("exam",new Exam());
		 return "addexam";
	 }
	 
	 /**
	  * <p>
	  *     Method which redirect the user or admin to the login page by invalidating the
	  *     session created for that particular user when the sign out button is clicked.
	  *    
	  * </p>
	  * 
	  * @param session
	  *     comprises the object of that particular user who have logged in.
	  * @return string
	  *     contains name of the java server page which need to be loaded.
	  */
	 @RequestMapping(value="/logout")
	 public String logout(HttpSession session) {
		 session.invalidate();
		 return("redirect:loginpage");
	 }
	 
	 /**
	  * <p>
	  *     Method which redirect the incoming request to add question page once the question is successfully inserted.
	  * </p>
	  * 
	  * @return string
	  *     contains name of the mapping to be called.
	  */
	 @RequestMapping(value="/reloadinsertQuestion")
	 public String reloadInsertQuestionPage() {
		 return("redirect:insertquestion");
	 }
	 
	 /**
	  * <p>
	  *     Method which gets invoked when the user click the take start test button on the user page.
	  *     checks if user already attended this exam.if not then gets the particular exam detials from examService class
	  *     and pass it to jsp page named questionpageforuser.jsp.
	  * </p>
	  * 
	  * @param session
	  *     contains object of the session when the user logged in.
	  * @param testId
	  *     contains id of the test the user wants to take.
	  * @param model
	  *     used to store message stating that the user already attended the selected
	  *     exam or to store the name of the user and exam as well as instance of an exam.
	  * @return string
	  *     contains name of the java server page which needs to be loaded.
	  */
	 @RequestMapping(value="/taketest")
	 public String redirectToQuestionPage(HttpSession session, @RequestParam("test") String testId, ModelMap model){
		 User user = null;
		 try {
             user = (User)session.getAttribute("user");
             if (user.getExams() != null) {
            	 for(Exam exam : user.getExams()) {
            		 if (exam.getExamId() == Integer.parseInt(testId)) {
            			 model.addAttribute("ExamMessage","Sorry..!!You already attended this exam.!!");
            			 return("userpage");
            		 }
            	 }
             }
		     examService.addUserToExam(testId, user.getUserId());
		     Exam exam = examService.getExamById(Integer.parseInt(testId));
		     for(int answerCount = 0;answerCount<Integer.parseInt(exam.getNoOfAllocatedQuestions());answerCount++) {
		    	 exam.addAnswer(new Answer());	    
		    	 List<Answer> answers = exam.getAnswers();
				 System.out.println(answers.size());		    	
		     }
		     model.addAttribute("userName", user.getUserName());
		     model.addAttribute("examName", exam.getExamName());
		     model.addAttribute("exam",exam);		     
		 } catch(DataException e) {
			 model.addAttribute("insertQuestionMessage", (e.toString())); 
		 } catch(NumberFormatException e) {
			 model.addAttribute("insertQuestionMessage", "Error occured during conversion of" + " " + testId + " " + "while allocting the exam"); 
		 }
			 return "questionpageforuser";
	 }
	 
	 /**
	  * <p>
	  *     Method which gets invoked when the request from add question page is received it
	  *     stores the question and answer of the type fill in the blanks into the database.
	  * </p>
	  * 
	  * @param questionName
	  *     contains name of the question entered on insert question page.
	  * @param answer
	  *     contains choice of answer's for that given answer.
	  * @param correctAnswer
	  *     contains correct answer for this question.
	  * @param model
	  *     contains model object for storing the insertion successor failure
	  *     message or exception message created during the database connection.
	  * @return string
	  *     contains name of the java server page to be loaded.
	  */
	 @SuppressWarnings("finally")
	@RequestMapping(value="/fillintheblanks")
	 public String addQuestionForFillup(@RequestParam("questionname") String questionName,
			 @RequestParam("answer") String answer, @RequestParam("checkbox") String correctAnswer,ModelMap model) {
		 try {
		     int questionId = questionService.addQuestion(questionName);
		     int choiceId = choiceService.addChoice(answer,Integer.parseInt(correctAnswer));
		     questionService.allocateQuestionType(1, questionId);
		     choiceService.allocateQuestion(choiceId, questionId);
		     model.addAttribute("SuccessMessage", correctAnswer + " " + "InserTion Success");
		 } catch(DataException e) {
			 model.addAttribute("insertQuestionMessage", (e.toString()));
		 } catch(NumberFormatException e) {
			 model.addAttribute("insertQuestionMessage", "Error occured during conversion of" + " " + correctAnswer + " " + "in insert fill up page"); 
		 } finally {
		     return("redirect:insertquestion");
		 }
	 }
	 
	 /**
	  * <p>
	  *     Method which calculates the result by calling the calculateResult method of resultService
	  *     class.return the mark back to login page.
	  *     
	  * </p>
	  * 
	  * @param exam
	  *     contains instance of the exam object which the user attended.
	  * @param result
	  *     contains binded object from the java server page.
	  * @param model
	  *     used to store the mark obtain by the user.
	  * @param examId
	  *     contains id of the exam attended by the user.
	  * @param session
	  *     object contains information about the user who logged in to write the exam.
	  * @return string
	  *     contains name of the java server page to be loaded.
	  */
	 @RequestMapping(value="/resultcalculation",method = RequestMethod.POST)
	 public String calculateResult(@ModelAttribute("exam")Exam exam, BindingResult result,ModelMap model,
			 @RequestParam("examId") int examId,HttpSession session) {
		 ResultService resultService = new ResultService();
		 
		 try {
		     model.addAttribute("mark",resultService.calculateResult(exam, examId, (User)session.getAttribute("user")));
		 } catch(DataException e) {
			 model.addAttribute("mark",e.getMessage().toString());
		 }
		 return "login";
	 }
	 
	 /**
	  * <p>
	  *     Method which add the multiple answer and choose the correct answer question.which gets question and 
	  *     its choices in object format named question.first add the question and map the added question into its questiontype
	  *     by calling allocateQuestionType method of questionService class.
	  *     then add the choices of this question one by one then map the choice to this question by calling allocateQuestion
	  *     method of choiceService class.
	  * </p>
	  * 
	  * @param question
	  *     contains instance of Question type with values entered by the admin to insert a question.
	  * @param model
	  *     used to store insertion success or failure message or exception message occured at the time of database connection.
	  * @param questionType
	  *     contains instance of question type the question was created.
	  * @return string
	  *     contains name of the java server page to be loaded.
	  */
	 @RequestMapping(value="/choosethebest",method = RequestMethod.POST)
	 public String addQuestionForChooseTheBest(@ModelAttribute("Question") Question question,
			 ModelMap model, @RequestParam("questionType") String questionType) {
		 try {
		     int questionId = questionService.addQuestion(question.getQuestionName());
		     questionService.allocateQuestionType(Integer.parseInt(questionType), questionId);
		     for( Choice choice : question.getChoices()) {
		    	 choiceService.allocateQuestion(choiceService.addChoice(choice.getChoiceName(),(choice.getIsCorrect())), questionId);
		     }
		     model.addAttribute("SuccessMessage","Added Successfully..!!");
	     } catch(DataException e) {
	    	 model.addAttribute("insertQuestionMessage",(e.toString()));
	     } catch(NumberFormatException e) {
	    	 model.addAttribute("insertQuestionMessage", "Error occured during conversion of" + " " + questionType + " " + "in insert choose the correct answer");
	     }
		 return("addquestion");
		     
	 }
}

