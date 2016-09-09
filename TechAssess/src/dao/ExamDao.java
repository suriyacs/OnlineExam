package dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.Exam;
import model.Question;
import exception.DataException;
import dbconnection.DataBaseConnection;

public class ExamDao {
	private DataBaseConnection connection = DataBaseConnection.getConnection();
    private SessionFactory factory = connection.createSessionFactory();
    
    public int insertExamDetails(Exam exam)throws DataException {
    	Session session = factory.openSession();
    	
    	try {    		
    		Transaction transaction = session.beginTransaction();
    		transaction.commit();
    		return ((int)session.save(exam));
    	} catch(HibernateException e) {
    		throw new DataException("Invalid Inputs Please Check The Inputs and Try Again");
    	} finally {
    		session.close();
    	}    
    }
        
    public Exam getExamById(int examId)throws DataException {
    	Session session = factory.openSession();
    	 
    	Transaction transaction = session.beginTransaction();
    	Exam exam =(Exam)session.get(Exam.class,examId); 
    	return exam;
    }
    
    public List<Exam> retrieveAllExamDetails()throws DataException {
    	Session session = factory.openSession();
    	List<Exam>allExams = new ArrayList<Exam>();
    	
    	try {
    		Transaction transaction = session.beginTransaction();
    		allExams = session.createQuery("from Exam").list();
    		transaction.commit();
        } catch(HibernateException e) {
        	throw new DataException(e.toString());
       	} finally {
       		session.close();
       	}
    	return allExams;
    }
    
    public void assignQuestionsToExam(int examId,int questionId)throws DataException {
    	Session session = factory.openSession();
    	try {
    		Set questionSet = new HashSet<>();
    		Set examSet = new HashSet();
    		Transaction transaction = session.beginTransaction();
    		Question question = (Question)session.get(Question.class, questionId);
    		Exam exam = (Exam)session.get(Exam.class,examId);
    		questionSet.add(question);
    		exam.setQuestions(questionSet);
    		examSet.add(exam);
    		question.setExams(examSet);
    		increaseAllocatedQuestionsCount(exam);
    		session.save(exam);
    		session.save(question);
    		transaction.commit();
    	} catch(HibernateException e) {
    		throw new DataException("Can't assign questions to this exam..!!Please Try again.!!");
    	} finally {
    		session.close();
    	}
    }
    
    public void increaseAllocatedQuestionsCount(Exam exam) {
    	if (exam.getNoOfAllocatedQuestions() != null) {
			int count = Integer.parseInt(exam.getNoOfAllocatedQuestions());
			count++;
			exam.setNoOfAllocatedQuestions(count+"");
		} else {
			exam.setNoOfAllocatedQuestions("1");
		}
    }
}
