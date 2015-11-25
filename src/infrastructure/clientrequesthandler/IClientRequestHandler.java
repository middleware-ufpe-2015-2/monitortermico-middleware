package infrastructure.clientrequesthandler;

import java.io.IOException;

public interface IClientRequestHandler {
	
	public void send(byte[] message) throws IOException, InterruptedException;
	public byte[] receive() throws IOException, InterruptedException, ClassNotFoundException;

}
