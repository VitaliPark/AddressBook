package model.dao;

import exceptioin.DataAccessException;
import model.entity.Address;

public interface AddressDao extends HasConnection{

	public void createAddress(Address address, int idPerson) throws DataAccessException;
	
	public void updateAddress(Address address, int personId) throws DataAccessException;
	
	public void deletePersonAdress(int idPerson) throws DataAccessException;
	
	public Address getPersonAddress(int idPerson) throws DataAccessException;
}
