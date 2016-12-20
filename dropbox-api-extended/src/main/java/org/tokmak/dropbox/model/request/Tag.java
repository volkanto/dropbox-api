package org.tokmak.dropbox.model.request;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

public class Tag {
	@NotBlank
	List<String> descriptions;

	public List<String> getDescriptions() {
		return this.descriptions;
	}

	public void setDescriptions(List<String> descriptions) {
		this.descriptions = descriptions;
	}
}
