package com.agile.feedback.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.agile.feedback.models.Company;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class CompanyDTO implements Serializable {
	private static final long serialVersionUID = 1L;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((headOffice == null) ? 0 : headOffice.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompanyDTO other = (CompanyDTO) obj;
		if (headOffice == null) {
			if (other.headOffice != null)
				return false;
		} else if (!headOffice.equals(other.headOffice))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
}
