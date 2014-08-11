package model;

import java.util.List;

import model.entity.Contact;

public class SearchRequest {

	private List<Contact> result;
	private int searchQueryId;

	public List<Contact> getResult() {
		return result;
	}
	public void setResult(List<Contact> result) {
		this.result = result;
	}
	public int getSearchQueryId() {
		return searchQueryId;
	}
	public void setSearchQueryId(int searchQueryId) {
		this.searchQueryId = searchQueryId;
	}
	
	
}
