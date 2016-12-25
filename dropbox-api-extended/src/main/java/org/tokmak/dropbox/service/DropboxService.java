package org.tokmak.dropbox.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tokmak.dropbox.model.request.DocumentTagDto;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.FolderMetadata;
import com.dropbox.core.v2.files.ListFolderErrorException;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;

@Service
public class DropboxService {
	private static final String ROOT_FOLDER = "";

	@Autowired
	private DbxClientV2 dropboxClient;

	@Autowired
	private Logger logger;

	public List<DocumentTagDto> getAllDocumentsWithPath() {
		List<DocumentTagDto> result = new ArrayList<>();
		try {
			this.listFolder(ROOT_FOLDER, result);
		} catch (DbxException e) {
			logger.error(e.getMessage(), e);
		}
		return result;
	}

	private boolean isFolder(Metadata metadata) {
		return (metadata instanceof FolderMetadata);
	}

	private boolean isFile(Metadata metadata) {
		return (metadata instanceof FileMetadata);
	}

	private List<DocumentTagDto> listFolder(String path, List<DocumentTagDto> documentTagList)
			throws ListFolderErrorException, DbxException {

		ListFolderResult result = this.dropboxClient.files().listFolder(path);
		while (true) {
			for (Metadata metadata : result.getEntries()) {
				if (this.isFile(metadata)) {
					documentTagList = this.generateDocument(metadata, documentTagList);
				} else if (this.isFolder(metadata)) {
					listFolder(metadata.getPathDisplay(), documentTagList);
				}
			}

			if (!result.getHasMore()) {
				break;
			}
			result = this.dropboxClient.files().listFolderContinue(result.getCursor());
		}

		return documentTagList;
	}

	private List<DocumentTagDto> generateDocument(Metadata metadata, List<DocumentTagDto> documentTagList) {
		DocumentTagDto document = new DocumentTagDto();
		document.setDocumentPath(metadata.getPathDisplay());
		document.setDocumentName(metadata.getName());
		documentTagList.add(document);

		return documentTagList;
	}
}
