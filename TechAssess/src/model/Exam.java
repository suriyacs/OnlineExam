package model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
	int exam_id;
	@Column(name="exam_name")
	String examName;
	@Column(name="duration")
	int examDuration;
	@Column(name="valid_days")
	int examValidDays;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="Examquestion",joinColumns = {@JoinColumn(name="exam_id",nullable = false, updatable = false)},
	           inverseJoinColumns = {@JoinColumn(name = "question_id",nullable = false, updatable = false)})
    Set<Question> questions = new HashSet<Question>(0);

	public Exam() {
		
	}
	
	public Exam(String examName,int duration,int validDays) {
		this.examName = examName;
		this.examDuration = duration;
		this.examValidDays = validDays;
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
	
	public void setExamValidDays(int validDays) {
		this.examValidDays = validDays;
	}
	
	public int getExamValidDays() {
		return examValidDays;
	}
	
    public Set<Question> getAllQuestions() {
    	 return this.questions;
    }
	
	public void setAllQuestions(Set<Question> question) {
		this.questions.addAll(question);
	}
}
