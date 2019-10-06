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

import com.agile.feedback.dtos.CompanyDTO;
import com.agile.feedback.enums.CompanyType;
import com.agile.feedback.exceptions.CompanyNotFoundException;
import com.agile.feedback.models.Company;
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
public class CompanyResourceTest {

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
	public void whenCompanyIdExistsReturnsExistingCompany() throws Exception {
		// Given
		Integer existingId = 1;

		Company company = new Company(existingId, "Dataprev", CompanyType.HEAD_OFFICE, null);

		given(companyService.find(existingId)).willReturn(company);

		// When and Then
		this.mockMvc.perform(get("/companies/" + existingId)).andExpect(status().isOk()).andExpect(content().json(
				"{'id':1,'name':'Dataprev','type':'HEAD_OFFICE','branches':[],'createdAt': null,'updatedAt': null}"));
	}
	
	@Test
	public void whenCompanyIdDoesNotExistReturnsNotFound() throws Exception {
		// Given
		Integer notExistingId = 2;

		given(companyService.find(notExistingId))
				.willThrow(new CompanyNotFoundException(CompanyService.COMPANY_NOT_FOUND_FOR_ID + notExistingId));

		// When and Then
		this.mockMvc.perform(get("/companies/" + notExistingId)).andExpect(status().isNotFound());
	}
	
	@Test
	public void whenSavingANewCompanyReturnNewId() throws Exception {
		// Given
		Integer id = 1;
		String name = "Dataprev";

		CompanyDTO companyDtoToCreate = new CompanyDTO();
		companyDtoToCreate.setName(name);
		companyDtoToCreate.setType(CompanyType.HEAD_OFFICE.getCodigo());
		
		Company companyToCreate = new Company(null, name, CompanyType.HEAD_OFFICE, null);
		given(companyService.fromDTO(companyDtoToCreate)).willReturn(companyToCreate);

		Company newCompany = new Company(id, name, CompanyType.HEAD_OFFICE, null);
		given(companyService.create(companyToCreate)).willReturn(newCompany);

		String inputJson = "{\"name\":\"Dataprev\", \"type\": 1}";

		// When and Then
		this.mockMvc.perform(post("/companies/").contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andExpect(status().isCreated())
				.andExpect(header().string(HttpHeaders.LOCATION, "http://localhost/companies/" + id));

	}
	
	@Test
	public void whenUpdatingACompanyReturnsStatusNoContent() throws Exception {
		// Given
		String name = "Dataprev";
		Integer existingCode = 1;

		CompanyDTO companyDtoToFind = new CompanyDTO();
		companyDtoToFind.setName(name);
		companyDtoToFind.setType(CompanyType.HEAD_OFFICE.getCodigo());

		Company companyToFind = new Company(null, name, CompanyType.HEAD_OFFICE, null);
		given(companyService.fromDTO(companyDtoToFind)).willReturn(companyToFind);

		String inputJson = "{\"name\":\"Dataprev\", \"type\": 1}";

		// When and Then
		this.mockMvc
				.perform(put("/companies/" + existingCode).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andExpect(status().isNoContent());
	}

	@Test
	public void whenRemovingACompanyReturnsStatusNoContent() throws Exception {
		// Given
		Integer existingCode = 1;

		// When and Then
		this.mockMvc.perform(delete("/companies/" + existingCode)).andExpect(status().isNoContent());
	}

}
