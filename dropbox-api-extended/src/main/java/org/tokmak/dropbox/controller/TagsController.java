package org.tokmak.dropbox.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tokmak.dropbox.model.request.DocumentIdTagDto;
import org.tokmak.dropbox.model.request.DocumentTagDto;
import org.tokmak.dropbox.service.DocumentTagService;

@RestController
@RequestMapping(value = "/tags", produces = MediaType.APPLICATION_JSON_VALUE)
public class TagsController {
	@Autowired
	private DocumentTagService tagService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addTags(@RequestBody DocumentTagDto tag) {
		boolean isSuccessfullyAdded = this.tagService.createNewDocument(tag.getDocumentName(), tag.getDocumentPath(),
				tag.getDescriptions());
		if (isSuccessfullyAdded) {
			return new ResponseEntity<String>("Successfully created", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("An error occured while saving document with tags",
					HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteTags(@RequestBody @Valid DocumentIdTagDto documentTag) {
		boolean isDeleted = this.tagService.deleteGivenTags(documentTag.getId(), documentTag.getTags());
		if (isDeleted) {
			return new ResponseEntity<String>("Successfully deleted!", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("An error occured while deleting your document tags!",
					HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<?> updateDocumentTags(@RequestBody @Valid DocumentIdTagDto documentTag) {
		boolean isUpdated = this.tagService.updateGivenTags(documentTag.getId(), documentTag.getTags());
		if (isUpdated) {
			return new ResponseEntity<String>("Successfully updated!", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("An error occured while updating your document!", HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> searchDocuments(@RequestParam(required = true, value = "tags") String[] tags,
			@RequestParam(required = true, value = "limit") Integer limit,
			@RequestParam(required = true, value = "offset") Integer offset,
			@PageableDefault(page = 0, size = 20) Pageable pageable) {
		return new ResponseEntity<>(this.tagService.searchDocumentByTag(tags, pageable), HttpStatus.OK);
	}
	
	@RequestMapping(value="/all", method = RequestMethod.GET)
	public ResponseEntity<?> listAllDocuments() {
		return new ResponseEntity<>(this.tagService.listAllDocuments(), HttpStatus.OK);
	}
}
