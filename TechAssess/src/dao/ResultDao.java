package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dbconnection.DataBaseConnection;
import exception.DataException;

import model.Result;

/**
 * <p>
 * This class provide interface between database and Service class.
 * used to store the result after user completed the exam.
 * </p>
 * 
 * @author TechAssess
 *
 */
public class ResultDao {
	private DataBaseConnection connection = DataBaseConnection.getConnection();
    private SessionFactory factory = connection.createSessionFactory();
    
    /**
     * <p>
     * This method get Result Object and Store this object into database
     * </p>
     * 
     * @param result
     *     object contains the details of result like examName,userName,mark etc.
     * @throws DataException
     *     if inputs are invalid or if any Hibernate Exception arrived
     */
    public void storeResult(Result result)throws DataException {
    	Session session = factory.openSession();
    	try {
			Transaction transaction = session.beginTransaction();
			session.save(result);
			transaction.commit();
		} catch (HibernateException e) {
			throw new DataException(e.toString());
		} finally {
			session.close();
		}
    }
}
