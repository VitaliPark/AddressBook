package model.dao.impementation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import constants.StringConstants;
import constants.database.PersonColumnNames;
import constants.database.SQLQuery;
import exceptioin.DataAccessException;
import model.dao.PersonDao;
import model.entity.Gender;
import model.entity.MaritalStatus;
import model.entity.Person;

public class DefaultPersonDao implements PersonDao{

	private Connection connection;
	private Logger LOGGER = Logger.getLogger(DefaultPersonDao.class);
	
	@Override
	public int createPerson(Person person) throws DataAccessException {
		PreparedStatement createPersonStatement = null;
		try {	
			createPersonStatement = connection.prepareStatement(SQLQuery.CREATE_PERSON.getValue(), Statement.RETURN_GENERATED_KEYS);
			buildCreataPersonStatement(person, createPersonStatement);
			createPersonStatement.executeUpdate();
			ResultSet generatedKeys = createPersonStatement.getGeneratedKeys();
			if(generatedKeys.next()){
				return generatedKeys.getInt(1);
			}
			else {
				throw new DataAccessException();
			}
		} catch (SQLException e){			
			LOGGER.error(e.getMessage());
			throw new DataAccessException(e.getMessage());
		} finally {
			closeStatement(createPersonStatement);
		}
	}
	
	@Override
	public void deletePerson(int idPerson) throws DataAccessException{
		PreparedStatement deletePersonStatement = null;
		try {
			deletePersonStatement = connection.prepareStatement(SQLQuery.DELETE_PERSON.getValue());
			deletePersonStatement.setInt(1, idPerson);
			deletePersonStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
			throw new DataAccessException(e.getMessage());
		} finally {
			closeStatement(deletePersonStatement);
		}
	}
	
	public String getEmail(int idPerson) throws DataAccessException{
		PreparedStatement emailStatement = null;
		String email = StringConstants.EMPTY_STRING;
		try {
			emailStatement = connection.prepareStatement(SQLQuery.GET_PERSON_EMAIL.getValue());
			emailStatement.setInt(1, idPerson);
			ResultSet set = emailStatement.executeQuery();
			if(set.next()){
				email = set.getString(PersonColumnNames.email);
			}
			return email;
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
			throw new DataAccessException(e.getMessage());
		}
	}
	
	@Override
	public Person getPerson(int personId) throws DataAccessException{
		PreparedStatement getPersonStatement = null;
		try {
			getPersonStatement = connection.prepareStatement(SQLQuery.GET_PERSON.getValue());
			getPersonStatement.setInt(1, personId);
			ResultSet set = getPersonStatement.executeQuery();
			return buildGetPersonResult(set);
			
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
			throw new DataAccessException(e.getMessage());
		} finally {
			closeStatement(getPersonStatement);
		}
	}
	
	@Override
	public int getPersonsCount() throws DataAccessException {
		PreparedStatement getPersonCount = null;
		int count = 0;
		try {
			getPersonCount = connection.prepareStatement(SQLQuery.GET_PERSONS_COUNT.getValue());
			ResultSet set = getPersonCount.executeQuery();
			if(set.next()){
				count = set.getInt(1);
			}
			return count;
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
			throw new DataAccessException(e.getMessage());
		} finally {
			closeStatement(getPersonCount);
		}
	}

	private Person buildGetPersonResult(ResultSet set) throws SQLException {
		Person person = new Person();
		if(set.next()){			
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
		}
		return person;
	}
	
	@Override
	public void updatePerson(Person person) throws DataAccessException {
		PreparedStatement updatePersonStatement = null;
		try {
			updatePersonStatement = connection.prepareStatement(SQLQuery.UPDATE_PERSON.getValue());
			buildUpdateStatement(person, updatePersonStatement);
			updatePersonStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
			throw new DataAccessException(e.getMessage());
		} finally {
			closeStatement(updatePersonStatement);
		}
		
	}

	public List<Person> getPersons(int first, int count) throws DataAccessException{	
		PreparedStatement getAllPersonStatement = null;
		try {
			getAllPersonStatement = connection.prepareStatement(SQLQuery.GET_PERSONS.getValue());
			getAllPersonStatement.setInt(1, first);
			getAllPersonStatement.setInt(2, count);
			ResultSet set = getAllPersonStatement.executeQuery();
			return buildAllPersonResult(set);
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
			throw new DataAccessException(e.getMessage());
		} finally {
			closeStatement(getAllPersonStatement);
		}
	}

