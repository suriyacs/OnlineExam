package service;

import java.util.List;

import model.Exam;
import dao.ExamDao;
import exception.DataException;

public class ExamService {
    ExamDao examDao = new ExamDao();
    
    public void addExamDetails(Exam exam)throws DataException {
    	try {
    	    examDao.insertExamDetails(exam);  
    	} catch(DataException e) {
    		throw new DataException(e.getMessage().toString());
    	}
    }
}
