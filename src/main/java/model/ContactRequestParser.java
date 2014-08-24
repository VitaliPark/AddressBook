package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import constants.DefaultValues;
import constants.Status;
import constants.StringConstants;
import constants.database.AddressColumnNames;
import constants.database.PersonColumnNames;
import exceptioin.ContactValidationException;
import model.entity.Address;
import model.entity.Attachment;
import model.entity.Contact;
import model.entity.Gender;
import model.entity.Image;
import model.entity.MaritalStatus;
import model.entity.Person;
import model.entity.Phone;

public class ContactRequestParser {
	
	private Validator fieldValidator;
	private ValidationObject validationObject;
	
	public ContactRequestParser() {
		fieldValidator = new Validator();
		validationObject = new ValidationObject();
	}

	public Contact parseContactRequest(ParameterContainer container) throws ContactValidationException{
		Contact contact = new Contact();
		Person person = formPerson(container);
		Address address = formAddress(container);
		Image image = formImage(container);
		List<Phone> phones = parsePhones(container);
		List<Attachment> attachments = parseAttachments(container);
		contact.setPerson(person);
		contact.setAddress(address);
		contact.setPhoneList(phones);
		contact.setImage(image);
		contact.setAttachmentList(attachments);
		if(!validationObject.isEmpty()){
			System.out.println(validationObject.toString());
			throw new ContactValidationException("Vaidation failed", validationObject);
		}
		return contact;
	}
	
	public List<Phone> parsePhones(ParameterContainer container){
		List<Phone> phones = new ArrayList<>();
		String[] phoneInputs = container.getParameterValues("phones");
		if(phoneInputs != null){
			for(String phoneString : phoneInputs){
				Phone phone = parsePhoneInput(phoneString);
				phones.add(phone);
			}
		}
		return phones;
	}
	
	public List<Attachment> parseAttachments(ParameterContainer container){
		List<Attachment> attachments = new ArrayList<>();
		String[] attachmentsInput = container.getParameterValues("attachments");
		if(attachmentsInput != null){
			for(String attachmentString : attachmentsInput){
				Attachment attach = parseAttachmentInput(attachmentString);
				if(attach != null){
					attachments.add(attach);
				}
			}
		}
		return attachments;
	}
	
	public Image formImage(ParameterContainer container){
		Image image = null;
		String imageProperties = container.getParameter("mainFormImageFileName");
		if(imageProperties != null && !imageProperties.isEmpty()){
			String [] values = imageProperties.split(StringConstants.SEMICOLON);
			image = parseImage(values);
		}
		return image;
	}
	
	private Image parseImage(String values[]){
		Image image = null;
		String fileName = values[1];
		if(fileName != null && !fileName.isEmpty()){
			image = new Image();
			String localFileName = values[0] + fileName;
			Status status = getStatus(values[2]);
			image.setFileName(fileName);
			image.setLocalFileName(localFileName);
			image.setStatus(status);
		}
		return image;
	}
	
	private Attachment parseAttachmentInput(String inputValue){
		String [] values = inputValue.split(StringConstants.SEMICOLON);
		Attachment attachment = getAttachment(values);
		return attachment;
	}

	private Attachment getAttachment(String[] values) {
		Attachment attachment = null;
		String fileName = values[0];
		if(!fileName.isEmpty()){
			attachment = new Attachment();
			Date date = getUploadDate(values[1]);
			String comment = values[2];
			int id = getId(values[3]);
			Status status = getStatus(values[4]);
			String localFileName = values[5] + fileName;
			
			attachment.setFileName(fileName);
			attachment.setLocalFileName(localFileName);
			attachment.setIdAttachment(id);
			attachment.setComment(comment);
			attachment.setStatus(status);
			attachment.setUploadDate(date);
		}
		return attachment;
	}
	
