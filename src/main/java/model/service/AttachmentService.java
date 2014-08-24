package model.service;

import java.sql.Connection;
import java.util.List;

import org.apache.log4j.Logger;

import constants.ExceptionMessages;
import exceptioin.DataAccessException;
import exceptioin.ServiceException;
import model.dao.AttachmentDao;
import model.dao.impementation.DefaultAttachmentDao;
import model.entity.Attachment;
import model.entity.Contact;

public class AttachmentService {

	private AttachmentDao attachmentDao;
	private Logger LOGGER = Logger.getLogger(AttachmentService.class);

	public AttachmentService() {
		this.attachmentDao = new DefaultAttachmentDao();
	}
	
	public void createAttachment(Attachment attachment, int idPerson, Connection connection) throws ServiceException{
		attachmentDao.setConnection(connection);
		try {
			attachmentDao.createAttachment(attachment, idPerson);
		} catch (DataAccessException e) {
			LOGGER.error(ExceptionMessages.ATTACHMENT_CREATION_FAILED);
			throw new ServiceException(ExceptionMessages.ATTACHMENT_CREATION_FAILED + e.getMessage());
		}
	}
	
	public void deleteAttachment(int attachmentId, Connection connection) throws ServiceException{
		attachmentDao.setConnection(connection);
		try {
			attachmentDao.deleteAttachment(attachmentId);
		} catch (DataAccessException e) {
			LOGGER.error(ExceptionMessages.ATTACHMENT_DELETION_FAILED);
			throw new ServiceException(ExceptionMessages.ATTACHMENT_DELETION_FAILED + e.getMessage());
		}
	}
	
	public void updateAttachment(Attachment attachment, Connection connection) throws ServiceException{
		attachmentDao.setConnection(connection);
		try {
			attachmentDao.updateAttachment(attachment);
		} catch (DataAccessException e) {
			LOGGER.error(ExceptionMessages.ATTACHMENT_UPDATE_FAILED);
			throw new ServiceException(ExceptionMessages.ATTACHMENT_UPDATE_FAILED + e.getMessage());
		}
	}
	
	public List<Attachment> getAllPersonPhones(int personId, Connection connection) throws ServiceException{
		attachmentDao.setConnection(connection);
		try {		
		  return attachmentDao.getPersonAttachments(personId);
		} catch (DataAccessException e) {
			LOGGER.error(ExceptionMessages.ATTACHMENT_READ_FAILED);
			throw new ServiceException(ExceptionMessages.ATTACHMENT_READ_FAILED + e.getMessage());
		}	
	}
	
	public void deletePersonAttachments(int personId, Connection connection) throws ServiceException{
		attachmentDao.setConnection(connection);
		try {
			attachmentDao.deleteContactAttachments(personId);
		} catch (DataAccessException e) {
			LOGGER.error(ExceptionMessages.ATTACHMENT_DELETION_FAILED);
			throw new ServiceException(ExceptionMessages.ATTACHMENT_DELETION_FAILED + e.getMessage());
		}
	}
	
	public void addAllAttachments(Contact contact, Connection connection) throws ServiceException{
		attachmentDao.setConnection(connection);
		for(Attachment attachment : contact.getAttachments()){
			try {
				attachmentDao.createAttachment(attachment, contact.getPersonId());
			} catch (DataAccessException e) {
				LOGGER.error(ExceptionMessages.ATTACHMENT_CREATION_FAILED);
				throw new ServiceException(ExceptionMessages.ATTACHMENT_CREATION_FAILED + e.getMessage());
			}
		}
	}
}
