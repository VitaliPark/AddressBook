package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;

import constants.DatabaseConstants;
import constants.ExceptionMessages;
import exceptioin.ContactCreationFailedException;
import exceptioin.DataAccessException;
import exceptioin.ServiceException;
import model.dao.AddressDao;
import model.dao.AttachmentDao;
import model.dao.PersonDao;
import model.dao.PhoneDao;
import model.dao.impementation.DefaultAddressDao;
import model.dao.impementation.DefaultAttachmentDao;
import model.dao.impementation.DefaultPersonDao;
import model.dao.impementation.DefaultPhoneDao;
import model.entity.Contact;
import model.entity.Phone;

public class ContactService {

	private DataSource pool;
	
	private PersonDao personDao;
	private AddressDao addressDao;
	private PhoneDao phoneDao;
	private AttachmentDao attachmentDao;
	
	public ContactService() {
		initDao();
		initDataSource();
	}

	private void initDao() {
		personDao = new DefaultPersonDao();
		addressDao = new DefaultAddressDao();
		phoneDao = new DefaultPhoneDao();
		attachmentDao = new DefaultAttachmentDao();
	}
	
	private void initDataSource(){
		 Context env = null;
	        try {
	            env = (Context) new InitialContext().lookup(DatabaseConstants.JNDI_CONTEXT.getValue());
	            pool = (DataSource) env.lookup(DatabaseConstants.DATASOURCE_NAME.getValue());
	            if(pool == null) {
	                throw new ServletException("Couldn't find Datasource");
	            }
	        } catch (NamingException e) {
	            
	        } catch (ServletException e){
	        	
	        }
	}
	
	public void createContact(Contact newContact) throws ContactCreationFailedException{
		Connection connection = null;
		try {
			connection = pool.getConnection();
			connection.setAutoCommit(false);
			int newId = createPerson(newContact, connection);
			newContact.getPerson().setIdPerson(newId);
			addAllPhones(newContact, connection);
			addAddress(newContact, connection);			
			connection.commit();
		} catch (ServiceException | SQLException e){
			transactionRollback(connection);
			throw new ContactCreationFailedException(ExceptionMessages.CONTACT_CREATION_FAILED + e.getMessage());
		} finally {
			closeConnection(connection);
		}	
	}
	
	public void deleteContact(int idPerson){
		Connection connection = null;
		try {
			connection = pool.getConnection();
			connection.setAutoCommit(false);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void transactionRollback(Connection connection) {
		if(connection != null){
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO handle
				e1.printStackTrace();
			}
		}
	}

	private void closeConnection(Connection connection) {
		if(connection != null){
			try {
				connection.setAutoCommit(true);
				connection.close();
			} catch (SQLException e) {
				// TODO handle
				e.printStackTrace();
			}
		}
	}
	
	private int createPerson(Contact newContact, Connection connection) throws ServiceException{
		int newId;
		try {
			personDao.setConnection(connection);
			newId = personDao.createPerson(newContact.getPerson());
		} catch (DataAccessException e) {
			throw new ServiceException(ExceptionMessages.PERSON_CREATION_FAILED + e.getMessage());
		}
		return newId;
	}
		
	private void addAllPhones(Contact newContact, Connection connection) throws ServiceException{
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
	
	public void addAddress(Contact newContact, Connection connection) throws ServiceException{
		addressDao.setConnection(connection);
		try {
			addressDao.createAddress(newContact.getAddress(), newContact.getPerson().getIdPerson());
		} catch (DataAccessException e) {
			throw new ServiceException(ExceptionMessages.ADDRESS_CREATION_FAILED + e.getMessage());
		}
	}
	
	// addAllAtachments
	

	
	private void deleteAllPhones(int idPerson, Connection connection) throws ServiceException{
		phoneDao.setConnection(connection);
		try {
			phoneDao.deletePersonPhones(idPerson);
		} catch (DataAccessException e) {
			throw new ServiceException(ExceptionMessages.PHONE_DELETION_FAILED + e.getMessage());
		}
	}
	
	private void deleteAddress(){
		
	}
	
}
