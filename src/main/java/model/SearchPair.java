package model;

public class SearchPair {

	private SearchCriteria criteria;
	private String value;
	
	public SearchPair(SearchCriteria criteria, String value) {
		super();
		this.criteria = criteria;
		this.value = value;
	}
	
	public SearchCriteria getCriteria() {
		return criteria;
	}
	public void setCriteria(SearchCriteria criteria) {
		this.criteria = criteria;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "SearchPair [criteria=" + criteria + ", value=" + value + "]";
	}
}
