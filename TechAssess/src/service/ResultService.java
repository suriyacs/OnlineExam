package service;



import service.ExamService;
import model.Answer;
import model.Choice;
import model.Exam;
import model.Question;
import model.Result;
import model.User;
import dao.ResultDao;
import exception.DataException;

public class ResultService {
	ExamService examService = new ExamService();
	ResultDao resultDao = new ResultDao();
	
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
		} return mark;
	}

}
