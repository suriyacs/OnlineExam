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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import model.Question;
@Entity
@Table(name="Exam")
public class Exam {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="exam_id")
	int examId;
	@Column(name="exam_name")
	String examName;
	@Column(name="duration")
	int examDuration;
	@Column(name="total_questions")
	int noOfTotalQuestions;
	@Column(name="allocated_questions")
	String noOfAllocatedQuestions;
	@ManyToMany(fetch= FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name="Examquestion",joinColumns = {@JoinColumn(name="exam_id",nullable = false, updatable = false)},
	           inverseJoinColumns = {@JoinColumn(name = "question_id",nullable = false, updatable = false)})
    Set<Question> questions = new HashSet<Question>(0);
	@ManyToMany(fetch= FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="Userexam",joinColumns = {@JoinColumn(name="exam_id",nullable = false, updatable = false)},
	           inverseJoinColumns = {@JoinColumn(name = "user_id",nullable = false, updatable = false)})
    Set<User> users = new HashSet<User>();

	public Exam() {
		
	}
	
	public Exam(String examName,int duration,int noOfTotalQuestions) {
		this.examName = examName;
		this.examDuration = duration;
		this.noOfTotalQuestions = noOfTotalQuestions;
	}
    
	public int getExamId() {
		return examId;
	}

	public void setExamId(int examId) {
		this.examId = examId;
	}
	
	public void setExamName(String examName) {
		this.examName = examName;
	}
	
	public String getExamName() {
		return examName;
	}
	
	public void setExamDuration(int duration) {
		this.examDuration = duration;
	}
	
	public int getExamDuration() {
		return examDuration;
	}
	
	public void setNoOfTotalQuestions(int noOfTotalQuestions) {
		this.noOfTotalQuestions = noOfTotalQuestions;
	}
	
	public int getNoOfTotalQuestions() {
		return this.noOfTotalQuestions;
	}
	
	public String getNoOfAllocatedQuestions() {
		return noOfAllocatedQuestions;
	}

	public void setNoOfAllocatedQuestions(String noOfAllocatedQuestions) {
		this.noOfAllocatedQuestions = noOfAllocatedQuestions;
	}

    public Set<Question> getQuestions() {
    	 return this.questions;
    }
	
	public void setQuestions(Set<Question> question) {
		this.questions.addAll(question);
	}


	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users.addAll(users);
	}
}
