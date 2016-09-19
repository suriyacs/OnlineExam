package service;

import java.util.List;

import dao.UserDao;
import model.User;
import exception.DataException;
import service.RoleService;

/**
 * Service which request the User DataAccessObject to process the request sent by the controller.
 * @author TechAssess.
 *
 */
public class UserService {
	UserDao userDao = new UserDao();
	RoleService roleService = new RoleService();
	
	/**
	 * Method which receives userName, emailId, password, mobileNumber as request parameter from controller 
	 * which converts the incoming parameter to instance of user and insert the user instance to database.
	 * @param userName
	 *     consist of name of the user.
	 * @param emailId
	 *     consist of mail id of the user.
	 * @param password
	 *     consist of password given by the user.
	 * @param mobileNumber
	 *     consist of user mobile number
	 * @throws DataException
	 *     throws an exception to controller which gets generated at the time of database connection.
	 */
	public void addUser(String userName, String emailId, String password, String mobileNumber)  throws DataException {
		checkIfUserAlreadyExist(emailId);
	    userDao.insertUser(new User(userName, emailId, password,
	    		mobileNumber, roleService.getRoleIdByName("User")));
	}
	
	/**
	 * Method which receive the request and send the request to User
	 * Repository for retrieving the entire details of user in the form of List.
	 * @return list
	 *     returns details of all user in the form of List.
	 * @throws DataException
	 *     throws an exception to controller which gets generated at the time of database connection.
	 */
	public List<User> getAllUsers() throws DataException {                                           /*Get User Detail*/
        return userDao.retrieveAllUser();
    }
	
	/**
	 * Method which accept emailId as parameter and request the
	 * User repository for retrieving the user details of that emailId.
	 * @param emailId
	 *     consist of emailId for retrieving the detail of that user.
	 * @return object
	 *     returns instance of user for given emailId.
	 * @throws DataException
	 *     throws an exception to controller which gets generated at the time of database connection.
	 */
	public User getUserByEmailId(String emailId) throws DataException {
		return userDao.retrieveUserByEmailId(emailId);
	}
	
	/**
	 * Method which receive request from controller and insert the
	 * user details as admin by passing request to User data access object.
	 * @param userName
	 *     consist of name of the user
	 * @param emailId
	 *     consist of emailId provided by the user.
	 * @param password
	 *     consist of password created by the user.
	 * @param mobileNumber
	 *     consist of user mobile number.
	 * @throws DataException
	 *     throws an exception to controller which gets generated at the time of database connection.
	 */
	public void addAdmin(String userName, String emailId, String password, String mobileNumber)  throws DataException {
		checkIfUserAlreadyExist(emailId);
	    userDao.insertUser(new User(userName, emailId, password,
	    		mobileNumber, roleService.getRoleIdByName("Admin")));
	}
	
	/**
	 * Method which accepts request and emailId as parameter and check the user with given emailId exists.
	 * @param emailId
	 *     consist of email Id which represent the user.
	 * @throws DataException
	 *     throws an exception to controller which gets generated at the time of database connection.
	 */
	public void checkIfUserAlreadyExist(String emailId) throws DataException {
		if(userDao.retrieveUserByEmailId(emailId) != null) {
			throw new DataException("Person With This Mail Id Already Exist..!!Try Again With Different Id..!!");
		}
	}
}
