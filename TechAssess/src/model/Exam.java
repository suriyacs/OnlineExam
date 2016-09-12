package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import model.Question;
import model.Answer;

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
	
	
	@ManyToMany(cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name="Examquestion",joinColumns = {@JoinColumn(name="exam_id",nullable = false, updatable = false)},
	           inverseJoinColumns = {@JoinColumn(name = "question_id",nullable = false, updatable = false)})
    List<Question> questions = new ArrayList<Question>();
	
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="Userexam",joinColumns = {@JoinColumn(name="exam_id",nullable = false, updatable = false)},
	           inverseJoinColumns = {@JoinColumn(name = "user_id",nullable = false, updatable = false)})
    Set<User> users = new HashSet<User>();
    
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "answerId")
	List<Answer> answers = new ArrayList<Answer>();
	
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

    public List<Question> getQuestions() {
    	 return this.questions;
    }
	
	public void setQuestions(Question question) {
		this.questions.add(question);
	}

	public List<Answer> getAnswers() {
		return this.answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
    
	public void addAnswer(Answer answer) {
		this.answers.add(answer);
	}
	
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users.addAll(users);
	}
}
