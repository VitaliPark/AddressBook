package model;

import constants.database.AddressColumnNames;
import constants.database.PersonColumnNames;

public enum SearchCriteria {

	FIRST_NAME(PersonColumnNames.firstName), 
	SECOND_NAME(PersonColumnNames.secondName),
	PATRONYMIC_NAME(PersonColumnNames.patronymicName),
	DATE_OF_BIRTH(PersonColumnNames.dateOfBirth),
	OPERATOR_TYPE("operatorType"),
	GENDER(PersonColumnNames.gender),
	CITIZENSHIP(PersonColumnNames.citizenship), 
	MARITAL_STATUS(PersonColumnNames.maritalStatus),
	COUNTRY(AddressColumnNames.country), 
	CITY(AddressColumnNames.city), 
	STREET(AddressColumnNames.street),
    HOUSE_NUMBER(AddressColumnNames.houseNumber),
    APARTMENT(AddressColumnNames.apartment),
    POST_CODE(AddressColumnNames.postIndex),
    
    WEB_SITE(PersonColumnNames.website),
    EMAIL(PersonColumnNames.email),
    WORKPLACE(PersonColumnNames.workplace);
	
	private String value;

	private SearchCriteria(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
