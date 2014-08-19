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
import exceptioin.DataAccessException;
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
	public List<Contact> search(SearchRequest searchRequest, int first, int maxCount)throws DataAccessException {
		PreparedStatement searchStatement = null;
		String operatorType = searchRequest.getOperatortype();
		String searchQuery = setDateTypeOperator(SQLQuery.SEARCH_REQUEST.getValue(), operatorType);
		try {
			searchStatement = connection.prepareStatement(searchQuery);
			buildSearchStatement(searchRequest, searchStatement, first, maxCount);
			ResultSet set = searchStatement.executeQuery();
			return buildResult(set);
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage());
		} finally {
			closeStatement(searchStatement);
		}
	}
	

	@Override
	public int getCountOnRequest(SearchRequest searchRequest) throws DataAccessException{
		PreparedStatement countStatement = null;
		String operatorType = searchRequest.getOperatortype();
		String searchQuery = setDateTypeOperator(SQLQuery.SEARCH_REQUEST_COUNT.getValue(), operatorType);
		int count = 0;
		try {
			countStatement = connection.prepareStatement(searchQuery);
			setMainFields(searchRequest, countStatement);
			ResultSet set = countStatement.executeQuery();
			if(set.next()){
				count = Integer.parseInt(set.getString(1));
			}
			return count;
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage());
		} finally {
			closeStatement(countStatement);
		}
	}

	private void buildSearchStatement(SearchRequest searchRequest,
			PreparedStatement searchStatement, int first, int count) throws SQLException {
		int i = 0;
		i = setMainFields(searchRequest, searchStatement);
		searchStatement.setInt(++i, first);
		searchStatement.setInt(++i, count);
	}

	private int setMainFields(SearchRequest searchRequest,
			PreparedStatement searchStatement) throws SQLException {
		int i;
		for(i = 0; i < searchRequest.getParams().size(); i++){
			SearchPair pair = searchRequest.getPair(i);
			if(i != 4){
				searchStatement.setString(i+1, "%" + pair.getValue() + "%");
			}
			else {
				searchStatement.setString(i+1, pair.getValue());
			}
		}
		return i;
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
	
	private void closeStatement(PreparedStatement createPersonStatement) {
		try {
			if(createPersonStatement != null){
				createPersonStatement.close();
			}
		} catch (SQLException e) {}
	}
}
