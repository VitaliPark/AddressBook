package model.service;

import java.sql.Connection;
import java.util.List;

import constants.ExceptionMessages;
import exceptioin.DataAccessException;
import exceptioin.ServiceException;
import model.dao.PhoneDao;
import model.dao.impementation.DefaultPhoneDao;
import model.entity.Contact;
import model.entity.Phone;

public class PhoneService {

	private PhoneDao phoneDao;

	public PhoneService() {
		phoneDao = new DefaultPhoneDao();
	}
	
	public void addAllPhones(Contact newContact, Connection connection) throws ServiceException{
		List<Phone> phones = newContact.getPhones();
		phoneDao.setConnection(connection);	
		for (Phone phone : phones) {
			try {
				phoneDao.createPhone(phone, newContact.getPerson().getIdPerson());
			} catch (DataAccessException e) {
				throw new ServiceException(ExceptionMessages.PHONE_CREATION_FAILED + e.getMessage());
			}
		}
	}
	
	public void deleteAllPersonPhones(int idPerson, Connection connection) throws ServiceException{
		phoneDao.setConnection(connection);
		try {
			phoneDao.deletePersonPhones(idPerson);
		} catch (DataAccessException e) {
			throw new ServiceException(ExceptionMessages.PHONE_DELETION_FAILED + e.getMessage());
		}
	}
	
	public List<Phone> getAllPersonPhones(int personId, Connection connection) throws ServiceException{
		phoneDao.setConnection(connection);
		try {		
		  return phoneDao.getPersonPhones(personId);
		} catch (DataAccessException e) {
			throw new ServiceException(ExceptionMessages.PHONE_READ_FAILED + e.getMessage());
		}	
	}
}