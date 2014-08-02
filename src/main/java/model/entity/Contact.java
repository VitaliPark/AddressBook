package model.entity;

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
}
