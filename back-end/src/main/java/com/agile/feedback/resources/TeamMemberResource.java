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
@RequestMapping(value = "/teamMembers")
public class TeamMemberResource {

	public static final String CREATING_NEW_TEAM_MEMBER = "Creating a new team member: {0}";
	public static final String UPDATING_TEAM_MEMBER = "Updating team member: {0}";
	public static final String REMOVING_TEAM_MEMBER = "Removing team member, id: {0}";
	public static final String GETTING_TEAM_MEMBER_BY_ID = "Getting team member by id: {0}";
	public static final String GETTING_ALL_TEAM_MEMBERS = "Getting all team member by id: {0}";

	Logger logger = LoggerFactory.getLogger(TeamMemberResource.class);

	@Autowired
	private TeamMemberService service;

	@GetMapping(path = "/")
	public ResponseEntity<List<TeamMember>> getAll() {

		logger.info(GETTING_ALL_TEAM_MEMBERS);

		List<TeamMember> teamMembers = service.findAll();

		return ResponseEntity.ok().body(teamMembers);
	}

	@PostMapping(value = "/")
	public ResponseEntity<TeamMember> create(@Valid @RequestBody TeamMemberDTO teamMemberDto) {

		logger.info(CREATING_NEW_TEAM_MEMBER, teamMemberDto.getName());

		TeamMember teamMember = service.fromDTO(teamMemberDto);
		TeamMember newTeamMember = service.create(teamMember);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newTeamMember.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<TeamMember> findTeamMemberById(@PathVariable Integer id) {

		logger.info(GETTING_TEAM_MEMBER_BY_ID, id);

		TeamMember teamMember = service.find(id);

		return ResponseEntity.ok().body(teamMember);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<TeamMember> update(@Valid @RequestBody TeamMemberDTO teamMemberDto,
			@PathVariable Integer id) {

		logger.info(UPDATING_TEAM_MEMBER, id);

		teamMemberDto.setId(id);
		TeamMember teamMember = service.fromDTO(teamMemberDto);

		service.update(teamMember);

		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<TeamMember> delete(@PathVariable Integer id) {

		logger.info(REMOVING_TEAM_MEMBER, id);

		service.remove(id);

		return ResponseEntity.noContent().build();
	}
}