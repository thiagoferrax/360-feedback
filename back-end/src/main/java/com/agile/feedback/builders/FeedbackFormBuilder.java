package com.agile.feedback.builders;

import com.agile.feedback.models.FeedbackForm;
import com.agile.feedback.models.Project;
import com.agile.feedback.models.TeamMember;

public class FeedbackFormBuilder {
	private FeedbackForm feedbackForm;

	private FeedbackFormBuilder() {
	}
	
	public static FeedbackFormBuilder newFeedbackForm() {
		FeedbackFormBuilder builder = new FeedbackFormBuilder();
		builder.feedbackForm = new FeedbackForm();
		return builder;
	}

	public FeedbackFormBuilder withId(Integer id) {
		feedbackForm.setId(id);
		return this;
	}
	
	public FeedbackFormBuilder withName(String name) {
		feedbackForm.setName(name);
		return this;
	}
	
	public FeedbackFormBuilder withDescription(String description) {
		feedbackForm.setDescription(description);
		return this;
	}
	
	public FeedbackFormBuilder withProject(Project project) {
		feedbackForm.setProject(project);
		return this;
	}
	
	public FeedbackFormBuilder withAuthor(TeamMember author) {
		feedbackForm.setAuthor(author);
		return this;
	}

	public FeedbackForm now() {
		return feedbackForm;
	}
}
