package model.entity;

import constants.DefaultValues;
import constants.StringConstants;

public class Address {
	private int idAddress;
	private String country;
	private String city;
	private String street;
	private int houseNumber;
	private int apartment;
	private String index;
	
	public int getIdAddress() {
		return idAddress;
	}
	public void setIdAddress(int idAddress) {
		this.idAddress = idAddress;
	}
	public String getCountry() {
		return (country != null) ? country : StringConstants.EMPTY_STRING;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return (city != null) ? city : StringConstants.EMPTY_STRING;
	}
	public void setCity(String city) {
		this.city = city;
		//this.city = (city != null) ? city : StringConstants.EMPTY_STRING;
	}
	public String getStreet() {
		return (street != null) ? street : StringConstants.EMPTY_STRING;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public int getHouseNumber() {
		return houseNumber;
	}
	public String getHouseNumberAsString() {
		return (houseNumber == DefaultValues.defaultNumericValue) ? "" : Integer.toString(houseNumber) ;
	}
	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}
	public int getApartment() {
		return apartment;
	}
	
	public String getApartmentAsString() {
		return (apartment == DefaultValues.defaultNumericValue) ? "" : Integer.toString(apartment);
	}
	public void setApartment(int apartment) {
		this.apartment = apartment;
	}
	public String getIndex() {
		return (index != null) ? index : StringConstants.EMPTY_STRING;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(getCountry()); builder.append(StringConstants.COMMA);
		builder.append(getCity()); builder.append(StringConstants.COMMA);
		builder.append(getStreet()); builder.append(StringConstants.COMMA);
		if(houseNumber != -1) {
			builder.append(houseNumber); builder.append(StringConstants.COMMA);
		}
		if(apartment != -1){
			builder.append(apartment); builder.append(StringConstants.COMMA);
		}
		builder.append(getIndex());
		return builder.toString();
	}
}
