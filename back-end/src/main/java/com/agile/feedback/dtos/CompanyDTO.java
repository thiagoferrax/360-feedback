package com.agile.feedback.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.agile.feedback.enums.CompanyType;
import com.agile.feedback.models.Company;

public class CompanyDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotNull
	@NotEmpty
	private String name;

	@NotNull
	@NotEmpty
	private Integer type;

	private Company headOffice;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CompanyType getType() {
		return CompanyType.findById(type);
	}

	public void setType(CompanyType companyType) {
		this.type = companyType.getId();
	}

	public Company getHeadOffice() {
		return headOffice;
	}

	public void setHeadOffice(Company headOffice) {
		this.headOffice = headOffice;
	}

}
