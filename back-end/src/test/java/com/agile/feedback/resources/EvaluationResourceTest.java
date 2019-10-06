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

import com.agile.feedback.dtos.EvaluationDTO;
import com.agile.feedback.enums.TeamMemberType;
import com.agile.feedback.exceptions.EvaluationNotFoundException;
import com.agile.feedback.models.Evaluation;
import com.agile.feedback.models.FeedbackForm;
import com.agile.feedback.models.FeedbackItem;
import com.agile.feedback.models.Project;
import com.agile.feedback.models.TeamMember;
import com.agile.feedback.services.CompanyService;
import com.agile.feedback.services.EvaluationService;
import com.agile.feedback.services.FeedbackFormService;
import com.agile.feedback.services.FeedbackItemService;
import com.agile.feedback.services.ProjectService;
import com.agile.feedback.services.TeamMemberService;

@RunWith(SpringRunner.class)
@WebMvcTest
public class EvaluationResourceTest {

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
	public void whenEvaluationIdExistsReturnsExistingEvaluation() throws Exception {
		// Given
		Integer existingId = 1;

		TeamMember thiago = new TeamMember(1, "Thiago", TeamMemberType.DEVELOPER, "thiago@email.com");
		Project project = new Project(1, "EDOC");
		FeedbackForm feedback360 = new FeedbackForm(1, "360 Dataprev", "360 Dataprev", project, thiago);

		FeedbackItem feedbackItem = new FeedbackItem(existingId, "Technical Experience",
				"Level of Technical Experience", true, feedback360);
		Evaluation evaluation = new Evaluation(existingId, 10.0, feedbackItem, thiago, thiago);

		given(evaluationService.find(existingId)).willReturn(evaluation);

		// When and Then
		this.mockMvc.perform(get("/evaluations/" + existingId)).andExpect(status().isOk())
				.andExpect(content().json("{'id':1,'grade':10.0,'createdAt': null,'updatedAt': null}"));
	}

	@Test
	public void whenEvaluationIdDoesNotExistReturnsNotFound() throws Exception {
		// Given
		Integer notExistingId = 2;

		given(evaluationService.find(notExistingId))
				.willThrow(new EvaluationNotFoundException(EvaluationService.EVALUATION_NOT_FOUND));

		// When and Then
		this.mockMvc.perform(get("/evaluations/" + notExistingId)).andExpect(status().isNotFound());
	}

	@Test
	public void whenSavingANewEvaluationReturnNewId() throws Exception {
		// Given
		Integer id = 1;
		Double grade = 10.0;
		EvaluationDTO evaluationDtoToCreate = new EvaluationDTO();
		evaluationDtoToCreate.setGrade(grade);

		TeamMember thiago = new TeamMember(1, "Thiago", TeamMemberType.DEVELOPER, "thiago@email.com");
		Project project = new Project(1, "EDOC");
		FeedbackForm feedback360 = new FeedbackForm(1, "360 Dataprev", "360 Dataprev", project, thiago);
		FeedbackItem feedbackItem = new FeedbackItem(1, "Technical Experience", "Level of Technical Experience", true,
				feedback360);

		Evaluation evaluationToCreate = new Evaluation(null, 10.0, feedbackItem, thiago, thiago);

		given(evaluationService.fromDTO(evaluationDtoToCreate)).willReturn(evaluationToCreate);

		Evaluation newEvaluation = new Evaluation(id, 10.0, feedbackItem, thiago, thiago);
		given(evaluationService.create(evaluationToCreate)).willReturn(newEvaluation);

		String inputJson = "{\"grade\": 10.0}";

		// When and Then
		this.mockMvc.perform(post("/evaluations/").contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andExpect(status().isCreated())
				.andExpect(header().string(HttpHeaders.LOCATION, "http://localhost/evaluations/" + id));

	}

	@Test
	public void whenUpdatingAEvaluationReturnsStatusNoContent() throws Exception {
		// Given
		Integer existingId = 1;
		Double grade = 10.0;

		EvaluationDTO evaluationDtoToFind = new EvaluationDTO();
		evaluationDtoToFind.setGrade(grade);

		TeamMember thiago = new TeamMember(1, "Thiago", TeamMemberType.DEVELOPER, "thiago@email.com");
		Project project = new Project(1, "EDOC");
		FeedbackForm feedback360 = new FeedbackForm(1, "360 Dataprev", "360 Dataprev", project, thiago);

		FeedbackItem feedbackItem = new FeedbackItem(existingId, "Technical Experience",
				"Level of Technical Experience", true, feedback360);
		Evaluation evaluationToFind = new Evaluation(existingId, 10.0, feedbackItem, thiago, thiago);
		;
		given(evaluationService.fromDTO(evaluationDtoToFind)).willReturn(evaluationToFind);

		String inputJson = "{\"name\":\"Technical Experience\",\"description\":\"Level of Technical Experience\", \"active\": true, \"form\": {\"name\":\"360 Dataprev\",\"description\":\"360 Dataprev\", \"project\":{\"name\":\"LifeProof\", \"type\": 1}}}";

		// When and Then
		this.mockMvc.perform(
				put("/evaluations/" + existingId).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andExpect(status().isNoContent());
	}

	@Test
	public void whenRemovingAEvaluationReturnsStatusNoContent() throws Exception {
		// Given
		Integer existingCode = 1;

		// When and Then
		this.mockMvc.perform(delete("/evaluations/" + existingCode)).andExpect(status().isNoContent());
	}

}
