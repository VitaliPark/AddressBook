package model.dao.impementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import constants.AddressColumnNames;
import constants.SQLQuery;
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
			System.out.println(deletePersonAddressStatement);
			deletePersonAddressStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e.getMessage());
		} finally {
			closeStatement(deletePersonAddressStatement);
		}	
	}
	
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
	
	private Address buildGetPersonAddress(ResultSet set ) throws SQLException{
		Address address = new Address();
		if(set.next()){
			address.setCountry(set.getString(AddressColumnNames.country));
			address.setCity(set.getString(AddressColumnNames.city));
			address.setStreet(set.getString(AddressColumnNames.street));
			address.setHouseNumber(set.getInt(AddressColumnNames.houseNumber));
			address.setAppartement(set.getInt(AddressColumnNames.apartment));
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
		createAddressStatement.setInt(6, address.getAppartement());
		createAddressStatement.setString(7, address.getIndex());
		System.out.println(createAddressStatement);
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
	public void updateAddress(Address address) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;
	}


	

}
