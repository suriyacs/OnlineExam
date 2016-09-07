package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.Exam;
import exception.DataException;
import dbconnection.DataBaseConnection;

public class ExamDao {
	private DataBaseConnection connection = DataBaseConnection.getConnection();
    private SessionFactory factory = connection.createSessionFactory();
    
    public void insertExamDetails(Exam exam)throws DataException {
    	Session session = factory.openSession();
    	
    	try {    		
    		Transaction transaction = session.beginTransaction();
    		session.save(exam);
    		transaction.commit();
    	} catch(HibernateException e) {
    		throw new DataException("Invalid Inputs Please Check The Inputs and Try Again");
    	} finally {
    		session.close();
    	}    	
    }
}
