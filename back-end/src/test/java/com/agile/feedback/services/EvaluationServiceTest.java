package com.agile.feedback.services;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.agile.feedback.exceptions.ObjectNotFoundException;
import com.agile.feedback.models.Evaluation;
import com.agile.feedback.repositories.EvaluationRepository;

public class EvaluationServiceTest {

	@InjectMocks
	private EvaluationService evaluationService;

	@Mock
	private EvaluationRepository evaluationRepository;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void whenIdExistsReturnsEvaluation() {
		// Given
		Integer existingId = 1;

		Evaluation existingEvaluation = new Evaluation();
		existingEvaluation.setId(existingId);

		Optional<Evaluation> optional = Optional.of(existingEvaluation);
		Mockito.when(evaluationRepository.findById(existingId)).thenReturn(optional);

		// When
		Evaluation evaluation = evaluationService.find(existingId);

		// Then
		Assert.assertEquals(existingEvaluation, evaluation);
	}

	@Test
	public void whenIdDoesNotExistThrowsEvaluationNotFoundException() {
		// Given
		Integer notExistingId = 2;

		Mockito.when(evaluationRepository.findById(notExistingId))
				.thenThrow(new ObjectNotFoundException(EvaluationService.EVALUATION_NOT_FOUND));

		// When
		try {
			evaluationService.find(notExistingId);
			Assert.fail();
		} catch (ObjectNotFoundException e) {
			// Then
			Assert.assertEquals(EvaluationService.EVALUATION_NOT_FOUND, e.getMessage());
		}
	}

	@Test
	public void whenUpdatinAEvaluationVerifyThatRepositorySaveIsCalled() {
		// Given
		Integer existingId = 1;
		Evaluation existingEvaluation = new Evaluation();
		existingEvaluation.setId(existingId);

		Optional<Evaluation> optional = Optional.of(existingEvaluation);
		Mockito.when(evaluationRepository.findById(existingId)).thenReturn(optional);

		// When
		evaluationService.update(existingEvaluation);

		// Then
		Mockito.verify(evaluationRepository).save(existingEvaluation);
	}

	@Test
	public void whenDeletingAEvaluationVerifyThatRepositoryDeleteByIdIsCalled() {
		// Given
		Integer existingId = 1;

		Evaluation existingEvaluation = new Evaluation();
		existingEvaluation.setId(existingId);

		Optional<Evaluation> optional = Optional.of(existingEvaluation);
		Mockito.when(evaluationRepository.findById(existingId)).thenReturn(optional);

		// When
		evaluationService.remove(existingId);

		// Then
		Mockito.verify(evaluationRepository).deleteById(existingId);
	}

}
