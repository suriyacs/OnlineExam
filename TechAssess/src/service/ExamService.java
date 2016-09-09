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
    	    Exam exam = examDao.getExamById(examId);
    	    if (exam.getNoOfAllocatedQuestions() != null) {
    	        if (Integer.parseInt(exam.getNoOfAllocatedQuestions()) == exam.getNoOfTotalQuestions()) {
    	    	    throw new DataException("This Exam already allocated with enough questions..!Try again with different Id..!!");
    	        }
    	    }
    	    examDao.assignQuestionsToExam(examId,questionId);
    }
    
    public List<Exam> getAllExamDetails() throws DataException {
    	if (examDao.retrieveAllExamDetails() == null) {
    		throw new DataException("There is no Exams in Database.Please insert some Exams first..!!");
    	}
    	return(examDao.retrieveAllExamDetails());
    }
    
    public void checkIfExamExist(int examId)throws DataException {
    	if (examDao.getExamById(examId) == null) {
        		throw new DataException("Exam with this Id Does not Exist..!!Try Again..!!");
    	}
    }
    
    public void addUserToExam(String examId, int userId) throws DataException {
    	examDao.assignUserToExam(Integer.parseInt(examId), userId);
    }
    
    public Exam getExamById(int examId) throws DataException {
    	return examDao.retrieveExamById(examId);
    }
}
