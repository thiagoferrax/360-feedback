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

import com.agile.feedback.dtos.TeamMemberDTO;
import com.agile.feedback.models.TeamMember;
import com.agile.feedback.services.TeamMemberService;

@RestController
@RequestMapping(value = "/feedbackForms")
public class FeedbackResource {

	public static final String CREATING_NEW_FEEDBACK_FORM = "Creating a new feedback form";
	public static final String UPDATING_FEEDBACK_FORM = "Updating feedback form";
	public static final String REMOVING_FEEDBACK_FORM = "Removing feedback form";
	public static final String GETTING_FEEDBACK_FORM_BY_ID = "Getting feedback form";
	public static final String GETTING_ALL_FEEDBACK_FORMS = "Getting all feedback forms";

	Logger logger = LoggerFactory.getLogger(FeedbackResource.class);

	@Autowired
	private TeamMemberService service;

	@GetMapping(path = "/")
	public ResponseEntity<List<TeamMember>> getAll() {

		logger.info(GETTING_ALL_FEEDBACK_FORMS);

		List<TeamMember> feedbackForms = service.findAll();

		return ResponseEntity.ok().body(feedbackForms);
	}

	@PostMapping(value = "/")
	public ResponseEntity<TeamMember> create(@Valid @RequestBody TeamMemberDTO feedbackFormDto) {

		logger.info(CREATING_NEW_FEEDBACK_FORM, feedbackFormDto.getName());

		TeamMember feedbackForm = service.fromDTO(feedbackFormDto);
		TeamMember newTeamMember = service.create(feedbackForm);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newTeamMember.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<TeamMember> findTeamMemberById(@PathVariable Integer id) {

		logger.info(GETTING_FEEDBACK_FORM_BY_ID);

		TeamMember feedbackForm = service.find(id);

		return ResponseEntity.ok().body(feedbackForm);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<TeamMember> update(@Valid @RequestBody TeamMemberDTO feedbackFormDto,
			@PathVariable Integer id) {

		logger.info(UPDATING_FEEDBACK_FORM);

		feedbackFormDto.setId(id);
		TeamMember feedbackForm = service.fromDTO(feedbackFormDto);

		service.update(feedbackForm);

		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<TeamMember> delete(@PathVariable Integer id) {

		logger.info(REMOVING_FEEDBACK_FORM);

		service.remove(id);

		return ResponseEntity.noContent().build();
	}
}