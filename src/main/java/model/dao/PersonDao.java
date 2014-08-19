package model.dao;

import java.util.Date;
import java.util.List;

import exceptioin.DataAccessException;
import model.entity.Person;

public interface PersonDao extends HasConnection {

	public int createPerson(Person person) throws DataAccessException;
	
	public void deletePerson(int idPerson) throws DataAccessException;
	
	public Person getPerson(int personId) throws DataAccessException;
	
	public List<Person> getPersonsByDate(Date date) throws DataAccessException;
	
	public String getEmail(int personId) throws DataAccessException;
	
	public int getPersonsCount() throws DataAccessException;
	
	public void updatePerson(Person person);
	
	public List<Person> getPersons(int first, int count) throws DataAccessException;

}
