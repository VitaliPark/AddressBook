package constants;

public enum CommandTypes {

	GET_ALL_CONTACTS("getAllContacts"), DELETE_CONTACTS("deleteContacts"), EDIT_CONTACT("editContact"),
	CREATE_CONTACT("createContact"), SEARCH_CONTACTS("searchContacts"), SHOW_SEARCH_PAGE("showSearchPage");
		
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
