package service;

import java.util.List;

import model.User;
import dao.UserDao;
import exception.DataException;
import service.RoleService;

public class UserService {
	UserDao userDao = new UserDao();
	RoleService roleService = new RoleService();
	
	public void addUser(String userName, String emailId, String password, String mobileNumber)  throws DataException {
		checkIfUserAlreadyExist(emailId);
	    userDao.insertUser(new User((getAllUsers().size() + 1), userName, emailId, password,
	    		mobileNumber, roleService.getRoleIdByName("User")));
	}
	
	public List<User> getAllUsers() throws DataException {                                           /*Get User Detail*/
        return userDao.retrieveAllUser();
    }
	
	public User getUserByEmailId(String emailId) throws DataException {
		return userDao.retrieveUserByEmailId(emailId);
	}
	
	public void addAdmin(String userName, String emailId, String password, String mobileNumber)  throws DataException {
		checkIfUserAlreadyExist(emailId);
	    userDao.insertUser(new User((getAllUsers().size() + 1), userName, emailId, password,
	    		mobileNumber, roleService.getRoleIdByName("Admin")));
	}
	
	public void checkIfUserAlreadyExist(String emailId) throws DataException {
		if(userDao.retrieveUserByEmailId(emailId) != null) {
			throw new DataException("Person With This Mail Id Already Exist..!!Try Again With Different Id..!!");
		}
	}
}
