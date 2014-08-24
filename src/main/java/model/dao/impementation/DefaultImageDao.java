package model.dao.impementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import constants.database.ImageColumnNames;
import constants.database.SQLQuery;
import exceptioin.DataAccessException;
import model.dao.ImageDao;
import model.entity.Image;

public class DefaultImageDao implements ImageDao{

	private Connection connection;
	private Logger LOGGER = Logger.getLogger(DefaultImageDao.class);
	
	@Override
	public void createImage(Image image, int personId) throws DataAccessException {
		PreparedStatement createImageStatement = null;
		try {
			createImageStatement = connection.prepareStatement(SQLQuery.CREATE_IMAGE.getValue());
			createImageStatement.setInt(1, personId);
			createImageStatement.setString(2, image.getFileName());
			createImageStatement.setString(3, image.getLocalFileName());
			createImageStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
			throw new DataAccessException(e.getMessage());
		} finally {
			closeStatement(createImageStatement);
		}
	}

	@Override
	public void updateImage(Image image, int personId) throws DataAccessException {
		PreparedStatement updateImageStatement = null;
		try {
			updateImageStatement = connection.prepareStatement(SQLQuery.UPDATE_IMAGE.getValue());
			updateImageStatement.setString(1, image.getFileName());
			updateImageStatement.setString(2, image.getLocalFileName());
			updateImageStatement.setInt(3, personId);
			updateImageStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
			throw new DataAccessException(e.getMessage());
		} finally {
			closeStatement(updateImageStatement);
		}
	}

	@Override
	public void deleteImage(int personId) throws DataAccessException {
		PreparedStatement deleteImageStatement = null;
		try {
			deleteImageStatement = connection.prepareStatement(SQLQuery.DELETE_PERSON_IMAGE.getValue());
			deleteImageStatement.setInt(1, personId);
			deleteImageStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
			throw new DataAccessException(e.getMessage());
		} finally {
			closeStatement(deleteImageStatement);
		}
		
	}

	@Override
	public Image getImage(int personId) throws DataAccessException {
		PreparedStatement getImageStatement = null;
		try {
			getImageStatement = connection.prepareStatement(SQLQuery.GET_PERSON_IMAGE.getValue());
			getImageStatement.setInt(1, personId);
			ResultSet set = getImageStatement.executeQuery();
			return buildGetImageStatement(set);
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
			throw new DataAccessException(e.getMessage());
		}
	}

	private Image buildGetImageStatement(ResultSet set) throws SQLException {
		Image image = null; 
		if(set.next()){
			image = new Image();
			image.setImageId(set.getInt(ImageColumnNames.idImage));
			image.setFileName(set.getString(ImageColumnNames.fileName));
			image.setLocalFileName(set.getString(ImageColumnNames.localFileName));
		}
		return image;
	}

	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	private void closeStatement(PreparedStatement statement) {
		if(statement != null){
			try {
				statement.close();
			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
			}
		}
	}

	
}
