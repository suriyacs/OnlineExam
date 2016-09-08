package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import exception.DataException;
import model.User;

import org.springframework.web.bind.annotation.RequestParam;

import service.RoleService;
import service.UserService;
import service.QuestionTypeService;
import service.ChoiceService;
import service.ExamService;
import service.QuestionService;
import model.Exam;
import model.Choice;
import model.Question;
@Controller
public class ApplicationController {	
	UserService userService = new UserService();
	RoleService roleService = new RoleService();
	QuestionTypeService questionTypeService = new QuestionTypeService();
	ExamService examService = new ExamService();
	QuestionService questionService = new QuestionService();
	ChoiceService choiceService = new ChoiceService();
	 /**
	  * <p>
	  * Redirect to admin pageContact
	  * </p>
	  * 
	  * @return url pattern of jsp page named adminpage
	  */
	@RequestMapping("loginpage")
	public String goToRegisterationPage() {
	    return "login";
	}
	
	@RequestMapping("/userRegisteration")
	public ModelAndView registerUser(@RequestParam("userName") String userName, @RequestParam("emailId") String emailId,
			@RequestParam("password") String password, @RequestParam("mobileNumber") String mobileNumber) {
		try {
			userService.addUser(userName, emailId, password, mobileNumber);
	        return new ModelAndView("login", "LogInMessage","Welcome to Tech Assess..!!Please login to proceed..!!");
		} catch(DataException e) {
			return new ModelAndView("login", "LogInMessage", (e.getMessage().toString()));
		}
	}
	
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
	               return "redirect:gotouserpage";
	           } else {
	        	   model.addAttribute("Message", roleService.getRoleNameById(user.getRoleId()));
	           	return "success";
	           }
	       } catch (DataException e) {
	    	   model.addAttribute("LogInMessage", e.toString());
	       	   return "login";
	       }
	   }
	 @RequestMapping("/adminRegisteration")
		public ModelAndView createNewAdmin(@RequestParam("userName") String userName, @RequestParam("emailId") String emailId,
				@RequestParam("password") String password, @RequestParam("mobileNumber") String mobileNumber) {
			try {
				userService.addAdmin(userName, emailId, password, mobileNumber);
		        return new ModelAndView("addadmin", "LogInMessage","New Admin Created Successfully..!!");
			} catch(DataException e) {
				return new ModelAndView("addadmin", "LogInMessage", (e.getMessage().toString()));
			}
		}
	 
	 @RequestMapping(value="/addingexam",method = RequestMethod.POST)
	 public String insertExam(@ModelAttribute Exam exam,ModelMap Message) {
		 try {
			 examService.addExamDetails(exam);
			/* for (int questionId = fromQuestionId;questionId <= toQuestionId;questionId++) {
			 * @RequestParam("fromquestionid") int fromQuestionId,@RequestParam("toquestionid") int toQuestionId
			     examService.allocateQuestionsToExam(examId, questionId);
			 }*/
			 Message.addAttribute("InsertExamMessage","Added Successfully..!!");
		 } catch(DataException e) {
			 Message.addAttribute("InsertExamMessage",(e.getMessage().toString()));
		 } finally {
			 return "addexam";
		 }
	 }
	 @RequestMapping(value = "/adminpage")
	 public String goToAdminPage() {
		 return "adminpage";
	 }
	 
	 @RequestMapping(value="/allocating",method=RequestMethod.POST)
	 public String allocateQuestionsToExam(ModelMap model,@RequestParam("examId") int examId,@RequestParam("fromQuestionId")int fromQuestionId,@RequestParam("toQuestionId") int toQuestionId) {
		 try {
		     examService.checkIfExamExist(examId);
		     questionService.checkIfQuestionExist(fromQuestionId);
		     questionService.checkIfQuestionExist(toQuestionId);
		     for (int questionId = fromQuestionId;questionId <= toQuestionId;questionId++) { 
		    	 examService.allocateQuestionsToExam(examId, questionId);
		     }
		     model.addAttribute("allocateMessage","AllocatedSuccessfully..!!");
		 } catch(DataException e) {
			 model.addAttribute("allocateMessage",e.getMessage().toString());
		 }
		 return("assignquestions");
	 }   
	 
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
	 
	 @RequestMapping(value = "/gotouserpage")
	 public String goToUserPage() {
		 return "userpage";
	 }
	 
	 @RequestMapping(value = "/insertadmin")
	 public String redirctToInsertAdminPage() {
		 return "addadmin";
	 }
	 
	 @RequestMapping(value="/insertquestion") 
     public String redirctToInsertQuestionPage(ModelMap model) {
		 Question question = new Question();
		 question.add(new Choice());
		 question.add(new Choice());
		 question.add(new Choice());
		 question.add(new Choice());
		 model.addAttribute("Question",question);
    	 return "addquestion";
         }
	 
	 @RequestMapping(value="/insertexamdetails")
	 public String redirctToInsertTestPage(ModelMap model){
		 try {
			 List<Question> questions = questionService.getAllQuestions();
		     model.addAttribute("questionList", questions);
		 } catch(DataException e) {
			 model.addAttribute("InsertExamMessage",e.toString());
		 }
		 model.addAttribute("exam",new Exam());
		 return "addexam";
	 }
	 
	 @RequestMapping(value="/logout")
	 public String logout(HttpSession session) {
		 session.invalidate();
		 return("redirect:loginpage");
	 }
	 
	 @RequestMapping(value="/reloadinsertQuestion")
	 public String reloadInsertQuestion() {
		 return("redirect:addquestion");
	 }
	 
	 @RequestMapping(value="/taketest")
	 public String redirectToStartTestPage(ModelMap model){
		 model.addAttribute("ToStartTest", "start");
		 return "questionpageforuser";
	 }
	 
	 @RequestMapping(value="/fillintheblanks")
	 public String addQuestionForFillup(@RequestParam("questionname") String questionName,
			 @RequestParam("answer") String answer, @RequestParam("checkbox") String correctAnswer,ModelMap model) {
		 try {
		     int questionId = questionService.addQuestion(questionName);
		     int choiceId = choiceService.addChoice(answer,Integer.parseInt(correctAnswer));
		     questionService.allocateQuestionType(1, questionId);
		     choiceService.allocateQuestion(choiceId, questionId);
		     model.addAttribute("insertQuestionMessage", correctAnswer + " " + "InserTion Success");
		 } catch(DataException e) {
			 model.addAttribute("insertQuestionMessage", (e.toString()));
		 }
		 return("redirect:insertquestion");
	 }
	 
	 @RequestMapping(value="/choosethebest",method = RequestMethod.POST)
	 public String addQuestionForChooseTheBest(@ModelAttribute("Question") Question question,
			 ModelMap model, @RequestParam("questionType") String questionType) {
		 try {
		     int questionId = questionService.addQuestion(question.getQuestionName());
		     questionService.allocateQuestionType(Integer.parseInt(questionType), questionId);
		     for( Choice choice : question.getChoices()) {
		    	 choiceService.allocateQuestion(choiceService.addChoice(choice.getChoiceName(),(choice.getIsCorrect())), questionId);
		     }
		     model.addAttribute("insertQuestionMessage","Added Successfully..!!");
	     } catch(DataException e) {
	    	 model.addAttribute("insertQuestionMessage",(e.toString()));
	     }
		 return("addquestion");
		     
	 }
}
