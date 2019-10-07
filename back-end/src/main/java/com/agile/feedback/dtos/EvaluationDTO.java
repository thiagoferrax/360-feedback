package com.agile.feedback.dtos;

import java.io.Serializable;

import com.agile.feedback.models.FeedbackItem;
import com.agile.feedback.models.TeamMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class EvaluationDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Double grade;

	@JsonIgnore
	private FeedbackItem feedbackItem;

	@JsonIgnore
	private TeamMember evaluator;

	@JsonIgnore
	private TeamMember memberEvaluated;

	public EvaluationDTO() {
	}
	
	public EvaluationDTO(Double grade) {
		super();
		this.grade = grade;
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((evaluator == null) ? 0 : evaluator.hashCode());
		result = prime * result + ((feedbackItem == null) ? 0 : feedbackItem.hashCode());
		result = prime * result + ((grade == null) ? 0 : grade.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((memberEvaluated == null) ? 0 : memberEvaluated.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		EvaluationDTO other = (EvaluationDTO) obj;
		if (evaluator == null) {
			if (other.evaluator != null) {
				return false;
			}
		} else if (!evaluator.equals(other.evaluator)) {
			return false;
		}
		if (feedbackItem == null) {
			if (other.feedbackItem != null) {
				return false;
			}
		} else if (!feedbackItem.equals(other.feedbackItem)) {
			return false;
		}
		if (grade == null) {
			if (other.grade != null) {
				return false;
			}
		} else if (!grade.equals(other.grade)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (memberEvaluated == null) {
			if (other.memberEvaluated != null) {
				return false;
			}
		} else if (!memberEvaluated.equals(other.memberEvaluated)) {
			return false;
		}
		return true;
	}

}
