package distribution;

import java.io.Serializable;

public class Termination implements Serializable {
	
	private static final long serialVersionUID = 7450457498729281381L;
	
	private Object result;

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

}
