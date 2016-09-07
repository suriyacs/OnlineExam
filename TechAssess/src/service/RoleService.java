package service;

import dao.RoleDao;
import exception.DataException;

public class RoleService {

	RoleDao roleDao = new RoleDao();
	
	public int getRoleIdByName(String roleName) throws DataException {
		return roleDao.retrieveRoleId(roleName);
	}
	
	public String getRoleNameById(int roleId) throws DataException {
		return roleDao.retrieveRoleName(roleId);
	}
}

