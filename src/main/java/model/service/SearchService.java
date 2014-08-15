package model.service;

import java.sql.Connection;
import java.util.List;

import model.SearchRequest;
import model.dao.SearchDao;
import model.dao.impementation.DefaultSearchDao;
import model.entity.Contact;

public class SearchService {
	
	private SearchDao searchDao;
	
	public SearchService() {
		searchDao = new DefaultSearchDao();
	}

	public List<Contact> searchContacts(Connection con, SearchRequest request, int first, int count){
		searchDao.setConnection(con);
		return searchDao.search(request, first, count);
	}
}
