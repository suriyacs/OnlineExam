package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import dbconnection.DataBaseConnection;
import exception.DataException;
import model.Role;
import util.FileUtil;

/**
 * <p>
 *     This class provide interface between database and Service class. insert Exam
 *     Details from Service class into database and also perform retrieve Exam
 *     information from database and allocate Question operations.
 * </p>
 * 
 * @author suriyakumar
 *
 */
@Repository
public class RoleDao {

    private DataBaseConnection connection = DataBaseConnection.getConnection();
    private SessionFactory factory = connection.createSessionFactory();

    /**
     * <p>
     *     Retrieve all Roles from Database in List format and send this list back
     *     to Service Class.
     * </p>
     * 
     * @return list 
     *     list Which contains instance of all Roles.
     * @throws DataException
     *     Throws an exception if any Hibernate Exception raised during
     *     database connection.
     */
    @SuppressWarnings("unchecked")
    public List<Role> getAllRoles() throws DataException {
        Session session = factory.openSession();
        try {
            return session.createQuery("from Role").list();
        } catch (HibernateException e) {
            throw new DataException("Error Occured while retrieving all Role details");
        } finally {
            session.close();
        }
    }

    /**
     * <p>
     *     Retrieves the Role id of particular Name from database and return this
     *     details to RoleService class
     * </p>
     * 
     * @param roleName
     *     Contains name of particular Role.
     * @return id 
     *     Returns id for given roleName.
     * @throws DataException
     *     Throws an exception if inputs are invalid or if any Hibernate
     *     Exception is raised during database connection.
     */
    public int retrieveRoleId(String roleName) throws DataException {
        int id = 0;
        for (Role role : getAllRoles()) {
            if (role.getRoleName().equals(roleName)) {
                id = role.getRoleId();
            }
        }
        return id;
    }

    /**
     * <p>
     *     Retrieves Role Name of Particular Id from Database and return this
     *     details to Service class.
     * </p>
     * 
     * @param roleId
     *     Contains id of particular id.
     * @return string
     *     Returns Name of Particular role.
     * @throws DataException
     *     Throws an exception if inputs are invalid or if any Hibernate
     *     Exception is raised during database connection.
     */
    public String retrieveRoleName(int roleId) throws DataException {
        try {
            for (Role role : getAllRoles()) {
                if (role.getRoleId() == roleId) {
                    return role.getRoleName();
                }
            }
        } catch (HibernateException e) {
            FileUtil.logError("Exception occured in retrieveRoleName method in RoleDao" + e);
            throw new DataException("No details found for" + " " + roleId);
        }
        return null;
    }
}
