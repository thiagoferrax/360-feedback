package com.agile.feedback.dtos;

import java.io.Serializable;

import com.agile.feedback.models.FeedbackItem;
import com.agile.feedback.models.TeamMember;

public class EvaluationDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Double grade;

	private FeedbackItem feedbackItem;

	private TeamMember evaluator;

	private TeamMember memberEvaluated;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

}
