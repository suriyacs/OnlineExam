package service;

import dao.RoleDao;
import exception.DataException;

/**
 * Service which accept the incoming request from controller by passing the same request ro Role Dao. 
 * @author TechAssess
 *
 */
public class RoleService {

	private RoleDao roleDao = new RoleDao();
	
	/**
	 * Method which request Role DataAccess to retrieve role id for given role name.
	 * @param roleName
	 *     consist of name of the role to be tracked.
	 * @return int
	 *     return id of the given role name.
	 * @throws DataException
	 *     throws an exception to controller which gets generated at the time of database connection.
	 */
	public int getRoleIdByName(String roleName) throws DataException {
		return roleDao.retrieveRoleId(roleName);
	}
	
	/**
	 * Method which accept the incoming request and returns role name of the given role id.
	 * @param roleId
	 *     contains id of the role to be fetched.
	 * @return string
	 *     consist of role name for the given role id.
	 * @throws DataException
	 *     throws an exception to controller which gets generated at the time of database connection.
	 */
	public String getRoleNameById(int roleId) throws DataException {
		return roleDao.retrieveRoleName(roleId);
	}
}

