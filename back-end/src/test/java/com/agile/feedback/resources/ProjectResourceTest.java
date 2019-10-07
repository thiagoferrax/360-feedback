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

import com.agile.feedback.builders.ProjectBuilder;
import com.agile.feedback.dtos.ProjectDTO;
import com.agile.feedback.exceptions.ObjectNotFoundException;
import com.agile.feedback.models.Project;
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
public class ProjectResourceTest {

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
	public void whenProjectIdExistsReturnsExistingProject() throws Exception {
		// Given
		Integer existingId = 1;

		Project project = ProjectBuilder.newProject().withId(existingId).withName("LifeProof").now();

		given(projectService.find(existingId)).willReturn(project);

		// When and Then
		this.mockMvc.perform(get("/projects/" + existingId)).andExpect(status().isOk())
				.andExpect(content().json("{'id':1,'name':'LifeProof'}"));
	}

	@Test
	public void whenProjectIdDoesNotExistReturnsNotFound() throws Exception {
		// Given
		Integer notExistingId = 2;

		given(projectService.find(notExistingId))
				.willThrow(new ObjectNotFoundException(ProjectService.PROJECT_NOT_FOUND_FOR_ID));

		// When and Then
		this.mockMvc.perform(get("/projects/" + notExistingId)).andExpect(status().isNotFound());
	}

	@Test
	public void whenSavingANewProjectReturnNewId() throws Exception {
		// Given
		Integer id = 1;
		String name = "LifeProof";

		ProjectDTO projectDtoToCreate = new ProjectDTO();
		projectDtoToCreate.setName(name);

		Project projectToCreate = ProjectBuilder.newProject().withName(name).now();

		given(projectService.fromDTO(projectDtoToCreate)).willReturn(projectToCreate);

		Project newProject = ProjectBuilder.newProject().withId(id).withName(name).now();

		given(projectService.create(projectToCreate)).willReturn(newProject);

		String inputJson = "{\"name\":\"LifeProof\", \"type\": 1}";

		// When and Then
		this.mockMvc.perform(post("/projects/").contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andExpect(status().isCreated())
				.andExpect(header().string(HttpHeaders.LOCATION, "http://localhost/projects/" + id));

	}

	@Test
	public void whenUpdatingAProjectReturnsStatusNoContent() throws Exception {
		// Given
		String name = "LifeProof";
		Integer existingCode = 1;

		ProjectDTO projectDtoToFind = new ProjectDTO();
		projectDtoToFind.setName(name);

		Project projectToFind = ProjectBuilder.newProject().withName(name).now();

		given(projectService.fromDTO(projectDtoToFind)).willReturn(projectToFind);

		String inputJson = "{\"name\":\"LifeProof\", \"type\": 1}";

		// When and Then
		this.mockMvc.perform(
				put("/projects/" + existingCode).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andExpect(status().isNoContent());
	}

	@Test
	public void whenRemovingAProjectReturnsStatusNoContent() throws Exception {
		// Given
		Integer existingCode = 1;

		// When and Then
		this.mockMvc.perform(delete("/projects/" + existingCode)).andExpect(status().isNoContent());
	}

}
