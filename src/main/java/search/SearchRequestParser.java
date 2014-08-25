package search;

import javax.servlet.http.HttpServletRequest;

import constants.database.AddressColumnNames;
import constants.database.PersonColumnNames;
import controller.FormParameter;

public enum SearchRequestParser {
	INSTANCE;
	
	public SearchRequest parseSearchRequest(HttpServletRequest request){
		SearchRequest searchRequest = new SearchRequest();
		//do not change the order
		addFirstNameToSearchRequest(request, searchRequest);
		addSecondNameToSearchRequest(request, searchRequest);
		addPatronymicNameToSearchRequest(request, searchRequest);
		addCitizenshipToSearchRequest(request, searchRequest);
		addDateOfBirthToSearchRequest(request, searchRequest);
		searchRequest.setOperatortype(getOperatorType(request));
		addGenderToSearchRequest(request, searchRequest);
		addMaritalStatusToSearchRequest(request, searchRequest);
		addCountryToSearchRequest(request, searchRequest);
		addCityToSearchRequest(request, searchRequest);
		addStreetToSearchRequest(request, searchRequest);
		addHouseNumberToSearchRequest(request, searchRequest);
		addApartmentToSearchRequest(request, searchRequest);
		addPostCodeToSearchRequest(request, searchRequest);
		//
		return searchRequest;
	}

//	private void addOperatorTypeToSearchRequest(HttpServletRequest request,
//			SearchRequest searchRequest) {
//		searchRequest.addParam(SearchCriteria.OPERATOR_TYPE, getOperatorType(request));
//	}

	private void addDateOfBirthToSearchRequest(HttpServletRequest request,
			SearchRequest searchRequest) {
		searchRequest.addParam(FormParameter.DATE_OF_BIRTH, getDateOfBirth(request));
	}

	private void addPostCodeToSearchRequest(HttpServletRequest request,
			SearchRequest searchRequest) {
		searchRequest.addParam(FormParameter.POST_CODE, getPostCode(request));
	}

	private void addApartmentToSearchRequest(HttpServletRequest request,
			SearchRequest searchRequest) {
		searchRequest.addParam(FormParameter.APARTMENT, getApartment(request));
	}

	private void addHouseNumberToSearchRequest(HttpServletRequest request,
			SearchRequest searchRequest) {
		searchRequest.addParam(FormParameter.HOUSE_NUMBER, getHouseNumber(request));
	}

	private void addStreetToSearchRequest(HttpServletRequest request,
			SearchRequest searchRequest) {
		searchRequest.addParam(FormParameter.STREET, getStreet(request));
	}

	private void addCityToSearchRequest(HttpServletRequest request,
			SearchRequest searchRequest) {
		searchRequest.addParam(FormParameter.CITY, getCity(request));
	}

	private void addCountryToSearchRequest(HttpServletRequest request,
			SearchRequest searchRequest) {
		searchRequest.addParam(FormParameter.COUNTRY, getCountry(request));
	}

	private void addMaritalStatusToSearchRequest(HttpServletRequest request,
			SearchRequest searchRequest) {
		searchRequest.addParam(FormParameter.MARITAL_STATUS, getMaritalStatus(request));
	}

	private void addCitizenshipToSearchRequest(HttpServletRequest request,
			SearchRequest searchRequest) {
		searchRequest.addParam(FormParameter.CITIZENSHIP, getCitizenship(request));
	}

	private void addGenderToSearchRequest(HttpServletRequest request,
			SearchRequest searchRequest) {
		searchRequest.addParam(FormParameter.GENDER, getGender(request));
	}

	private void addPatronymicNameToSearchRequest(HttpServletRequest request,
			SearchRequest searchRequest) {
		searchRequest.addParam(FormParameter.PATRONYMIC_NAME, getPatronymictName(request));
	}

	private void addSecondNameToSearchRequest(HttpServletRequest request,
			SearchRequest searchRequest) {
		searchRequest.addParam(FormParameter.SECOND_NAME, getSecondName(request));
	}

	private void addFirstNameToSearchRequest(HttpServletRequest request,
			SearchRequest searchRequest) {
		searchRequest.addParam(FormParameter.FIRST_NAME, getFirstName(request));
	}
	
	private String getFirstName(HttpServletRequest request){
		return request.getParameter(PersonColumnNames.firstName);
	}
	
	private String getSecondName(HttpServletRequest request){
		return request.getParameter(PersonColumnNames.secondName);
	}
	
	private String getPatronymictName(HttpServletRequest request){
		return request.getParameter(PersonColumnNames.patronymicName);
	}
	
	private String getDateOfBirth(HttpServletRequest request){
		return request.getParameter(PersonColumnNames.dateOfBirth);
	}
	
	private String getOperatorType(HttpServletRequest request){
		return request.getParameter("operatorType");
	}
	
	private String getGender(HttpServletRequest request){
		return request.getParameter(PersonColumnNames.gender);
	}
	
	private String getCitizenship(HttpServletRequest request){
		return request.getParameter(PersonColumnNames.citizenship);
	}
	
	private String getMaritalStatus(HttpServletRequest request){
		return request.getParameter(PersonColumnNames.maritalStatus);
	}
	
	private String getCountry(HttpServletRequest request){
		return request.getParameter(AddressColumnNames.country);
	}
	
	private String getCity(HttpServletRequest request){
		return request.getParameter(AddressColumnNames.city);
	}
	
	private String getStreet(HttpServletRequest request){
		return request.getParameter(AddressColumnNames.street);
	}
	
	private String getHouseNumber(HttpServletRequest request){
		return request.getParameter(AddressColumnNames.houseNumber);
	}
	
	private String getApartment(HttpServletRequest request){
		return request.getParameter(AddressColumnNames.apartment);
	}
	
	private String getPostCode(HttpServletRequest request){
		return request.getParameter(AddressColumnNames.postIndex);
	}
}
