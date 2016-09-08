/**
 * 
 */
package dao;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dbconnection.DataBaseConnection;
import exception.DataException;
import model.Question;
import model.QuestionType;

/**
 * @author user
 *
 */
public class QuestionTypeDao {

	private DataBaseConnection connection = DataBaseConnection.getConnection();
    private SessionFactory factory = connection.createSessionFactory();
	
    public QuestionType retrieveTypeDetailById(int typeId) throws DataException {
    	Session session = factory.openSession();
    	try {
    		Transaction transaction = session.beginTransaction();
    		 return (QuestionType)session.get(QuestionType.class, typeId);
    	} catch (HibernateException e) {
    		throw new DataException(e.toString());
    	}
    }
    
    public void addQuestionToQuestionType(QuestionType questionType, Question question) throws DataException {
    	Session session = factory.openSession();
    	try {
    		Transaction transaction = session.beginTransaction();
    		Set questionSet = new HashSet();
    		questionSet.add(question);
    		questionType.setQuestion(questionSet);
    		session.save(questionType);
    		session.save(question);
    		transaction.commit();
    	} catch (HibernateException e) {
    		e.printStackTrace();
    		//throw new DataException(e.toString());
    	}
    }
}

