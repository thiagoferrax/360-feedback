package com.agile.feedback.resources;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.agile.feedback.builders.FeedbackFormBuilder;
import com.agile.feedback.builders.ProjectBuilder;
import com.agile.feedback.builders.TeamMemberBuilder;
import com.agile.feedback.dtos.FeedbackItemDTO;
import com.agile.feedback.enums.TeamMemberType;
import com.agile.feedback.exceptions.FeedbackItemNotFoundException;
import com.agile.feedback.models.FeedbackForm;
import com.agile.feedback.models.FeedbackItem;
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
public class FeedbackItemResourceTest {

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

	private FeedbackForm feedback360;

	@Before
	public void setUp() {
		Project edoc = ProjectBuilder.newProject().withId(1).withName("EDOC").now();
		TeamMember thiago = TeamMemberBuilder.newTeamMember().withId(1).withName("Thiago")
				.withType(TeamMemberType.DEVELOPER).withEmail("thiago@email.com").now();
		feedback360 = FeedbackFormBuilder.newFeedbackForm().withId(1).withName("360 Dataprev")
				.withDescription("360 Feedback Dataprev").withProject(edoc).withAuthor(thiago).now();
	}

	@Test
	public void whenFeedbackItemIdExistsReturnsExistingFeedbackItem() throws Exception {
		// Given
		Integer existingId = 1;

		FeedbackItem feedbackItem = new FeedbackItem(existingId, "Technical Experience",
				"Level of Technical Experience", true, feedback360);

		given(feedbackItemService.find(existingId)).willReturn(feedbackItem);

		// When and Then
		this.mockMvc.perform(get("/feedbackItems/" + existingId)).andExpect(status().isOk()).andExpect(
				content().json("{'id':1,'name':'Technical Experience','createdAt': null,'updatedAt': null}"));
	}

	@Test
	public void whenFeedbackItemIdDoesNotExistReturnsNotFound() throws Exception {
		// Given
		Integer notExistingId = 2;

		given(feedbackItemService.find(notExistingId))
				.willThrow(new FeedbackItemNotFoundException(FeedbackItemService.FEEDBACK_ITEM_NOT_FOUND));

		// When and Then
		this.mockMvc.perform(get("/feedbackItems/" + notExistingId)).andExpect(status().isNotFound());
	}

	@Test
	public void whenSavingANewFeedbackItemReturnNewId() throws Exception {
		// Given
		Integer id = 1;
		String name = "Technical Experience";
		String description = "Level of Technical Experience";

		FeedbackItemDTO feedbackItemDtoToCreate = new FeedbackItemDTO(name, description);

		FeedbackItem feedbackItemToCreate = new FeedbackItem(null, "Technical Experience",
				"Level of Technical Experience", true, feedback360);

		given(feedbackItemService.fromDTO(feedbackItemDtoToCreate)).willReturn(feedbackItemToCreate);

		FeedbackItem newFeedbackItem = new FeedbackItem(id, name, "Level of Technical Experience", true, feedback360);
		given(feedbackItemService.create(feedbackItemToCreate)).willReturn(newFeedbackItem);

		String inputJson = "{\"name\":\"Technical Experience\",\"description\":\"Level of Technical Experience\", \"active\": true, \"form\": {\"name\":\"360 Dataprev\",\"description\":\"360 Dataprev\", \"project\":{\"name\":\"LifeProof\", \"type\": 1}}}";

		// When and Then
		this.mockMvc.perform(post("/feedbackItems/").contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andExpect(status().isCreated())
				.andExpect(header().string(HttpHeaders.LOCATION, "http://localhost/feedbackItems/" + id));

	}

	@Test
	public void whenUpdatingAFeedbackItemReturnsStatusNoContent() throws Exception {
		// Given
		Integer existingCode = 1;
		String name = "Technical Experience";
		String description = "Level of Technical Experience";

		FeedbackItemDTO feedbackItemDtoToFind = new FeedbackItemDTO(name, description);

		FeedbackItem feedbackItemToFind = new FeedbackItem(null, "Technical Experience",
				"Level of Technical Experience", true, feedback360);
		given(feedbackItemService.fromDTO(feedbackItemDtoToFind)).willReturn(feedbackItemToFind);

		String inputJson = "{\"name\":\"Technical Experience\",\"description\":\"Level of Technical Experience\", \"active\": true, \"form\": {\"name\":\"360 Dataprev\",\"description\":\"360 Dataprev\", \"project\":{\"name\":\"LifeProof\", \"type\": 1}}}";

		// When and Then
		this.mockMvc.perform(
				put("/feedbackItems/" + existingCode).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andExpect(status().isNoContent());
	}

	@Test
	public void whenRemovingAFeedbackItemReturnsStatusNoContent() throws Exception {
		// Given
		Integer existingCode = 1;

		// When and Then
		this.mockMvc.perform(delete("/feedbackItems/" + existingCode)).andExpect(status().isNoContent());
	}

}
