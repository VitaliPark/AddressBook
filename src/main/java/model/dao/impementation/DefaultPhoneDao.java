package model.dao.impementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import constants.database.PhoneColumnNames;
import constants.database.SQLQuery;
import exceptioin.DataAccessException;
import model.dao.PhoneDao;
import model.entity.Phone;

public class DefaultPhoneDao implements PhoneDao{

	private Connection connection;
	private Logger LOGGER = Logger.getLogger(DefaultPhoneDao.class);
	
	@Override
	public void createPhone(Phone phone, int idPerson) throws DataAccessException{
		PreparedStatement createPhoneStatement = null;
		try {
			createPhoneStatement = connection.prepareStatement(SQLQuery.CREATE_PHONE.getValue());
			buildCreatePhoneStatement(phone, idPerson, createPhoneStatement);
			createPhoneStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
			throw new DataAccessException(e.getMessage());
		} finally {
			closeStatement(createPhoneStatement);
		}
	}
	
	@Override
	public void updatePhone(Phone phone) throws DataAccessException {
		PreparedStatement updatePhoneStatement= null;
		try {
			updatePhoneStatement = connection.prepareStatement(SQLQuery.UPDATE_PHONE.getValue());
			buildUpdateStatement(phone, updatePhoneStatement);
			updatePhoneStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
			throw new DataAccessException(e.getMessage());
		} finally {
			closeStatement(updatePhoneStatement);
		}
	}
	
	@Override
	public void deletePhone(int phoneId) throws DataAccessException{
		PreparedStatement deletePhoneStatement = null;
		try {
			deletePhoneStatement = connection.prepareStatement(SQLQuery.DELETE_PHONE.getValue());
			deletePhoneStatement.setInt(1, phoneId);
			deletePhoneStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
			throw new DataAccessException(e.getMessage());
		} finally {
			closeStatement(deletePhoneStatement);
		}
	}

	private void buildUpdateStatement(Phone phone,PreparedStatement updatePhoneStatement) throws SQLException {
		updatePhoneStatement.setInt(1, phone.getCountryCode());
		updatePhoneStatement.setInt(2, phone.getOperatorCode());
		updatePhoneStatement.setString(3, phone.getPhoneNumber());
		updatePhoneStatement.setString(4, phone.getPhoneType());
		updatePhoneStatement.setString(5, phone.getComment());
		updatePhoneStatement.setInt(6, phone.getPhoneId());
	}

	
	@Override
	public void deletePersonPhones(int idPerson) throws DataAccessException{
		PreparedStatement deletePersonPhonesStatement = null;
		try {
			deletePersonPhonesStatement = connection.prepareStatement(SQLQuery.DELETE_PERSON_PHONES.getValue());
			deletePersonPhonesStatement.setInt(1, idPerson);
			deletePersonPhonesStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
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
			LOGGER.error(e.getMessage());
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
			LOGGER.error(e.getMessage());
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
	public void setConnection(Connection connection) {
		this.connection = connection;
		
	}


}
