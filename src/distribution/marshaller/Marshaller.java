package distribution.marshaller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import distribution.Message;

public class Marshaller implements IMarshaller {

	@Override
	public byte[] marshall(Message msgToBeMarshalled) throws IOException,
			InterruptedException {
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
		objectStream.writeObject(msgToBeMarshalled);
		return byteStream.toByteArray();
	}

	@Override
	public Message unmarshall(byte[] msgToBeUnmarshalled) throws IOException,
			InterruptedException, ClassNotFoundException {
		ByteArrayInputStream byteStream = new ByteArrayInputStream(msgToBeUnmarshalled);
		ObjectInputStream objectStream = new ObjectInputStream(byteStream);
		return (Message) objectStream.readObject();
	}

}
