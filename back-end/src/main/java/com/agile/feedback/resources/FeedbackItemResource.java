package com.agile.feedback.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.agile.feedback.dtos.FeedbackItemDTO;
import com.agile.feedback.models.FeedbackItem;
import com.agile.feedback.services.FeedbackItemService;

@RestController
@RequestMapping(value = "/feedbackItems")
public class FeedbackItemResource {

	public static final String CREATING_NEW_FEEDBACK_ITEM = "Creating a new feedback item";
	public static final String UPDATING_FEEDBACK_ITEM = "Updating feedback item";
	public static final String REMOVING_FEEDBACK_ITEM = "Removing feedback item";
	public static final String GETTING_FEEDBACK_ITEM_BY_ID = "Getting feedback item";
	public static final String GETTING_ALL_FEEDBACK_ITEMS = "Getting all feedback items";

	Logger logger = LoggerFactory.getLogger(FeedbackItemResource.class);

	@Autowired
	private FeedbackItemService service;

	@GetMapping(path = "/")
	public ResponseEntity<List<FeedbackItem>> getAll() {

		logger.info(GETTING_ALL_FEEDBACK_ITEMS);

		List<FeedbackItem> feedbackItems = service.findAll();

		return ResponseEntity.ok().body(feedbackItems);
	}

	@PostMapping(value = "/")
	public ResponseEntity<FeedbackItem> create(@Valid @RequestBody FeedbackItemDTO feedbackItemDto) {

		logger.info(CREATING_NEW_FEEDBACK_ITEM, feedbackItemDto.getName());

		FeedbackItem feedbackItem = service.fromDTO(feedbackItemDto);
		FeedbackItem newFeedbackItem = service.create(feedbackItem);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newFeedbackItem.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<FeedbackItem> findFeedbackItemById(@PathVariable Integer id) {

		logger.info(GETTING_FEEDBACK_ITEM_BY_ID);

		FeedbackItem feedbackItem = service.find(id);

		return ResponseEntity.ok().body(feedbackItem);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<FeedbackItem> update(@Valid @RequestBody FeedbackItemDTO feedbackItemDto,
			@PathVariable Integer id) {

		logger.info(UPDATING_FEEDBACK_ITEM);

		feedbackItemDto.setId(id);
		FeedbackItem feedbackItem = service.fromDTO(feedbackItemDto);

		service.update(feedbackItem);

		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<FeedbackItem> delete(@PathVariable Integer id) {

		logger.info(REMOVING_FEEDBACK_ITEM);

		service.remove(id);

		return ResponseEntity.noContent().build();
	}
}