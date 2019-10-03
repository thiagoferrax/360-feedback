package com.agile.feedback.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.agile.feedback.enums.CompanyType;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Company implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String name;

	private Integer type;

	@JsonIgnore
	@ManyToOne
	private Company headOffice;

	@OneToMany(mappedBy = "headOffice")
	private Collection<Company> branches = new ArrayList<Company>();

	@CreationTimestamp
	private Date createdAt;

	@UpdateTimestamp
	private Date updatedAt;

	public Company() {
	}

	public Company(Integer id, String name, CompanyType companyType, Company headOffice) {
		super();
		this.id = id;
		this.name = name;
		this.type = companyType.getCodigo();
		this.headOffice = headOffice;
	}

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
		return CompanyType.findByCodigo(this.type);
	}

	public void setType(CompanyType type) {
		this.type = type.getCodigo();
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Company getHeadOffice() {
		return headOffice;
	}

	public void setHeadOffice(Company headOffice) {
		this.headOffice = headOffice;
	}

	public Collection<Company> getBranches() {
		return branches;
	}

	public void setBranches(Collection<Company> branches) {
		this.branches = branches;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Company other = (Company) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
