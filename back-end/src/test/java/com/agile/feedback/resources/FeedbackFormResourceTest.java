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

import com.agile.feedback.dtos.FeedbackFormDTO;
import com.agile.feedback.enums.TeamMemberType;
import com.agile.feedback.exceptions.FeedbackFormNotFoundException;
import com.agile.feedback.models.FeedbackForm;
import com.agile.feedback.models.Project;
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
public class FeedbackFormResourceTest {

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
	public void whenFeedbackFormIdExistsReturnsExistingFeedbackForm() throws Exception {
		// Given
		Integer existingId = 1;

		TeamMember author = new TeamMember(1, "Thiago", TeamMemberType.DEVELOPER, "thiago@email.com");
		Project project = new Project(1, "EDOC");
		FeedbackForm feedbackForm = new FeedbackForm(existingId, "360 Dataprev", "360 Dataprev", project, author);

		given(feedbackFormService.find(existingId)).willReturn(feedbackForm);

		// When and Then
		this.mockMvc.perform(get("/feedbackForms/" + existingId)).andExpect(status().isOk())
				.andExpect(content().json("{'id':1,'name':'360 Dataprev','createdAt': null,'updatedAt': null}"));
	}

	@Test
	public void whenFeedbackFormIdDoesNotExistReturnsNotFound() throws Exception {
		// Given
		Integer notExistingId = 2;

		given(feedbackFormService.find(notExistingId))
				.willThrow(new FeedbackFormNotFoundException(FeedbackFormService.FEEDBACK_FORM_NOT_FOUND));

		// When and Then
		this.mockMvc.perform(get("/feedbackForms/" + notExistingId)).andExpect(status().isNotFound());
	}

	@Test
	public void whenSavingANewFeedbackFormReturnNewId() throws Exception {
		// Given
		Integer id = 1;
		String name = "360 Dataprev";
		String description = "360 Dataprev";

		FeedbackFormDTO feedbackFormDtoToCreate = new FeedbackFormDTO();
		feedbackFormDtoToCreate.setName(name);
		feedbackFormDtoToCreate.setDescription(description);

		TeamMember author = new TeamMember(1, "Thiago", TeamMemberType.DEVELOPER, "thiago@email.com");
		Project project = new Project(1, "EDOC");
		FeedbackForm feedbackFormToCreate = new FeedbackForm(null, name, "360 Dataprev", project, author);

		given(feedbackFormService.fromDTO(feedbackFormDtoToCreate)).willReturn(feedbackFormToCreate);

		FeedbackForm newFeedbackForm = new FeedbackForm(id, name, "360 Dataprev", project, author);
		given(feedbackFormService.create(feedbackFormToCreate)).willReturn(newFeedbackForm);

		String inputJson = "{\"name\":\"360 Dataprev\",\"description\":\"360 Dataprev\"}";

		// When and Then
		this.mockMvc.perform(post("/feedbackForms/").contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andExpect(status().isCreated())
				.andExpect(header().string(HttpHeaders.LOCATION, "http://localhost/feedbackForms/" + id));

	}

	@Test
	public void whenUpdatingAFeedbackFormReturnsStatusNoContent() throws Exception {
		// Given
		String name = "360 Dataprev";
		Integer existingCode = 1;

		FeedbackFormDTO feedbackFormDtoToFind = new FeedbackFormDTO();
		feedbackFormDtoToFind.setName(name);

		TeamMember author = new TeamMember(1, "Thiago", TeamMemberType.DEVELOPER, "thiago@email.com");
		Project project = new Project(1, "EDOC");
		FeedbackForm feedbackFormToFind = new FeedbackForm(null, name, "360 Dataprev", project, author);
		given(feedbackFormService.fromDTO(feedbackFormDtoToFind)).willReturn(feedbackFormToFind);

		String inputJson = "{\"name\":\"360 Dataprev\", \"type\": 1, \"email\": \"thiago@email.com\"}";

		// When and Then
		this.mockMvc.perform(
				put("/feedbackForms/" + existingCode).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andExpect(status().isNoContent());
	}

	@Test
	public void whenRemovingAFeedbackFormReturnsStatusNoContent() throws Exception {
		// Given
		Integer existingCode = 1;

		// When and Then
		this.mockMvc.perform(delete("/feedbackForms/" + existingCode)).andExpect(status().isNoContent());
	}

}
