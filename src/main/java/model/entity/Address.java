package model.entity;

import constants.StringConstants;

public class Address {
	private int idAddress;
	private String country;
	private String city;
	private String street;
	private int houseNumber;
	private int appartement;
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
	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}
	public int getAppartement() {
		return appartement;
	}
	public void setAppartement(int appartement) {
		this.appartement = appartement;
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
		if(houseNumber != 0) {
			builder.append(houseNumber); builder.append(StringConstants.COMMA);
		}
		if(appartement != 0){
			builder.append(appartement); builder.append(StringConstants.COMMA);
		}
		builder.append(getIndex());
		return builder.toString();
	}
}
