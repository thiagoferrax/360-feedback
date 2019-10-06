package com.agile.feedback.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agile.feedback.dtos.FeedbackItemDTO;
import com.agile.feedback.exceptions.FeedbackItemNotFoundException;
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

	public FeedbackItem find(Integer FeedbackItemId) {

		logger.info(FINDING_FEEDBACK_ITEM);

		Optional<FeedbackItem> optional = repository.findById(FeedbackItemId);
		return optional.orElseThrow(() -> new FeedbackItemNotFoundException(FEEDBACK_ITEM_NOT_FOUND));
	}

	public FeedbackItem update(FeedbackItem FeedbackItem) {
		logger.info(UPDATING_FEEDBACK_ITEM);

		FeedbackItem foundFeedbackItem = find(FeedbackItem.getId());
		FeedbackItem.setCreatedAt(foundFeedbackItem.getCreatedAt());

		return repository.save(FeedbackItem);
	}

	public void remove(Integer id) {

		logger.info(REMOVING_FEEDBACK_ITEM);

		find(id);
		repository.deleteById(id);
	}

	public FeedbackItem fromDTO(@Valid FeedbackItemDTO FeedbackItemDto) {
		return new FeedbackItem(FeedbackItemDto.getId(), FeedbackItemDto.getName(), FeedbackItemDto.getDescription(),
				FeedbackItemDto.getAtive(), FeedbackItemDto.getForm());
	}
}
