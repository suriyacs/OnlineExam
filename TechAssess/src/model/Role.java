package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Role")
public class Role {
	
	@Id
	@Column(name="role_id")
    int roleId;
	@Column(name="role_name")
    String roleName;
	
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	public int getRoleId() {
	    return this.roleId;
	}
	
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public String getRoleName() {
		return this.roleName;
	}
}
