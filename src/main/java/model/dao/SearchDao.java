package model.dao;

import model.SearchRequest;

public interface SearchDao {
	public SearchRequest searchContact(int first, int maxCount);
}
