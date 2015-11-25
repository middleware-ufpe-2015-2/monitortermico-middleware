package infrastructure.serverrequesthandler;

import java.io.IOException;

public interface IServerRequestHandler {
	
	public void send(byte[] message) throws IOException, InterruptedException;
	public byte[] receive() throws IOException, Throwable;

}
