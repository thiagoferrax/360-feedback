package com.agile.feedback.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agile.feedback.dtos.FeedbackFormDTO;
import com.agile.feedback.exceptions.ObjectNotFoundException;
import com.agile.feedback.models.FeedbackForm;
import com.agile.feedback.repositories.FeedbackFormRepository;

@Service
public class FeedbackFormService {

	Logger logger = LoggerFactory.getLogger(FeedbackFormService.class);

	public static final String REMOVING_FEEDBACK_FORM = "Removing feedback form";
	public static final String FEEDBACK_FORM_NOT_FOUND = "Team member not found";
	public static final String FINDING_FEEDBACK_FORM = "Finding feedback form";
	public static final String FINDING_ALL_FEEDBACK_FORMS = "Finding all feedback forms";
	public static final String CREATING_A_FEEDBACK_FORM = "Creating a new feedback form";
	public static final String UPDATING_FEEDBACK_FORM = "Updating feedback form";
	@Autowired
	private FeedbackFormRepository repository;

	public FeedbackForm create(FeedbackForm feedbackForm) {
		logger.info(CREATING_A_FEEDBACK_FORM);

		feedbackForm.setId(null);
		return repository.save(feedbackForm);
	}

	public List<FeedbackForm> findAll() {
		logger.info(FINDING_ALL_FEEDBACK_FORMS);
		return repository.findAll();
	}

	public FeedbackForm find(Integer feedbackFormId) {

		logger.info(FINDING_FEEDBACK_FORM);

		Optional<FeedbackForm> optional = repository.findById(feedbackFormId);
		return optional.orElseThrow(() -> new ObjectNotFoundException(FEEDBACK_FORM_NOT_FOUND));
	}

	public FeedbackForm update(FeedbackForm feedbackForm) {
		logger.info(UPDATING_FEEDBACK_FORM);

		FeedbackForm foundFeedbackForm = find(feedbackForm.getId());
		feedbackForm.setCreatedAt(foundFeedbackForm.getCreatedAt());

		return repository.save(feedbackForm);
	}

	public void remove(Integer id) {

		logger.info(REMOVING_FEEDBACK_FORM);

		find(id);
		repository.deleteById(id);
	}

	public FeedbackForm fromDTO(@Valid FeedbackFormDTO feedbackFormDto) {
		return new FeedbackForm(feedbackFormDto.getId(), feedbackFormDto.getName(), feedbackFormDto.getDescription(),
				feedbackFormDto.getProject(), feedbackFormDto.getAuthor());
	}
}
