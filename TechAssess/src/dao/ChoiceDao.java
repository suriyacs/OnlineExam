package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import dbconnection.DataBaseConnection;
import exception.DataException;
import model.Choice;
import model.Question;
import util.FileUtil;

/**
 * <p>
 * This class provide interface between database and Service class.
 * insert Choice Details from Service class into database and also perform retrieve 
 * Choice information from database.
 * </p>
 * @author TechAssess
 *
 */
@Repository
public class ChoiceDao {

    private DataBaseConnection connection = DataBaseConnection.getConnection();
    private SessionFactory factory = connection.createSessionFactory();

    /**
     * <p>
     * Gets Choice model Object from Service class which contains details of Choice and 
     * create the session then begin the transaction 
     * persist the Choice object and close the session.
     * returns id after insertion of chioce into database.
     * </p>
     * @param choice
     *     Choice object which contains the details of choice like name,IsCorrect etc.
     * @return choiceId
     *     Contains id Choice which is added  to database;
     * @throws DataException
     *     If inputs are invalid or if any hibernate Exception occured
     */
    public int insertChoice(Choice choice) throws DataException {
	    Session session = factory.openSession();
	    int choiceId = 0;
	    try {
            Transaction transaction = session.beginTransaction();
            choiceId = (int)session.save(choice);
            transaction.commit();
            return choiceId;
        } catch (HibernateException e) {
		    FileUtil.logError("Exception occured in insertChoice method in ChoiceDao" + e);
            throw new DataException(e.getMessage());
        } finally {
            session.close();
        }
	}
	
	/**
	 * <p>
	 * Retrieves the Choice Details of particular id from database and return this details
     * to ChoiceService class
     * </p>
	 * @param choiceId
	 *     Contains id Choice to retrieve
	 * @return choice
	 *     Object contains details of choice of particular id
	 * @throws DataException
	 *     If choice not present or if any hibernate Exception occured
	 */    
    public Choice retrieveChoiceDetailById(int choiceId) throws DataException {
	    Session session = factory.openSession();
        try {
       	 return (Choice)session.get(Choice.class, choiceId);
        } catch (HibernateException e) {
            FileUtil.logError("Exception occured in retrieveChoiceDetailById method in ChoiceDao" + e);
            throw new DataException("Cannot able to retrieve details for choiceId" + " " + choiceId);
        } finally {
            session.close();
        }
	}
	
	/**
	 * <p>
	 * Allocate Question to Choice by retrieving objects of Choice and Question by id
	 * and pass the question object into setQuestion Method of Choice model class.
	 * </p>
	 * 
	 * @param choiceId
	 *     Id of choice to allocate question
	 * @param questionId
	 *     Id question to allocate
	 * @throws DataException
	 *     If id is not exist or if any hibernate Exception arrived
	 */
 	public void assignQuestion(int choiceId, int questionId) throws DataException {
	    Session session = factory.openSession();
	    try {
	    	Transaction transaction = session.beginTransaction();
	    	Choice choice = (Choice)session.get(Choice.class, choiceId);
	    	Question question = (Question)session.get(Question.class, questionId);
	    	choice.setQuestionId(question);
	        transaction.commit();
	    } catch (HibernateException e) {
	    	FileUtil.logError("Exception occured in assignQuestion method in ChoiceDao" + e);
            throw new DataException("Error occured whilie allocating questionId"+ " " + questionId +" " +  "to choiceId" + " " + choiceId);
        } finally {
        	session.close();
        }
	}
}

