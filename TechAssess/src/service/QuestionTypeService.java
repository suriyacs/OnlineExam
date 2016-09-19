/**
 * 
 */
package service;

import org.springframework.stereotype.Service;

import dao.QuestionTypeDao;
import exception.DataException;
import model.Question;
import model.QuestionType;

/**
 *     Service which accepts request from controller to perform operations like
 *     insert question type and retrieve question type and
 *     it resolve the request by forwarding the incoming request to their
 *     corresponding methods in Question Data Access Object. 
 * @author TechAssess
 *
 */
@Service
public class QuestionTypeService {

	QuestionTypeDao questionTypeDao = new QuestionTypeDao();

	/**
	 * <p>
	 *     Method which pass the typeId to retrieveTypeDetailById method present in QuestionType
	 *     DataAccessObject for retrieving details of given type Id.
	 * </p>
	 * 
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
	 * <p>
	 *     Method which pass questionType object and question object to allocateQuestionToQuestionType
	 *     method present in data access object for allocating question to questiontype.
	 * </p>
	 * 
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

