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
 * <p>
 * This class provide interface between database and Service class.
 * insert QuestionType Details from Service class into database and also perform retrieve 
 * QuestionType information from database and allocate Question operations.
 * 
 * @author TechAssess
 *
 */
public class QuestionTypeDao {

	private DataBaseConnection connection = DataBaseConnection.getConnection();
    private SessionFactory factory = connection.createSessionFactory();
	
    /**
     * <p>
     * retrieves the QuestionType Details of particular id from database and return this details
     * to QuestionTypeService class
     * </p>
     * 
     * @param typeId
     *     contains id of QuestionType to retrieve.
     * @return
     *     QuestionType Details in object format.
     * @throws DataException
     *     if input is invalid or if any Hibernate Exception is arrived
     */
    public QuestionType retrieveTypeDetailById(int typeId) throws DataException {
    	Session session = factory.openSession();
    	try {
    		 return (QuestionType)session.get(QuestionType.class, typeId);
    	} catch (HibernateException e) {
    		throw new DataException(e.toString());
    	} finally {
			session.close();
		}
    }
    
    /**
     * <p>
     * Allocate Question to QuestionType by  Calling setQuestion and 
     * method of QuestionType model class.
     * </p>
     * 
     * @param questionType
     *      object which contains the details of particular QuestionType.
     * @param question
     *      object which contains the details of particular Question.
     * @throws DataException  
     *      if input is invalid or if any Hibernate Exception is arrived
     */   
    @SuppressWarnings("unchecked")
	public void allocateQuestionToQuestionType(QuestionType questionType, Question question) throws DataException {
    	Session session = factory.openSession();
    	try {
    		Transaction transaction = session.beginTransaction();
    		@SuppressWarnings("rawtypes")
			Set questionSet = new HashSet();
    		questionSet.add(question);
    		questionType.setQuestion(questionSet);
    		session.save(questionType);
    		session.save(question);
    		transaction.commit();
    	} catch (HibernateException e) {
    		throw new DataException(e.toString());
    	} finally {
			session.close();
		}
    }
}

