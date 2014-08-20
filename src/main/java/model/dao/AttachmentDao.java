package model.dao;

import java.util.List;

import exceptioin.DataAccessException;
import model.entity.Attachment;

public interface AttachmentDao extends HasConnection{

	public List<Attachment> getPersonAttachments(int idPerson) throws DataAccessException;
	
	public void deletePersonAttachments(int idPerson) throws DataAccessException;
}
