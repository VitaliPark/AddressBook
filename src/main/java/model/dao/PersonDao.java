package model.dao;

import exceptioin.DataAccessException;
import model.entity.Person;

public interface PersonDao extends HasConnection {

	public int createPerson(Person person) throws DataAccessException;
	
	public void deletePerson(int idPerson) throws DataAccessException;
	
	public void getPerson();
	
	public void updatePerson(Person person);

}
