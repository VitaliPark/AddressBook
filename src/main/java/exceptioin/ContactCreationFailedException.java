package exceptioin;

@SuppressWarnings("serial")
public class ContactCreationFailedException extends Exception{

	public ContactCreationFailedException() {}

	public ContactCreationFailedException(String message) {
		super(message);
	}
}
