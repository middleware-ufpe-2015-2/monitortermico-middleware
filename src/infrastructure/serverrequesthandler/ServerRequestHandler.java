package infrastructure.serverrequesthandler;

import infrastructure.plugins.Plugin;
import infrastructure.plugins.ProtocolType;

import java.io.IOException;

public class ServerRequestHandler {
	private int port;
	private Plugin plug;

	public ServerRequestHandler(int port) throws IOException {
		
		this.port = port;
		ProtocolType protocolo = new ProtocolType();
		plug = protocolo.getProtocol(this.port);

	}

	public byte [] receive() throws IOException, Throwable {
		byte [] rcvMsg = null;
		rcvMsg=plug.receive();				
		return rcvMsg;
	}

	public void send(byte [] msg) throws IOException, InterruptedException {
		plug.send(msg);
		return;
	}
}
