package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dbconnection.DataBaseConnection;
import exception.DataException;
import model.Role;
import model.User;

/**
 * <p>
 * This class provide interface between database and Service class.
 * insert Exam Details from Service class into database and also perform retrieve 
 * Exam information from database and allocate Question operations.
 * </p>
 * 
 * @author suriyakumar
 *
 */
public class RoleDao {

	private DataBaseConnection connection = DataBaseConnection.getConnection();
    private SessionFactory factory = connection.createSessionFactory();
    
    /**
     * <p>
     *  retrieve all Roles from Database in List format and
     * send this list back to Service Class.
     * </p>
     * 
     * @return list object
     *     which contains all Roles.
     * @throws DataException
     *     if input is invalid or if any hibernate Exception is arrived
     */
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
	 * retrieves the Role id of particular Name from database and return this details
     * to RoleService class
	 * </p>
	 * @param roleName
	 *     contains name of particular Role.
	 * @return
	 *     returns of particular role.
	 * @throws DataException
	 *     if input is invalid or if any hibernate Exception is arrived
	 */
	public int retrieveRoleId(String roleName) throws DataException {
		for(Role role : getAllRoles()) {
			if(role.getRoleName().equals(roleName)) {
				return role.getRoleId();
			}
		}
		return 0;
	}
	
	/**
	 * <p>
	 *  retrieves Role Name of Particular Id from Database and return this details to
	 *  Service class.
	 *  </p>
	 *  
	 * @param roleId
	 *     contains id of particular id.
	 * @return
	 *     name of Particular role.
	 * @throws DataException
	 *     if input is invalid or if any hibernate Exception is arrived
	 */
	public String retrieveRoleName(int roleId) throws DataException {
		try {
			for(Role role : getAllRoles()) {
				if(role.getRoleId() == roleId) {
					return role.getRoleName();
				}
			}
		} catch (HibernateException e) {
			throw new DataException("No details found for" + " " + roleId);
		}
		return null;
	}
}

