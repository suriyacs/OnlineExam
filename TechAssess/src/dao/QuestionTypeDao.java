package dao;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import dbconnection.DataBaseConnection;
import exception.DataException;
import model.Question;
import model.QuestionType;
import util.FileUtil;

/**
 * <p>
 *     This class provide interface between database and Service class. insert
 *     QuestionType Details from Service class into database and also perform
 *     retrieve QuestionType information from database and allocate Question
 *     operations.
 * </p>
 *
 * @author TechAssess
 */
@Repository
public class QuestionTypeDao {

    private DataBaseConnection connection = DataBaseConnection.getConnection();
    private SessionFactory factory = connection.createSessionFactory();

    /**
     * <p>
     *     Retrieves the QuestionType Details of particular id from database and
     *     return this details to QuestionTypeService class
     * </p>
     * 
     * @param typeId
     *     Contains id of QuestionType to retrieve.
     * @return QuestionType 
     *     Contains instance of question type.
     * @throws DataException
     *     Throws an exception if inputs are invalid or if any Hibernate
     *     Exception is raised during database connection.
     */
    public QuestionType retrieveTypeDetailById(int typeId) throws DataException {
        Session session = factory.openSession();
        try {
            return (QuestionType) session.get(QuestionType.class, typeId);
        } catch (HibernateException e) {
            FileUtil.logError("Exception occured in retrieveTypeDetailById method in QuestionTypeDao" + e);
            throw new DataException("Cannot able retrieve details for given typeId" + " " + typeId);
        } finally {
            session.close();
        }
    }

    /**
     * <p>
     *     Allocate Question to QuestionType by Calling setQuestion and method of
     *     QuestionType model class.
     * </p>
     * 
     * @param questionType
     *     Object which contains the details of particular QuestionType.
     * @param question
     *     Object which contains the details of particular Question.
     * @throws DataException
     *     Throws an exception if inputs are invalid or if any Hibernate
     *     Exception is raised during database connection.
     */
    public void allocateQuestionToQuestionType(QuestionType questionType, Question question) throws DataException {
        Session session = factory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            Set<Question> questionSet = new HashSet<Question>();
            questionSet.add(question);
            questionType.setQuestion(questionSet);
            session.save(questionType);
            session.save(question);
            transaction.commit();
        } catch (HibernateException e) {
            FileUtil.logError("Exception occured in allocateQuestionToQuestionType method in QuestionTypeDao" + e);
            throw new DataException("Cannot able to allocate questionId" + " " + question.getQuestionId()
                    + "to questionType" + " " + questionType.getTypeId());
        } finally {
            session.close();
        }
    }
}
