package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import model.Exam;

@Entity
@Table(name="Question")
public class Question {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="question_id")
	int questionId;
	@Column(name="question")
	String questionName;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="type_id")
	QuestionType typeId;	
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name="question_id")
	List<Choice> choices = new ArrayList<Choice>();
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(cascade = CascadeType.ALL,mappedBy="questions") 
    Set<Exam> exams = new HashSet<Exam>();
	
    public Question() {
    	
    }
    
	public Question(String questionName) {
		this.questionName = questionName;
	}
	
	public void setQuestionId(int questionId) {
	    this.questionId = questionId;
	}
	
	public int getQuestionId() {
		return this.questionId;
	}
	
	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}
	
	public String getQuestionName() {
		return this.questionName;
	}
	
	public QuestionType getTypeId() {
		return this.typeId;
	}

	public void setTypeId(QuestionType typeId) {
		this.typeId = typeId;
	}
	
	public void setChoices(List<Choice> choice) {
		this.choices = choice;
	}
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(mappedBy="questions") 
	public void setExams(Set<Exam> exam) {
		this.exams.addAll(exam);
    }
	
	public Set<Exam> getExams() {
		return exams;
	}
	
	public List<Choice> getChoices() {
		return this.choices;
	}
	
	public void add(Choice choice) {
		this.choices.add(choice);
	}
	
	@Override
	public String toString() {
        return questionId + "\t" + questionName + "\t" + typeId;
    }
}

