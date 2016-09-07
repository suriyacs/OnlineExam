package service;

import java.util.List;

import model.Exam;
import dao.ExamDao;
import exception.DataException;

public class ExamService {
    ExamDao examDao = new ExamDao();
    
    public int addExamDetails(Exam exam)throws DataException {
    	try {
    	    return(examDao.insertExamDetails(exam));  
    	} catch(DataException e) {
    		throw new DataException(e.getMessage().toString());
    	}
    }
    
    public void allocateQuestionsToExam(int examId,int questionId ) throws DataException {
    	    examDao.assignQuestionsToExam(examId,questionId);
    }
}
