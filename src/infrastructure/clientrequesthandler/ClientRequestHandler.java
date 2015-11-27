package infrastructure.clientrequesthandler;

import java.io.IOException;

public abstract class ClientRequestHandler implements IClientRequestHandler {

	@Override
	public void send(byte[] message) throws IOException, InterruptedException {
	}

	@Override
	public byte[] receive() throws IOException, InterruptedException,ClassNotFoundException {
		return null;
	}

}
