package model.entity;

public enum MaritalStatus {

	SINGLE("MS_S"), MARRIED("MS_M"), UNKNOWN("");
	
	private String value;

	private MaritalStatus(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	static public MaritalStatus getType(String status) {
        for (MaritalStatus type: MaritalStatus.values()) {
            if (type.getValue().equals(status)) {
                return type;
            }
        }
        return UNKNOWN;
    }	
	
}
