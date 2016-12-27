package org.tokmak.dropbox.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.tokmak.dropbox.Application;
import org.tokmak.dropbox.data.DocumentTag;
import org.tokmak.dropbox.repository.DocumentTagRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class, loader=SpringBootContextLoader.class)
public class DocumentTagServiceShould {

	@Autowired
	private DocumentTagService tagService;
	
	@Autowired
	private DocumentTagRepository tagRepository;
	
	@Before
	public void before() {
		this.createTestData();
	}
	
	@Test public void 
	creates_new_document() {
		this.tagService.createNewDocument("Java 8 Essentials.pdf", "/ebooks/java/Java 8 Essentials.pdf", Arrays.asList("java", "essentials"));
		List<DocumentTag> document = this.tagRepository.findByName("Java 8 Essentials.pdf");

		assertEquals(document.size(), 1);
		assertTrue("Java 8 Essentials.pdf".equals(document.get(0).getName()));
	}
	
	@Test public void 
	searches_document_by_tag() {
		List<DocumentTag> documents = this.tagService.searchDocumentByTag(new String[] {"web"}, new PageRequest(0, 5));
		
		assertEquals(documents.size(), 2);
		assertTrue(documents.stream().filter(tag -> tag.getName().equals("Socket.IO Real-time Web Application Development.pdf")).collect(Collectors.toList()).size() > 0);
	}

	private void createTestData() {
		this.addTagToIndex("Socket.IO Real-time Web Application Development.pdf", "/ebooks/nodejs/socket/Socket.IO Real-time Web Application Development.pdf", Arrays.asList("socket", "web", "real-time"));
		this.addTagToIndex("Developing Microservices with Node.js.pdf", "/ebooks/nodejs/Developing Microservices with Node.js.pdf", Arrays.asList("nodejs", "microservices", "web"));
		this.addTagToIndex("Get Started with Dropbox.pdf", "/Get Started with Dropbox.pdf", Arrays.asList("dropbox", "start"));
		this.addTagToIndex("hf-html-cheat-sheet.pdf", "/ebooks/cheat-sheet/hf-html-cheat-sheet.pdf", Arrays.asList("html", "cheat", "sheet"));
		
	}
	
	private void addTagToIndex(String name, String path, List<String> tags) {
		DocumentTag documentTag = new DocumentTag();
		documentTag.setName(name);
		documentTag.setPath(path);
		documentTag.setTags(tags);
		this.tagRepository.save(documentTag);
	}
}
