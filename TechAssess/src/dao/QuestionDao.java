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
 * <p>
 * This class provide interface between database and Service class.
 * insert Questions Details from Service class into database and also perform retrieve 
 * Question information from database and assign questiontype operations.
 * </p>
 * 
 * @author TechAssess
 *
 */
public class QuestionDao {

	private DataBaseConnection connection = DataBaseConnection.getConnection();
    private SessionFactory factory = connection.createSessionFactory();
	
    /**
     * <p>
     * gets Question model Object from Service class which contains details of Question and 
     * create the session then begin the transaction 
     * persist the exam object and close the session
     * returns id after insertion of Question into database.
     * </p>
     * @param question
     *     object which contains the details of Question like name,id etc.
     * @return questioId
     *     id of question which is added to the database.     
     * @throws DataException
     *     if inputs are invalid or if any Hibernate Exception arrived
     */
	@SuppressWarnings("finally")
	public int insertQuestion(Question question) throws DataException {
		int questionId = 0;
		Session session = factory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			questionId = (int)session.save(question);
			transaction.commit();
		} catch (HibernateException e) {
			throw new DataException(e.toString());
		} finally {
			session.close();
			return questionId;
		}
	}
	
	/**
	 * <p>
	 * Allocate QuestionType to Question by Retrieving objects of Question  and QuestionType Model classes  and
     * finally perform allocation by Calling setTypeId method of Question Model Class.
	 * </p>
	 * @param typeId
	 *     contains id of QuestionType.
	 * @param questionId
	 *     contains id of Question.
	 * @throws DataException
	 *     if inputs are invalid or if any Hibernate Exception arrived
	 */
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
	
	/**
	 * <p>
	 * retrieves the Question Details of particular id from database and return this details
     * to QuestionService class
	 * </p>
	 * @param questionId
	 *     contains id of question to retrieve.
	 * @return 
	 *     question details in object format.
	 * @throws DataException
	 *     if inputs are invalid or if any Hibernate Exception arrived
	 */
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
	/**
	 * <p>
	 * retrieve all Questions Details from Database in List format and
     * send this list back to Service Class.
	 * </p>
	 * 
	 * @return allQuestions
	 *     object of list contains All questions.
	 * @throws DataException
	 *     if inputs are invalid or if any Hibernate Exception arrived
	 */
	@SuppressWarnings({ "unchecked", "finally" })
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
	    	return allQuestions;
	    }
	} 
}

