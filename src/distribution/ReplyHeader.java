package distribution;

import java.io.Serializable;

public class ReplyHeader implements Serializable {
	
	private static final long serialVersionUID = 3821550794762706600L;
	
	private String serviceContext;
	private int requestId;
	private int replyStatus;
	public ReplyHeader(String string, int i, int j) {
		// TODO Auto-generated constructor stub
	}
	public String getServiceContext() {
		return serviceContext;
	}
	public void setServiceContext(String serviceContext) {
		this.serviceContext = serviceContext;
	}
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	public int getReplyStatus() {
		return replyStatus;
	}
	public void setReplyStatus(int replyStatus) {
		this.replyStatus = replyStatus;
	}

}
