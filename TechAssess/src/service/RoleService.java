package service;

import org.springframework.stereotype.Service;

import dao.RoleDao;
import exception.DataException;

/**
 * <p>
 *     Service which accepts request from controller to perform operations like
 *     insert role and retrieve role and
 *     it resolve the request by forwarding the request to their
 *     corresponding methods present in Role Data Access Object. 
 * </p>
 * @author TechAssess
 *
 */
@Service
public class RoleService {
    private RoleDao roleDao = new RoleDao();
	
	/**
	 * <p>
	 *     Method which pass roleName to retrieveRoleId method present in
	 *     Role Data Access Object to retrieve role id for given role name.
	 * </p>
	 * 
	 * @param roleName
	 *     Consist of name of the role to be tracked.
	 * @return int
	 *     Return id of the given role name.
	 * @throws DataException
	 *     Throws an exception to controller which gets generated at the time of database connection.
	 */
	public int getRoleIdByName(String roleName) throws DataException {
	    return roleDao.retrieveRoleId(roleName);
	}
	
	/**
	 * <p>
	 *     Method which pass roleId to retrieveRoleName method present in Role Data Access Object
	 *     to retrieve role name of the given role id.
	 * </p>
	 * 
	 * @param roleId
	 *     Contains id of the role to be fetched.
	 * @return string
	 *     Consist of role name for the given role id.
	 * @throws DataException
	 *     Throws an exception to controller which gets generated at the time of database connection.
	 */
	public String getRoleNameById(int roleId) throws DataException {
	    return roleDao.retrieveRoleName(roleId);
	}
}

