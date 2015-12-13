package commonservices.naming;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import distribution.Termination;
import distribution.clientproxy.ClientProxy;

public interface INaming {
	
	public void bind(String serviceName, ClientProxy clientProxy) throws UnknownHostException, IOException, Throwable;
	
	/**
	 * Esse serviço foi alterado para retornar um Termination. Esse termination será uma
	 * AOR, com todos os atributos necessários para a identificação, principalmente o 
	 * Objeto ID
	 */
	
	public Termination lookup(String serviceName) throws UnknownHostException, IOException, Throwable;
	
	public ArrayList<String> list() throws UnknownHostException, IOException, Throwable;
}
