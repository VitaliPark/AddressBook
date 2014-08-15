package constants.database;

public enum DatabaseConstants {

	JNDI_CONTEXT("java:comp/env"), DATASOURCE_NAME("jdbc/contacts");
	
	private String value;

	private DatabaseConstants(String value) {
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
}
