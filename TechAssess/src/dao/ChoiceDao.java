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
     * gets Choice model Object from Service class which contains details of Choice and 
     * create the session then begin the transaction 
     * persist the Choice object and close the session.
     * returns id after insertion of chioce into database.
     * </p>
     * @param choice
     *     choice object which contains the details of choice like name,IsCorrect etc.
     * @return choiceId
     *     contains id Choice which is added  to database;
     * @throws DataException
     *     if inputs are invalid or if any hibernate Exception occured
     */
	public int insertChoice(Choice choice) throws DataException {
		Session session = factory.openSession();
		try {
            Transaction transaction = session.beginTransaction();
            int choiceId = (int)session.save(choice);
            transaction.commit();
            return choiceId;
		} catch (HibernateException e) {
			throw new DataException(e.toString());
		}
	}
	
	/**
	 * <p>
	 * retrieves the Choice Details of particular id from database and return this details
     * to ChoiceService class
     * </p>
	 * @param choiceId
	 *     contains id Choice to retrieve
	 * @return choice
	 *     object contains details of choice of particular id
	 * @throws DataException
	 *     if choice not present or if any hibernate Exception occured
	 */    
	public Choice retrieveChoiceDetailById(int choiceId) throws DataException {
		Session session = factory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			return (Choice)session.get(Choice.class, choiceId);
		} catch (HibernateException e) {
			throw new DataException(e.toString());
		}
	}
	
	/**
	 * <p>
	 * allocate Question to Choice by retrieving objects of Choice and Question by id
	 * and pass the question object into setQuestion Method of Choice model class.
	 * </p>
	 * 
	 * @param choiceId
	 *     id of choice to allocate question
	 * @param questionId
	 *     id question to allocate
	 * @throws DataException
	 *     if id is not exist or if any hibernate Exception arrived
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
			throw new DataException(e.toString());
		}
	}
}

