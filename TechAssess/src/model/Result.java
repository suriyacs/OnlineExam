package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>
 * Pojo which creates instance for Result for insertion and also contains setter
 * and getter method to store or retrieve particular element.
 * </p>
 *
 * @author TechAssess
 * @created 2016-08-27
 */
@Entity
@Table(name = "result")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "result_id")
    int resultId;

    @Column(name = "exam_name")
    String examName;
    @Column(name = "user_name")
    String userName;
    @Column(name = "mark")
    int mark;

    public Result(String examName, String userName, int mark) {
        this.examName = examName;
        this.userName = userName;
        this.mark = mark;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}
