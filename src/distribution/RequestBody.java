package distribution;

import java.io.Serializable;
import java.util.ArrayList;

public class RequestBody implements Serializable {
	
	private static final long serialVersionUID = 3776636121705945280L;

	private ArrayList<Object> parameters = new ArrayList<Object>();

	public RequestBody(){
		
	}
	
	public RequestBody(ArrayList<Object> parameters) {
		this.parameters = parameters;
	}

	public ArrayList<Object> getParameters() {
		return parameters;
	}

	public void setParameters(ArrayList<Object> parameters) {
		this.parameters = parameters;
	}

}
