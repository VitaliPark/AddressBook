package model.service;

import java.sql.Connection;
import java.sql.SQLException;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;

import constants.ExceptionMessages;
import constants.database.DatabaseConstants;
import exceptioin.ContactCreationFailedException;
import exceptioin.ContactDelitionFailedException;
import exceptioin.ContactReadFailedException;
import exceptioin.EmailReadFailedException;
import exceptioin.ServiceException;
import model.ContactTransferObject;
import model.SearchRequest;
import model.entity.Address;
import model.entity.Attachment;
import model.entity.Contact;
import model.entity.Person;
import model.entity.Phone;


public class ContactService {

	private DataSource pool;

	private PhoneService phoneService;
	private AddressService addressService;
	private PersonService personService;
	private SearchService searchService;
	private AttachmentService attachmentService;
	
	public ContactService() {
		initServices();
		initDataSource();
	}
	
	private void initServices(){
		phoneService = new PhoneService();
		addressService = new AddressService();
		personService = new PersonService();
		attachmentService = new AttachmentService();
		searchService = new SearchService();
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
	
	public ContactTransferObject performSearch(SearchRequest searchRequest, int first, int maxPageCount) throws ContactReadFailedException{
		Connection connection = null;
		ContactTransferObject searchResult = new ContactTransferObject();
		List<Contact> contactList = new ArrayList<>();
		try {
			connection = pool.getConnection();
			connection.setAutoCommit(false);
			int totalCount = searchService.getCountOnSearchRequest(connection, searchRequest);
			contactList = searchService.searchContacts(connection,searchRequest, first, maxPageCount);
			searchResult.setContacts(contactList);
			searchResult.setNumberOfRecords(totalCount);
			connection.commit();
			return searchResult;
		} catch (SQLException | ServiceException e) {
			throw new ContactReadFailedException(ExceptionMessages.CONTACT_READ_FAILED + e.getMessage());
		} finally {
			closeConnection(connection);
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
			attachmentService.addAllAttachments(newContact, connection);
			addressService.addAddress(newContact, connection);			
			connection.commit();
		} catch (ServiceException | SQLException e){
			transactionRollback(connection);
			throw new ContactCreationFailedException(ExceptionMessages.CONTACT_CREATION_FAILED + e.getMessage());
		} finally {
			closeConnection(connection);
		}	
	}
	
	public void updateContact(Contact contact){
		Connection connection = null;
		try {
			connection = pool.getConnection();
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void deleteContact(int idPerson) throws ContactDelitionFailedException{
		Connection connection = null;
		try {
			connection = pool.getConnection();
			connection.setAutoCommit(false);
			phoneService.deleteAllPersonPhones(idPerson, connection);
			addressService.deleteAddressByPersonId(idPerson, connection);
			personService.deletePerson(idPerson, connection);
			attachmentService.deletePersonAttachments(idPerson, connection);
			connection.commit();
		} catch (ServiceException | SQLException e) {
			transactionRollback(connection);
			throw new ContactDelitionFailedException(ExceptionMessages.CONTACT_DELITION_FAILED + e.getMessage());
		} finally {
			closeConnection(connection);
		}
	}
	
	public Contact getContact(int personId) throws ContactReadFailedException{
		Connection connection = null;
		Contact contact = new Contact();
		try {
			connection = pool.getConnection();
			connection.setAutoCommit(false);
			Person person = personService.getPerson(personId, connection);
			List<Phone> phones = phoneService.getAllPersonPhones(personId, connection);
			List<Attachment> attachments = attachmentService.getAllPersonPhones(personId, connection);
			Address address = addressService.getAddressByPersonId(personId, connection);
			contact.setPerson(person);
			contact.setAddress(address);
			contact.setPhoneList(phones);
			contact.setAttachmentList(attachments);
			connection.commit();
			return contact;
		} catch (ServiceException | SQLException e){
			transactionRollback(connection);
			throw new ContactReadFailedException(ExceptionMessages.CONTACT_READ_FAILED + e.getMessage());
		} finally {
			closeConnection(connection);
		}
	}
	
	public String getContactEmail(int contactId) throws EmailReadFailedException{
		Connection connection = null;
		try {
			connection = pool.getConnection();
			connection.setAutoCommit(false);
			String email = personService.getPersonEmail(contactId, connection);
			connection.commit();
			return email;
		} catch (ServiceException | SQLException e) {
			throw new EmailReadFailedException(ExceptionMessages.EMAIL_READ_FAILED + e.getMessage());
		} finally{
			closeConnection(connection);
		}
	}
	
	public ContactTransferObject getContacts(int first, int maxCount) throws ContactReadFailedException{
		Connection connection = null;
		ContactTransferObject result = new ContactTransferObject();
		List<Contact> contactList = new ArrayList<>();
		List<Person> persons = new ArrayList<>();
		try {
			connection = pool.getConnection();
			connection.setAutoCommit(false);
			int count = personService.getPersonsCount(connection);
			persons = personService.getPersons(first, maxCount, connection);
			result = buildResult(connection, contactList, persons, count);
			connection.commit();
			return result;
		} catch (ServiceException | SQLException e) {
			transactionRollback(connection);
			throw new ContactReadFailedException(ExceptionMessages.CONTACT_READ_FAILED + e.getMessage());
		} finally {
			closeConnection(connection);
		}
	}

	private ContactTransferObject buildResult(Connection connection, List<Contact> contactList,
			List<Person> persons, int count) throws ServiceException {
		ContactTransferObject result = new ContactTransferObject();
		buildAllContacts(connection, contactList, persons);
		result.setContacts(contactList);
		result.setNumberOfRecords(count);
		return result;
	}
	
	public List<Person> getContactsByDate(Date date) throws ContactReadFailedException{
		Connection connection = null;
		try {
			connection = pool.getConnection();
			return personService.getPersonsByDate(date, connection);
		} catch (SQLException | ServiceException e) {
			throw new ContactReadFailedException(ExceptionMessages.CONTACT_READ_FAILED + e.getMessage());
		} finally {
			closeConnection(connection);
		}
		
	}

	private void buildAllContacts(Connection connection, List<Contact> result,
			List<Person> persons) throws ServiceException {
		for (Person person : persons) {
			Address address = addressService.getAddressByPersonId(person.getIdPerson(), connection);
			Contact contact = new Contact();
			contact.setPerson(person);
			contact.setAddress(address);
			result.add(contact);
		}
	}

	private void transactionRollback(Connection connection) {
		if(connection != null){
			try {
				System.out.println("rollback");
				connection.rollback();
				System.out.println("rollback seccesfull");
			} catch (SQLException e1) {
				// TODO logger
			}
		}
	}

	private void closeConnection(Connection connection) {
		if(connection != null){
			try {
				connection.setAutoCommit(true);
				connection.close();
			} catch (SQLException e) {
				// TODO logger
			}
		}
	}
}
