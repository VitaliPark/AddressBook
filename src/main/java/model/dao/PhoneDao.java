package model.dao;

import java.util.List;

import exceptioin.DataAccessException;
import model.entity.Phone;

public interface PhoneDao extends HasConnection {

	public void createPhone(Phone phone, int idPerson) throws DataAccessException;
	
	public List<Phone> getPersonPhones(int idPerson) throws DataAccessException;
	
	public void updatePhone(Phone phone) throws DataAccessException;
	
	public void deletePhone(int phoneId) throws DataAccessException;
	
	public void deletePersonPhones(int idPerson) throws DataAccessException;
		
}
