package dbconnection;

import org.hibernate.SessionFactory;  
import org.hibernate.cfg.AnnotationConfiguration; 

/**
 * <p>
 *    Class which gets invoked by repository of both employee and project for 
 *    creating a connection with the database for processing the request given by the Service.
 *</p>
 * @author Antony
 *
 */
public class DataBaseConnection{
    private static DataBaseConnection connection = null;
    private AnnotationConfiguration configuration = null;
    private SessionFactory sessionFactory = null;
    
    private DataBaseConnection() {
    }
    
    /**
     * <p>
     *     Method which gets invoked by the repository of employee
     *     or project for creating an connection with database.
     * </p>
     * @return object:
     *     Contains instance of HibernateConnection type which create a connection with database.
     */
    public static DataBaseConnection getConnection() {
        if(null == connection) {
            connection = new DataBaseConnection();
        }
        return connection;
    }

    /**
     * <p>
     *     Method which gets invoked by the repository of project or employee 
     *     to configure the annotated model of project or employee
     *     with hibernate and add the annotated classes to create a session factory.
     * </p>
     * @return object:
     *     Contains an instance of sessionfactory which holds the configured annotated class objects.
     */
    public SessionFactory createSessionFactory() {
     if(null == sessionFactory) {
     configuration = new AnnotationConfiguration();
            configuration.configure("hibernate.cfg.xml");
            configuration.addAnnotatedClass(model.User.class);
            configuration.addAnnotatedClass(model.Role.class);
            configuration.addAnnotatedClass(model.Exam.class);
            configuration.addAnnotatedClass(model.Question.class);
            configuration.addAnnotatedClass(model.Choice.class);
            configuration.addAnnotatedClass(model.QuestionType.class);
         sessionFactory = configuration.buildSessionFactory();
     }
     return sessionFactory;
    }
}
