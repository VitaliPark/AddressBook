package model.dao;

import model.entity.Image;
import exceptioin.DataAccessException;

public interface ImageDao extends HasConnection{

	public void createImage(Image image, int personId) throws DataAccessException;
	
	public void updateImage(Image image, int personId) throws DataAccessException;
	
	public Image getImage(int personId) throws DataAccessException;
	
	public void deleteImage(int personId) throws DataAccessException;
}
