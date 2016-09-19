package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import dbconnection.DataBaseConnection;
import exception.DataException;
import model.Question;
import model.QuestionType;
import util .FileUtil;

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
@Repository
public class QuestionDao {

	private DataBaseConnection connection = DataBaseConnection.getConnection();
    private SessionFactory factory = connection.createSessionFactory();
	
    /**
     * <p>
     * Gets Question model Object from Service class which contains details of Question and 
     * create the session then begin the transaction 
     * persist the exam object and close the session
     * returns id after insertion of Question into database.
     * </p>
     * @param question
     *     Object which contains the details of Question like name,id etc.
     * @return questioId
     *     Id of question which is added to the database.     
     * @throws DataException
     *     If inputs are invalid or if any Hibernate Exception arrived
     */
	public int insertQuestion(Question question) throws DataException {
		int questionId = 0;
		Session session = factory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			questionId = (int)session.save(question);
			transaction.commit();
			return questionId;
		} catch (HibernateException e) {
			FileUtil.logError("Error occured in insertQuestion method in QuestionDao" + e);
			throw new DataException("Cannot able to add Question. Kindly try again");
		} finally {
			session.close();
		}
	}
	
	/**
	 * <p>
	 * Allocate QuestionType to Question by Retrieving objects of Question  and QuestionType Model classes  and
     * finally perform allocation by Calling setTypeId method of Question Model Class.
	 * </p>
	 * @param typeId
	 *     Contains id of QuestionType.
	 * @param questionId
	 *     Contains id of Question.
	 * @throws DataException
	 *     If inputs are invalid or if any Hibernate Exception arrived
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
			FileUtil.logError("Error occured in assignQuestionType method in QuestionDao" + e);
			throw new DataException("Error occured while assigning typeId" + " " + typeId + " " + "to questionId" + " " + questionId);
		} finally {
			session.close();
		}
	}
	
	/**
	 * <p>
	 * Retrieves the Question Details of particular id from database and return this details
     * to QuestionService class
	 * </p>
	 * @param questionId
	 *     Contains id of question to retrieve.
	 * @return 
	 *     Question details in object format.
	 * @throws DataException
	 *     If inputs are invalid or if any Hibernate Exception arrived
	 */
	public Question retrieveQuestionDetailById(int questionId) throws DataException {
		Session session = factory.openSession();
		try {
			return (Question)session.get(Question.class, questionId);
		} catch (HibernateException e) {
			FileUtil.logError("Error occured in retrieveQuestionDetailById method in QuestionDao" + e);
			throw new DataException("Error occured while retrieving details for given questionId" + " " + questionId);
		} finally {
			session.close();
		}
	}
	/**
	 * <p>
	 * Retrieve all Questions Details from Database in List format and
     * send this list back to Service Class.
	 * </p>
	 * 
	 * @return allQuestions
	 *     Object of list contains All questions.
	 * @throws DataException
	 *     If inputs are invalid or if any Hibernate Exception arrived
	 */
	@SuppressWarnings("unchecked")
	public List<Question> retrieveAllQuestions() throws DataException {
		Session session = factory.openSession();
		try {
			return session.createQuery("from Question").list();
		} catch(HibernateException e) {
			FileUtil.logError("Error occured in retrieveAllQuestions method in QuestionDao" + e);
			throw new DataException("Error occured while retrieving details for all questions");		
	    } finally {
	    	session.close();
	    }
	} 
}

