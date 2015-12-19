package aplication.server;

import java.io.Serializable;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

import commonservices.naming.NamingProxy;

import distribution.clientproxy.MonitorProxy;
import distribution.invoker.MonitorInvoker;

public class MonitorServer implements Serializable {

	private static final long serialVersionUID = 5325309740816413781L;
	
	public static void main(String[] args) throws RemoteException, AlreadyBoundException, Throwable {
		MonitorInvoker monitorInvoker = new MonitorInvoker();
		
		//remote object
		MonitorProxy monitorProxy = new MonitorProxy();
		monitorProxy.setHost("localhost");
		monitorProxy.setPort(2001);
		
		//obtain instance of Naming Service
		NamingProxy namingService = new NamingProxy("localhost", 1313);
		
		//register monitor in Naming service
		namingService.bind("Monitor", monitorProxy);
		
		System.out.println("Monitor server ready...");
		
		monitorInvoker.invoke(monitorProxy);
	
	}

}
