/**
 * 
 */
package dao;

import java.util.ArrayList;
import java.util.List;

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
public class QuestionDao {

	private DataBaseConnection connection = DataBaseConnection.getConnection();
    private SessionFactory factory = connection.createSessionFactory();
	
	public int insertQuestion(Question question) throws DataException {
		Session session = factory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			int questionId = (int)session.save(question);
			transaction.commit();
			return questionId;
		} catch (HibernateException e) {
			e.printStackTrace();
			//throw new DataException(e.toString());
		} finally {
			session.close();
		}
		return 0;
	}
	
	
	public void assignQuestionType(int typeId, int questionId) throws DataException {
		Session session = factory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			Question question = (Question)session.get(Question.class, questionId);
			QuestionType questionType = (QuestionType)session.get(QuestionType.class, typeId);
			question.setTypeId(questionType);
		    transaction.commit();
		}catch (HibernateException e) {
			throw new DataException(e.toString());
		} finally {
			session.close();
		}
	}
	
	public Question retrieveQuestionDetailById(int questionId) throws DataException {
		Session session = factory.openSession();
		try {
			return (Question)session.get(Question.class, questionId);
		} catch (HibernateException e) {
			throw new DataException(e.toString());
		} finally {
			session.close();
		}
	}
	
	public List<Question> retrieveAllQuestions() throws DataException {
		List<Question>allQuestions = new ArrayList<Question>();
		Session session = factory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			allQuestions = session.createQuery("from Question").list();
			transaction.commit();
		} catch(HibernateException e) {
			throw new DataException(e.toString());		
	    } finally {
	    	session.close();
	    }return allQuestions;
	} 
}

