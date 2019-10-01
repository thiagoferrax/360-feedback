package com.agile.feedback.services;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agile.feedback.dtos.CompanyDTO;
import com.agile.feedback.exceptions.CompanyNotFoundException;
import com.agile.feedback.models.Company;
import com.agile.feedback.repositories.CompanyRepository;

@Service
public class CompanyService {

	Logger logger = LoggerFactory.getLogger(CompanyService.class);

	public static final String UPDATING_COMPANY = "Updating company: {0}";
	public static final String REMOVING_COMPANY = "Removing company, id: {0}";
	public static final String COMPANY_NOT_FOUND_FOR_ID = "Company not found for id ";
	public static final String FINDING_COMPANY_BY_ID = "Finding company, id: {0}";
	public static final String CREATING_A_COMPANY = "Creating a company: {0}";

	@Autowired
	private CompanyRepository repository;

	public Company create(Company company) {
		logger.info(CREATING_A_COMPANY, company);

		company.setId(null);
		return repository.save(company);
	}

	public Company find(Integer companyId) {

		logger.info(FINDING_COMPANY_BY_ID, companyId);

		Optional<Company> optional = repository.findById(companyId);
		return optional.orElseThrow(() -> new CompanyNotFoundException(COMPANY_NOT_FOUND_FOR_ID + companyId));
	}

	public Company update(Company company) {
		logger.info(UPDATING_COMPANY, company);

		Company foundCompany = find(company.getId());
		company.setCreatedAt(foundCompany.getCreatedAt());

		return repository.save(company);
	}

	public void remove(Integer id) {

		logger.info(REMOVING_COMPANY, id);

		find(id);
		repository.deleteById(id);
	}

	public Company fromDTO(@Valid CompanyDTO companyDto) {
		// TODO Auto-generated method stub
		return null;
	}
}
