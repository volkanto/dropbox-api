package org.tokmak.dropbox.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.tokmak.dropbox.data.DocumentTag;

public interface DocumentTagCustomRepository {

	public List<DocumentTag> searchByTags(String[] tags, Pageable page);
}
