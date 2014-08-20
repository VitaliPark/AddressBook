package controller.command;

public class ErrorCommand implements Command{

	@Override
	public void execute() {
		System.out.println("errorrrrr");
	}

	@Override
	public String getResultPage() {
		// TODO Auto-generated method stub
		return null;
	}

}
