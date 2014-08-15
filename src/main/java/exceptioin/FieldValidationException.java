package exceptioin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.SearchCriteria;

@SuppressWarnings("serial")
public class FieldValidationException extends IOException{
	
	private List<SearchCriteria> wrongFields;
	
	public FieldValidationException(String msg) {
		super(msg);
		wrongFields = new ArrayList<>();
	}
	
	public void addWrongField(SearchCriteria wrongField){
		wrongFields.add(wrongField);
	}
	
}
