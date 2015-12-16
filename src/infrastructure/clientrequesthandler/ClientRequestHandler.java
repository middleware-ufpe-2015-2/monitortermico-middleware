package infrastructure.clientrequesthandler;

import java.io.IOException;

public class ClientRequestHandler implements IClientRequestHandler {

	public ClientRequestHandler(String host, int port) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void send(byte[] message) throws IOException, InterruptedException {
	}

	@Override
	public byte[] receive() throws IOException, InterruptedException,ClassNotFoundException {
		return null;
	}

}
