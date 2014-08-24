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

import org.apache.log4j.Logger;

import search.SearchRequest;
import constants.ExceptionMessages;
import constants.Status;
import constants.database.DatabaseConstants;
import exceptioin.ContactCreationFailedException;
import exceptioin.ContactDelitionFailedException;
import exceptioin.ContactReadFailedException;
import exceptioin.ContactUpdateFailed;
import exceptioin.EmailReadFailedException;
import exceptioin.ServiceException;
import model.ContactTransferObject;
import model.entity.Address;
import model.entity.Attachment;
import model.entity.Contact;
import model.entity.Image;
import model.entity.Person;
import model.entity.Phone;


public class ContactService {

	private DataSource pool;

	private PhoneService phoneService;
	private AddressService addressService;
	private PersonService personService;
	private SearchService searchService;
	private ImageService imageService;
	private AttachmentService attachmentService;
	private Logger LOGGER = Logger.getLogger(ContactService.class);
	
	public ContactService() {
		initServices();
		initDataSource();
	}
	
	private void initServices(){
		phoneService = new PhoneService();
		addressService = new AddressService();
		personService = new PersonService();
		attachmentService = new AttachmentService();
		imageService = new ImageService();
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
	    	 LOGGER.error(e.getMessage());
	     } catch (ServletException e){
	    	 LOGGER.error(e.getMessage());
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
			LOGGER.error(e.getMessage());
			transactionRollback(connection);
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
			Image image = newContact.getImage();
			if(image != null){
				imageService.createImage(image, newContact.getPersonId(), connection);
			}
			connection.commit();
		} catch (ServiceException | SQLException e){
			LOGGER.error(e.getMessage());
			transactionRollback(connection);
			throw new ContactCreationFailedException(ExceptionMessages.CONTACT_CREATION_FAILED + e.getMessage());
		} finally {
			closeConnection(connection);
		}	
	}
	
	public void updateContact(Contact contact) throws ContactUpdateFailed{
		Connection connection = null;
		try {
			connection = pool.getConnection();
			connection.setAutoCommit(false);
			personService.updatePerson(contact.getPerson(), connection);
			addressService.updatePersonAddress(contact.getAddress(), contact.getPersonId(), connection);
			processImage(contact, connection);
			processPhones(contact, connection);
			processAttachments(contact, connection);
			connection.commit();
		} catch (SQLException | ServiceException e) {
			LOGGER.error(e.getMessage());
			transactionRollback(connection);
			throw new ContactUpdateFailed(" Unable to update contcat " + e.getMessage());
		} finally {
			closeConnection(connection);
		}
	}

	@SuppressWarnings("incomplete-switch")
	private void processImage(Contact contact, Connection connection)
			throws ServiceException {
		Image image = contact.getImage();
		if(image != null){
			Status status = image.getStatus();
			switch(status){
				case CREATED: {
					imageService.createImage(image, contact.getPersonId(), connection);
					break;
				}
				case UPDATED: {
					imageService.updateImage(image, contact.getPersonId(), connection);
					break;
				}
			}
		}
	}
	
	public void deleteContact(int idPerson) throws ContactDelitionFailedException{
		Connection connection = null;
		try {
			connection = pool.getConnection();
			connection.setAutoCommit(false);
			phoneService.deleteAllPersonPhones(idPerson, connection);
			addressService.deleteAddressByPersonId(idPerson, connection);
			attachmentService.deletePersonAttachments(idPerson, connection);
			imageService.deletePersonImage(idPerson, connection);
			personService.deletePerson(idPerson, connection);
			connection.commit();
		} catch (ServiceException | SQLException e) {
			LOGGER.error(e.getMessage());
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
			Image image = imageService.getImage(personId, connection);
			contact.setPerson(person);
			contact.setAddress(address);
			contact.setImage(image);
			contact.setPhoneList(phones);
			contact.setAttachmentList(attachments);
			connection.commit();
			return contact;
		} catch (ServiceException | SQLException e){
			LOGGER.error(e.getMessage());
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
			LOGGER.error(e.getMessage());
			transactionRollback(connection);
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
			LOGGER.error(e.getMessage());
			transactionRollback(connection);
			throw new ContactReadFailedException(ExceptionMessages.CONTACT_READ_FAILED + e.getMessage());
		} finally {
			closeConnection(connection);
		}
	}

	private void processAttachments(Contact contact, Connection connection)
			throws ServiceException {
		for(Attachment attach : contact.getAttachments()){
			Status status = attach.getStatus();
			switch(status){
				case DELETED:{
					attachmentService.deleteAttachment(attach.getIdAttachment(), connection);
					break;
				}
				case CREATED: {
					attachmentService.createAttachment(attach, contact.getPersonId(), connection);
					break;
				}
				case UPDATED: {
					attachmentService.updateAttachment(attach, connection);
					break;
				}
			}
		}
	}
	

	private void processPhones(Contact contact, Connection connection) throws ServiceException {
		for(Phone phone : contact.getPhones()){
			Status status = phone.getStatus();
			System.out.println(status.getValue());
			switch(status){
				case DELETED: {
					phoneService.deletePhone(phone.getPhoneId(), connection);
					break;
				}
				case CREATED:{
					phoneService.createPhone(phone, contact.getPersonId(), connection);
					break;
				}
				case UPDATED:{
					phoneService.updatePhone(phone, connection);
					break;
				}
			}
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
			LOGGER.error(e.getMessage());
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
				LOGGER.debug("Transaction rollback...");
				connection.rollback();
				LOGGER.debug("Transaction rollback successfull");
			} catch (SQLException e1) {
				LOGGER.error(e1.getMessage());
			}
		}
	}

	private void closeConnection(Connection connection) {
		if(connection != null){
			try {
				connection.setAutoCommit(true);
				connection.close();
			} catch (SQLException e) {
				LOGGER.debug(e.getMessage());
			}
		}
	}
}
