/**
 * 
 */
package service;

import dao.ChoiceDao;
import exception.DataException;
import model.Choice;

/**
 * @author user
 *
 */
public class ChoiceService {

	ChoiceDao choiceDao = new ChoiceDao();
	
	public int addChoice(String answer, String correctAnswer) throws DataException {
		return choiceDao.insertChoice(new Choice(answer,Integer.parseInt(correctAnswer)));
	}
	
	public Choice getChoiceDetailsById(int choiceId) throws DataException {
		return choiceDao.retrieveChoiceDetailById(choiceId);
	}
	
	public void allocateQuestion(int choiceId, int questionId) throws DataException {
		choiceDao.assignQuestion(choiceId, questionId);
	}
}

