package model.service;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import constants.ExceptionMessages;
import exceptioin.DataAccessException;
import exceptioin.ServiceException;
import model.dao.PersonDao;
import model.dao.impementation.DefaultPersonDao;
import model.entity.Contact;
import model.entity.Person;

public class PersonService {
	private PersonDao personDao;
	private Logger LOGGER = Logger.getLogger(PersonService.class);

	public PersonService() {
		personDao = new DefaultPersonDao();
	}
	
	public int createPerson(Contact newContact, Connection connection) throws ServiceException{
		int newId;
		try {
			personDao.setConnection(connection);
			newId = personDao.createPerson(newContact.getPerson());
		} catch (DataAccessException e) {
			LOGGER.error(ExceptionMessages.PERSON_CREATION_FAILED);
			throw new ServiceException(ExceptionMessages.PERSON_CREATION_FAILED + e.getMessage());
		}
		return newId;
	}
	
	public void deletePerson(int idPerson, Connection connection) throws ServiceException{
		personDao.setConnection(connection);
		try {
			personDao.deletePerson(idPerson);
		} catch (DataAccessException e) {
			LOGGER.error(ExceptionMessages.PERSON_DELITION_FAILED);
			throw new ServiceException(ExceptionMessages.PERSON_DELITION_FAILED + e.getMessage());
		}
	}
	
	public void updatePerson(Person person, Connection connection) throws ServiceException{
		personDao.setConnection(connection);
		try {
			personDao.updatePerson(person);
		} catch (DataAccessException e) {
			LOGGER.error(ExceptionMessages.PERSON_UPDATE_FAILED);
			throw new ServiceException(ExceptionMessages.PERSON_UPDATE_FAILED + e.getMessage());
		}
	}
	
	public Person getPerson(int personId, Connection connection) throws ServiceException{
		personDao.setConnection(connection);
		try {
			return personDao.getPerson(personId);
		} catch (DataAccessException e) {
			LOGGER.error(ExceptionMessages.PERSON_READ_FAILED);
			throw new ServiceException(ExceptionMessages.PERSON_READ_FAILED + e.getMessage());
		}
	}
	
	public int getPersonsCount(Connection connection) throws ServiceException{
		personDao.setConnection(connection);
		try {
			return personDao.getPersonsCount();
		} catch (DataAccessException e) {
			LOGGER.error(ExceptionMessages.PERSON_READ_FAILED);
			throw new ServiceException(ExceptionMessages.PERSON_READ_FAILED + e.getMessage());
		}
	}
	
	public List<Person> getPersons(int first, int count, Connection connection) throws ServiceException{
		personDao.setConnection(connection);
		try {
			return personDao.getPersons(first, count);
		} catch (DataAccessException e) {
			LOGGER.error(ExceptionMessages.PERSON_READ_FAILED);
			throw new ServiceException(ExceptionMessages.PERSON_READ_FAILED + e.getMessage());
		}
	}
	
	public List<Person> getPersonsByDate(Date date, Connection connection) throws ServiceException{
		personDao.setConnection(connection);
		try {
			return personDao.getPersonsByDate(date);
		} catch (DataAccessException e) {
			LOGGER.error(ExceptionMessages.PERSON_READ_FAILED);
			throw new ServiceException(ExceptionMessages.PERSON_READ_FAILED + e.getMessage());
		}
	}
	
	public String getPersonEmail(int personId, Connection connection) throws ServiceException{
		personDao.setConnection(connection);
		try {
			return personDao.getEmail(personId);
		} catch (DataAccessException e) {
			LOGGER.error(ExceptionMessages.PERSON_READ_FAILED);
			throw new ServiceException(ExceptionMessages.PERSON_READ_FAILED  + e.getMessage());
		}
	}
	 
	
}
