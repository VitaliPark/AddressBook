package model.service;

import java.sql.Connection;

import constants.ExceptionMessages;
import exceptioin.DataAccessException;
import exceptioin.ServiceException;
import model.dao.PersonDao;
import model.dao.impementation.DefaultPersonDao;
import model.entity.Contact;

public class PersonService {
	private PersonDao personDao;

	public PersonService() {
		personDao = new DefaultPersonDao();
	}
	
	public int createPerson(Contact newContact, Connection connection) throws ServiceException{
		int newId;
		try {
			personDao.setConnection(connection);
			newId = personDao.createPerson(newContact.getPerson());
		} catch (DataAccessException e) {
			throw new ServiceException(ExceptionMessages.PERSON_CREATION_FAILED + e.getMessage());
		}
		return newId;
	}
	
	public void deletePerson(int idPerson, Connection connection) throws ServiceException{
		personDao.setConnection(connection);
		try {
			personDao.deletePerson(idPerson);
		} catch (DataAccessException e) {
			throw new ServiceException(ExceptionMessages.PERSON_DELITION_FAILED + e.getMessage());
		}
	}
	
	
}
