package model.dao.impementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import constants.database.AddressColumnNames;
import constants.database.PersonColumnNames;
import constants.database.SQLQuery;
import model.SearchPair;
import model.SearchRequest;
import model.dao.SearchDao;
import model.entity.Address;
import model.entity.Contact;
import model.entity.Gender;
import model.entity.MaritalStatus;
import model.entity.Person;

public class DefaultSearchDao implements SearchDao{

	private Connection connection;
	
	@Override
	public List<Contact> search(SearchRequest searchRequest, int first, int maxCount) {
		PreparedStatement searchStatement = null;
		String operatorType = searchRequest.getOperatortype();
		String searchQuery = setDateTypeOperator(SQLQuery.SEARCH_REQUEST.getValue(), operatorType);
		try {
			searchStatement = connection.prepareStatement(searchQuery);
			buildSearchStatement(searchRequest, searchStatement, first, maxCount);
			ResultSet set = searchStatement.executeQuery();
			return buildResult(set);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void buildSearchStatement(SearchRequest searchRequest,
			PreparedStatement searchStatement, int first, int count) throws SQLException {
		int i = 0;
		for(i = 0; i < searchRequest.getParams().size(); i++){
			SearchPair pair = searchRequest.getPair(i);
			if(i != 4){
				searchStatement.setString(i+1, "%" + pair.getValue() + "%");
			}
			else {
				searchStatement.setString(i+1, pair.getValue());
			}
		}
		searchStatement.setInt(++i, first);
		searchStatement.setInt(++i, count);
		System.out.println(searchStatement.toString());
	}
	
	private String setDateTypeOperator(String searchQuery, String operator){
		return searchQuery.replace("dateOperator", operator);
	}
	
	private List<Contact> buildResult(ResultSet set) throws SQLException{
		List<Contact> result = new ArrayList<>();
		while(set.next()){
			Contact contact = new Contact();
			Person person = buildPerson(set);
			Address address = buildAddress(set);
			contact.setPerson(person);
			contact.setAddress(address);
			result.add(contact);
		}
		return result;
	}

	private Address buildAddress(ResultSet set) throws SQLException {
		Address address = new Address();
		address.setCountry(set.getString(AddressColumnNames.country));
		address.setCity(set.getString(AddressColumnNames.city));
		address.setStreet(set.getString(AddressColumnNames.street));
		address.setHouseNumber(set.getInt(AddressColumnNames.houseNumber));
		address.setApartment(set.getInt(AddressColumnNames.apartment));
		address.setIndex(set.getString(AddressColumnNames.postIndex));
		
		return address;
	}

	private Person buildPerson(ResultSet set) throws SQLException {
		Person person = new Person();
		MaritalStatus maritalStatus = MaritalStatus.getType(set.getString(PersonColumnNames.maritalStatus));
		Gender gender = Gender.getType(set.getString(PersonColumnNames.gender)); 
		person.setIdPerson(set.getInt(PersonColumnNames.idPerson));
		person.setFirstName(set.getString(PersonColumnNames.firstName));
		person.setSecondName(set.getString(PersonColumnNames.secondName));
		person.setPatronymicName(set.getString(PersonColumnNames.patronymicName));
		setPersonDateOfBirth(set, person);
		person.setMaritalStatus(maritalStatus);
		person.setGender(gender);
		person.setCitizenship(set.getString(PersonColumnNames.citizenship));
		person.setWebSite(set.getString(PersonColumnNames.website));
		person.setEmail(set.getString(PersonColumnNames.email));
		person.setWorkplace(set.getString(PersonColumnNames.workplace));
	
		return person;
	}
	
	private void setPersonDateOfBirth(ResultSet set, Person person) throws SQLException {
		java.sql.Date dateOfBirth = set.getDate(PersonColumnNames.dateOfBirth);
		java.util.Date date = null;
		if(dateOfBirth != null){
			date = new java.util.Date(set.getDate(PersonColumnNames.dateOfBirth).getTime());
		}
		person.setDateOfBirth(date);
	}
	
	

	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	


}
