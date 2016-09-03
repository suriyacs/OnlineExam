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
@Controller
public class ApplicationController {
	
	UserService userService = new UserService();
	RoleService roleService = new RoleService();
	
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
	        return new ModelAndView("login", "LogInMessage", userService.addUser(userName, emailId, password, mobileNumber));
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
	               session.setAttribute("role", user.getRoleId());
	               return "redirect:adminpage";
	           } else if("User".equals(roleService.getRoleNameById(user.getRoleId())) && password.equals(user.getPassword())) {
	           	session.setAttribute("role", user.getRoleId());
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
	
	 @RequestMapping(value = "/adminpage")
	 public String goToAdminPage() {
		 return "adminpage";
	 }

	 @RequestMapping(value = "/gotouserpage")
	 public String goToUserPage() {
		 return "userpage";
	 }
	 
	 @RequestMapping(value = "/insertadmin")
	 public String redirectToInsertAdminPage() {
		 return "addadmin";
	 }
	 
	 @RequestMapping(value="/insertquestion") 
     public String redirectToInsertQuestionPage() {
    	 return "addquestion";
     }
	 
	 @RequestMapping(value="/inserttest")
	 public String redirectToInsertTestPage() {
		 return "addtest";
	 }
	 
	 @RequestMapping(value="/taketest")
	 public String redirectToStartTestPage(ModelMap model){
		 model.addAttribute("ToStartTest", "start");
		 return "questionpageforuser";
	 }
}
