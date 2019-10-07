package com.agile.feedback.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agile.feedback.dtos.EvaluationDTO;
import com.agile.feedback.exceptions.ObjectNotFoundException;
import com.agile.feedback.models.Evaluation;
import com.agile.feedback.repositories.EvaluationRepository;

@Service
public class EvaluationService {

	Logger logger = LoggerFactory.getLogger(EvaluationService.class);

	public static final String REMOVING_EVALUATION = "Removing evaluation";
	public static final String EVALUATION_NOT_FOUND = "Team member not found";
	public static final String FINDING_EVALUATION = "Finding evaluation";
	public static final String FINDING_ALL_EVALUATIONS = "Finding all evaluations";
	public static final String CREATING_A_EVALUATION = "Creating a new evaluation";
	public static final String UPDATING_EVALUATION = "Updating evaluation";

	@Autowired
	private EvaluationRepository repository;

	public Evaluation create(Evaluation evaluation) {
		logger.info(CREATING_A_EVALUATION, evaluation);

		evaluation.setId(null);
		return repository.save(evaluation);
	}
	
	public List<Evaluation> findAll() {
		logger.info(FINDING_ALL_EVALUATIONS);
		return repository.findAll();
	}

	public Evaluation find(Integer evaluationId) {

		logger.info(FINDING_EVALUATION);

		Optional<Evaluation> optional = repository.findById(evaluationId);
		return optional.orElseThrow(() -> new ObjectNotFoundException(EVALUATION_NOT_FOUND));
	}

	public Evaluation update(Evaluation evaluation) {
		logger.info(UPDATING_EVALUATION);

		Evaluation foundEvaluation = find(evaluation.getId());
		evaluation.setCreatedAt(foundEvaluation.getCreatedAt());

		return repository.save(evaluation);
	}

	public void remove(Integer id) {

		logger.info(REMOVING_EVALUATION);

		find(id);
		repository.deleteById(id);
	}

	public Evaluation fromDTO(@Valid EvaluationDTO evaluationDto) {
		return new Evaluation(evaluationDto.getId(), evaluationDto.getGrade(), evaluationDto.getFeedbackItem(),
				evaluationDto.getEvaluator(), evaluationDto.getMemberEvaluated());
	}
}
