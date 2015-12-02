package distribution;

import java.io.Serializable;
import java.util.ArrayList;

public class Invocation implements Serializable {

	private static final long serialVersionUID = -4877269674908616498L;
	
	private String ipAdress;
	private int portNumber;
	private int objectId;
	private String operationName;
	private ArrayList<Object> parameters;

	public String getIpAdress() {
		return ipAdress;
	}
	public void setIpAdress(String ipAdress) {
		this.ipAdress = ipAdress;
	}
	public int getPortNumber() {
		return portNumber;
	}
	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
	}
	public int getObjectId() {
		return objectId;
	}
	public void setObjectId(int objectId) {
		this.objectId = objectId;
	}
	public String getOperationName() {
		return operationName;
	}
	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}
	public ArrayList<Object> getParameters() {
		return parameters;
	}
	public void setParameters(ArrayList<Object> parameters) {
		this.parameters = parameters;
	}

}
