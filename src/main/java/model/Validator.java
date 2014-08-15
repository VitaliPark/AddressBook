package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import constants.StringConstants;

public enum Validator {

	INSTANCE;
	
	private Pattern pattern;
	private Matcher matcher;
	private String emailPattern = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private String numberPattern = "(-?[0-9]+)+";
	
	public ValidationObject validateSearchRequest(SearchRequest request) {
		ValidationObject validationResult = new ValidationObject();
		for(SearchPair pair : request.getParams()){
			SearchCriteria criteria = pair.getCriteria();
			String value = pair.getValue();
			System.out.println(pair.toString());
			switch (criteria) {
			case FIRST_NAME:
				if(isNull(value)){
					validationResult.addWrongField(SearchCriteria.FIRST_NAME);
				}
				break;
			case SECOND_NAME: 
				if(isNull(value)){
					//System.out.println(value);
					validationResult.addWrongField(SearchCriteria.SECOND_NAME);
				}
				break;
			case PATRONYMIC_NAME: 
				if(isNull(value)){
					//System.out.println(value);
					validationResult.addWrongField(SearchCriteria.PATRONYMIC_NAME);
				}
				break;
			case DATE_OF_BIRTH: 
				if(!validateDate(value)){
					//System.out.println(value);
					validationResult.addWrongField(SearchCriteria.DATE_OF_BIRTH);
				}
				break;
			case GENDER:
				if(isNull(value)){
					//System.out.println(value);
					validationResult.addWrongField(SearchCriteria.GENDER);
				}
				break;
			case MARITAL_STATUS:
				if(isNull(value)){
					//System.out.println(value);
					validationResult.addWrongField(SearchCriteria.MARITAL_STATUS);
				}
				break;
			case CITIZENSHIP:
				if(isNull(value)){
					//System.out.println(value);
					validationResult.addWrongField(SearchCriteria.CITIZENSHIP);
				}
				break;
			case COUNTRY:
				if(isNull(value)){
					//System.out.println(value);
					validationResult.addWrongField(SearchCriteria.COUNTRY);
				}
				break;
			case CITY:
				if(isNull(value)){
					//System.out.println(value);
					validationResult.addWrongField(SearchCriteria.CITY);
				}
				break;
			case STREET:
				if(isNull(value)){
					//System.out.println(value);
					validationResult.addWrongField(SearchCriteria.STREET);
				}
				break;
			case HOUSE_NUMBER:
				if(!validateNumber(value)){
					//System.out.println(value);
					validationResult.addWrongField(SearchCriteria.HOUSE_NUMBER);
				}
				break;
			case APARTMENT:
				if(!validateNumber(value)){
					//System.out.println(value);
					validationResult.addWrongField(SearchCriteria.APARTMENT);
				}
				break;
			case POST_CODE:
				if(isNull(value)){
					//System.out.println(value);
					validationResult.addWrongField(SearchCriteria.POST_CODE);
				}
				break;
			default:
				break;
			}
		}
		return validationResult;
	}
	
	private boolean validateRequiredField(String requiredField){
		return requiredField != null && !requiredField.isEmpty(); 
	}
	
	private boolean isNull(String field){
		return field == null;
	}
	
	private boolean validateEmail(String email){
		if(email == null) return false;
		pattern = Pattern.compile(emailPattern);
		matcher = pattern.matcher(email);
		return matcher.matches() || email.isEmpty();
	}
	
	private boolean validateNumber(String number){
		if(number == null) return false;
		pattern = Pattern.compile(numberPattern);
		matcher = pattern.matcher(number);
		return matcher.matches() || number.isEmpty();
	}
	
	private boolean validateDate(String stringDate){
		if(stringDate == null) return false;
		if(stringDate.isEmpty()) return true;
		SimpleDateFormat dateFormat = new SimpleDateFormat(StringConstants.defaultDateFormat);
		dateFormat.setLenient(false);
		try {
			@SuppressWarnings("unused")
			Date date = dateFormat.parse(stringDate);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}
	
}
