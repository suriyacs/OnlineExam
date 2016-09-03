package model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Question")
public class Question {

	@Id
	@Column(name="question_id")
	int questionId;
	@Column(name="question")
	String question;
	@Column(name="type_id")
	int typeId;
    @Column(name="user_id")
	int userId;
    @OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name="question_id")
	Set<Choice> choices = new HashSet<Choice>();
    
	public Question() {
		
	}
	
	public void setQuestionId(int questionId) {
	    this.questionId = questionId;
	}
	
	public int getQquestionId() {
		return this.questionId;
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}
	
	public String getQuestion() {
		return this.question;
	}
	
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	
	public int getTypeId() {
		return this.typeId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getUserId() {
		return this.userId;
	} 
}
