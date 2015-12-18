package commonservices.naming;

import java.io.IOException;

import distribution.clientproxy.ClientProxy;

public class NamingServer {
	
	public static void main(String[] args) throws IOException, Throwable {
		NamingInvoker namingInvoker = new NamingInvoker();
		
		System.out.println("Naming service ready...");
		ClientProxy clientProxy = new ClientProxy();
		clientProxy.setPort(1313);
		namingInvoker.invoke(clientProxy);
	}	

}
