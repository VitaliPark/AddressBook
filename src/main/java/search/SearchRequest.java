package search;

import java.util.ArrayList;
import java.util.List;

import controller.FormParameter;

public class SearchRequest {
	
	private List<SearchPair> requestparams;
	private String operatortype;

	public String getOperatortype() {
		return operatortype;
	}

	public void setOperatortype(String operatortype) {
		this.operatortype = operatortype;
	}

	public SearchRequest() {
		requestparams = new ArrayList<>();
	}
	
	public void addParam(FormParameter criteria, String value){
		SearchPair pair = new SearchPair(criteria, value);
		requestparams.add(pair);
	}
	
	public List<SearchPair> getParams(){
		return requestparams;
	}
	
	public SearchPair getPair(int i){
		return requestparams.get(i);
	}
	
	public String getValueByKey(FormParameter criteria){
		for (SearchPair pair: requestparams) {
			if(pair.getCriteria() == criteria){
				return pair.getValue();
			}
		}
		return "";
	}
}
