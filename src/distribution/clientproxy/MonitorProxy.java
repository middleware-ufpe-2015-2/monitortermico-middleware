package distribution.clientproxy;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import aplication.IMonitor;
import aplication.Medicao;
import aplication.TipoGrandeza;
import distribution.Invocation;
import distribution.Termination;
import distribution.requestor.Requestor;

public class MonitorProxy extends ClientProxy implements IMonitor {

	private static final long serialVersionUID = -5924054021194325894L;
	
	public MonitorProxy(String h, int p) {
		this.host = h;
		this.port = p;
	}

	public MonitorProxy() {
		super();
	}

	@Override
	public Medicao getMedicao(TipoGrandeza tipo) {
		
		//preparando as variáveis
		Invocation inv = new Invocation();
		Termination ter = new Termination();
		ArrayList<Object> parameters = new ArrayList<Object>();
		class Local {
		}
		;
		String methodName;
		Requestor requestor = new Requestor();
		
		// Preenche variáveis temporárias
		methodName = Local.class.getEnclosingMethod().getName();
		parameters.add(tipo);
				
		// preenche os parâmetros da chamada
		inv.setIpAdress(this.getHost());
		inv.setOperationName(methodName);
		inv.setParameters(parameters);
		
		
		try {
			//chamando o requestor
			ter = requestor.invoke(inv);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		// TODO Auto-generated method stub
		return  (Medicao) ter.getResult();
	}

	@Override
	public void setmedicao(Medicao m) {
		
		//preparando as variáveis
		Invocation inv = new Invocation();
		Termination ter = new Termination();
		ArrayList<Object> parameters = new ArrayList<Object>();
		class Local {
		}
		;
		String methodName;
		Requestor requestor = new Requestor();
		
		// information received from Client
		methodName = Local.class.getEnclosingMethod().getName();
		parameters.add(m);
				
		// information sent to Requestor
		inv.setIpAdress(this.getHost());
		inv.setOperationName(methodName);
		inv.setParameters(parameters);
		
		
		try {
			ter = requestor.invoke(inv);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub

	}

	@Override
	public Medicao getMedicaoAnterior() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Medicao> getCincoUltimasMedicoes() {
		// TODO Auto-generated method stub
		return null;
	}

}
