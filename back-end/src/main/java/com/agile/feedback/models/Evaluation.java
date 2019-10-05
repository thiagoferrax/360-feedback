package com.agile.feedback.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Evaluation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private Double grade;

	@ManyToOne
	private FeedbackItem feedbackItem;

	@ManyToOne
	private TeamMember evaluator;

	@ManyToOne
	private TeamMember memberEvaluated;

	@CreationTimestamp
	private Date createdAt;

	@UpdateTimestamp
	private Date updatedAt;

	public Evaluation() {
	}

	public Evaluation(Integer id, Double grade, FeedbackItem feedbackItem, TeamMember evaluator, TeamMember memberEvaluated) {
		super();
		this.id = id;
		this.grade = grade;
		this.feedbackItem = feedbackItem;
		this.evaluator = evaluator;
		this.memberEvaluated = memberEvaluated;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Double getGrade() {
		return grade;
	}

	public void setGrade(Double grade) {
		this.grade = grade;
	}

	public FeedbackItem getFeedbackItem() {
		return feedbackItem;
	}

	public void setFeedbackItem(FeedbackItem feedbackItem) {
		this.feedbackItem = feedbackItem;
	}

	public TeamMember getEvaluator() {
		return evaluator;
	}

	public void setEvaluator(TeamMember evaluator) {
		this.evaluator = evaluator;
	}

	public TeamMember getMemberEvaluated() {
		return memberEvaluated;
	}

	public void setMemberEvaluated(TeamMember memberEvaluated) {
		this.memberEvaluated = memberEvaluated;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evaluation other = (Evaluation) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
