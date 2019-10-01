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
	private CompanyService service;

	@Mock
	private CompanyRepository repository;

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
		Mockito.when(repository.findById(existingId)).thenReturn(optional);

		// When
		Company company = service.find(existingId);

		// Then
		Assert.assertEquals(existingCompany, company);
	}

	@Test(expected = CompanyNotFoundException.class)
	public void whenIdDoesNotExistThrowsCompanyNotFoundException() {
		// Given
		Integer notExistingId = 2;

		Mockito.when(repository.findById(notExistingId))
				.thenThrow(new CompanyNotFoundException(CompanyService.COMPANY_NOT_FOUND_FOR_ID + notExistingId));

		// When
		service.find(notExistingId);
	}

	@Test
	public void whenUpdatinAnCompanyVerifyThatRepositorySaveIsCalled() {
		// Given
		Integer existingId = 1;
		Company existingCompany = new Company();
		existingCompany.setId(existingId);

		Optional<Company> optional = Optional.of(existingCompany);
		Mockito.when(repository.findById(existingId)).thenReturn(optional);

		// When
		service.update(existingCompany);

		// Then
		Mockito.verify(repository).save(existingCompany);
	}

	@Test
	public void whenDeletingAnCompanyVerifyThatRepositoryDeleteByIdIsCalled() {
		// Given
		Integer existingId = 1;

		Company existingCompany = new Company();
		existingCompany.setId(existingId);

		Optional<Company> optional = Optional.of(existingCompany);
		Mockito.when(repository.findById(existingId)).thenReturn(optional);

		// When
		service.remove(existingId);

		// Then
		Mockito.verify(repository).deleteById(existingId);
	}

}
