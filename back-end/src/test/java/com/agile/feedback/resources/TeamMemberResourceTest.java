package com.agile.feedback.resources;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.agile.feedback.dtos.TeamMemberDTO;
import com.agile.feedback.enums.TeamMemberType;
import com.agile.feedback.exceptions.TeamMemberNotFoundException;
import com.agile.feedback.models.TeamMember;
import com.agile.feedback.services.CompanyService;
import com.agile.feedback.services.EvaluationService;
import com.agile.feedback.services.FeedbackFormService;
import com.agile.feedback.services.FeedbackItemService;
import com.agile.feedback.services.ProjectService;
import com.agile.feedback.services.TeamMemberService;

@RunWith(SpringRunner.class)
@WebMvcTest
public class TeamMemberResourceTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CompanyService companyService;

	@MockBean
	private ProjectService projectService;
	
	@MockBean
	private TeamMemberService teamMemberService;
	
	@MockBean
	private EvaluationService evaluationService;
	
	@MockBean
	private FeedbackFormService feedbackFormService;
	
	@MockBean
	private FeedbackItemService feedbackItemService;

	@Test
	public void whenTeamMemberIdExistsReturnsExistingTeamMember() throws Exception {
		// Given
		Integer existingId = 1;

		TeamMember teamMember = new TeamMember(existingId, "Thiago", TeamMemberType.DEVELOPER, "thiago@email.com");

		given(teamMemberService.find(existingId)).willReturn(teamMember);

		// When and Then
		this.mockMvc.perform(get("/teamMembers/" + existingId)).andExpect(status().isOk())
				.andExpect(content().json("{'id':1,'name':'Thiago','createdAt': null,'updatedAt': null}"));
	}

	@Test
	public void whenTeamMemberIdDoesNotExistReturnsNotFound() throws Exception {
		// Given
		Integer notExistingId = 2;

		given(teamMemberService.find(notExistingId)).willThrow(
				new TeamMemberNotFoundException(TeamMemberService.TEAM_MEMBER_NOT_FOUND_FOR_ID + notExistingId));

		// When and Then
		this.mockMvc.perform(get("/teamMembers/" + notExistingId)).andExpect(status().isNotFound());
	}

	@Test
	public void whenSavingANewTeamMemberReturnNewId() throws Exception {
		// Given
		Integer id = 1;
		String name = "Thiago";

		TeamMemberDTO teamMemberDtoToCreate = new TeamMemberDTO();
		teamMemberDtoToCreate.setName(name);

		TeamMember teamMemberToCreate = new TeamMember(null, name, TeamMemberType.DEVELOPER, "thiago@email.com");
		given(teamMemberService.fromDTO(teamMemberDtoToCreate)).willReturn(teamMemberToCreate);

		TeamMember newTeamMember = new TeamMember(id, name, TeamMemberType.DEVELOPER, "thiago@email.com");
		given(teamMemberService.create(teamMemberToCreate)).willReturn(newTeamMember);

		String inputJson = "{\"name\":\"Thiago\", \"type\": 1, \"email\": \"thiago@email.com\"}";

		// When and Then
		this.mockMvc.perform(post("/teamMembers/").contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andExpect(status().isCreated())
				.andExpect(header().string(HttpHeaders.LOCATION, "http://localhost/teamMembers/" + id));

	}

	@Test
	public void whenUpdatingATeamMemberReturnsStatusNoContent() throws Exception {
		// Given
		String name = "Thiago";
		Integer existingCode = 1;

		TeamMemberDTO teamMemberDtoToFind = new TeamMemberDTO();
		teamMemberDtoToFind.setName(name);

		TeamMember teamMemberToFind = new TeamMember(null, name, TeamMemberType.DEVELOPER, "thiago@email.com");
		given(teamMemberService.fromDTO(teamMemberDtoToFind)).willReturn(teamMemberToFind);

		String inputJson = "{\"name\":\"Thiago\", \"type\": 1, \"email\": \"thiago@email.com\"}";

		// When and Then
		this.mockMvc.perform(
				put("/teamMembers/" + existingCode).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andExpect(status().isNoContent());
	}

	@Test
	public void whenRemovingATeamMemberReturnsStatusNoContent() throws Exception {
		// Given
		Integer existingCode = 1;

		// When and Then
		this.mockMvc.perform(delete("/teamMembers/" + existingCode)).andExpect(status().isNoContent());
	}

}
