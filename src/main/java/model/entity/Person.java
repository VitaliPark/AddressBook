package model.entity;

import java.util.Date;

import constants.StringConstants;

public class Person {
	
	private int idPerson;
	private String firstName;
	private String patronymicName;
	private String secondName;
	private Date dateOfBirth;
	private MaritalStatus maritalStatus;
	private String citizenship;
	private Gender gender;
	private String webSite;
	private String email;
	private String workplace;
	
	public int getIdPerson() {
		return idPerson;
	}
	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	}
	public String getFirstName() {
		return (firstName != null) ? firstName : StringConstants.EMPTY_STRING;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getPatronymicName() {
		return (patronymicName != null) ? patronymicName : StringConstants.EMPTY_STRING;
	}
	public void setPatronymicName(String patronymicName) {
		this.patronymicName = patronymicName;
	}
	public String getSecondName() {
		return (secondName != null) ? secondName : StringConstants.EMPTY_STRING;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getCitizenship() {
		return citizenship;
	}
	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}
	public String getWebSite() {
		return webSite;
	}
	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWorkplace() {
		return workplace;
	}
	public void setWorkplace(String workplace) {
		this.workplace = workplace;
	}
	
	public String getFullName(){
		StringBuilder builder = new StringBuilder();
		builder.append(getSecondName()); builder.append(StringConstants.SPACE);
		builder.append(getFirstName()); builder.append(StringConstants.SPACE);
		builder.append(getPatronymicName()); builder.append(StringConstants.SPACE);
		return builder.toString();
	}
}
