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
	           return "adminpage";
	           /*if(null == user) {
	               model.addAttribute("Message", "Entered Details does not Match. Kindly Enter Correct Details");
	           	   return "gotologin";
	           } else if(1 == user.getRoleId() && password.equals(user.getPassword())) {
	               session.setAttribute("role", "Admin");
	               return "redirect:initiatepage";
	           } else if("User".equals(roleService.getRoleName(user.getRoleId())) && password.equals(user.getPassword())) {
	           	session.setAttribute("role", "User");
	               return "redirect:gotouserpage";
	           } else {
	           	return "gotologin";
	           }*/
	       } catch (DataException e) {
	    	   model.addAttribute("Message", e.toString());
	       	return "success";
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
		 return "addnewadmin";
	 }
	 
	 @RequestMapping(value="/insertquestion") 
     public String redirctToInsertQuestionPage() {
    	 return "addquestion";
     }
	 
	 @RequestMapping(value="/inserttest")
	 public String redirctToInsertTestPage() {
		 return "addtest";
	 }
}