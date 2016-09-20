package controller;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;

import exception.DataException;
import model.User;
import service.RoleService;
import service.UserService;

/**
 * <p>
 * This class performs user registration,login authentication and admin
 * registration functions.
 * </p>
 * 
 * @author TechAssess
 *
 */
@Controller
public class UserController {
    private UserService userService = new UserService();
    private RoleService roleService = new RoleService();

    /**
     * <p>
     * Method gets invoked when the user clicks sign-up button from the login
     * page to register the user with given details.
     * </p>
     * 
     * @param userName
     *     Consist of name of the user to be registered.
     * @param emailId
     *      Contains email id of the user.
     * @param password
     *      Contains password provided by the user.
     * @param mobileNumber
     *      Contains mobile number of the user to be registered.
     * @return object return name of the java server page along with the
     *         message(i.e login success or failure message or database
     *         connection error message).
     */
    @RequestMapping("/userRegisteration")
    public ModelAndView registerUser(@RequestParam("userName") String userName, @RequestParam("emailId") String emailId,
            @RequestParam("password") String password, @RequestParam("mobileNumber") String mobileNumber) {
        try {
            userService.addUser(userName, emailId, password, mobileNumber);
            return new ModelAndView("login", "SuccessMessage", "Welcome to Tech Assess..!!Please login to proceed..!!");
        } catch (DataException e) {
            return new ModelAndView("login", "LogInMessage", (e.getMessage().toString()));
        }
    }

    /**
     * <p>
     * Method which gets invoked by the login button clicked by the user to
     * check the authorization such as (user or admin) and redirect the user to
     * their desired page.
     * </p>
     * 
     * @param emailId
     *     Consist of emailId of the user to be verified.
     * @param password
     *     Contains password provided by the user.
     * @param session
     *     Session is used to create a access level to the user.
     * @param model
     *     Used for storing the messages related to database connection
     *     exception or login error message.
     * @return string contains name of the java server page.
     */
    @RequestMapping(value = "/AuthenticateLogin", method = RequestMethod.POST)
    public String doGet(@RequestParam("emailId") String emailId, @RequestParam("password") String password,
            HttpSession session, ModelMap model) {
        try {
            User user = userService.getUserByEmailId(emailId);
            if (null == user) {
                model.addAttribute("LogInMessage", "Entered Details does not Match. Kindly Enter Correct Details");
                return "login";
            } else if ("Admin".equals(roleService.getRoleNameById(user.getRoleId()))
                    && password.equals(user.getPassword())) {
                session.setAttribute("role", "Admin");
                return "redirect:adminpage";
            } else if ("User".equals(roleService.getRoleNameById(user.getRoleId()))
                    && password.equals(user.getPassword())) {
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
     * method which add admin details by calling addAdmin method of userService
     * class.
     * </p>
     * 
     * @param userName
     *     Contains name of the admin.
     * @param emailId
     *     Contains emailId of the admin.
     * @param password
     *     Contains password for the admin.
     * @param mobileNumber
     *     Contains mobile number of the admin.
     * @return object contains the name of the java server page along with the
     *         message to be displayed such as insertion successful or failure.
     */
    @RequestMapping("/adminRegisteration")
    public ModelAndView createNewAdmin(@RequestParam("userName") String userName,
            @RequestParam("emailId") String emailId, @RequestParam("password") String password,
            @RequestParam("mobileNumber") String mobileNumber) {
        try {
            userService.addAdmin(userName, emailId, password, mobileNumber);
            return new ModelAndView("addadmin", "SuccessMessage", " Admin Created Successfully..!!");
        } catch (DataException e) {
            return new ModelAndView("addadmin", "LogInMessage", (e.getMessage().toString()));
        }
    }

}
