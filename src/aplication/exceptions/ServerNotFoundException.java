package aplication.exceptions;

public class ServerNotFoundException extends Exception {

	private static final long serialVersionUID = -7379187156802325203L;
	
	public ServerNotFoundException (){
		super();
	}
	public ServerNotFoundException (String message){
		super(message);
	}

}
