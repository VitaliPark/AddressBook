package model.entity;


public enum Gender {

	MALE("G_M"), FEMALE("G_F"), UNKNOWN("");
	
	private String value;

	public String getValue() {
		return value;
	}

	private Gender(String value) {
		this.value = value;
	}
	
	static public Gender getType(String gender) {
        for (Gender type: Gender.values()) {
            if (type.getValue().equals(gender)) {
                return type;
            }
        }
        return UNKNOWN;
    }	
	
}
