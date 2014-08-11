package model.dao.impementation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import constants.PersonColumnNames;
import constants.SQLQuery;
import exceptioin.DataAccessException;
import model.dao.PersonDao;
import model.entity.Person;

public class DefaultPersonDao implements PersonDao{

	private Connection connection;
	//private String query = "SELECT * FROM contacts.person WHERE (idPerson LIKE ? OR ? IS NULL) AND (secondName LIKE ? OR ? IS NULL)";
	
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
			throw new DataAccessException(e.getMessage());
		} finally {
			closeStatement(deletePersonStatement);
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
			throw new DataAccessException(e.getMessage());
		} finally {
			closeStatement(getPersonStatement);
		}
		
	}

	private Person buildGetPersonResult(ResultSet set) throws SQLException {
		Person person = new Person();
		if(set.next()){			
			person.setIdPerson(set.getInt(PersonColumnNames.idPerson));
			person.setFirstName(set.getString(PersonColumnNames.firstName));
			person.setSecondName(set.getString(PersonColumnNames.secondName));
			person.setPatronymicName(set.getString(PersonColumnNames.patronymicName));
			java.util.Date date = new java.util.Date(set.getDate(PersonColumnNames.dateOfBirth).getTime());
			person.setDateOfBirth(date);
			person.setMaritalStatus(set.getString(PersonColumnNames.maritalStatus));
			person.setCitizenship(set.getString(PersonColumnNames.citizenship));
			person.setWebSite(set.getString(PersonColumnNames.website));
			person.setEmail(set.getString(PersonColumnNames.email));
			person.setWorkplace(set.getString(PersonColumnNames.workplace));
		}
		return person;
	}
	
	public List<Person> getAllPersons(int first, int count) throws DataAccessException{	
		PreparedStatement getAllPersonStatement = null;
		try {
			getAllPersonStatement = connection.prepareStatement(SQLQuery.GET_ALL_PERSONS.getValue());
			getAllPersonStatement.setInt(1, first);
			getAllPersonStatement.setInt(2, count);
			ResultSet set = getAllPersonStatement.executeQuery();
			return buildAllPersonResult(set);
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage());
		} finally {
			closeStatement(getAllPersonStatement);
		}
	}

	private List<Person> buildAllPersonResult(ResultSet set) throws SQLException {
		List<Person> result = new ArrayList<>();
		while(set.next()){
			Person person = new Person();
			person.setIdPerson(set.getInt(PersonColumnNames.idPerson));
			person.setFirstName(set.getString(PersonColumnNames.firstName));
			person.setSecondName(set.getString(PersonColumnNames.secondName));
			person.setPatronymicName(set.getString(PersonColumnNames.patronymicName));
			java.util.Date date = new java.util.Date(set.getDate(PersonColumnNames.dateOfBirth).getTime());
			person.setDateOfBirth(date);
			person.setMaritalStatus(set.getString(PersonColumnNames.maritalStatus));
			person.setCitizenship(set.getString(PersonColumnNames.citizenship));
			person.setWebSite(set.getString(PersonColumnNames.website));
			person.setEmail(set.getString(PersonColumnNames.email));
			person.setWorkplace(set.getString(PersonColumnNames.workplace));
			result.add(person);
		}
		return result;
	}


	private void closeStatement(PreparedStatement createPersonStatement) {
		try {
			if(createPersonStatement != null){
				createPersonStatement.close();
			}
		} catch (SQLException e) {}
	}
	

	private void buildCreataPersonStatement(Person person,
			PreparedStatement createPersonStatement) throws SQLException {
		Date date = new Date(person.getDateOfBirth().getTime());	
		createPersonStatement.setString(1, person.getFirstName());
		createPersonStatement.setString(2, person.getSecondName());
		createPersonStatement.setString(3, person.getPatronymicName());
		createPersonStatement.setDate(4, date);
		createPersonStatement.setString(5, person.getMaritalStatus());
		createPersonStatement.setString(6, person.getCitizenship());
		createPersonStatement.setString(7, person.getWebSite());
		createPersonStatement.setString(8, person.getEmail());
		createPersonStatement.setString(9, person.getWorkplace());
	}





	@Override
	public void updatePerson(Person person) {
		// TODO Auto-generated method stub
		
	}
	
	public void setConnection(Connection connection){
		this.connection = connection;
	}

}