	private List<Person> buildAllPersonResult(ResultSet set) throws SQLException {
		List<Person> result = new ArrayList<>();
		while(set.next()){
			MaritalStatus maritalStatus = MaritalStatus.getType(set.getString(PersonColumnNames.maritalStatus));
			Gender gender = Gender.getType(set.getString(PersonColumnNames.gender));
			Person person = new Person();
			setPersonDateOfBirth(set, person);
			person.setIdPerson(set.getInt(PersonColumnNames.idPerson));
			person.setFirstName(set.getString(PersonColumnNames.firstName));
			person.setSecondName(set.getString(PersonColumnNames.secondName));
			person.setPatronymicName(set.getString(PersonColumnNames.patronymicName));
			person.setMaritalStatus(maritalStatus);
			person.setGender(gender);
			person.setCitizenship(set.getString(PersonColumnNames.citizenship));
			person.setWebSite(set.getString(PersonColumnNames.website));
			person.setEmail(set.getString(PersonColumnNames.email));
			person.setWorkplace(set.getString(PersonColumnNames.workplace));
			result.add(person);
		}
		return result;
	}
	
	private void buildUpdateStatement(Person person,
			PreparedStatement updatePersonStatement) throws SQLException {
		Date date = new Date(person.getDateOfBirth().getTime());	
		updatePersonStatement.setString(1, person.getFirstName());
		updatePersonStatement.setString(2, person.getSecondName());
		updatePersonStatement.setString(3, person.getPatronymicName());
		updatePersonStatement.setDate(4, date);
		updatePersonStatement.setString(5, person.getMaritalStatus().getValue());
		updatePersonStatement.setString(6, person.getGender().getValue());
		updatePersonStatement.setString(7, person.getCitizenship());
		updatePersonStatement.setString(8, person.getWebSite());
		updatePersonStatement.setString(9, person.getEmail());
		updatePersonStatement.setString(10, person.getWorkplace());
		updatePersonStatement.setInt(11, person.getIdPerson());
	}
	

	private void setPersonDateOfBirth(ResultSet set, Person person) throws SQLException {
		java.sql.Date dateOfBirth = set.getDate(PersonColumnNames.dateOfBirth);
		java.util.Date date = null;
		if(dateOfBirth != null){
			date = new java.util.Date(set.getDate(PersonColumnNames.dateOfBirth).getTime());
		}
		person.setDateOfBirth(date);
	}

	private void closeStatement(PreparedStatement createPersonStatement) {
		try {
			if(createPersonStatement != null){
				createPersonStatement.close();
			}
		} catch (SQLException e) {LOGGER.error(e.getMessage());}
	}
	

	private void buildCreataPersonStatement(Person person,
			PreparedStatement createPersonStatement) throws SQLException {
		Date date = new Date(person.getDateOfBirth().getTime());	
		createPersonStatement.setString(1, person.getFirstName());
		createPersonStatement.setString(2, person.getSecondName());
		createPersonStatement.setString(3, person.getPatronymicName());
		createPersonStatement.setDate(4, date);
		createPersonStatement.setString(5, person.getMaritalStatus().getValue());
		createPersonStatement.setString(6, person.getGender().getValue());
		createPersonStatement.setString(7, person.getCitizenship());
		createPersonStatement.setString(8, person.getWebSite());
		createPersonStatement.setString(9, person.getEmail());
		createPersonStatement.setString(10, person.getWorkplace());
	}

	public void setConnection(Connection connection){
		this.connection = connection;
	}

	@Override
	public List<Person> getPersonsByDate(java.util.Date date)
			throws DataAccessException {
		PreparedStatement getPersonsStatement = null;
		try {
			getPersonsStatement = connection.prepareStatement(SQLQuery.GET_PERSON_INFO_BY.getValue());
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			int month = calendar.get(Calendar.MONTH);
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			getPersonsStatement.setInt(1, month+1);
			getPersonsStatement.setInt(2, day);
			ResultSet set = getPersonsStatement.executeQuery();
			return buildAllPersonResult(set);
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
			throw new DataAccessException(e.getMessage());
		} finally {
			closeStatement(getPersonsStatement);
		}
		
	}
}
