package model.dao.impementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import constants.database.AddressColumnNames;
import constants.database.SQLQuery;
import exceptioin.DataAccessException;
import model.dao.AddressDao;
import model.entity.Address;

public class DefaultAddressDao implements AddressDao{

	private Connection connection;
	
	@Override
	public void createAddress(Address address, int idPerson) throws DataAccessException{
		PreparedStatement createAddressStatement = null;
		try  {
			createAddressStatement = connection.prepareStatement(SQLQuery.CREATE_ADDRESS.getValue());
			buildCreateAddressStatement(address, idPerson, createAddressStatement);
			createAddressStatement.executeUpdate();
		} catch (SQLException e){
				throw new DataAccessException(e.getMessage());
		} finally {
			closeStatement(createAddressStatement);
		}
	}
	
	@Override
	public void deletePersonAdress(int idPerson) throws DataAccessException{
		PreparedStatement deletePersonAddressStatement = null;
		try {
			deletePersonAddressStatement = connection.prepareStatement(SQLQuery.DELETE_PERSON_ADDRESS.getValue());
			deletePersonAddressStatement.setInt(1, idPerson);
			deletePersonAddressStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage());
		} finally {
			closeStatement(deletePersonAddressStatement);
		}	
	}
	
	@Override
	public Address getPersonAddress(int idPerson) throws DataAccessException{
		PreparedStatement getPersonAddressStatement = null;
		try {
			getPersonAddressStatement = connection.prepareStatement(SQLQuery.GET_PERSON_ADDRESS.getValue());
			getPersonAddressStatement.setInt(1, idPerson);
			ResultSet set = getPersonAddressStatement.executeQuery();
			return buildGetPersonAddress(set);
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage());
		} finally {
			closeStatement(getPersonAddressStatement);
		}
	}
	
	@Override
	public void updateAddress(Address address, int personid) throws DataAccessException{
		PreparedStatement updateAddressStatement = null;
		try {
			updateAddressStatement = connection.prepareStatement(SQLQuery.UPDATE_ADDRESS.getValue());
			buildUpdateStetement(address, personid,updateAddressStatement);
			updateAddressStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage());
		} finally {
			closeStatement(updateAddressStatement);
		}
	}

	private void buildUpdateStetement(Address address, int idPerson, 
			PreparedStatement updateAddressStatement) throws SQLException {
		updateAddressStatement.setString(1, address.getCountry());
		updateAddressStatement.setString(2, address.getCity());
		updateAddressStatement.setString(3, address.getStreet());
		updateAddressStatement.setInt(4, address.getHouseNumber());
		updateAddressStatement.setInt(5, address.getApartment());
		updateAddressStatement.setString(6, address.getIndex());
		updateAddressStatement.setInt(7, idPerson);
	}
	
	private Address buildGetPersonAddress(ResultSet set ) throws SQLException{
		Address address = new Address();
		if(set.next()){
			address.setCountry(set.getString(AddressColumnNames.country));
			address.setCity(set.getString(AddressColumnNames.city));
			address.setStreet(set.getString(AddressColumnNames.street));
			address.setHouseNumber(set.getInt(AddressColumnNames.houseNumber));
			address.setApartment(set.getInt(AddressColumnNames.apartment));
			address.setIndex(set.getString(AddressColumnNames.postIndex));
		}
		return address;		
	}
		


	private void closeStatement(PreparedStatement createAddressStatement) {
		if(createAddressStatement != null){
			try {
				createAddressStatement.close();
			} catch (SQLException e) {}
		}
	}


	private void buildCreateAddressStatement(Address address, int idPerson,
			PreparedStatement createAddressStatement) throws SQLException {
		createAddressStatement.setInt(1, idPerson);
		createAddressStatement.setString(2, address.getCountry());
		createAddressStatement.setString(3, address.getCity());
		createAddressStatement.setString(4, address.getStreet());
		createAddressStatement.setInt(5, address.getHouseNumber());
		createAddressStatement.setInt(6, address.getApartment());
		createAddressStatement.setString(7, address.getIndex());
	}


	@Override
	public void deleteAddress(Address address) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Address getAddress() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;
	}


	

}
