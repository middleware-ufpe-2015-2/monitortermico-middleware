package distribution.requestor;

import infrastructure.clientrequesthandler.ClientRequestHandler;
import infrastructure.clientrequesthandler.ClientRequestHandlerType;
import infrastructure.clientrequesthandler.http.HttpClientProxyRequestHandler;
import infrastructure.clientrequesthandler.tcp.TCPClientRequestHandler;
import infrastructure.clientrequesthandler.udp.UDPClientRequestHandler;

import java.io.IOException;
import java.net.UnknownHostException;

import distribution.Invocation;
import distribution.Termination;

public class Requestor implements IRequestor {
	
	public Requestor() {
		super();
	}

	public Requestor(ClientRequestHandlerType crhType) {
		super();
		if(crhType.equals(ClientRequestHandlerType.HTTP)){
			setCrh(new HttpClientProxyRequestHandler());
		} else if(crhType.equals(ClientRequestHandlerType.TCP)){
			setCrh(new TCPClientRequestHandler());
		} else if(crhType.equals(ClientRequestHandlerType.UDP)){
			setCrh(new UDPClientRequestHandler());
		}
	}

	private ClientRequestHandler crh;

	@Override
	public Termination invoke(Invocation inv) throws UnknownHostException,
			IOException, Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	public ClientRequestHandler getCrh() {
		return crh;
	}

	public void setCrh(ClientRequestHandler crh) {
		this.crh = crh;
	}

}