	private Date getUploadDate(String stringDate){
		DateFormat format = new SimpleDateFormat(StringConstants.DEFAULT_DATE_FORMAT);
		Date date = null;
		try {
			date = format.parse(stringDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	private Phone parsePhoneInput(String inputValue){
		Phone phone = new Phone();
		String [] values = inputValue.split(StringConstants.SEMICOLON);
		setFullPhone(phone, values);
		//check
		String phoneType = values[1];
		String comment = values[2];
		int id = getId(values[3]);
		Status status = getStatus(values[4]);
		//
		phone.setPhoneType(phoneType);
		phone.setComment(comment);
		phone.setPhoneId(id);
		phone.setStatus(status);
		return phone;
	}
	
	private void setFullPhone(Phone phone, String[] values){
		String fullPhone = values[0];
		String[] phoneComponents = fullPhone.split(StringConstants.PHONE_SEPARATOR);
		int countryCode = getCountryCode(phoneComponents[0]);
		int operatorCode = getOperatorCode(phoneComponents[1]);
		String phoneNumber = getPhoneNumber(phoneComponents[2]);
		
		phone.setCountryCode(countryCode);
		phone.setOperatorCode(operatorCode);
		phone.setPhoneNumber(phoneNumber);
	}
	
	private int getCountryCode(String stringCode){
		System.out.println(stringCode);
		int countryCode;
		if(!fieldValidator.validateNumber(stringCode)){
			validationObject.addWrongField(SearchCriteria.COUNTRY_CODE);
			return DefaultValues.defaultNumericValue;
		}
		if(stringCode.isEmpty()){
			countryCode = DefaultValues.defaultNumericValue;
		}else {
			countryCode = Integer.parseInt(stringCode);
		}
		return countryCode;
	}
	
	private int getId(String stringId){
		//int id;
		return Integer.parseInt(stringId);
	}
	
	private Status getStatus(String stringStatus){
		Status status = Status.getType(stringStatus);
		if(status == null){
			validationObject.addWrongField(SearchCriteria.STATUS);
			return null;
		}
		return status;
	}
	
	private int getOperatorCode(String stringCode){
		int opearatoeCode;
		if(!fieldValidator.validateNumber(stringCode)){
			validationObject.addWrongField(SearchCriteria.OPERATOR_CODE);
			return DefaultValues.defaultNumericValue;
		}
		if(stringCode.isEmpty()){
			opearatoeCode = DefaultValues.defaultNumericValue;
		}else {
			opearatoeCode = Integer.parseInt(stringCode);
		}
		return opearatoeCode;
	}
	
	private String getPhoneNumber(String phoneNumber){
		if(fieldValidator.isNull(phoneNumber)){
			validationObject.addWrongField(SearchCriteria.PHONE_NUMBER);
			return null;
		}
		return phoneNumber;
	}
	
	private Person formPerson(ParameterContainer container){
		Person person = new Person();
		person.setFirstName(getFirstName(container));
		person.setSecondName(getSecondName(container));
		person.setDateOfBirth(getDateOfBirth(container));
		person.setPatronymicName(getPatronymicName(container));
		person.setGender(getGender(container));
		person.setCitizenship(getCitizenship(container));
		person.setMaritalStatus(getMaritialStatus(container));
		person.setEmail(getEmail(container));
		person.setWebSite(getWebSite(container));
		person.setWorkplace(getWorkplace(container));
		return person;
	}
	
	private Address formAddress(ParameterContainer container){
		Address address = new Address();
		address.setCountry(getCountry(container));
		address.setCity(getCity(container));
		address.setStreet(getStreet(container));
		address.setHouseNumber(getHouseNumber(container));
		address.setApartment(getApartment(container));
		address.setIndex(getPostCode(container));
		return address;
	}
	
	private String getFirstName(ParameterContainer container){
		String firstName = container.getParameter(PersonColumnNames.firstName);
		if(!fieldValidator.validateRequiredField(firstName)){
			validationObject.addWrongField(SearchCriteria.FIRST_NAME);
			return null;
		}
		return firstName;
	}
	
	private String getSecondName(ParameterContainer container){
		String secondName = container.getParameter(PersonColumnNames.secondName);
		if(!fieldValidator.validateRequiredField(secondName)){
			validationObject.addWrongField(SearchCriteria.SECOND_NAME);
			return null;
		}
		return secondName;
	}
	
	private String getPatronymicName(ParameterContainer container){
		String patronymicName = container.getParameter(PersonColumnNames.patronymicName);
		if(fieldValidator.isNull(patronymicName)){
			validationObject.addWrongField(SearchCriteria.PATRONYMIC_NAME);
			return null;
		}
		return patronymicName;
	}
	
	private Date getDateOfBirth(ParameterContainer container){
		String dateOfBirth = container.getParameter(PersonColumnNames.dateOfBirth);
		if(!fieldValidator.validateDate(dateOfBirth) || !fieldValidator.validateRequiredField(dateOfBirth)){
			validationObject.addWrongField(SearchCriteria.DATE_OF_BIRTH);
			return null;
		}
		DateFormat formatter = new SimpleDateFormat(StringConstants.DEFAULT_DATE_FORMAT);
		Date date = null;
		try {
			if(dateOfBirth.isEmpty()){
				date = null;
			}
			else{
				date = formatter.parse(dateOfBirth);
			}
		} catch (ParseException e) {}
		return date;
	}
	
	private Gender getGender(ParameterContainer container){
		String genderString = container.getParameter(PersonColumnNames.gender);
		if(fieldValidator.isNull(genderString)){
			validationObject.addWrongField(SearchCriteria.GENDER);
			return Gender.UNKNOWN;
		}
		return Gender.getType(genderString);
	}
	
	private String getCitizenship(ParameterContainer container){
		String citizenship = container.getParameter(PersonColumnNames.citizenship);
		if(fieldValidator.isNull(citizenship)){
			validationObject.addWrongField(SearchCriteria.CITIZENSHIP);
			return null;
		}
		return citizenship;
	}
	
	private MaritalStatus getMaritialStatus(ParameterContainer container){
		String maritalStatusString = container.getParameter(PersonColumnNames.maritalStatus);
		System.out.println(maritalStatusString);
		if(fieldValidator.isNull(maritalStatusString)){
			validationObject.addWrongField(SearchCriteria.MARITAL_STATUS);
			return MaritalStatus.UNKNOWN;
		}	
		return MaritalStatus.getType(maritalStatusString);
	}
	
	private String getEmail(ParameterContainer container){
		String email = container.getParameter(PersonColumnNames.email);
		if(!fieldValidator.validateEmail(email)){
			validationObject.addWrongField(SearchCriteria.EMAIL);
			return null;
		}
		return email;
	}
	
	private String getWebSite(ParameterContainer container){
		String webSite = container.getParameter(PersonColumnNames.website);
		if(fieldValidator.isNull(webSite)){
			validationObject.addWrongField(SearchCriteria.WEB_SITE);
			return null;
		}
		return webSite;
	}
	
	private String getWorkplace(ParameterContainer container){
		String workplace = container.getParameter(PersonColumnNames.workplace);
		if(fieldValidator.isNull(workplace)){
			validationObject.addWrongField(SearchCriteria.WORKPLACE);
			return null;
		}
		return workplace;
	}
	
	private String getCountry(ParameterContainer container){
		String country = container.getParameter(AddressColumnNames.country);
		if(fieldValidator.isNull(country)){
			validationObject.addWrongField(SearchCriteria.COUNTRY);
			return null;
		}
		return country;
	}
	
	private String getCity(ParameterContainer container){
		String city = container.getParameter(AddressColumnNames.city);
		if(fieldValidator.isNull(city)){
			validationObject.addWrongField(SearchCriteria.CITY);
			return null;
		}
		return city;
	}
	
	private String getStreet(ParameterContainer container){
		String street = container.getParameter(AddressColumnNames.street);
		if(fieldValidator.isNull(street)){
			validationObject.addWrongField(SearchCriteria.STREET);
			return null;
		}
		return street;
	}
	
	private int getHouseNumber(ParameterContainer container){
		String houseNumberString = container.getParameter(AddressColumnNames.houseNumber);
		int houseNumber;
		if(!fieldValidator.validateNumber(houseNumberString)){
			validationObject.addWrongField(SearchCriteria.HOUSE_NUMBER);
			return DefaultValues.defaultNumericValue;
		}
		if(houseNumberString.isEmpty()){
			houseNumber = DefaultValues.defaultNumericValue;
		} else{
			houseNumber = Integer.parseInt(houseNumberString);
		}
		return houseNumber;
	}
	
	private int getApartment(ParameterContainer container){
		String apartmentString = container.getParameter(AddressColumnNames.apartment);
		int apartment;
		if(!fieldValidator.validateNumber(apartmentString)){
			validationObject.addWrongField(SearchCriteria.APARTMENT);
			return DefaultValues.defaultNumericValue;
		}
		if(apartmentString.isEmpty())
			apartment = DefaultValues.defaultNumericValue;
		else {
			apartment = Integer.parseInt(apartmentString);
		}
		return apartment;
	}
	
	private String getPostCode(ParameterContainer container){
		String postCode = container.getParameter(AddressColumnNames.postIndex);
		if(fieldValidator.isNull(postCode)){
			validationObject.addWrongField(SearchCriteria.POST_CODE);
			return null;
		}
		return postCode;
	}
	
}
