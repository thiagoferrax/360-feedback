package com.agile.feedback.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agile.feedback.dtos.CompanyDTO;
import com.agile.feedback.enums.CompanyType;
import com.agile.feedback.exceptions.ObjectNotFoundException;
import com.agile.feedback.models.Company;
import com.agile.feedback.repositories.CompanyRepository;

@Service
public class CompanyService {

	Logger logger = LoggerFactory.getLogger(CompanyService.class);

	public static final String UPDATING_COMPANY = "Updating company";
	public static final String REMOVING_COMPANY = "Removing company";
	public static final String COMPANY_NOT_FOUND = "Company not found";
	public static final String FINDING_COMPANY_BY_ID = "Finding company by id";
	public static final String FINDING_ALL_COMPANIES = "Finding all companies";
	public static final String CREATING_A_COMPANY = "Creating a company";

	@Autowired
	private CompanyRepository repository;

	public Company create(Company company) {
		logger.info(CREATING_A_COMPANY, company);

		company.setId(null);
		return repository.save(company);
	}

	public List<Company> findAll() {
		logger.info(FINDING_ALL_COMPANIES);
		return repository.findAll();
	}

	public Company find(Integer companyId) {

		logger.info(FINDING_COMPANY_BY_ID);

		Optional<Company> optional = repository.findById(companyId);
		return optional.orElseThrow(() -> new ObjectNotFoundException(COMPANY_NOT_FOUND));
	}

	public Company update(Company company) {
		logger.info(UPDATING_COMPANY, company);

		Company foundCompany = find(company.getId());
		company.setCreatedAt(foundCompany.getCreatedAt());

		return repository.save(company);
	}

	public void remove(Integer id) {

		logger.info(REMOVING_COMPANY);

		find(id);
		repository.deleteById(id);
	}

	public Company fromDTO(@Valid CompanyDTO companyDto) {
		return new Company(companyDto.getId(), companyDto.getName(), CompanyType.findByCodigo(companyDto.getType()),
				companyDto.getHeadOffice());
	}
}
