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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author user
 *
 */
@Entity
@Table(name="QuestionType")
public class QuestionType {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="type_id")
	int typeId;
	@Column(name="type_name")
	String typeName;
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name="type_id")
	Set<Question> questions = new HashSet<Question>();
	
	public QuestionType() {
		
	}
	
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	
	public int getTypeId() {
		return this.typeId;
	}
	
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	public String getTypeName() {
		return this.typeName;
	}
	
	public void setQuestion(Set<Question> questions) {
		this.questions.addAll(questions);
	}
	
	public Set<Question> getQuestion() {
		return this.questions;
	}
}

