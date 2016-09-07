package model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author user
 *
 */
@Entity
@Table(name="Choice")
public class Choice {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="choice_id")
	int choiceId;
	@Column(name="choice_name")
	String choiceName;
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="question_id")
	Question questionId;
	@Column(name="is_correct")
	int isCorrect;
	
	public Choice() {
		
	}
	
	public Choice(String answer, int isCorrect) {
		this.choiceName = answer;
		this.isCorrect = isCorrect;
	}
	
	public void setChoiceId(int choiceId) {
		this.choiceId = choiceId;
	}
	
	public int getChoiceId() {
		return this.choiceId;
	}
	
	public void setChoiceName(String choiceName) {
		this.choiceName = choiceName;
	}
	
	public String getchoiceName() {
		return this.choiceName;
	}
	
	public void setQuestionId(Question questionId) {
		this.questionId =questionId;
	}
	
	public Question getQuestionId() {
		return this.questionId;
	}
	
	public void setIsCorrect(int isCorrect) {
		this.isCorrect = isCorrect;
	}
}

