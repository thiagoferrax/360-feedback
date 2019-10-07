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
import com.agile.feedback.models.TeamMember;
import com.agile.feedback.repositories.TeamMemberRepository;

public class TeamMemberServiceTest {

	@InjectMocks
	private TeamMemberService teamMemberService;

	@Mock
	private TeamMemberRepository teamMemberRepository;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void whenIdExistsReturnsTeamMember() {
		// Given
		Integer existingId = 1;

		TeamMember existingTeamMember = new TeamMember();
		existingTeamMember.setId(existingId);

		Optional<TeamMember> optional = Optional.of(existingTeamMember);
		Mockito.when(teamMemberRepository.findById(existingId)).thenReturn(optional);

		// When
		TeamMember teamMember = teamMemberService.find(existingId);

		// Then
		Assert.assertEquals(existingTeamMember, teamMember);
	}

	@Test
	public void whenIdDoesNotExistThrowsTeamMemberNotFoundException() {
		// Given
		Integer notExistingId = 2;

		Mockito.when(teamMemberRepository.findById(notExistingId))
				.thenThrow(new ObjectNotFoundException(TeamMemberService.TEAM_MEMBER_NOT_FOUND_FOR_ID));

		// When
		try {
			teamMemberService.find(notExistingId);
			Assert.fail();
		} catch (ObjectNotFoundException e) {
			// Then
			Assert.assertEquals(TeamMemberService.TEAM_MEMBER_NOT_FOUND_FOR_ID, e.getMessage());
		}
	}

	@Test
	public void whenUpdatinATeamMemberVerifyThatRepositorySaveIsCalled() {
		// Given
		Integer existingId = 1;
		TeamMember existingTeamMember = new TeamMember();
		existingTeamMember.setId(existingId);

		Optional<TeamMember> optional = Optional.of(existingTeamMember);
		Mockito.when(teamMemberRepository.findById(existingId)).thenReturn(optional);

		// When
		teamMemberService.update(existingTeamMember);

		// Then
		Mockito.verify(teamMemberRepository).save(existingTeamMember);
	}

	@Test
	public void whenDeletingATeamMemberVerifyThatRepositoryDeleteByIdIsCalled() {
		// Given
		Integer existingId = 1;

		TeamMember existingTeamMember = new TeamMember();
		existingTeamMember.setId(existingId);

		Optional<TeamMember> optional = Optional.of(existingTeamMember);
		Mockito.when(teamMemberRepository.findById(existingId)).thenReturn(optional);

		// When
		teamMemberService.remove(existingId);

		// Then
		Mockito.verify(teamMemberRepository).deleteById(existingId);
	}

}
