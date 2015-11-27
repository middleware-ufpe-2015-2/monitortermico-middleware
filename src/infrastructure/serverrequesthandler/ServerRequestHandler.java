package infrastructure.serverrequesthandler;

import java.io.IOException;

public abstract class ServerRequestHandler implements IServerRequestHandler {

	@Override
	public void send(byte[] message) throws IOException, InterruptedException {
		// TODO Auto-generated method stub

	}

	@Override
	public byte[] receive() throws IOException, Throwable {
		// TODO Auto-generated method stub
		return null;
	}

}
