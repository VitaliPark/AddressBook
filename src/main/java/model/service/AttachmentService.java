package model.service;

import model.dao.AttachmentDao;
import model.dao.impementation.DefaultAttachmentDao;

public class AttachmentService {

	private AttachmentDao attachmentDao;

	public AttachmentService() {
		this.attachmentDao = new DefaultAttachmentDao();
	}
	
	
	
	
}
