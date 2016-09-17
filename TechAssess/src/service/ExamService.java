package service;

import java.util.List;

import model.Exam;
import dao.ExamDao;
import exception.DataException;

/**
 * <p>
 *     Service which accepts request from controller and purify the request
 *     by requesting the Data Access Object of Exam to process the incoming request.
 * </p>
 * @author TechAssess
 *
 */
public class ExamService {
    ExamDao examDao = new ExamDao();
    
    /**
     *  Method which accept request from controller and redirects the instance of Exam to Data Access Object
     *  to insert the exam.
     * @param exam
     *     consist instance of exam to be forwarded to Data Access Object.
     * @return int
     *     returns id of the given exam when the instance of exam is inserted successfully.
     * @throws DataException
     *    throws an exception to controller which gets generated at the time of database connection.
     */
    public int addExamDetails(Exam exam)throws DataException {
    	try {
    	    return(examDao.insertExamDetails(exam));  
    	} catch(DataException e) {
    		throw new DataException(e.getMessage().toString());
    	}
    }
    
    /**
     * Method which send request to Data Access Object for allocating an
     * exam id to question once the request from controller is received.
     * 
     * @param examId
     *     contains id of exam for which the question needs to be allocated.
     * @param questionId
     *     contains id of question needs to be traced for allocating to the exam.
     * @throws DataException
     *     throws an exception to controller which gets generated at the time of database connection.
     */
    public void allocateQuestionsToExam(int examId,int fromQuestionId,int toQuestionId) throws DataException {
    	    Exam exam = getExamById(examId);
    	    if (exam.getNoOfAllocatedQuestions() != null) {
    	        if (Integer.parseInt(exam.getNoOfAllocatedQuestions()) == exam.getNoOfTotalQuestions()) {
    	    	    throw new DataException("This Exam already allocated with enough questions..!Try again with different Id..!!");
    	        }
    	    }
    	    for (int questionId = fromQuestionId;questionId <= toQuestionId;questionId++) { 
    	        examDao.assignQuestionsToExam(examId,questionId);
    	    }
    }
    
    /**
     * Method which send request to DataAccessObject for 
     * retrieving details of entire exam in the form of List.
     * @return list<Exam>
     *     return entire exam details in the form of list.
     * @throws DataException
     *     throws an exception to controller which gets generated at the time of database connection.
     */
    public List<Exam> getAllExamDetails() throws DataException {
    	if (examDao.retrieveAllExamDetails() == null) {
    		throw new DataException("There is no Exams in Database.Please insert some Exams first..!!");
    	}
    	return(examDao.retrieveAllExamDetails());
    }
    
    /**
     * Method which send request to DataAccessObject to check if the given exam id exist.
     * @param examId
     *     consist of exam id which needs to be check if the given id exists.
     * @throws DataException
     *     throws an exception to controller which gets generated at the time of database connection.
     */
    public void checkIfExamExist(int examId)throws DataException {
    	if (getExamById(examId) == null) {
        		throw new DataException("Exam with this Id Does not Exist..!!Try Again..!!");
    	}
    }
    
    /**
     * Method which send request to Exam DataAccessObject  for allocating user to exam they selected.
     * @param examId
     *     consist of examId to which the user has to be allocated.
     * @param userId
     *     consist of userId who have selected the exam.
     * @throws DataException
     *     throws an exception to controller which gets generated at the time of database connection.
     */
    public void addUserToExam(String examId, int userId) throws DataException {
    	examDao.assignUserToExam(Integer.parseInt(examId), userId);
    }
    
    /**
     * Method which accept the request and retrieve the exam details
     * for the given by sending request to DataAccessObject.
     * 
     * @param examId
     *     consist of id for retrieving the exam details of given id.
     * @return object
     *     returns exam object of given exam id.
     * @throws DataException
     *     throws an exception to controller which gets generated at the time of database connection.
     */
    public Exam getExamById(int examId) throws DataException {
    	return examDao.retrieveExamById(examId);
    }
}
