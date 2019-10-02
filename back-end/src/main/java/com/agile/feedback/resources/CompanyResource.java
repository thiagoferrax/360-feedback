package com.agile.feedback.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.agile.feedback.dtos.CompanyDTO;
import com.agile.feedback.models.Company;
import com.agile.feedback.services.CompanyService;

@RestController
@RequestMapping(value = "/companies")
public class CompanyResource {

	public static final String CREATING_NEW_COMPANY = "Creating a new company: {0}";
	public static final String UPDATING_COMPANY = "Updating company: {0}";
	public static final String REMOVING_COMPANY = "Removing company, id: {0}";
	public static final String GETTING_COMPANY_BY_ID = "Getting company by id: {0}";
	public static final String GETTING_ALL_COMPANIES = "Getting all company by id: {0}";

	Logger logger = LoggerFactory.getLogger(CompanyResource.class);

	@Autowired
	private CompanyService service;

	@GetMapping(path = "/")
	public ResponseEntity<List<Company>> getAll() {

		logger.info(GETTING_ALL_COMPANIES);

		List<Company> companies = service.findAll();

		return ResponseEntity.ok().body(companies);
	}

	@PostMapping(value = "/")
	public ResponseEntity<Company> create(@Valid @RequestBody(required = true) CompanyDTO companyDto) {

		logger.info(CREATING_NEW_COMPANY, companyDto.getName());

		Company company = service.fromDTO(companyDto);
		Company newCompany = service.create(company);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newCompany.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Company> findCompanyById(@PathVariable Integer id) {

		logger.info(GETTING_COMPANY_BY_ID, id);

		Company summary = service.find(id);

		return ResponseEntity.ok().body(summary);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Company> update(@Valid @RequestBody CompanyDTO companyDto, @PathVariable Integer id) {

		logger.info(UPDATING_COMPANY, id);

		companyDto.setId(id);
		Company company = service.fromDTO(companyDto);

		service.update(company);

		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Company> delete(@PathVariable Integer id) {

		logger.info(REMOVING_COMPANY, id);

		service.remove(id);

		return ResponseEntity.noContent().build();
	}
}