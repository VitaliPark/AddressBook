package model.dao;

import java.util.List;

import model.SearchRequest;
import model.entity.Contact;

public interface SearchDao extends HasConnection{
	public List<Contact> search(SearchRequest searchRequest, int first, int maxCount);
}
