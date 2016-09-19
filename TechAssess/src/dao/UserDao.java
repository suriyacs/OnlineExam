package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import exception.DataException;
import dbconnection.DataBaseConnection;
import model.User;
import util.FileUtil;

/**
 * <p>
 * This class provide interface between database and Service class.
 * insert User Details from Service class into database and also perform retrieve 
 * User information from database.
 * </p>
 * 
 * @author suriyakumar
 *
 */
@Repository
public class UserDao {

	private DataBaseConnection connection = DataBaseConnection.getConnection();
    private SessionFactory factory = connection.createSessionFactory();


    /**
     * <p>
     * Retrieve all Users Details from Database in List format and
     * send this list back to Service Class.
     * </p>
     * 
     * @return
     *    List object contains details of all Users.
     * @throws DataException
     *     If inputs are invalid or if any Hibernate Exception arrived
     */
	@SuppressWarnings("unchecked")
	public List<User> retrieveAllUser() throws DataException {                                    /*To populate employee*/
        Session session = factory.openSession();
        try {
            return session.createQuery("from User").list();
        } catch (HibernateException e) {
        	FileUtil.logError("Exception occured in retrieveAllUser method in UserDao" + e);
            throw new DataException("Error occured while retrieving details of all user");
        } finally {
            session.close();
        }
    } 
	
	/**
	 * <p>
	 * Gets User model Object from Service class which contains details of Exam and 
     * create the session then begin the transaction 
     * persist the user object and close the session
     * returns id after insertion of user into database.
	 * </p>
	 * 
	 * @param user
	 *     Object contains the details of User like name,id etc.
	 * @throws DataException
	 *      If inputs are invalid or if any Hibernate Exception arrived
	 */
	public void insertUser(User user) throws DataException {            
        Session session = factory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (HibernateException e) {
        	FileUtil.logError("Exception occured in inserUser method in UserDao" + e);
            throw new DataException("Error occured while adding your details. Kindly provide proper details or try again sometime");
        } finally {
            session.close(); 
        }
    }
	
	/**
	 * <p>
	 * Retrieves the User Details of particular emmailId from database and return this details
     * to Service class
	 * </p>
	 * 
	 * @param emailId
	 *     Contains email id of Particular user.
	 * @return user
	 *     Object which contains the details of user.
	 * @throws DataException
	 *     If inputs are invalid or if any Hibernate Exception arrived
	 */
	public User retrieveUserByEmailId(String emailId) throws DataException {
		 try {
			 for(User user : retrieveAllUser()) {
				 if(user.getEmailId().equals(emailId)) {
					 return user;
				 }
			 }
		 } catch (HibernateException e) {
			 FileUtil.logError("Exception occured in retrieveUserByEmailId method in UserDao" + " " + e );
			 throw new DataException("Error occured while retrieving details for given emailId" + " " + emailId);
		 }
		 return null;
	}
}
