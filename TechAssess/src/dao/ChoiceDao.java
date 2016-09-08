/**
 * 
 */
package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dbconnection.DataBaseConnection;
import exception.DataException;
import model.Choice;
import model.Question;

/**
 * @author user
 *
 */
public class ChoiceDao {

	private DataBaseConnection connection = DataBaseConnection.getConnection();
    private SessionFactory factory = connection.createSessionFactory();
    
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
	
	public Choice retrieveChoiceDetailById(int choiceId) throws DataException {
		Session session = factory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			return (Choice)session.get(Choice.class, choiceId);
		} catch (HibernateException e) {
			throw new DataException(e.toString());
		}
	}
	
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

