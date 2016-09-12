/**
 * 
 */
package service;

import dao.QuestionTypeDao;
import exception.DataException;
import model.Question;
import model.QuestionType;

/**
 * Service which pass the incoming request to Data Access Object of QuestionType
 * to process the request and send desired response.
 * @author TechAssess
 *
 */
public class QuestionTypeService {

	QuestionTypeDao questionTypeDao = new QuestionTypeDao();

	/**
	 * Method which send request to QuestionType DataAccessObject for retrieving details of given type Id.
	 * @param typeId
	 *     consist of type id for retrieving the details.
	 * @return object
	 *     returns QuestionType object of given typeId.
	 * @throws DataException
	 *     throws an exception to controller which gets generated at the time of database connection.
	 */
	public QuestionType getTypeDetailById(int typeId) throws DataException {
		return questionTypeDao.retrieveTypeDetailById(typeId);
	}
	
	/**
	 * Method which redirects to QuestionType repository for allocating question type to question which was created.
	 * @param questionType
	 *     consist of questionType in which the question is created.
	 * @param question
	 *     it consist of question object to which the question type needs to be allocated.
	 * @throws DataException
	 *     throws an exception to controller which gets generated at the time of database connection.
	 */
	public void addQuestion(QuestionType questionType, Question question) throws DataException {
		questionTypeDao.allocateQuestionToQuestionType(questionType, question);
	}
}

