package constants;

public enum Status {

	UPDATED("updated"),
	DELETED("deleted"),
	CREATED("created");
	
	private String value;

	private Status(String value) {
		this.value = value;
	}
	
    public String getValue() {
		return value;
	}

	static public Status getType(String status) {
        for (Status type: Status.values()) {
            if (type.getValue().equals(status)) {
                return type;
            }
        }
        return null;
    }	 
}
