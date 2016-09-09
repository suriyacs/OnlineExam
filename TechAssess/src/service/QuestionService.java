/**
 * 
 */
package service;

import java.util.List;

import dao.QuestionDao;
import exception.DataException;
import model.Question;

/**
 * @author user
 *
 */
public class QuestionService {

	QuestionDao questionDao = new QuestionDao();
	
	public int addQuestion(String questionName) throws DataException {
	    return questionDao.insertQuestion(new Question(questionName));
	}
	
	public void allocateQuestionType(int typeId, int questionId) throws DataException {
		questionDao.assignQuestionType(typeId, questionId);
	}
	
	public Question getQuestionDetailById(int questionId) throws DataException {
		return questionDao.retrieveQuestionDetailById(questionId);
	}
	
	public List<Question> getAllQuestions() throws DataException {
		if (questionDao.retrieveAllQuestions() == null) {
			throw new DataException("There are no questions in database.Please insert some questions first.!!");
		}
		return (questionDao.retrieveAllQuestions());
	}
	
	public void checkIfQuestionExist(int questionId)throws DataException {
		if(questionDao.retrieveQuestionDetailById(questionId) == null) {
			throw new DataException("Question with this id does not exist.!!Try Again..!!");
		}
	}
}

