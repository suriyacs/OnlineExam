package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import dbconnection.DataBaseConnection;
import exception.DataException;
import model.Result;
import util.FileUtil;

/**
 * <p>
 *     This class provide interface between database and Service class. used to
 *     store the result after user completed the exam.
 * </p>
 * 
 * @author TechAssess
 *
 */
@Repository
public class ResultDao {
    private DataBaseConnection connection = DataBaseConnection.getConnection();
    private SessionFactory factory = connection.createSessionFactory();

    /**
     * <p>
     *     This method get Result Object and Store this object into database
     * </p>
     * 
     * @param result
     *     Object contains an instance of result.
     * @throws DataException
     *     Throws an exception if inputs are invalid or if any Hibernate
     *     Exception is raised during database connection.
     */
    public void storeResult(Result result) throws DataException {
        Session session = factory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.save(result);
            transaction.commit();
        } catch (HibernateException e) {
            FileUtil.logError("Exception occured in storeResult method in ResultDao" + e);
            throw new DataException("Cannot able to store result.");
        } finally {
            session.close();
        }
    }
}
