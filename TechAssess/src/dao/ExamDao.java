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
import model.User;
import exception.DataException;
import dbconnection.DataBaseConnection;

/**
 * <p>
 * This class provide interface between database and Service class.
 * insert Exam Details from Service class into database and also perform retrieve 
 * Exam information from database and allocate Question operations.
 * </p>
 * 
 * @author TechAssess
 *
 */
public class ExamDao {
	private DataBaseConnection connection = DataBaseConnection.getConnection();
    private SessionFactory factory = connection.createSessionFactory();
    
    /**
     * <p>
     * gets Exam model Object from Service class which contains details of Exam and 
     * create the session then begin the transaction 
     * persist the exam object and close the session
     * returns id after insertion of exam into database.
     * </p>
     * 
     * @param exam
     *     object contains details of Exam like exam name,Duration etc.
     * @return id
     *     id of exam which is added to the database.
     * @throws DataException
     *     if inputs are invalid or if any Hibernate Exception arrived
     */
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
             
    /**
     * <p>
     * retrieve all Exams from Database in List format and
     * send this list back to Service Class.
     * </p>
     * 
     * @return allExams
     *    contains list of all exams
     * @throws DataException
     *    if input is invalid or if any hibernate Exception is arrived
     */
    @SuppressWarnings("unchecked")
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
    
    /**
     * <p>
     * Allocate Questions to Exam by Retrieving objects of Question  and Exam Model classes  and
     * create the temporary set.finally perform allocation by Calling setExams and setQuestions methods 
     * Question and Exam model classes.
     * </p>
     * 
     * @param examId
     *     contains id of Exam to allocate
     * @param questionId
     *     contains id of Question to allocate
     * @throws DataException
     *     if inputs are invalid or if any Hibernate Exception is arrived
     */
    @SuppressWarnings("unchecked")
	public void assignQuestionsToExam(int examId,int questionId)throws DataException {
    	Session session = factory.openSession();
    	try {
    		Set examSet = new HashSet();
    		Transaction transaction = session.beginTransaction();
    		Question  question = (Question)session.get(Question.class, questionId);
    		Exam exam = (Exam)session.get(Exam.class,examId);
    		exam.setQuestions(question);
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
    
    /**
     * <p>
     * increase the AllocatedQuestionscount of Exam after allocation of Question is
     * performed successfully by calling setNoOfAllocatedQuestions method of Exam model classes.
     * </p>
     * 
     * @param exam
     *     objects contains details of exam.
     */
    public void increaseAllocatedQuestionsCount(Exam exam) {
    	if (exam.getNoOfAllocatedQuestions() != null) {
			int count = Integer.parseInt(exam.getNoOfAllocatedQuestions());
			count++;
			exam.setNoOfAllocatedQuestions(count+"");
		} else {
			exam.setNoOfAllocatedQuestions("1");
		}
    }
    
    /**
     * <p>
     *  Allocate User to Exam by Retrieving objects of User  and Exam Model classes  and
     * create the temporary set and add user object into that set.finally perform allocation by Calling setUsers and 
     * setExams methods of User and Exam model classes.
     * </p>
     * 
     * @param examId
     *     contains id of Exam.
     * @param userId
     *     contains id of user.
     * @throws DataException
     *     if inputs are invalid or if any Hibernate Exception is arrived
     */
    @SuppressWarnings("finally")
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
    
    /**
     * <p>
     * retrieves the Exam Details of particular id from database and return this details
     * to ExamService class
     * </p>
     * 
     * @param examId
     *     contains id of Exam to retrieve.
     * @return exam
     *     object which contains details of exam like name,duration etc.      
     * @throws DataException
     *     if input is invalid or if any Hibernate Exception is arrived
     */
    
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
