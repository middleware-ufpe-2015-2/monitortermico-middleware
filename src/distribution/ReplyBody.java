package distribution;

import java.io.Serializable;

public class ReplyBody implements Serializable {

	private static final long serialVersionUID = -3049759111020081830L;
	
	private Object operationResult;

	public ReplyBody(){
		
	}
	
	public ReplyBody(Object result) {
		this.operationResult = result;
	}

	public Object getOperationResult() {
		return this.operationResult;
	}

	public void setOperationResult(Object operationResult) {
		this.operationResult = operationResult;
	}

}
