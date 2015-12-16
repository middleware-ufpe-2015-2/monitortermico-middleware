package distribution;

import java.io.Serializable;

public class MessageHeader implements Serializable {

	private static final long serialVersionUID = 1989256266404640637L;
	
	public MessageHeader(){
		super();
	}
	
	public MessageHeader(String magic, int v, boolean order, int type, long size) {
		this.magic = magic;
		this.version = v;
		this.byteOrder = order;
		this.messageType = type;
		this.messageSize = size;
	}
	
	private String magic;
	private int version;
	private boolean byteOrder;
	private int messageType;
	private long messageSize;
	public String getMagic() {
		return magic;
	}
	public void setMagic(String magic) {
		this.magic = magic;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public boolean isByteOrder() {
		return byteOrder;
	}
	public void setByteOrder(boolean byteOrder) {
		this.byteOrder = byteOrder;
	}
	public int getMessageType() {
		return messageType;
	}
	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}
	public long getMessageSize() {
		return messageSize;
	}
	public void setMessageSize(long messageSize) {
		this.messageSize = messageSize;
	}

}
