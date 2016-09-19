package service;

import java.util.List;

import org.springframework.stereotype.Service;

import dao.UserDao;
import model.User;
import exception.DataException;
import service.RoleService;

/**
 * <p>
 *     Service which accepts request from controller to perform operations like
 *     insert user, retrieve user, insert admin and to check the given user is present. 
 *     by forwarding the request to their corresponding methods in Question Data Access Object.
 * </p>
 * 
 * @author TechAssess.
 *
 */
@Service
public class UserService {
	UserDao userDao = new UserDao();
	RoleService roleService = new RoleService();
	
	/** <p>
	 *     Method which receive request from controller and insert the
	 *     user details as user role  by passing an instance of user type 
	 *     to insertUser method in User data access object.
	 *  <p>
	 * @param userName
	 *     Consist of name of the user.
	 * @param emailId
	 *     Consist of mail id of the user.
	 * @param password
	 *     Consist of password given by the user.
	 * @param mobileNumber
	 *     Consist of user mobile number
	 * @throws DataException
	 *     Throws an exception to controller which gets generated at the time of database connection.
	 */
	public void addUser(String userName, String emailId, String password, String mobileNumber)  throws DataException {
		checkIfUserAlreadyExist(emailId);
	    userDao.insertUser(new User(userName, emailId, password,
	    		mobileNumber, roleService.getRoleIdByName("User")));
	}
	
	/**
	 * <p>
	 *     Method which receive the request and send the request to User
	 *     Repository for retrieving the entire details of user in the form of List.
	 * </p>
	 * 
	 * @return list
	 *     Returns details of all user in the form of List.
	 * @throws DataException
	 *     Throws an exception to controller which gets generated at the time of database connection.
	 */
	public List<User> getAllUsers() throws DataException {                                           /*Get User Detail*/
        return userDao.retrieveAllUser();
    }
	
	/**
	 * <p>
	 *     Method which pass emailId as parameter and request the
	 *     User repository to retrieve the details of given emailId.
	 * </p>
	 * @param emailId
	 *     Consist of emailId for retrieving the detail of that user.
	 * @return object
	 *     Returns instance of user for given emailId.
	 * @throws DataException
	 *     Throws an exception to controller which gets generated at the time of database connection.
	 */
	public User getUserByEmailId(String emailId) throws DataException {
		return userDao.retrieveUserByEmailId(emailId);
	}
	
	/**
	 * <p>
	 *     Method which receive request from controller and insert the
	 *     user details as admin role  by passing an instance of user type 
	 *     to insertUser method in User data access object.
	 * </p>
	 * @param userName
	 *     Consist of name of the user
	 * @param emailId
	 *     Consist of emailId provided by the user.
	 * @param password
	 *     Consist of password created by the user.
	 * @param mobileNumber
	 *     Consist of user mobile number.
	 * @throws DataException
	 *     Throws an exception to controller which gets generated at the time of database connection.
	 */
	public void addAdmin(String userName, String emailId, String password, String mobileNumber)  throws DataException {
		checkIfUserAlreadyExist(emailId);
	    userDao.insertUser(new User(userName, emailId, password,
	    		mobileNumber, roleService.getRoleIdByName("Admin")));
	}
	
	/**
	 * <p>
	 *     Method which pass emailId as parameter to getUserByEmailId 
	 *     and check the user with given emailId exists.
	 * </p>
	 * 
	 * @param emailId
	 *     Consist of email Id which represent the user.
	 * @throws DataException
	 *     Throws an exception to controller which gets generated at the time of database connection.
	 */
	public void checkIfUserAlreadyExist(String emailId) throws DataException {
		if(null != getUserByEmailId(emailId)) {
			throw new DataException("Person With This Mail Id Already Exist..!!Try Again With Different Id..!!");
		}
	}
}
