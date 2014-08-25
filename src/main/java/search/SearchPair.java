package search;

import controller.FormParameter;

public class SearchPair {

	private FormParameter criteria;
	private String value;
	
	public SearchPair(FormParameter criteria, String value) {
		super();
		this.criteria = criteria;
		this.value = value;
	}
	
	public FormParameter getCriteria() {
		return criteria;
	}
	public void setCriteria(FormParameter criteria) {
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
