package com.agile.feedback.services;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.agile.feedback.exceptions.CompanyNotFoundException;
import com.agile.feedback.models.Company;
import com.agile.feedback.repositories.CompanyRepository;

public class CompanyServiceTest {

	@InjectMocks
	private CompanyService companyService;
	
	@Mock
	private CompanyRepository companyRepository;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void whenIdExistsReturnsCompany() {
		// Given
		Integer existingId = 1;

		Company existingCompany = new Company();
		existingCompany.setId(existingId);

		Optional<Company> optional = Optional.of(existingCompany);
		Mockito.when(companyRepository.findById(existingId)).thenReturn(optional);

		// When
		Company company = companyService.find(existingId);

		// Then
		Assert.assertEquals(existingCompany, company);
	}

	@Test(expected = CompanyNotFoundException.class)
	public void whenIdDoesNotExistThrowsCompanyNotFoundException() {
		// Given
		Integer notExistingId = 2;

		Mockito.when(companyRepository.findById(notExistingId))
				.thenThrow(new CompanyNotFoundException(CompanyService.COMPANY_NOT_FOUND_FOR_ID + notExistingId));

		// When
		companyService.find(notExistingId);
	}

	@Test
	public void whenUpdatinACompanyVerifyThatRepositorySaveIsCalled() {
		// Given
		Integer existingId = 1;
		Company existingCompany = new Company();
		existingCompany.setId(existingId);

		Optional<Company> optional = Optional.of(existingCompany);
		Mockito.when(companyRepository.findById(existingId)).thenReturn(optional);

		// When
		companyService.update(existingCompany);

		// Then
		Mockito.verify(companyRepository).save(existingCompany);
	}

	@Test
	public void whenDeletingACompanyVerifyThatRepositoryDeleteByIdIsCalled() {
		// Given
		Integer existingId = 1;

		Company existingCompany = new Company();
		existingCompany.setId(existingId);

		Optional<Company> optional = Optional.of(existingCompany);
		Mockito.when(companyRepository.findById(existingId)).thenReturn(optional);

		// When
		companyService.remove(existingId);

		// Then
		Mockito.verify(companyRepository).deleteById(existingId);
	}

}
