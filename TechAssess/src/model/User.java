package model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * <p>
 * Pojo which creates an instance of User type by constructor or by using setter
 * and getter methods.
 * </p>
 * 
 * @author TechAssess
 *
 * @created 2016-08-27
 */
@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    int userId;
    @Column(name = "user_name")
    String userName;
    @Column(name = "password")
    String password;
    @Column(name = "email_id")
    String emailId;
    @Column(name = "mobile_number")
    String mobileNumber;
    @Column(name = "role_id")
    int roleId;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "users")
    Set<Exam> exams = new HashSet<Exam>();

    public User() {

    }

    public User(String userName, String emailId, String password, String mobileNumber, int roleId) {
        this.userName = userName;
        this.emailId = emailId;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.roleId = roleId;

    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getEmailId() {
        return this.emailId;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getMobileNumber() {
        return this.mobileNumber;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getRoleId() {
        return this.roleId;
    }

    public Set<Exam> getExams() {
        return exams;
    }

    public void setExams(Set<Exam> exams) {
        this.exams.addAll(exams);
    }
}
