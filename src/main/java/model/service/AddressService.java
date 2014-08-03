package model.service;

import java.sql.Connection;

import constants.ExceptionMessages;
import exceptioin.DataAccessException;
import exceptioin.ServiceException;
import model.dao.AddressDao;
import model.dao.impementation.DefaultAddressDao;
import model.entity.Address;
import model.entity.Contact;

public class AddressService {

	private AddressDao addressDao;

	public AddressService() {
		addressDao = new DefaultAddressDao();
	}
	
	public void addAddress(Contact newContact, Connection connection) throws ServiceException{
		addressDao.setConnection(connection);
		try {
			addressDao.createAddress(newContact.getAddress(), newContact.getPerson().getIdPerson());
		} catch (DataAccessException e) {
			throw new ServiceException(ExceptionMessages.ADDRESS_CREATION_FAILED + e.getMessage());
		}
	}
	
	public void deleteAddressByPersonId(int idPerson, Connection connection) throws ServiceException{
		addressDao.setConnection(connection);
		try {
			addressDao.deletePersonAdress(idPerson);
		} catch (DataAccessException e) {
			throw new ServiceException(ExceptionMessages.ADDRESS_DELITION_FAILED + e.getMessage());
		}
	}
	
	public Address getAddressByPersonId(int idPerson, Connection connection) throws ServiceException{
		addressDao.setConnection(connection);
		try {
			Address address = addressDao.getPersonAddress(idPerson);
			return address;
		} catch (DataAccessException e) {
			throw new ServiceException(ExceptionMessages.ADDRESS_READ_FAILED + e.getMessage());
		}
	}
		
}
