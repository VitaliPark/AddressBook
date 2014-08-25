package validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import search.SearchPair;
import search.SearchRequest;
import constants.StringConstants;
import controller.FormParameter;

public class Validator {

	private Pattern pattern;
	private Matcher matcher;
	private String emailPattern = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private String numberPattern = "(-?[0-9]+)+";
	private String phoneNumberPattern = "([0-9]+)";
	
	public ValidationObject validateSearchRequest(SearchRequest request) {
		ValidationObject validationResult = new ValidationObject();
		for(SearchPair pair : request.getParams()){
			FormParameter criteria = pair.getCriteria();
			String value = pair.getValue();
			switch (criteria) {
			case FIRST_NAME:
				if(isNull(value)){
					validationResult.addWrongField(FormParameter.FIRST_NAME);
				}
				break;
			case SECOND_NAME: 
				if(isNull(value)){
					validationResult.addWrongField(FormParameter.SECOND_NAME);
				}
				break;
			case PATRONYMIC_NAME: 
				if(isNull(value)){
					validationResult.addWrongField(FormParameter.PATRONYMIC_NAME);
				}
				break;
			case DATE_OF_BIRTH: 
				if(!validateDate(value)){
					validationResult.addWrongField(FormParameter.DATE_OF_BIRTH);
				}
				break;
			case GENDER:
				if(isNull(value)){
					validationResult.addWrongField(FormParameter.GENDER);
				}
				break;
			case MARITAL_STATUS:
				if(isNull(value)){
					validationResult.addWrongField(FormParameter.MARITAL_STATUS);
				}
				break;
			case CITIZENSHIP:
				if(isNull(value)){
					validationResult.addWrongField(FormParameter.CITIZENSHIP);
				}
				break;
			case COUNTRY:
				if(isNull(value)){
					validationResult.addWrongField(FormParameter.COUNTRY);
				}
				break;
			case CITY:
				if(isNull(value)){
					validationResult.addWrongField(FormParameter.CITY);
				}
				break;
			case STREET:
				if(isNull(value)){
					validationResult.addWrongField(FormParameter.STREET);
				}
				break;
			case HOUSE_NUMBER:
				if(!validateNumber(value)){
					validationResult.addWrongField(FormParameter.HOUSE_NUMBER);
				}
				break;
			case APARTMENT:
				if(!validateNumber(value)){
					validationResult.addWrongField(FormParameter.APARTMENT);
				}
				break;
			case POST_CODE:
				if(isNull(value)){
					validationResult.addWrongField(FormParameter.POST_CODE);
				}
				break;
			default:
				break;
			}
		}
		return validationResult;
	}
	
	public boolean validateRequiredField(String requiredField){
		return requiredField != null && !requiredField.isEmpty(); 
	}
	
	public boolean isNull(String field){
		return field == null;
	}
	
	public boolean validateEmail(String email){
		if(email == null) return false;
		pattern = Pattern.compile(emailPattern);
		matcher = pattern.matcher(email);
		return matcher.matches() || email.isEmpty();
	}
	
	public boolean validateNumber(String number){
		if(number == null) return false;
		pattern = Pattern.compile(numberPattern);
		matcher = pattern.matcher(number);
		return matcher.matches() || number.isEmpty();
	}
	
	public boolean validateDate(String stringDate){
		if(stringDate == null) return false;
		if(stringDate.isEmpty()) return true;
		SimpleDateFormat dateFormat = new SimpleDateFormat(StringConstants.DEFAULT_DATE_FORMAT);
		dateFormat.setLenient(false);
		try {
			@SuppressWarnings("unused")
			Date date = dateFormat.parse(stringDate);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}
	
	public boolean validatePhoneNumber(String phoneNumberString){
		if(phoneNumberString == null) return false;
		pattern = Pattern.compile(phoneNumberPattern);
		matcher = pattern.matcher(phoneNumberString);
		return matcher.matches() || phoneNumberString.isEmpty();
	}
	
}
