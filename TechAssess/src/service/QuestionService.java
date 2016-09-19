package service;

import java.util.List;

import org.springframework.stereotype.Service;

import dao.QuestionDao;
import exception.DataException;
import model.Question;

/**
 * <p>
 *     Service which accepts request from controller to perform operations like
 *     insert question, retrieve question details, allocate QuestionType to question,
 *     and finally to check the given question is present.
 *     It resolve the request by forwarding the incoming request to their
 *     corresponding methods in Question Data Access Object. 
 * <p>
 * 
 * @author TechAssess
 *
 */
@Service
public class QuestionService {
    QuestionDao questionDao = new QuestionDao();
	
   /**
    * <p>
    * Method which pass a question object to insert question method present i
    * Question data access object to insert the given question into database.
    * </p>
    * 
    * @param questionName
    *     Consist of question to be inserted.
    * @return int
    *     Returns id of inserted question as questionId.
    * @throws DataException
    *     Throws an exception to controller which gets generated at the time of database connection.
    */ 
    public int addQuestion(String questionName) throws DataException {
    	return questionDao.insertQuestion(new Question(questionName));
    }
	
    /**
     * <p>
     *     Method which pass id of the question and question type to assign question type method present
     *     in data access object to allocate the question with the corresponding question type.
     * </p>
     * 
     * @param typeId
     *     Consist of question type id for track and allocate it to the question.
     * @param questionId
     *     Consist of question id which needs to be tracked for existence.
     * @throws DataException
     *     Throws an exception to controller which gets generated at the time of database connection.
     */
    public void allocateQuestionType(int typeId, int questionId) throws DataException {
        questionDao.assignQuestionType(typeId, questionId);
     }
	
    /**
     * <p>
     *     Method which pass the questinoId to retrieve question detail by id method
     *     present in data access object to retrieve the details of given questionId.
     * </p>
     * 
     * @param questionId
     *     Consist of question id which needs to be tracked for retrieving the details.
     * @return object
     *     Returns instance of Question type for given questionId.
     * @throws DataException
     *     Throws an exception to controller which gets generated at the time of database connection.
     */
    public Question getQuestionDetailById(int questionId) throws DataException {
        return questionDao.retrieveQuestionDetailById(questionId);
    }
	
    /**
     * <p>
     *     Method which request retrieveAllQuestions method in
     *     data access object to retrieve details of entire questions in the form of list.
     * </p>
     * 
     * @return list
     *     Contains details of all Question in the form of List.
     * @throws DataException
     *     Throws an exception to controller which gets generated at the time of database connection.
     */
    public List<Question> getAllQuestions() throws DataException {
        if (null == questionDao.retrieveAllQuestions()) {  
            throw new DataException("There are no questions in database.Please insert some questions first.!!");
	}
        return (questionDao.retrieveAllQuestions());
    }
	 
    /**
     * <p>
     *     Method which pass the question Id to data access object to check the given id exist.
     * </p>
     * 
     * @param questionId
     *     Consist of id of question which needs to be tracked for existence.
     * @throws DataException
     *     Throws an exception to controller which gets generated at the time of database connection.
     */
    public void checkIfQuestionExist(int questionId)throws DataException {
        if(null == getQuestionDetailById(questionId)) {
            throw new DataException("Question with this id does not exist.!!Try Again..!!");
        }
    }
}
