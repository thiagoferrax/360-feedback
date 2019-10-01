package com.agile.feedback.resources;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agile.feedback.models.Company;
import com.agile.feedback.services.CompanyService;

@RestController
@RequestMapping(value = "/companies")
public class CompanyResource {

	Logger logger = LoggerFactory.getLogger(CompanyResource.class);

	@Autowired
	private CompanyService service;

	@GetMapping(path = "/{id}")
	public ResponseEntity<Company> getCompanyById(@PathVariable Integer id) {

		logger.info(GETTING_COMPANY_BY_ID, id);

		Company summary = service.find(id);

		return ResponseEntity.ok().body(summary);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Company> update(@Valid @RequestBody CompanyDTO companyDto, @PathVariable String id) {

		logger.info(UPDATING_COMPANY, companyDto);

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