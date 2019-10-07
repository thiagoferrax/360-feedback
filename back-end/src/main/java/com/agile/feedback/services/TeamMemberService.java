package com.agile.feedback.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agile.feedback.dtos.TeamMemberDTO;
import com.agile.feedback.enums.TeamMemberType;
import com.agile.feedback.exceptions.ObjectNotFoundException;
import com.agile.feedback.models.TeamMember;
import com.agile.feedback.repositories.TeamMemberRepository;

@Service
public class TeamMemberService {

	Logger logger = LoggerFactory.getLogger(TeamMemberService.class);

	public static final String UPDATING_TEAM_MEMBER = "Updating team member";
	public static final String REMOVING_TEAM_MEMBER = "Removing team member";
	public static final String TEAM_MEMBER_NOT_FOUND_FOR_ID = "Team member not found for id ";
	public static final String FINDING_TEAM_MEMBER_BY_ID = "Finding team member";
	public static final String FINDING_ALL_TEAM_MEMBERS = "Finding all team members";
	public static final String CREATING_A_TEAM_MEMBER = "Creating a team member";

	@Autowired
	private TeamMemberRepository repository;

	public TeamMember create(TeamMember teamMember) {
		logger.info(CREATING_A_TEAM_MEMBER, teamMember);

		teamMember.setId(null);
		return repository.save(teamMember);
	}

	public List<TeamMember> findAll() {
		logger.info(FINDING_ALL_TEAM_MEMBERS);
		return repository.findAll();
	}

	public TeamMember find(Integer teamMemberId) {

		logger.info(FINDING_TEAM_MEMBER_BY_ID, teamMemberId);

		Optional<TeamMember> optional = repository.findById(teamMemberId);
		return optional.orElseThrow(() -> new ObjectNotFoundException(TEAM_MEMBER_NOT_FOUND_FOR_ID));
	}

	public TeamMember update(TeamMember teamMember) {
		logger.info(UPDATING_TEAM_MEMBER, teamMember);

		TeamMember foundTeamMember = find(teamMember.getId());
		teamMember.setCreatedAt(foundTeamMember.getCreatedAt());

		return repository.save(teamMember);
	}

	public void remove(Integer id) {

		logger.info(REMOVING_TEAM_MEMBER);

		find(id);
		repository.deleteById(id);
	}

	public TeamMember fromDTO(@Valid TeamMemberDTO teamMemberDto) {
		return new TeamMember(teamMemberDto.getId(), teamMemberDto.getName(), TeamMemberType.findByCodigo(teamMemberDto.getType()), teamMemberDto.getEmail());
	}
}
