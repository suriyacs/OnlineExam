package service;

import java.util.List;

import model.Exam;
import model.User;
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
    
    public List<Exam> getAllExams() throws DataException {
    	return examDao.retrieveAllExams();
    }
    
    public void addUserToExam(String examId, int userId) throws DataException {
    	examDao.assignUserToExam(Integer.parseInt(examId), userId);
    }
    
    public Exam getExamById(int examId) throws DataException {
    	return examDao.retrieveExamById(examId);
    }
}
