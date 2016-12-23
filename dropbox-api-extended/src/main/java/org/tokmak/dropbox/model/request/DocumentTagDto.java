package org.tokmak.dropbox.model.request;

import java.util.List;

public class DocumentTagDto {
	String documentName;
	String documentPath;
	List<String> descriptions;

	public String getDocumentName() {
		return this.documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getDocumentPath() {
		return this.documentPath;
	}

	public void setDocumentPath(String documentPath) {
		this.documentPath = documentPath;
	}

	public List<String> getDescriptions() {
		return this.descriptions;
	}

	public void setDescriptions(List<String> descriptions) {
		this.descriptions = descriptions;
	}
}
