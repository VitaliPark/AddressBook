package constants.database;


public enum SQLQuery {

	CREATE_PERSON("INSERT INTO " + TableNames.databaseName + "." + TableNames.personTable + 
			"(" + PersonColumnNames.firstName + "," +
			PersonColumnNames.secondName + "," +
			PersonColumnNames.patronymicName + "," +
			PersonColumnNames.dateOfBirth + "," + 
			PersonColumnNames.maritalStatus + "," +
			PersonColumnNames.gender + "," + 
			PersonColumnNames.citizenship + "," +
			PersonColumnNames.website + "," +
			PersonColumnNames.email + "," +
			PersonColumnNames.workplace + ")" +
			" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"),
	
	CREATE_PHONE("INSERT INTO " + TableNames.databaseName + "." + TableNames.phoneTable + 
			"(" + PersonColumnNames.idPerson + "," +
			PhoneColumnNames.countryCode + "," +
			PhoneColumnNames.operatorCode + "," +
			PhoneColumnNames.phoneNumber + ", " + 
			PhoneColumnNames.phoneType + ", " + 
			PhoneColumnNames.comment + " )" + 
	" VALUES(?, ?, ?, ?, ?, ?)"),
	
	CREATE_ADDRESS("INSERT INTO " + TableNames.databaseName + "." + TableNames.addressTable + 
			"(" + PersonColumnNames.idPerson + "," +
			AddressColumnNames.country + "," +
			AddressColumnNames.city + "," + 
			AddressColumnNames.street + "," + 
			AddressColumnNames.houseNumber + "," + 
			AddressColumnNames.apartment + "," + 
			AddressColumnNames.postIndex +  " )" +
	" VALUES(?, ?, ?, ?, ?, ?, ?)"),
	
	DELETE_PERSON("DELETE FROM " + TableNames.databaseName + "." + TableNames.personTable +
			" WHERE " + PersonColumnNames.idPerson + "= ?"),
	
	DELETE_PERSON_PHONES("DELETE FROM " + TableNames.databaseName + "." + TableNames.phoneTable +
			" WHERE " + PersonColumnNames.idPerson + "= ?"),
			
	DELETE_PERSON_ADDRESS("DELETE FROM " + TableNames.databaseName + "." + TableNames.addressTable +
			" WHERE " + PersonColumnNames.idPerson + "= ?"),
	
	GET_ALL_PERSONS("SELECT * FROM " + TableNames.databaseName + "." + TableNames.personTable +
			" LIMIT ?, ?"),
			
	GET_PERSON_ADDRESS("SELECT * FROM " + TableNames.databaseName + "." + TableNames.addressTable + 
			" WHERE " + PersonColumnNames.idPerson + "= ?"),
			
	GET_PERSON_PHONES("SELECT * FROM " + TableNames.databaseName + "." + TableNames.phoneTable + 
			" WHERE " + PersonColumnNames.idPerson + "= ?"),
	
	GET_PERSON("SELECT * FROM " + TableNames.databaseName + "." + TableNames.personTable + 
			" WHERE " + PersonColumnNames.idPerson + "= ?"),
	
	SEARCH_REQUEST("SELECT * FROM " + TableNames.databaseName + "." + TableNames.personTable + 
			" INNER JOIN " + TableNames.databaseName + "." + TableNames.addressTable + 
			" ON " + TableNames.personTable + "." + PersonColumnNames.idPerson + "=" +
			TableNames.addressTable + "." + PersonColumnNames.idPerson + 
			" WHERE " + PersonColumnNames.firstName + " LIKE ?" +
			" AND " + PersonColumnNames.secondName + " LIKE ?" + 
			" AND " + PersonColumnNames.patronymicName + " LIKE ?" +
			" AND " + PersonColumnNames.citizenship + " LIKE ?" +
			" AND " + PersonColumnNames.dateOfBirth + " dateOperator ?" + 
			" AND " + PersonColumnNames.gender + " LIKE ?" + 
			" AND " + PersonColumnNames.maritalStatus + " LIKE ?" +
			" AND " + AddressColumnNames.country + " LIKE ?" + 
			" AND " + AddressColumnNames.city + " LIKE ?" + 
			" AND " + AddressColumnNames.street + " LIKE ?" + 
			" AND " + AddressColumnNames.houseNumber + " LIKE ?" +
			" AND " + AddressColumnNames.apartment + " LIKE ?" + 
			" AND " + AddressColumnNames.postIndex + " LIKE ?" +
			" LIMIT ?, ?");
	
	private String value;
	
	private SQLQuery(String value) {
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
}
