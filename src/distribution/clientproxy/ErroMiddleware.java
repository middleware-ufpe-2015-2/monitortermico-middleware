package distribution.clientproxy;

public class ErroMiddleware {
	
	int codeError;
	String description;
	
	public ErroMiddleware (){
		super();
	}
	
	public ErroMiddleware (int codeError, String description){
		this.codeError = codeError;
		this.description =  description;
				
	}

	public int getCodeError() {
		return codeError;
	}

	public void setCodeError(int codeError) {
		this.codeError = codeError;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
