package model.service;

import java.sql.Connection;
import java.util.List;

import constants.ExceptionMessages;
import exceptioin.DataAccessException;
import exceptioin.ServiceException;
import model.dao.PersonDao;
import model.dao.impementation.DefaultPersonDao;
import model.entity.Contact;
import model.entity.Person;

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
	
	public Person getPerson(int personId, Connection connection) throws ServiceException{
		personDao.setConnection(connection);
		try {
			return personDao.getPerson(personId);
		} catch (DataAccessException e) {
			throw new ServiceException(ExceptionMessages.PERSON_READ_FAILED + e.getMessage());
		}
	}
	
	public List<Person> getAllPersons(int first, int count, Connection connection) throws ServiceException{
		personDao.setConnection(connection);
		try {
			return personDao.getAllPersons(first, count);
		} catch (DataAccessException e) {
			throw new ServiceException(ExceptionMessages.PERSON_READ_FAILED + e.getMessage());
		}
	}
	
	
}
