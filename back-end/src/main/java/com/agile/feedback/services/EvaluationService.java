package com.agile.feedback.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agile.feedback.dtos.EvaluationDTO;
import com.agile.feedback.exceptions.EvaluationNotFoundException;
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

	public Evaluation find(Integer EvaluationId) {

		logger.info(FINDING_EVALUATION);

		Optional<Evaluation> optional = repository.findById(EvaluationId);
		return optional.orElseThrow(() -> new EvaluationNotFoundException(EVALUATION_NOT_FOUND));
	}

	public Evaluation update(Evaluation Evaluation) {
		logger.info(UPDATING_EVALUATION);

		Evaluation foundEvaluation = find(Evaluation.getId());
		Evaluation.setCreatedAt(foundEvaluation.getCreatedAt());

		return repository.save(Evaluation);
	}

	public void remove(Integer id) {

		logger.info(REMOVING_EVALUATION);

		find(id);
		repository.deleteById(id);
	}

	public Evaluation fromDTO(@Valid EvaluationDTO EvaluationDto) {
		return new Evaluation(EvaluationDto.getId(), EvaluationDto.getGrade(), EvaluationDto.getFeedbackItem(),
				EvaluationDto.getEvaluator(), EvaluationDto.getMemberEvaluated());
	}
}
