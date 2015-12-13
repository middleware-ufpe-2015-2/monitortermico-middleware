package distribution.requestor;

import infrastructure.clientrequesthandler.ClientRequestHandler;

import java.io.IOException;
import java.net.UnknownHostException;

import distribution.Invocation;
import distribution.Termination;

public class Requestor implements IRequestor {
	
	public Requestor() {
		super();
	}

	private ClientRequestHandler crh;

	@Override
	public Termination invoke(Invocation inv) throws UnknownHostException,
			IOException, Throwable {
		// TODO Auto-generated method stub
		Termination ter= new Termination();
		return ter;
	}

	public ClientRequestHandler getCrh() {
		return crh;
	}

	public void setCrh(ClientRequestHandler crh) {
		this.crh = crh;
	}

}
