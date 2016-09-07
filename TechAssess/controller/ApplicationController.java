package controller;

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
import service.ExamService;
import model.Exam;
import model.Question;
@Controller
public class ApplicationController {	
	UserService userService = new UserService();
	RoleService roleService = new RoleService();
	QuestionTypeService questionTypeService = new QuestionTypeService();
	ExamService examService = new ExamService();
	
	
	 /**
	  * <p>
	  * Redirect to admin page
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
		 try {
		     model.addAttribute("questionTypes",questionTypeService.getAllQuestionTypes());
		 } catch(DataException e) {
			 model.addAttribute("insertQuestionMessage",(e.getMessage().toString()));
		 }
    	 return "addquestion";
         }
	 
	 @RequestMapping(value="/insertexamdetails")
	 public String redirctToInsertTestPage(ModelMap model){
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
	 public ModelAndView addQuestionForFillup(@RequestParam("questionname") String questionName,
			 @RequestParam("answer") String answer, @RequestParam("checkbox") String correctAnswer) {
		 try {
		     int questionId = questionService.addQuestion(questionName);
		     int choiceId = choiceService.addChoice(answer,correctAnswer);
		     questionService.addQuestionType(1, questionId);
		     choiceService.allocateQuestion(choiceId, questionId);
		     return new ModelAndView("addquestion", "QuestionMessage", correctAnswer + " " + "InserTion Success");
		 } catch(DataException e) {
			 return new ModelAndView("addquestion", "QuestionMessage", (e.toString()));
		 }
	 }
}
