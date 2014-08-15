package model.dao.impementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import constants.database.PhoneColumnNames;
import constants.database.SQLQuery;
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
	public List<Phone> getPersonPhones(int idPerson) throws DataAccessException {
		PreparedStatement getPhoneStatement = null;
		try {
			getPhoneStatement = connection.prepareStatement(SQLQuery.GET_PERSON_PHONES.getValue());
			getPhoneStatement.setInt(1, idPerson);
			ResultSet set = getPhoneStatement.executeQuery();
			return buildGetPersonPhone(set);
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage());
		} finally {
			closeStatement(getPhoneStatement);
		}		
	}

	private List<Phone> buildGetPersonPhone(ResultSet set) throws SQLException {		
		List<Phone> phones = new ArrayList<>();
		while(set.next()){
			Phone phone = new Phone();
			phone.setCountryCode(set.getInt(PhoneColumnNames.countryCode));
			phone.setOperatorCode(set.getInt(PhoneColumnNames.operatorCode));
			phone.setPhoneNumber(set.getString(PhoneColumnNames.phoneNumber));
			phone.setPhoneType(set.getString(PhoneColumnNames.phoneType));
			phone.setComment(set.getString(PhoneColumnNames.comment));
			phone.setPhoneId(set.getInt(PhoneColumnNames.idPhone));
			phones.add(phone);
		}
		return phones;
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
