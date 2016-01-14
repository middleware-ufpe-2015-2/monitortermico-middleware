package distribution.clientproxy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import aplication.IMonitor;
import aplication.Medicao;
import aplication.TipoGrandeza;
import aplication.exceptions.ServerNotFoundException;
import distribution.Invocation;
import distribution.Termination;
import distribution.pooling.exception.TamanhoPoolException;
import distribution.requestor.Requestor;

public class ClientProxy implements IMonitor, Serializable {


	private static final long serialVersionUID = -5924054021194325894L;
	
	protected String host;
	protected int port;
	protected int objectId;
	
	public String getHost() {
		return host;
	}
	
	public void setHost(String host) {
		this.host = host;
	}
	
	public int getPort() {
		return port;
	}
	
	public void setPort(int port) {
		this.port = port;
	}
	
	public int getObjectId() {
		return objectId;
	}
	
	public void setObjectId(int objectId) {
		this.objectId = objectId;
	}

	public ClientProxy(String h, int p) {
		this.host = h;
		this.port = p;
	}

	public ClientProxy() {
		super();
	}
	
	public Termination invokeMethod(String methodname, Object parameter) throws ServerNotFoundException, TamanhoPoolException{
		Invocation inv = new Invocation();
		Termination ter = new Termination();
		ArrayList<Object> parameters = new ArrayList<Object>();
		
		Requestor requestor = new Requestor();

		// preenche os parametros da chamada
		inv.setClientProxy(new ClientProxy());
		inv.getClientProxy().setHost(this.getHost());
		inv.getClientProxy().setPort(this.port);
		inv.setOperationName(methodname);
		if (parameter != null){
			parameters.add(parameter);			
		}		
		inv.setParameters(parameters);
		ter = requestor.invoke(inv);		
		
		return ter;
		
	}

	@Override
	public Medicao getMedicao(TipoGrandeza tipo) throws ServerNotFoundException, TamanhoPoolException{
		class Local{};
		Medicao result = (Medicao) invokeMethod(Local.class.getEnclosingMethod().getName(), tipo).getResult();	
		return result;
	}

	@Override
	public void setmedicao(Medicao m) throws ServerNotFoundException, TamanhoPoolException{
		class Local{};
		invokeMethod(Local.class.getEnclosingMethod().getName(), m);
	}

	@Override
	public Medicao getMedicaoAnterior() throws ServerNotFoundException, TamanhoPoolException{

		class Local {};	
		Medicao result = (Medicao) invokeMethod(Local.class.getEnclosingMethod().getName(), null).getResult();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Medicao> getCincoUltimasMedicoes() throws ServerNotFoundException, TamanhoPoolException{

		class Local{};
		// TODO Auto-generated method stub
		return ((List<Medicao>) invokeMethod(Local.class.getEnclosingMethod().getName(), null).getResult());
	}

}