package distribution.clientproxy;

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
	public Object getMedicao(TipoGrandeza tipo) {

		// preparando as variaveis
		Invocation inv = new Invocation();
		Termination ter = new Termination();
		ArrayList<Object> parameters = new ArrayList<Object>();
		class Local {
		}
		;
		String methodName;
		Requestor requestor = new Requestor();

		// Preenche variaveis temporarias
		methodName = Local.class.getEnclosingMethod().getName();
		parameters.add(tipo);

		// preenche os parametros da chamada
		inv.setClientProxy(new ClientProxy());
		inv.getClientProxy().setHost(this.getHost());
		inv.getClientProxy().setPort(this.port);
		inv.setOperationName(methodName);
		inv.setParameters(parameters);

		ter = requestor.invoke(inv);
		TableExcpetions table = new TableExcpetions();
		Object result = ter.getResult();

//		if (ter.getCodeResult() != null) {
//
//			result.setErro(table.buscarError(ter.getCodeResult()));
//
//		}

		return result;
	}

	@Override
	public void setmedicao(Medicao m) {

		// preparando as variaveis
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
		inv.getClientProxy().setHost(this.getHost());
		inv.getClientProxy().setPort(this.port);
		inv.setOperationName(methodName);
		inv.setParameters(parameters);

		ter = requestor.invoke(inv);
	}

	@Override
	public Medicao getMedicaoAnterior() {

		// preparando as variaveis
		Invocation inv = new Invocation();
		Termination ter = new Termination();
		ArrayList<Object> parameters = new ArrayList<Object>();
		class Local {
		}
		;
		String methodName;
		Requestor requestor = new Requestor();

		// Preenche variaveis temporarias
		methodName = Local.class.getEnclosingMethod().getName();

		// preenche os parametros da chamada
		inv.getClientProxy().setHost(this.getHost());
		inv.getClientProxy().setPort(this.port);
		inv.setOperationName(methodName);

		ter = requestor.invoke(inv);

		TableExcpetions table = new TableExcpetions();
		Medicao result = (Medicao) ter.getResult();
//
//		if (ter.getCodeResult() != null) {
//			result.setErro(table.buscarError(ter.getCodeResult()));
//		}

		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Medicao> getCincoUltimasMedicoes() {

		// preparando as variaveis
		Invocation inv = new Invocation();
		Termination ter = new Termination();
		ArrayList<Object> parameters = new ArrayList<Object>();
		class Local {
		}
		;
		String methodName;
		Requestor requestor = new Requestor();

		// Preenche variaveis temporarias
		methodName = Local.class.getEnclosingMethod().getName();

		// preenche os parametros da chamada
		inv.setClientProxy(new ClientProxy());
		inv.getClientProxy().setHost(this.getHost());
		inv.getClientProxy().setPort(this.port);
		inv.setOperationName(methodName);
		inv.setParameters(parameters);

		ter = requestor.invoke(inv);

		// TODO Auto-generated method stub
		return ((List<Medicao>) ter.getResult());
	}

}