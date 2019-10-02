package com.agile.feedback.resources;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.agile.feedback.enums.CompanyType;
import com.agile.feedback.models.Company;
import com.agile.feedback.repositories.CompanyRepository;
import com.agile.feedback.services.CompanyService;

@RunWith(SpringRunner.class)
@WebMvcTest
public class CompanyResourceTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CompanyService companyService;

	@MockBean
	private CompanyRepository companyRepository;

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

}
