/**
 * 
 */
package service;

import dao.QuestionTypeDao;
import exception.DataException;
import model.Question;
import model.QuestionType;

/**
 * @author user
 *
 */
public class QuestionTypeService {

	QuestionTypeDao questionTypeDao = new QuestionTypeDao();

	public QuestionType getTypeDetailById(int typeId) throws DataException {
		return questionTypeDao.retrieveTypeDetailById(typeId);
	}
	
	public void addQuestion(QuestionType questionType, Question question) throws DataException {
		questionTypeDao.addQuestionToQuestionType(questionType, question);
	}
}

