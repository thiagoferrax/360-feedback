package com.agile.feedback.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String name;

	@ManyToMany
	@JoinTable(name = "Project_Company", joinColumns = { @JoinColumn(name = "project_id") }, inverseJoinColumns = {
			@JoinColumn(name = "company_id") })
	private Collection<Company> executingCompanies = new ArrayList<>();

	@ManyToMany(mappedBy = "projects")
	private Collection<TeamMember> members = new ArrayList<>();

	@OneToMany(mappedBy = "project")
	private Collection<FeedbackForm> feedbackForms = new ArrayList<>();

	@CreationTimestamp
	private Date createdAt;

	@UpdateTimestamp
	private Date updatedAt;

	public Project() {
	}

	public Project(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
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

	public Collection<Company> getExecutingCompanies() {
		return executingCompanies;
	}

	public void setExecutingCompanies(Collection<Company> executingCompanies) {
		this.executingCompanies = executingCompanies;
	}

	public Collection<TeamMember> getMembers() {
		return members;
	}

	public void setMembers(Collection<TeamMember> members) {
		this.members = members;
	}

	public Collection<FeedbackForm> getFeedbackForms() {
		return feedbackForms;
	}

	public void setFeedbackForms(Collection<FeedbackForm> feedbackForms) {
		this.feedbackForms = feedbackForms;
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
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Project other = (Project) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
	
}
