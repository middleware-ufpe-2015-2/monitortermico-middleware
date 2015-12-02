package distribution;

import java.io.Serializable;

public class ReplyBody implements Serializable {

	private static final long serialVersionUID = -3049759111020081830L;
	
	private Object operationResult;

	public Object getOperationResult() {
		return operationResult;
	}

	public void setOperationResult(Object operationResult) {
		this.operationResult = operationResult;
	}

}
