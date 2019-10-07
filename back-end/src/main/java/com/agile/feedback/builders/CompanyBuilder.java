package com.agile.feedback.builders;

import com.agile.feedback.enums.CompanyType;
import com.agile.feedback.models.Company;

public class CompanyBuilder {
	private Company company;

	private CompanyBuilder() {
	}
	
	public static CompanyBuilder newCompany() {
		CompanyBuilder builder = new CompanyBuilder();
		builder.company = new Company();
		return builder;
	}

	public CompanyBuilder withId(Integer id) {
		company.setId(id);
		return this;
	}
	
	public CompanyBuilder withName(String name) {
		company.setName(name);
		return this;
	}
	
	public CompanyBuilder withType(CompanyType type) {
		company.setType(type);
		return this;
	}


	public Company now() {
		return company;
	}
}
