package infrastructure.clientrequesthandler;

import infrastructure.plugins.Plugin;
import infrastructure.plugins.ProtocolType;

import java.io.IOException;

import aplication.exceptions.ServerNotFoundException;

public class ClientRequestHandler {
	
	private Plugin protocol;

	public ClientRequestHandler(String host, int port) throws IOException {
		
		ProtocolType protocolo = new ProtocolType();		
		protocol = protocolo.getProtocol(host,port);

	}

	public void send(byte [] msg) throws IOException, InterruptedException, ServerNotFoundException {
		protocol.send(msg);
	}

	public byte [] receive() throws IOException, InterruptedException, ClassNotFoundException, ServerNotFoundException {
		
		byte [] msg;
		msg = protocol.receive();
		return msg;
	}

}