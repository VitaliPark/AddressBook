package model.dao.impementation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	public void deletePerson(Person person) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getPerson() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePerson(Person person) {
		// TODO Auto-generated method stub
		
	}
	
	public void setConnection(Connection connection){
		this.connection = connection;
	}
	


}
