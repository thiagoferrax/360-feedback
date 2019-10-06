package com.agile.feedback.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.agile.feedback.models.FeedbackForm;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class FeedbackItemDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotNull
	@NotEmpty
	private String name;

	private String description;
	
	private Boolean ative;

	@JsonIgnore
	private FeedbackForm form;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		FeedbackItemDTO other = (FeedbackItemDTO) obj;
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
		return true;
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

}
