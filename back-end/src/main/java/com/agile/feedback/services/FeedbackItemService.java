package com.agile.feedback.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agile.feedback.dtos.FeedbackItemDTO;
import com.agile.feedback.exceptions.ObjectNotFoundException;
import com.agile.feedback.models.FeedbackItem;
import com.agile.feedback.repositories.FeedbackItemRepository;

@Service
public class FeedbackItemService {

	Logger logger = LoggerFactory.getLogger(FeedbackItemService.class);

	public static final String REMOVING_FEEDBACK_ITEM = "Removing feedback item";
	public static final String FEEDBACK_ITEM_NOT_FOUND = "Team member not found";
	public static final String FINDING_FEEDBACK_ITEM = "Finding feedback item";
	public static final String FINDING_ALL_FEEDBACK_ITEMS = "Finding all feedback items";
	public static final String CREATING_A_FEEDBACK_ITEM = "Creating a new feedback item";
	public static final String UPDATING_FEEDBACK_ITEM = "Updating feedback item";

	@Autowired
	private FeedbackItemRepository repository;

	public FeedbackItem create(FeedbackItem feedbackItem) {
		logger.info(CREATING_A_FEEDBACK_ITEM, feedbackItem);

		feedbackItem.setId(null);
		return repository.save(feedbackItem);
	}
	
	public List<FeedbackItem> findAll() {
		logger.info(FINDING_ALL_FEEDBACK_ITEMS);
		return repository.findAll();
	}

	public FeedbackItem find(Integer feedbackItemId) {

		logger.info(FINDING_FEEDBACK_ITEM);

		Optional<FeedbackItem> optional = repository.findById(feedbackItemId);
		return optional.orElseThrow(() -> new ObjectNotFoundException(FEEDBACK_ITEM_NOT_FOUND));
	}

	public FeedbackItem update(FeedbackItem feedbackItem) {
		logger.info(UPDATING_FEEDBACK_ITEM);

		FeedbackItem foundFeedbackItem = find(feedbackItem.getId());
		feedbackItem.setCreatedAt(foundFeedbackItem.getCreatedAt());

		return repository.save(feedbackItem);
	}

	public void remove(Integer id) {

		logger.info(REMOVING_FEEDBACK_ITEM);

		find(id);
		repository.deleteById(id);
	}

	public FeedbackItem fromDTO(@Valid FeedbackItemDTO feedbackItemDto) {
		return new FeedbackItem(feedbackItemDto.getId(), feedbackItemDto.getName(), feedbackItemDto.getDescription(),
				feedbackItemDto.getAtive(), feedbackItemDto.getForm());
	}
}
