package distribution;

import java.io.Serializable;

public class RequestHeader implements Serializable {

	private static final long serialVersionUID = -1025610686104071335L;

	private String context;
	private int requestId;
	private boolean responseExpected;
	private int objectKey;
	private String operation;
	
	public RequestHeader(){
		
	}
	
	public RequestHeader(String context, int requestId, boolean response, int objKey,
			String operationName) {
		super();
		this.context = context;
		this.requestId = requestId;
		this.responseExpected = response;
		this.objectKey = objKey;
		this.operation = operationName;
	}
	
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	public boolean isResponseExpected() {
		return responseExpected;
	}
	public void setResponseExpected(boolean responseExpected) {
		this.responseExpected = responseExpected;
	}
	public int getObjectKey() {
		return objectKey;
	}
	public void setObjectKey(int objectKey) {
		this.objectKey = objectKey;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
}
