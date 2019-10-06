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

import com.agile.feedback.dtos.EvaluationDTO;
import com.agile.feedback.models.Evaluation;
import com.agile.feedback.services.EvaluationService;

@RestController
@RequestMapping(value = "/evaluations")
public class EvaluationResource {

	public static final String CREATING_NEW_EVALUATION = "Creating a new evaluation";
	public static final String UPDATING_EVALUATION = "Updating evaluation";
	public static final String REMOVING_EVALUATION = "Removing evaluation";
	public static final String GETTING_EVALUATION_BY_ID = "Getting evaluation by id";
	public static final String GETTING_ALL_EVALUATIONS = "Getting all evaluation by id";

	Logger logger = LoggerFactory.getLogger(EvaluationResource.class);

	@Autowired
	private EvaluationService service;

	@GetMapping(path = "/")
	public ResponseEntity<List<Evaluation>> getAll() {

		logger.info(GETTING_ALL_EVALUATIONS);

		List<Evaluation> evaluations = service.findAll();

		return ResponseEntity.ok().body(evaluations);
	}

	@PostMapping(value = "/")
	public ResponseEntity<Evaluation> create(@Valid @RequestBody EvaluationDTO evaluationDto) {

		logger.info(CREATING_NEW_EVALUATION);

		Evaluation evaluation = service.fromDTO(evaluationDto);
		Evaluation newEvaluation = service.create(evaluation);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newEvaluation.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Evaluation> findEvaluationById(@PathVariable Integer id) {

		logger.info(GETTING_EVALUATION_BY_ID);

		Evaluation evaluation = service.find(id);

		return ResponseEntity.ok().body(evaluation);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Evaluation> update(@Valid @RequestBody EvaluationDTO evaluationDto,
			@PathVariable Integer id) {

		logger.info(UPDATING_EVALUATION);

		evaluationDto.setId(id);
		Evaluation evaluation = service.fromDTO(evaluationDto);

		service.update(evaluation);

		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Evaluation> delete(@PathVariable Integer id) {

		logger.info(REMOVING_EVALUATION);

		service.remove(id);

		return ResponseEntity.noContent().build();
	}
}