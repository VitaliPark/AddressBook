package model.dao.impementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import constants.SQLQuery;
import exceptioin.DataAccessException;
import model.dao.PhoneDao;
import model.entity.Phone;

public class DefaultPhoneDao implements PhoneDao{

	private Connection connection;
	
	@Override
	public void createPhone(Phone phone, int idPerson) throws DataAccessException{
		PreparedStatement createPhoneStatement = null;
		try {
			createPhoneStatement = connection.prepareStatement(SQLQuery.CREATE_PHONE.getValue());
			buildCreatePhoneStatement(phone, idPerson, createPhoneStatement);
			createPhoneStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage());
		} finally {
			closeStatement(createPhoneStatement);
		}
	}
	
	@Override
	public void deletePersonPhones(int idPerson) throws DataAccessException{
		PreparedStatement deletePersonPhonesStatement = null;
		try {
			deletePersonPhonesStatement = connection.prepareStatement(SQLQuery.DELETE_PERSON_PHONES.getValue());
			deletePersonPhonesStatement.setInt(1, idPerson);
			System.out.println(deletePersonPhonesStatement);
			deletePersonPhonesStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage());
		} finally {
			closeStatement(deletePersonPhonesStatement);
		}
	}

	private void closeStatement(PreparedStatement statement) {
		try {
			if(statement != null){
				statement.close();
			}
		} catch (SQLException e) {
			//TODO logger
		}
	}

	private void buildCreatePhoneStatement(Phone phone, int idPerson,
			PreparedStatement createPhoneStatement) throws SQLException {
		createPhoneStatement.setInt(1, idPerson);
		createPhoneStatement.setInt(2, phone.getCountryCode());
		createPhoneStatement.setInt(3, phone.getOperatorCode());
		createPhoneStatement.setString(4, phone.getPhoneNumber());
		createPhoneStatement.setString(5, phone.getPhoneType());
		createPhoneStatement.setString(6, phone.getComment());
	}

	@Override
	public Phone getPhone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updatePhone(Phone phone) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePhone(Phone phone) {
		// TODO Auto-generated method stub
		
	}
	


	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;
		
	}


}
