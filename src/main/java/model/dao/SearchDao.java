package model.dao;

import java.util.List;

import exceptioin.DataAccessException;
import model.SearchRequest;
import model.entity.Contact;

public interface SearchDao extends HasConnection{
	public List<Contact> search(SearchRequest searchRequest, int first, int maxCount) throws DataAccessException;
	
	public int getCountOnRequest(SearchRequest searchRequest)throws DataAccessException;
}
