/**
 * 
 */
package service;

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
}

