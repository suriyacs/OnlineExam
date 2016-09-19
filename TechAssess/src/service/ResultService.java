package service;

import org.springframework.stereotype.Service;

import dao.ResultDao;
import exception.DataException;
import model.Answer;
import model.Choice;
import model.Exam;
import model.Question;
import model.Result;
import model.User;
import service.ExamService;

/**
 * <p>
 *     Class which calculate the result of exam written by the user and pass
 *     the mark along with user name and the exam name to storeResult method in data access object.
 * </p>
 * 
 * @author user
 *
 */
@Service
public class ResultService {
    ExamService examService = new ExamService();
    ResultDao resultDao = new ResultDao();
	/**
	 *  <p>
	 *      Method which calculates the result by comparing the question present in question page
	 *      and question in the database. Once the question is equal it fetch the answers from
	 *      the choice database and it checked with the user selected answer if the answer were equal
	 *      the mark gets incremented. Atlast when all the answers were evaluated it pass the
	 *      resultant mark, examName, userName to storeResult method in data access object.   
	 *  </p>
	 *  
	 * @param exam
	 *      It contains the instance of user written exam.
	 * @param examId
	 *      Contains id of the exam which the user selected.
	 * @param user
	 *      Contains instance of user who selected the exam.
	 * @return mark
	 *      Contains calculated mark taken by the user.
	 * @throws DataException
	 *      Throws an exception to controller which gets generated at the time of database connection.
	 */
	public int calculateResult(Exam exam,int examId,User user) throws DataException {
	    int mark =0;
		
	    try {
		    Exam originalExamObject = examService.getExamById(examId);
	        for(Answer answer : exam.getAnswers()) {
		        for (Question originalQuestion : originalExamObject.getQuestions()) {
			        if (answer.getQuestionId() == originalQuestion.getQuestionId()) {
				        for (Choice choice : originalQuestion.getChoices()) {
				    	    if (choice.getIsCorrect() == 1) {
				    	        if (choice.getChoiceName().equalsIgnoreCase(answer.getUserAnswer())) {
				    		        mark = mark + 1;		
				    	        }
				    	    }
				        }				    
			        }
		        }
	        }
	        resultDao.storeResult(new Result(originalExamObject.getExamName(),user.getUserName(),mark));
		} catch(DataException e) {
			throw new DataException(e.getMessage().toString());
		} 
		return mark;
	}

}
