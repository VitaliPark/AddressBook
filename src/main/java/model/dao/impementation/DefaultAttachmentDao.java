package model.dao.impementation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import constants.database.AttachmentColumnNames;
import constants.database.SQLQuery;
import exceptioin.DataAccessException;
import model.dao.AttachmentDao;
import model.entity.Attachment;
import model.entity.Contact;

public class DefaultAttachmentDao implements AttachmentDao{

	private Connection connection;
	
	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public List<Attachment> getPersonAttachments(int idPerson) throws DataAccessException {
		PreparedStatement getAttachmentStatement = null;
		try {
			getAttachmentStatement = connection.prepareStatement(SQLQuery.GET_PERSON_ATTACHMENTS.getValue());
			getAttachmentStatement.setInt(1, idPerson);
			ResultSet set = getAttachmentStatement.executeQuery();
			return buildGetPersonAttachments(set);
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage());
		} finally {
			closeStatement(getAttachmentStatement);
		}	
	}
	
	@Override
	public void deleteContactAttachments(int idPerson) throws DataAccessException {
		PreparedStatement deletePersonAttachments = null;
		try {
			deletePersonAttachments = connection.prepareStatement(SQLQuery.DELETE_PERSON_ATTACHMENTS.getValue());
			deletePersonAttachments.setInt(1, idPerson);
			deletePersonAttachments.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage());
		} finally{
			closeStatement(deletePersonAttachments);
		}
	}
	
	public void createAttachment(Attachment attach, int personId) throws DataAccessException{
		PreparedStatement createAttachmentStatement = null;
		try {
			createAttachmentStatement = connection.prepareStatement(SQLQuery.CREATE_ATTACHMENT.getValue());
			buildCreateAttachmentStatement(attach, personId,
					createAttachmentStatement);
			createAttachmentStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage());
		} finally {
			closeStatement(createAttachmentStatement);
		}
	}

	private void buildCreateAttachmentStatement(Attachment attach,
			int personId, PreparedStatement createAttachmentStatement)
			throws SQLException {
		Date date = new Date(attach.getUploadDate().getTime());
		createAttachmentStatement.setInt(1, personId);
		createAttachmentStatement.setString(2, attach.getFileName());
		createAttachmentStatement.setDate(3, date);
		createAttachmentStatement.setString(4, attach.getComment());
		createAttachmentStatement.setString(5, attach.getLocalFileName());
	}

	
	private List<Attachment> buildGetPersonAttachments(ResultSet set) throws SQLException {		
		List<Attachment> attachments = new ArrayList<>();
		while(set.next()){
			Attachment attach = new Attachment();
			attach.setIdAttachment(set.getInt(AttachmentColumnNames.idAttachment));
			attach.setFileName(set.getString(AttachmentColumnNames.fileName));
			attach.setLocalFileName(set.getString(AttachmentColumnNames.localFileName));
			attach.setComment(set.getString(AttachmentColumnNames.comment));
			setAttachmentUploadDate(set, attach);
			attachments.add(attach);
		}
		return attachments;
	}
	
	private void setAttachmentUploadDate(ResultSet set, Attachment attach) throws SQLException {
		java.sql.Date uploadDate = set.getDate(AttachmentColumnNames.uploadDate);
		java.util.Date date = null;
		if(uploadDate != null){
			date = new java.util.Date(uploadDate.getTime());
		}
		attach.setUploadDate(date);
	}
	
	private void closeStatement(PreparedStatement statement) {
		try {
			if(statement != null){
				statement.close();
			}
		} catch (SQLException e) {}
	}

}
