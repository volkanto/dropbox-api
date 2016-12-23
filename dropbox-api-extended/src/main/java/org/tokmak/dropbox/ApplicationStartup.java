package org.tokmak.dropbox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.tokmak.dropbox.service.DocumentTagService;
import org.tokmak.dropbox.service.DropboxService;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {
	@Autowired
	private DropboxService dropboxService;
	@Autowired
	private DocumentTagService documentTagService;

	@Override
	public void onApplicationEvent(final ApplicationReadyEvent event) {
		this.documentTagService.removeAllDocuments();
		this.documentTagService.indexDocuments(this.dropboxService.getAllDocumentsWithPath());
	}
}
