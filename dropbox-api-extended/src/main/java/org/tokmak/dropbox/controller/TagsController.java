package org.tokmak.dropbox.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tokmak.dropbox.model.request.Tag;

@RestController(value = "/tags")
public class TagsController {
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> addTags(@RequestBody @Valid Tag tag) {
		return null;
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteTags(@RequestBody @Valid Tag tag) {
		return null;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> searchDocuments(@RequestParam(required = true, value = "search") String[] searchKeys,
			@RequestParam(required = true, value = "limit") Integer limit,
			@RequestParam(required = true, value = "offset") Integer offset) {

		return null;
	}
}
