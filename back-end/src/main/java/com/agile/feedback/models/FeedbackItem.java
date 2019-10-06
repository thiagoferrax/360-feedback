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

@Entity
public class FeedbackItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String name;

	private String description;

	private Boolean ative;

	@ManyToOne
	private FeedbackForm form;

	@ManyToOne
	private FeedbackItem parent;

	@OneToMany(mappedBy = "parent")
	private Collection<FeedbackItem> children = new ArrayList<>();

	@OneToMany(mappedBy = "feedbackItem")
	private Collection<Evaluation> itemEvaluations = new ArrayList<>();

	@CreationTimestamp
	private Date createdAt;

	@UpdateTimestamp
	private Date updatedAt;

	public FeedbackItem() {
	}

	public FeedbackItem(Integer id, String name, String description, Boolean ative, FeedbackForm form) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.ative = ative;
		this.form = form;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getAtive() {
		return ative;
	}

	public void setAtive(Boolean ative) {
		this.ative = ative;
	}

	public FeedbackForm getForm() {
		return form;
	}

	public void setForm(FeedbackForm form) {
		this.form = form;
	}

	public FeedbackItem getParent() {
		return parent;
	}

	public void setParent(FeedbackItem parent) {
		this.parent = parent;
	}

	public Collection<FeedbackItem> getChildren() {
		return children;
	}

	public void setChildren(Collection<FeedbackItem> children) {
		this.children = children;
	}

	public Collection<Evaluation> getItemEvaluations() {
		return itemEvaluations;
	}

	public void setItemEvaluations(Collection<Evaluation> itemEvaluations) {
		this.itemEvaluations = itemEvaluations;
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
		FeedbackItem other = (FeedbackItem) obj;
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
