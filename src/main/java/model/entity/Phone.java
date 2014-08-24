package model.entity;

import constants.DefaultValues;
import constants.Status;
import constants.StringConstants;

public class Phone {
	
	private int phoneId;
	private int countryCode;
	private int operatorCode;
	private String phoneNumber;
	private String phoneType;
	private String comment;
	
	private Status status;
	
	public int getPhoneId() {
		return phoneId;
	}
	public void setPhoneId(int phoneId) {
		this.phoneId = phoneId;
	}
	
	public int getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(int countryCode) {
		this.countryCode = countryCode;
	}
	public int getOperatorCode() {
		return operatorCode;
	}
	public void setOperatorCode(int operatorCode) {
		this.operatorCode = operatorCode;
	}
	public String getPhoneNumber() {
		return (phoneNumber != null) ? phoneNumber : StringConstants.EMPTY_STRING;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPhoneType() {
		return (phoneType != null) ? phoneType : StringConstants.EMPTY_STRING;
	}
	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}
	public String getComment() {
		return (comment != null) ? comment : StringConstants.EMPTY_STRING;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getFullPhone(){
		StringBuilder builder = new StringBuilder();
		if(countryCode != DefaultValues.defaultNumericValue){
			builder.append(getCountryCode()); builder.append(StringConstants.PHONE_SEPARATOR);
		}
		if(operatorCode != DefaultValues.defaultNumericValue){
			builder.append(getOperatorCode()); builder.append(StringConstants.PHONE_SEPARATOR);
		}
		builder.append(getPhoneNumber());
		return builder.toString();
	}
}
