package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author user
 *
 */
@Entity
@Table(name="Choice")
public class Choice {

	@Id
	@Column(name="choice_id")
	int choiceId;
	@Column(name="choice_name")
	String choiceName;
	@Column(name="question_id")
	int questionId;
	@Column(name="is_correct")
	int isCorrect;
	
	public Choice() {
		
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
	
	public void setQuestionId(int questionId) {
		this.questionId =questionId;
	}
	
	public int getQuestionId() {
		return this.questionId;
	}
	
	public void setIsCorrect(int isCorrect) {
		this.isCorrect = isCorrect;
	}
}
