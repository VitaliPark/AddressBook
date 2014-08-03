package model.service;

import java.sql.Connection;
import java.sql.SQLException;



import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;

import constants.DatabaseConstants;
import constants.ExceptionMessages;
import exceptioin.ContactCreationFailedException;
import exceptioin.ContactDelitionFailedException;
import exceptioin.ContactReadFailedException;
import exceptioin.ServiceException;
import model.entity.Address;
import model.entity.Contact;
import model.entity.Person;


public class ContactService {

	private DataSource pool;

	private PhoneService phoneService;
	private AddressService addressService;
	private PersonService personService;
	
	public ContactService() {
		initServices();
		initDataSource();
	}
	
	private void initServices(){
		phoneService = new PhoneService();
		addressService = new AddressService();
		personService = new PersonService();
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
			int newId = personService.createPerson(newContact, connection);
			newContact.getPerson().setIdPerson(newId);
			phoneService.addAllPhones(newContact, connection);
			addressService.addAddress(newContact, connection);			
			connection.commit();
		} catch (ServiceException | SQLException e){
			transactionRollback(connection);
			throw new ContactCreationFailedException(ExceptionMessages.CONTACT_CREATION_FAILED + e.getMessage());
		} finally {
			closeConnection(connection);
		}	
	}
	
	public void deleteContact(int idPerson) throws ContactDelitionFailedException{
		Connection connection = null;
		try {
			connection = pool.getConnection();
			connection.setAutoCommit(false);
			phoneService.deleteAllPhones(idPerson, connection);
			addressService.deleteAddressByPersonId(idPerson, connection);
			personService.deletePerson(idPerson, connection);
			connection.commit();
		} catch (ServiceException | SQLException e) {
			transactionRollback(connection);
			throw new ContactDelitionFailedException(ExceptionMessages.CONTACT_DELITION_FAILED + e.getMessage());
		} finally {
			closeConnection(connection);
		}
	}
	
	public List<Contact> getAllContacts(int first, int count) throws ContactReadFailedException{
		Connection connection = null;
		List<Contact> result = new ArrayList<>();
		List<Person> persons = new ArrayList<>();
		try {
			connection = pool.getConnection();
			connection.setAutoCommit(false);
			persons = personService.getAllPersons(first, count, connection);
			for (Person person : persons) {
				Address address = addressService.getAddressByPersonId(person.getIdPerson(), connection);
				Contact contact = new Contact();
				contact.setPerson(person);
				contact.setAddress(address);
				result.add(contact);
			}
			connection.commit();
			return result;
		} catch (ServiceException | SQLException e) {
			transactionRollback(connection);
			throw new ContactReadFailedException(ExceptionMessages.CONTACT_READ_FAILED + e.getMessage());
		} finally {
			closeConnection(connection);
		}
		
	}

	private void transactionRollback(Connection connection) {
		if(connection != null){
			try {
				System.out.println("rollback");
				connection.rollback();
				System.out.println("rollback seccesfull");
			} catch (SQLException e1) {
				// TODO handle
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
			}
		}
	}
	
	// addAllAtachments	
}
