package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>
 * Pojo for Creating instance of Role so that the user can be created according
 * to the roles required at the time of login.
 * </p>
 * 
 * @author TechAssess
 * @created 2016-08-27
 */
@Entity
@Table(name = "Role")
public class Role {

    @Id
    @Column(name = "role_id")
    int roleId;
    @Column(name = "role_name")
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
