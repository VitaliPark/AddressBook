package model;

import java.util.List;

import model.entity.Contact;

public class ContactTransferObject {

	private List<Contact> contacts;
	private int numberOfRecords;
	
	public List<Contact> getContacts() {
		return contacts;
	}
	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
	public int getNumberOfRecords() {
		return numberOfRecords;
	}
	public void setNumberOfRecords(int numberOfRecords) {
		this.numberOfRecords = numberOfRecords;
	}
	
	
}
