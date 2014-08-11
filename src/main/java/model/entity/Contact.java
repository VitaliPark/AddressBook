package model.entity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Contact {

	private Person person;
	private Address address;
	private List<Phone> phones;
	private List<Attachment> attachments;
	
	public Contact() {
		phones = new ArrayList<>();
		attachments = new ArrayList<>();
	}

	public Person getPerson(){
		return person;
	}
	
	public void setPerson(Person person){
		this.person = person;
	}
	
	public List<Phone> getPhones() {
		return phones;
	}

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public void addPhone(Phone newPhoneNumber){
		phones.add(newPhoneNumber);
	}
	
	public void addAttachment(Attachment newAttachment){
		attachments.add(newAttachment);
	}
	
	public int getPersonId(){
		return person.getIdPerson();
	}
	
	public String getFullName(){
		return person.getFullName();
	}
	
	public String getWorkplace(){
		return person.getWorkplace();
	}
	
	public String getBirthDate(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(person.getDateOfBirth());
	}
	
	public void setPhoneList(List<Phone> phones){
		this.phones = phones;
	}
}
