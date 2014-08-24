package model;

import java.util.ArrayList;
import java.util.List;

public class ValidationObject {

	private List<SearchCriteria> wrongFields;

	public ValidationObject() {
		wrongFields = new ArrayList<>();
	}
	
	public void addWrongField(SearchCriteria criteria){
		wrongFields.add(criteria);
	}
	
	public boolean isEmpty(){
		return wrongFields.isEmpty();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for(SearchCriteria criteria : wrongFields){
			builder.append(criteria.getValue());
			builder.append(", ");
		}
		return builder.toString();
	}
	
	
	
	
}
