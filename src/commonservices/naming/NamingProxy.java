package commonservices.naming;

import java.io.IOException;
import java.io.Serializable;
import java.net.UnknownHostException;
import java.util.ArrayList;

import distribution.clientproxy.ClientProxy;

public class NamingProxy implements Serializable, INaming {

	private static final long serialVersionUID = -8846996576561055585L;
	
	public NamingProxy(String host, int port) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void bind(String serviceName, ClientProxy clientProxy)
			throws UnknownHostException, IOException, Throwable {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ClientProxy lookup(String serviceName) throws UnknownHostException,
			IOException, Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> list() throws UnknownHostException, IOException,
			Throwable {
		// TODO Auto-generated method stub
		return null;
	}

}
