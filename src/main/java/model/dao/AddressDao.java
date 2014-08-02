package model.dao;

import exceptioin.DataAccessException;
import model.entity.Address;

public interface AddressDao extends HasConnection{

	public void createAddress(Address address, int idPerson) throws DataAccessException;
	
	public void deleteAddress(Address address);
	
	public Address getAddress();
	
	public void updateAddress(Address address);
	
}
