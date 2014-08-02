package model.dao;

import exceptioin.DataAccessException;
import model.entity.Person;

public interface PersonDao extends HasConnection {

	public int createPerson(Person person) throws DataAccessException;
	
	public void deletePerson(Person person);
	
	public void getPerson();
	
	public void updatePerson(Person person);

}
