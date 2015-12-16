package distribution.marshaller;

import java.io.IOException;

import distribution.Message;

public interface IMarshaller {
	
	public byte[] marshall(Message msgToBeMarshalled) throws IOException, InterruptedException;
	
	public Message unmarshall(byte[] msgToBeUnmarshalled) throws IOException, InterruptedException, ClassNotFoundException;

}
