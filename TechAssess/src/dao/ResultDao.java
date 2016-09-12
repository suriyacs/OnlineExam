package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dbconnection.DataBaseConnection;
import exception.DataException;

import model.Result;

public class ResultDao {
	private DataBaseConnection connection = DataBaseConnection.getConnection();
    private SessionFactory factory = connection.createSessionFactory();
    
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
