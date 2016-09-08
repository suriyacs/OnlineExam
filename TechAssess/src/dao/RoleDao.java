package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dbconnection.DataBaseConnection;
import exception.DataException;
import model.Role;
import model.User;

public class RoleDao {

	private DataBaseConnection connection = DataBaseConnection.getConnection();
    private SessionFactory factory = connection.createSessionFactory();
    
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
	
	public int retrieveRoleId(String roleName) throws DataException {
		for(Role role : getAllRoles()) {
			if(role.getRoleName().equals(roleName)) {
				return role.getRoleId();
			}
		}
		return 0;
	}
	
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

