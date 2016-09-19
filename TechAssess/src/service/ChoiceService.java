package service;

import exception.DataException;

import org.springframework.stereotype.Service;

import dao.ChoiceDao;
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
@Service
public class ChoiceService {

	ChoiceDao choiceDao = new ChoiceDao();
	
	/**
	 * <p>
	 *  method which gets choice details from controller and pass this details 
	 *  back to insertChoice method of choiceDao class.
	 *  </p> 
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
	 * Method which gets details of particular choice by passing id of that choice
	 * into retrieveChoiceDetailsById method of ChoiceDao class.
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
	 * <p>
	 *  method which allocateQuestion to particular choice by passing id of both 
	 *  question and chioce into assignQuestion method of ChoiceDao class
	 * </p>
	 * @param choiceId
	 *     consist id of choice to allocate.
	 * @param questionId
	 *     consist id of question to allocate.
	 * @throws DataException
	 *     throws an exception to controller which gets generated at the time of database connection.
	 */
	public void allocateQuestion(int choiceId, int questionId) throws DataException {
		choiceDao.assignQuestion(choiceId, questionId);
	}
}

