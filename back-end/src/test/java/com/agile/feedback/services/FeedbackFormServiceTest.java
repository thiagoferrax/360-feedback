package com.agile.feedback.services;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.agile.feedback.exceptions.FeedbackFormNotFoundException;
import com.agile.feedback.models.FeedbackForm;
import com.agile.feedback.repositories.FeedbackFormRepository;

public class FeedbackFormServiceTest {

	@InjectMocks
	private FeedbackFormService feedbackFormService;
			
	@Mock
	private FeedbackFormRepository feedbackFormRepository;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void whenIdExistsReturnsFeedbackForm() {
		// Given
		Integer existingId = 1;

		FeedbackForm existingFeedbackForm = new FeedbackForm();
		existingFeedbackForm.setId(existingId);

		Optional<FeedbackForm> optional = Optional.of(existingFeedbackForm);
		Mockito.when(feedbackFormRepository.findById(existingId)).thenReturn(optional);

		// When
		FeedbackForm feedbackForm = feedbackFormService.find(existingId);

		// Then
		Assert.assertEquals(existingFeedbackForm, feedbackForm);
	}

	@Test(expected = FeedbackFormNotFoundException.class)
	public void whenIdDoesNotExistThrowsFeedbackFormNotFoundException() {
		// Given
		Integer notExistingId = 2;

		Mockito.when(feedbackFormRepository.findById(notExistingId))
				.thenThrow(new FeedbackFormNotFoundException(FeedbackFormService.FEEDBACK_FORM_NOT_FOUND_FOR_ID + notExistingId));

		// When
		feedbackFormService.find(notExistingId);
	}

	@Test
	public void whenUpdatinAFeedbackFormVerifyThatRepositorySaveIsCalled() {
		// Given
		Integer existingId = 1;
		FeedbackForm existingFeedbackForm = new FeedbackForm();
		existingFeedbackForm.setId(existingId);

		Optional<FeedbackForm> optional = Optional.of(existingFeedbackForm);
		Mockito.when(feedbackFormRepository.findById(existingId)).thenReturn(optional);

		// When
		feedbackFormService.update(existingFeedbackForm);

		// Then
		Mockito.verify(feedbackFormRepository).save(existingFeedbackForm);
	}

	@Test
	public void whenDeletingAFeedbackFormVerifyThatRepositoryDeleteByIdIsCalled() {
		// Given
		Integer existingId = 1;

		FeedbackForm existingFeedbackForm = new FeedbackForm();
		existingFeedbackForm.setId(existingId);

		Optional<FeedbackForm> optional = Optional.of(existingFeedbackForm);
		Mockito.when(feedbackFormRepository.findById(existingId)).thenReturn(optional);

		// When
		feedbackFormService.remove(existingId);

		// Then
		Mockito.verify(feedbackFormRepository).deleteById(existingId);
	}

}
