package dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.Exam;
import model.Question;
import model.User;
import exception.DataException;
import dbconnection.DataBaseConnection;

public class ExamDao {
	private DataBaseConnection connection = DataBaseConnection.getConnection();
    private SessionFactory factory = connection.createSessionFactory();
    
    public int insertExamDetails(Exam exam)throws DataException {
    	Session session = factory.openSession();
    	
    	try {    		
    		Transaction transaction = session.beginTransaction();
    		int examId = (int)session.save(exam);
    		transaction.commit();
    		return examId;
    	} catch(HibernateException e) {
    		throw new DataException(e.toString());
    	} finally {
    		session.close();
    	}    
    }
    
    public void assignQuestionsToExam(int examId,int questionId)throws DataException {
    	Session session = factory.openSession();
    	try {
    		Set questionSet = new HashSet();
    		Set examSet = new HashSet();
    		Transaction transaction = session.beginTransaction();
    		Question question = (Question)session.get(Question.class, questionId);
    		Exam exam = (Exam)session.get(Exam.class,examId);
    		questionSet.add(question);
    		exam.setQuestions(questionSet);
    		examSet.add(exam);
    		question.setExams(examSet);
    		session.save(exam);
    		session.save(question);
    		transaction.commit();
    	} catch(HibernateException e) {
    		throw new DataException("Can't assign questions to this exam..!!Please Try again.!!");
    	} finally {
    		session.close();
    	}
    }
    
    public List<Exam> retrieveAllExams() throws DataException {
    	Session session = factory.openSession();
    	try {
    		return session.createQuery("from Exam").list();
    	} catch(HibernateException e) {
    		throw new DataException("Can't assign questions to this exam..!!Please Try again.!!");
    	} finally {
    		session.close();
    	}
    }
    
    public Exam assignUserToExam(int examId, int userId) throws DataException {
    	Session session = factory.openSession();
    	Exam exam = null;
    	try {
    		Set userSet = new HashSet();
    		Set examSet = new HashSet();
    		Transaction transaction = session.beginTransaction();
    		exam = (Exam)session.get(Exam.class, examId);
    		User user = (User)session.get(User.class, userId);
    		userSet.add(user);
    		exam.setUsers(userSet);
    		examSet.add(exam);
    		user.setExams(examSet);
    		session.save(user);
    		session.save(exam);
    		transaction.commit();
    	} catch(HibernateException e) {
    		throw new DataException("Can't assign questions to this exam..!!Please Try again.!!");
    	} finally {
    		session.close();
    		return exam;
    	}
    }
    
    public Exam retrieveExamById(int examId) throws DataException {
    	Session session = factory.openSession();
    	try {
    		return (Exam)session.get(Exam.class, examId);
    	} catch(HibernateException e) {
    		throw new DataException("Can't assign questions to this exam..!!Please Try again.!!");
    	} finally {
    		session.close();
    	}
    }
}
