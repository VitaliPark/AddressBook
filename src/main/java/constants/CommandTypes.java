package constants;

public enum CommandTypes {

	GET_ALL_CONTACTS("getAllContacts"), 
	DELETE_CONTACTS("deleteContacts"), 
	EDIT_CONTACT("editContact"),
	UPDATE_CONTACT("updateContact"),
	CREATE_CONTACT("createContact"), 
	SEARCH_CONTACTS("searchContacts"), 
	SHOW_SEARCH_PAGE("showSearchPage"),
	SHOW_MAIL_PAGE("showMailPage"),
	SEND_MAIL("sendMail"),
	SCHEDULE_MAIL_SEND("scheduleMail");
		
	private String value;

	private CommandTypes(String value) {
		this.value = value;
	}
	
    public String getValue() {
		return value;
	}

	static public CommandTypes getType(String command) {
        for (CommandTypes commandType: CommandTypes.values()) {
            if (commandType.getValue().equals(command)) {
                return commandType;
            }
        }
        throw new RuntimeException("unknown type");
    }	
}
