package model.dao.impementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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


	private void closeStatement(PreparedStatement createAddressStatement) {
		if(createAddressStatement != null){
			try {
				System.out.println("closing");
				createAddressStatement.close();
				System.out.println("closed");
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
