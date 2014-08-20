package model.service;

import java.sql.Connection;
import java.util.List;

import javax.print.attribute.standard.Severity;

import constants.ExceptionMessages;
import exceptioin.DataAccessException;
import exceptioin.ServiceException;
import model.dao.AttachmentDao;
import model.dao.impementation.DefaultAttachmentDao;
import model.entity.Attachment;

public class AttachmentService {

	private AttachmentDao attachmentDao;

	public AttachmentService() {
		this.attachmentDao = new DefaultAttachmentDao();
	}
	
	public List<Attachment> getAllPersonPhones(int personId, Connection connection) throws ServiceException{
		attachmentDao.setConnection(connection);
		try {		
		  return attachmentDao.getPersonAttachments(personId);
		} catch (DataAccessException e) {
			throw new ServiceException(ExceptionMessages.ATTACHMENT_READ_FAILED + e.getMessage());
		}	
	}
	
	public void deletePersonAttachments(int personId, Connection connection) throws ServiceException{
		attachmentDao.setConnection(connection);
		try {
			attachmentDao.deletePersonAttachments(personId);
		} catch (DataAccessException e) {
			throw new ServiceException(ExceptionMessages.ATTACHMENT_DELETION_FAILED + e.getMessage());
		}
	}
	
	
}
