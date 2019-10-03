package com.agile.feedback.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.agile.feedback.models.Company;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class CompanyDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	private Integer id;

	@NotNull
	@NotEmpty
	private String name;

	@NotNull
	private Integer type;

	@JsonIgnore
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Company getHeadOffice() {
		return headOffice;
	}

	public void setHeadOffice(Company headOffice) {
		this.headOffice = headOffice;
	}
}
