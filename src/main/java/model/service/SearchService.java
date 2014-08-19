package model.service;

import java.sql.Connection;
import java.util.List;

import exceptioin.DataAccessException;
import exceptioin.ServiceException;
import model.SearchRequest;
import model.dao.SearchDao;
import model.dao.impementation.DefaultSearchDao;
import model.entity.Contact;

public class SearchService {
	
	private SearchDao searchDao;
	
	public SearchService() {
		searchDao = new DefaultSearchDao();
	}

	public List<Contact> searchContacts(Connection con, SearchRequest request, int first, int count) throws ServiceException{
		searchDao.setConnection(con);
		try {
			return searchDao.search(request, first, count);
		} catch (DataAccessException e) {
			throw new ServiceException(e.getMessage());
		} 
	}

	public int getCountOnSearchRequest(Connection connection, SearchRequest searchRequest) throws ServiceException{
		searchDao.setConnection(connection);
		try {
			return searchDao.getCountOnRequest(searchRequest);
		} catch (DataAccessException e) {
			throw new ServiceException(e.getMessage());
		} 
	}
	
}
