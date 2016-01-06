package commonservices.naming;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import distribution.clientproxy.ClientProxy;

public interface INaming {
	
	public void bind(String serviceName, ClientProxy clientProxy) throws UnknownHostException, IOException, Throwable;
	
	public Object lookup(String serviceName) throws UnknownHostException, IOException, Throwable;
	
	public ArrayList<String> list() throws UnknownHostException, IOException, Throwable;
}
