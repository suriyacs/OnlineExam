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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Question")
public class Question {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="question_id")
	int questionId;
	@Column(name="question")
	String question;
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="type_id")
	QuestionType typeId;
    
    public Question() {
    	
    }
    
	public Question(String question) {
		this.question = question;
	}
	
	public void setQuestionId(int questionId) {
	    this.questionId = questionId;
	}
	
	public int getQuestionId() {
		return this.questionId;
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}
	
	public String getQuestion() {
		return this.question;
	}
	
	public QuestionType getTypeId() {
		return typeId;
	}

	public void setTypeId(QuestionType typeId) {
		this.typeId = typeId;
	}
	
	@Override
	public String toString() {
        return questionId + "\t" + question + "\t" + typeId;
    }
}

