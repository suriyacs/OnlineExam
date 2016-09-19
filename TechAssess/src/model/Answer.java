package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import model.Choice;

/**
 * <p>
 * Answer model is to used create an instance of answer type
 * by using a constructor or by using setter and getter methods.
 * </p>
 * 
 * @author TechAssess
 * 
 * @param question 
 *     instance of Question type to be stored.
 *     
 * @param choices
 *     list consist of instance of choice which needs to be stored in list of choice type.
 *
 */
@Entity
@Table(name="answer")
public class Answer {
    
	@Id
	@GeneratedValue
	@Column(name="id")
	private int answerId;
	
	public int getAnswerId() {
		return answerId;
	}

	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}
	

	@Column(name = "question_id")
	private int questionId;
	
	@Column(name="choice_name")
    String userAnswer;
    
	public Answer() {
	}
    
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	
	public String getUserAnswer() {
		return userAnswer;
	}
	public void setUserAnswer(String userAnswer) {
		this.userAnswer = userAnswer;
	}
    
   
}

