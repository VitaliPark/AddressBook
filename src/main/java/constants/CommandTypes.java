package constants;

public enum CommandTypes {

	GET_ALL_CONTACTS("getAllContacts");
		
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
