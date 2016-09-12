package service;

import java.util.List;

import dao.QuestionDao;
import exception.DataException;
import model.Question;

/**
 * Service which accept the incoming request from controller and
 * process the request by forwarding it to the Data Access Object of Question.
 * 
 * @author TechAssess
 *
 */
public class QuestionService {

	QuestionDao questionDao = new QuestionDao();
	
	/**
	 * Method which request the question Data Access Object
	 * for inserting the question to the database.
	 * 
	 * @param questionName
	 *     consist of question to be inserted.
	 * @return int
	 *     returns id of inserted question as questionId.
	 * @throws DataException
	 *     throws an exception to controller which gets generated at the time of database connection.
	 */
	public int addQuestion(String questionName) throws DataException {
	    return questionDao.insertQuestion(new Question(questionName));
	}
	
	/**
	 * Method which request the Question repository for allocating the question type to question.
	 * @param typeId
	 *     consist of question type id for track and allocate it to the question.
	 * @param questionId
	 *     consist of question id which needs to be tracked for existence.
	 * @throws DataException
	 *     throws an exception to controller which gets generated at the time of database connection.
	 */
	public void allocateQuestionType(int typeId, int questionId) throws DataException {
		questionDao.assignQuestionType(typeId, questionId);
	}
	
	/**
	 * Method which request Question DataAccessObject for retrieving exam details of given examId.
	 * @param questionId
	 *     consist of question id which needs to be tracked for retrieving the details.
	 * @return object
	 *     returns instance of Question type for given questionId.
	 * @throws DataException
	 *     throws an exception to controller which gets generated at the time of database connection.
	 */
	public Question getQuestionDetailById(int questionId) throws DataException {
		return questionDao.retrieveQuestionDetailById(questionId);
	}
	
	/**
	 * Method which send request to Question DataAccessObject for retrieving details of all exam in the form of List.
	 * @return list
	 *     contains details of all Question in the form of List.
	 * @throws DataException
	 *     throws an exception to controller which gets generated at the time of database connection.
	 */
	public List<Question> getAllQuestions() throws DataException {
		if (questionDao.retrieveAllQuestions() == null) {
			throw new DataException("There are no questions in database.Please insert some questions first.!!");
		}
		return (questionDao.retrieveAllQuestions());
	}
	
	/**
	 * Method which send request to Question DataAccessObject to check if the given id exist in database.
	 * @param questionId
	 *     consist of id of question which needs to be tracked for existence.
	 * @throws DataException
	 *     throws an exception to controller which gets generated at the time of database connection.
	 */
	public void checkIfQuestionExist(int questionId)throws DataException {
		if(questionDao.retrieveQuestionDetailById(questionId) == null) {
			throw new DataException("Question with this id does not exist.!!Try Again..!!");
		}
	}
}

