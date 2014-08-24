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
	
	CREATE_ATTACHMENT("INSERT INTO " + TableNames.databaseName + "." + TableNames.attachmentTable + 
			"(" + PersonColumnNames.idPerson + "," + 
			AttachmentColumnNames.fileName + "," + 
			AttachmentColumnNames.uploadDate + "," + 
			AttachmentColumnNames.comment + "," + 
			AttachmentColumnNames.localFileName + ")" + 
	" VALUES(?,?,?,?,?)"),
	
	CREATE_ADDRESS("INSERT INTO " + TableNames.databaseName + "." + TableNames.addressTable + 
			"(" + PersonColumnNames.idPerson + "," +
			AddressColumnNames.country + "," +
			AddressColumnNames.city + "," + 
			AddressColumnNames.street + "," + 
			AddressColumnNames.houseNumber + "," + 
			AddressColumnNames.apartment + "," + 
			AddressColumnNames.postIndex +  " )" +
	" VALUES(?, ?, ?, ?, ?, ?, ?)"),
	
	CREATE_IMAGE("INSERT INTO " + TableNames.databaseName + "." + TableNames.imageTable + 
			"(" + PersonColumnNames.idPerson + "," + 
			ImageColumnNames.fileName + "," + 
			ImageColumnNames.localFileName + ")" +
	" VALUES(?,?,?)"),
	
	UPDATE_PERSON("UPDATE " + TableNames.databaseName + "." + TableNames.personTable + 
			" SET " + PersonColumnNames.firstName + " = ?," +
			PersonColumnNames.secondName + "=?," + 
			PersonColumnNames.patronymicName + "=?," + 
			PersonColumnNames.dateOfBirth + "=?," +
			PersonColumnNames.maritalStatus + "=?," +
			PersonColumnNames.gender + "=?," +
			PersonColumnNames.citizenship + "=?," + 
			PersonColumnNames.website + "=?," + 
			PersonColumnNames.email + "=?," +
			PersonColumnNames.workplace + "=?" +
	" WHERE " + PersonColumnNames.idPerson + "=?"),
	
	UPDATE_ADDRESS("UPDATE " + TableNames.databaseName + "." + TableNames.addressTable + 
			" SET " + AddressColumnNames.country + " = ?," +
			AddressColumnNames.city + "=?," + 
			AddressColumnNames.street + "=?," + 
			AddressColumnNames.houseNumber + "=?," +
			AddressColumnNames.apartment + "=?," +
			AddressColumnNames.postIndex + "=?" + 
	" WHERE " + PersonColumnNames.idPerson + "=?"),
	
	UPDATE_IMAGE("UPDATE " + TableNames.databaseName + "." + TableNames.imageTable + 
			" SET " + ImageColumnNames.fileName + " = ?," +
			ImageColumnNames.localFileName + "=?" + 
	" WHERE " + PersonColumnNames.idPerson + "=?"),
	
	UPDATE_PHONE("UPDATE " + TableNames.databaseName + "." + TableNames.phoneTable + 
			" SET " + PhoneColumnNames.countryCode + " = ?," +
			PhoneColumnNames.operatorCode + "=?," + 
			PhoneColumnNames.phoneNumber + "=?," + 
			PhoneColumnNames.phoneType + "=?," +
			PhoneColumnNames.comment + "=?" +
	" WHERE " + PhoneColumnNames.idPhone + "=?"),
	
	UPDATE_ATTACHMENT("UPDATE " + TableNames.databaseName + "." + TableNames.attachmentTable + 
			" SET " + AttachmentColumnNames.fileName + " = ?," +
			AttachmentColumnNames.uploadDate + "=?," + 
			AttachmentColumnNames.comment + "=?," + 
			AttachmentColumnNames.localFileName + "=?" +
	" WHERE " + AttachmentColumnNames.idAttachment + "=?"),
	
	DELETE_PERSON("DELETE FROM " + TableNames.databaseName + "." + TableNames.personTable +
			" WHERE " + PersonColumnNames.idPerson + "= ?"),
	
	DELETE_PERSON_PHONES("DELETE FROM " + TableNames.databaseName + "." + TableNames.phoneTable +
			" WHERE " + PersonColumnNames.idPerson + "= ?"),
			
	DELETE_PHONE("DELETE FROM " + TableNames.databaseName + "."+ TableNames.phoneTable + 
			" WHERE " + PhoneColumnNames.idPhone + "=?"),		
			
	DELETE_PERSON_ATTACHMENTS("DELETE FROM " + TableNames.databaseName + "." + TableNames.attachmentTable +
			" WHERE " + PersonColumnNames.idPerson + "= ?"),
			
	DELETE_ATTACHMENT("DELETE FROM " + TableNames.databaseName + "."+ TableNames.attachmentTable + 
			" WHERE " + AttachmentColumnNames.idAttachment + "=?"),
			
	DELETE_PERSON_ADDRESS("DELETE FROM " + TableNames.databaseName + "." + TableNames.addressTable +
			" WHERE " + PersonColumnNames.idPerson + "= ?"),
			
	DELETE_PERSON_IMAGE("DELETE FROM " + TableNames.databaseName + "." + TableNames.imageTable +
			" WHERE " + PersonColumnNames.idPerson + "= ?"),			
	
	GET_PERSONS("SELECT * FROM " + TableNames.databaseName + "." + TableNames.personTable +
			" LIMIT ?, ?"),
			
	GET_PERSONS_COUNT("SELECT COUNT(*) FROM " + TableNames.databaseName + "." + TableNames.personTable),
			
	GET_PERSON_ADDRESS("SELECT * FROM " + TableNames.databaseName + "." + TableNames.addressTable + 
			" WHERE " + PersonColumnNames.idPerson + "= ?"),
			
	GET_PERSON_PHONES("SELECT * FROM " + TableNames.databaseName + "." + TableNames.phoneTable + 
			" WHERE " + PersonColumnNames.idPerson + "= ?"),
			
    GET_PERSON_ATTACHMENTS("SELECT * FROM " + TableNames.databaseName + "." + TableNames.attachmentTable + 
			" WHERE " + PersonColumnNames.idPerson + "= ?"),
			
	GET_PERSON_IMAGE("SELECT * FROM " + TableNames.databaseName + "." + TableNames.imageTable + 
			" WHERE " + PersonColumnNames.idPerson + "= ?"),		
	
	GET_PERSON("SELECT * FROM " + TableNames.databaseName + "." + TableNames.personTable + 
			" WHERE " + PersonColumnNames.idPerson + "= ?"),
			
	GET_PERSON_EMAIL("SELECT email FROM " + TableNames.databaseName + "." + TableNames.personTable +
			" WHERE " + PersonColumnNames.idPerson + "=?"),
			
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
			" LIMIT ?, ?"),
			
			SEARCH_REQUEST_COUNT("SELECT COUNT(*) FROM " + TableNames.databaseName + "." + TableNames.personTable + 
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
					" AND " + AddressColumnNames.postIndex + " LIKE ?"),
	
	GET_PERSON_INFO_BY("SELECT * FROM " + 
			TableNames.databaseName + "." + TableNames.personTable + " WHERE MONTH(dateOfBirth) = ?" + 
			" AND DAY(dateOfBirth ) = ?");
	
	private String value;
	
	private SQLQuery(String value) {
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
}
