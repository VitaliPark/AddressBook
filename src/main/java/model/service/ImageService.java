package model.service;

import java.sql.Connection;

import org.apache.log4j.Logger;

import exceptioin.DataAccessException;
import exceptioin.ServiceException;
import model.dao.ImageDao;
import model.dao.impementation.DefaultImageDao;
import model.entity.Image;

public class ImageService {

	
	private ImageDao imageDao;
	private Logger LOGGER = Logger.getLogger(ImageService.class);

	public ImageService() {
		this.imageDao = new DefaultImageDao();
	}
	
	public void createImage(Image image, int personId, Connection connection) throws ServiceException{
		imageDao.setConnection(connection);
			try {
				imageDao.createImage(image, personId);
			} catch (DataAccessException e) {
				LOGGER.error(" Unable to create image info");
				throw new ServiceException(" Unable to create image imfo " + e.getMessage());
			}
	}
	
	public void updateImage(Image image, int personId, Connection connection) throws ServiceException{
		imageDao.setConnection(connection);
			try {
				imageDao.updateImage(image, personId);
			} catch (DataAccessException e) {
				LOGGER.error(" Unable to update image info");
				throw new ServiceException(" Unable to update image info " + e.getMessage());
			}
	}
	
	public void deletePersonImage(int personId, Connection connection) throws ServiceException{
		imageDao.setConnection(connection);
		try {
			imageDao.deleteImage(personId);
		} catch (DataAccessException e) {
			LOGGER.error(" Unable to delete image info");
			throw new ServiceException(" Unable to delete image info " + e.getMessage());
		}
	}
	
	public Image getImage(int idPerson, Connection connection) throws ServiceException{
		imageDao.setConnection(connection);
		Image image = null;
		try {
			image = imageDao.getImage(idPerson);
			return image;
		} catch (DataAccessException e) {
			LOGGER.error(" Unable to read image info");
			throw new ServiceException(" Unable to read image info " + e.getMessage());
		}
	}
	
	
}
