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

import com.agile.feedback.builders.TeamMemberBuilder;
import com.agile.feedback.dtos.TeamMemberDTO;
import com.agile.feedback.enums.TeamMemberType;
import com.agile.feedback.exceptions.ObjectNotFoundException;
import com.agile.feedback.models.TeamMember;
import com.agile.feedback.repositories.CompanyRepository;
import com.agile.feedback.repositories.EvaluationRepository;
import com.agile.feedback.repositories.FeedbackFormRepository;
import com.agile.feedback.repositories.FeedbackItemRepository;
import com.agile.feedback.repositories.ProjectRepository;
import com.agile.feedback.repositories.TeamMemberRepository;
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

	@MockBean
	private CompanyRepository companyRepository;

	@MockBean
	private ProjectRepository projectRepository;

	@MockBean
	private TeamMemberRepository teamMemberRepository;

	@MockBean
	private FeedbackFormRepository feedbackFormRepository;

	@MockBean
	private FeedbackItemRepository feedbackItemRepository;

	@MockBean
	private EvaluationRepository evaluationRepository;

	@Test
	public void whenTeamMemberIdExistsReturnsExistingTeamMember() throws Exception {
		// Given
		Integer existingId = 1;

		TeamMember teamMember = TeamMemberBuilder.newTeamMember().withId(existingId).withName("Thiago")
				.withType(TeamMemberType.DEVELOPER).withEmail("thiago@email.com").now();

		given(teamMemberService.find(existingId)).willReturn(teamMember);

		// When and Then
		this.mockMvc.perform(get("/teamMembers/" + existingId)).andExpect(status().isOk())
				.andExpect(content().json("{'id':1,'name':'Thiago'}"));
	}

	@Test
	public void whenTeamMemberIdDoesNotExistReturnsNotFound() throws Exception {
		// Given
		Integer notExistingId = 2;

		given(teamMemberService.find(notExistingId)).willThrow(
				new ObjectNotFoundException(TeamMemberService.TEAM_MEMBER_NOT_FOUND_FOR_ID));

		// When and Then
		this.mockMvc.perform(get("/teamMembers/" + notExistingId)).andExpect(status().isNotFound());
	}

	@Test
	public void whenSavingANewTeamMemberReturnNewId() throws Exception {
		// Given
		Integer id = 1;
		String name = "Thiago";
		String email = "thiago@email.com";

		TeamMemberDTO teamMemberDtoToCreate = new TeamMemberDTO(name, TeamMemberType.DEVELOPER.getCodigo(), email);
		TeamMember teamMemberToCreate = TeamMemberBuilder.newTeamMember().withName(name)
				.withType(TeamMemberType.DEVELOPER).withEmail(email).now();

		given(teamMemberService.fromDTO(teamMemberDtoToCreate)).willReturn(teamMemberToCreate);

		TeamMember newTeamMember = TeamMemberBuilder.newTeamMember().withId(id).withName(name)
				.withType(TeamMemberType.DEVELOPER).withEmail(email).now();

		given(teamMemberService.create(teamMemberToCreate)).willReturn(newTeamMember);

		String inputJson = "{\"name\":\"Thiago\", \"type\": 2, \"email\": \"thiago@email.com\"}";

		// When and Then
		this.mockMvc.perform(post("/teamMembers/").contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andExpect(status().isCreated())
				.andExpect(header().string(HttpHeaders.LOCATION, "http://localhost/teamMembers/" + id));

	}

	@Test
	public void whenUpdatingATeamMemberReturnsStatusNoContent() throws Exception {
		// Given
		Integer existingCode = 1;
		String name = "Thiago";
		String email = "thiago@email.com";

		TeamMemberDTO teamMemberDtoToFind = new TeamMemberDTO(name, TeamMemberType.DEVELOPER.getCodigo(), email);

		TeamMember teamMemberToFind = new TeamMember(null, name, TeamMemberType.DEVELOPER, "thiago@email.com");
		given(teamMemberService.fromDTO(teamMemberDtoToFind)).willReturn(teamMemberToFind);

		String inputJson = "{\"name\":\"Thiago\", \"type\": 2, \"email\": \"thiago@email.com\"}";

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
