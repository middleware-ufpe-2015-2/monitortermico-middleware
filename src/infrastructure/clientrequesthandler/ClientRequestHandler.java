package infrastructure.clientrequesthandler;

import java.io.IOException;
import infrastructure.plugins.Plugin;
import infrastructure.plugins.ProtocolType;

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