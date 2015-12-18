package infrastructure.clientrequesthandler;

import infrastructure.plugins.Plugin;
import infrastructure.plugins.ProtocolType;

import java.io.IOException;

public class ClientRequestHandler {
	
	private Plugin protocol;

	public ClientRequestHandler(String host, int port) throws IOException {
		
		ProtocolType protocolo = new ProtocolType();		
		protocol = protocolo.getProtocol(host,port);

	}

	public void send(byte [] msg) throws IOException, InterruptedException {
		protocol.send(msg);
	}

	public byte [] receive() throws IOException, InterruptedException, ClassNotFoundException {
		
		byte [] msg;
		msg = protocol.receive();
		return msg;
	}

}