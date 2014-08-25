package validation;

import java.util.ArrayList;
import java.util.List;

import controller.FormParameter;

public class ValidationObject {

	private List<FormParameter> wrongFields;

	public ValidationObject() {
		wrongFields = new ArrayList<>();
	}
	
	public void addWrongField(FormParameter criteria){
		wrongFields.add(criteria);
	}
	
	public boolean isEmpty(){
		return wrongFields.isEmpty();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for(FormParameter criteria : wrongFields){
			builder.append(criteria.getValue());
			builder.append(", ");
		}
		return builder.toString();
	}
	
	
	
	
}
