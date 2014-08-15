package model.entity;

import constants.StringConstants;

public class Phone {
	
	private int phoneId;
	private int countryCode;
	private int operatorCode;
	private String phoneNumber;
	private String phoneType;
	private String comment;
	
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
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPhoneType() {
		return phoneType;
	}
	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getFullPhone(){
		StringBuilder builder = new StringBuilder();
		builder.append(getCountryCode()); builder.append(StringConstants.PHONE_SEPARATOR);
		builder.append(getOperatorCode()); builder.append(StringConstants.PHONE_SEPARATOR);
		builder.append(getPhoneNumber());
		return builder.toString();
	}
}
