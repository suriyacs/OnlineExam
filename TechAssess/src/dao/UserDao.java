package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.User;
import exception.DataException;
import dbconnection.DataBaseConnection;

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
public class UserDao {

	private DataBaseConnection connection = DataBaseConnection.getConnection();
    private SessionFactory factory = connection.createSessionFactory();
    
    /**
     * <p>
     * retrieve all Users Details from Database in List format and
     * send this list back to Service Class.
     * </p>
     * 
     * @return
     *    list object contains details of all Users.
     * @throws DataException
     *     if inputs are invalid or if any Hibernate Exception arrived
     */
	public List<User> retrieveAllUser() throws DataException {                                    /*To populate employee*/
        Session session = factory.openSession();
        try {
            return session.createQuery("from User").list();
        } catch (HibernateException e) {
            throw new DataException(e.toString());
        } finally {
            session.close();
        }
    } 
	
	/**
	 * <p>
	 * gets User model Object from Service class which contains details of Exam and 
     * create the session then begin the transaction 
     * persist the user object and close the session
     * returns id after insertion of user into database.
	 * </p>
	 * 
	 * @param user
	 *     object contains the details of User like name,id etc.
	 * @throws DataException
	 *      if inputs are invalid or if any Hibernate Exception arrived
	 */
	public void insertUser(User user) throws DataException {            
        Session session = factory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (HibernateException e) {
            throw new DataException("The provided details could not be inserted, kindly provide proper input");
        } finally {
            session.close(); 
        }
    }
	
	/**
	 * <p>
	 * retrieves the User Details of particular emmailId from database and return this details
     * to Service class
	 * </p>
	 * 
	 * @param emailId
	 *     contains email id of Particular user.
	 * @return user
	 *     object which contains the details of user.
	 * @throws DataException
	 *     if inputs are invalid or if any Hibernate Exception arrived
	 */
	public User retrieveUserByEmailId(String emailId) throws DataException {
		 try {
			 for(User user : retrieveAllUser()) {
				 if(user.getEmailId().equals(emailId)) {
					 return user;
				 }
			 }
		 } catch (HibernateException e) {
			 throw new DataException("Error occured while processing" + " " + emailId);
		 }
		 return null;
	}
}
