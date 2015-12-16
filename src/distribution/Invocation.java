package distribution;

import java.io.Serializable;
import java.util.ArrayList;

import distribution.clientproxy.ClientProxy;

public class Invocation implements Serializable {

	private static final long serialVersionUID = -4877269674908616498L;
	
	private ClientProxy clientProxy;
	private String operationName;
	private ArrayList<Object> parameters;

	public ClientProxy getClientProxy() {
		return clientProxy;
	}
	public void setClientProxy(ClientProxy clientProxy) {
		this.clientProxy = clientProxy;
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
