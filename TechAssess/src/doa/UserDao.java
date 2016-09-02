package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.User;
import exception.DataException;
import dbconnection.DataBaseConnection;

public class UserDao {

	private DataBaseConnection connection = DataBaseConnection.getConnection();
    private SessionFactory factory = connection.createSessionFactory();
    
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
	
	public boolean insertUser(User user) throws DataException {            
        Session session = factory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            throw new DataException("The provided details could not be inserted, kindly provide proper input");
        } finally {
            session.close(); 
        }
    }
	
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