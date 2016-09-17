package service;

import dao.ChoiceDao;
import exception.DataException;
import model.Choice;

/**
 * <p>
 *     ChoiceService gets request from controller and process the given
 *     data by forwarding the request to Data Access Object of Choice.
 * </p>
 * 
 * @author TechAssess
 *
 */
public class ChoiceService {

	ChoiceDao choiceDao = new ChoiceDao();
	
	/**
	 * Method which accept the request and reads the URL parameters and
	 * pass the same parameter to ChoiceDao to complete the given process.
	 * 
	 * @param answer
	 *     contains given answer for the question.
	 * @param correctAnswer
	 *     contains integer value to indicate that the given answer is correct or not.
	 * @return int 
	 *     comprise the choiceId generated at the time of insertion.
	 * @throws DataException
	 *     throws an exception to controller which gets generated at the time of database connection.
	 */
	public int addChoice(String answer, int correctAnswer) throws DataException {
		return choiceDao.insertChoice(new Choice(answer,correctAnswer));
	}
	
	/**
	 * <p>
	 * Method which accept the request from controller and
	 * process the request by forwarding the same request to ChocieDao.
	 * </p>
	 * 
	 * @param choiceId
	 *     consist of ChoiceId for retrieving the choice details of given id.
	 * @return object
	 *     comprise instance of Choice type of given choice Id.
	 * @throws DataException
	 *     throws an exception to controller which gets generated at the time of database connection.
	 */
	public Choice getChoiceDetailsById(int choiceId) throws DataException {
		return choiceDao.retrieveChoiceDetailById(choiceId);
	}
	
	/**
	 * Method which accept the request from controller and process
	 * the requested URL by passing it to the Data Access Object. 
	 * 
	 * @param choiceId
	 *     consist of id for which the instance of choice needs to be tracked.
	 * @param questionId
	 *     consist of id for which the instance of question to be traced.
	 * @throws DataException
	 *     throws an exception to controller which gets generated at the time of database connection.
	 */
	public void allocateQuestion(int choiceId, int questionId) throws DataException {
		choiceDao.assignQuestion(choiceId, questionId);
	}
}

