package model.dao;

import java.util.List;

import exceptioin.DataAccessException;
import model.entity.Attachment;

public interface AttachmentDao extends HasConnection{

	public List<Attachment> getPersonAttachments(int idPerson) throws DataAccessException;
	
	public void deleteContactAttachments(int idPerson) throws DataAccessException;
	
	public void createAttachment(Attachment attachment, int idPerson) throws DataAccessException;
}
