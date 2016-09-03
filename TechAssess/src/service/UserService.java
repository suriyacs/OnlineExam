package service;

import java.util.List;

import model.User;
import dao.UserDao;
import exception.DataException;
import service.RoleService;

public class UserService {
	UserDao userDao = new UserDao();
	RoleService roleService = new RoleService();
	
	public String addUser(String userName, String emailId, String password, String mobileNumber)  throws DataException {
	    if(userDao.insertUser(new User((getAllUsers().size() + 1), userName, emailId, password,
	    		mobileNumber, roleService.getRoleIdByName("User")))) {
		    return "inserted Successfully";
		}
	    return "Provide Correct Details";
	}
	
	public List<User> getAllUsers() throws DataException {                                           /*Get User Detail*/
        return userDao.retrieveAllUser();
    }
	
	public User getUserByEmailId(String emailId) throws DataException {
		return userDao.retrieveUserByEmailId(emailId);
	}
}