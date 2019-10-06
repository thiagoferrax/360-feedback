package com.agile.feedback.services;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.agile.feedback.exceptions.FeedbackItemNotFoundException;
import com.agile.feedback.models.FeedbackItem;
import com.agile.feedback.repositories.FeedbackItemRepository;

public class FeedbackItemServiceTest {

	@InjectMocks
	private FeedbackItemService feedbackItemService;

	@Mock
	private FeedbackItemRepository feedbackItemRepository;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void whenIdExistsReturnsFeedbackItem() {
		// Given
		Integer existingId = 1;

		FeedbackItem existingFeedbackItem = new FeedbackItem();
		existingFeedbackItem.setId(existingId);

		Optional<FeedbackItem> optional = Optional.of(existingFeedbackItem);
		Mockito.when(feedbackItemRepository.findById(existingId)).thenReturn(optional);

		// When
		FeedbackItem feedbackItem = feedbackItemService.find(existingId);

		// Then
		Assert.assertEquals(existingFeedbackItem, feedbackItem);
	}

	@Test(expected = FeedbackItemNotFoundException.class)
	public void whenIdDoesNotExistThrowsFeedbackItemNotFoundException() {
		// Given
		Integer notExistingId = 2;

		Mockito.when(feedbackItemRepository.findById(notExistingId))
				.thenThrow(new FeedbackItemNotFoundException(FeedbackItemService.FEEDBACK_ITEM_NOT_FOUND));

		// When
		feedbackItemService.find(notExistingId);
	}

	@Test
	public void whenUpdatinAFeedbackItemVerifyThatRepositorySaveIsCalled() {
		// Given
		Integer existingId = 1;
		FeedbackItem existingFeedbackItem = new FeedbackItem();
		existingFeedbackItem.setId(existingId);

		Optional<FeedbackItem> optional = Optional.of(existingFeedbackItem);
		Mockito.when(feedbackItemRepository.findById(existingId)).thenReturn(optional);

		// When
		feedbackItemService.update(existingFeedbackItem);

		// Then
		Mockito.verify(feedbackItemRepository).save(existingFeedbackItem);
	}

	@Test
	public void whenDeletingAFeedbackItemVerifyThatRepositoryDeleteByIdIsCalled() {
		// Given
		Integer existingId = 1;

		FeedbackItem existingFeedbackItem = new FeedbackItem();
		existingFeedbackItem.setId(existingId);

		Optional<FeedbackItem> optional = Optional.of(existingFeedbackItem);
		Mockito.when(feedbackItemRepository.findById(existingId)).thenReturn(optional);

		// When
		feedbackItemService.remove(existingId);

		// Then
		Mockito.verify(feedbackItemRepository).deleteById(existingId);
	}

}
