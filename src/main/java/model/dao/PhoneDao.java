package model.dao;

import exceptioin.DataAccessException;
import model.entity.Phone;

public interface PhoneDao extends HasConnection {

	public void createPhone(Phone phone, int idPerson) throws DataAccessException;
	
	public Phone getPhone();
	
	public void updatePhone(Phone phone);
	
	public void deletePhone(Phone phone);
	
	public void deletePersonPhones(int idPerson) throws DataAccessException;
		
}
