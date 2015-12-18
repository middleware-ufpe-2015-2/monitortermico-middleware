package distribution.clientproxy;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import utilsconf.UtilsConf;
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
	public Medicao getMedicao(TipoGrandeza tipo) throws Throwable {
		
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
		inv.setClientProxy(new ClientProxy());
		inv.getClientProxy().setHost(this.host);
		inv.getClientProxy().setPort(this.port);
		inv.setOperationName(methodName);
		inv.setParameters(parameters);
		
		Medicao medicao = null;
		boolean lancaExcecao = false;
		String msgErro = "";
		try {
			//chamando o requestor
			ter = requestor.invoke(inv);
			
			// @ Result sent back to Client
			if (ter.getCodeResult() == UtilsConf.COD_SUCESSO) {
				medicao = (Medicao) ter.getResult();
			} else {
				lancaExcecao = true;
				
				switch (ter.getCodeResult()) {
				case UtilsConf.COD_ERRO_POOL:
					msgErro = UtilsConf.MSG_ERRO_POOL;
					break;
				default:
					break;
				}
			}
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
		
		if (ter == null || lancaExcecao) {
			throw new Exception(msgErro);
		}
				
		// TODO Auto-generated method stub
		return  medicao;
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
		inv.getClientProxy().setHost(this.getHost());
		inv.getClientProxy().setPort(this.port);
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

		// preenche os parâmetros da chamada
		inv.getClientProxy().setHost(this.getHost());
		inv.getClientProxy().setPort(this.port);
		inv.setOperationName(methodName);


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
	public List<Medicao> getCincoUltimasMedicoes() {

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

		// preenche os parâmetros da chamada
		inv.getClientProxy().setHost(this.getHost());
		inv.getClientProxy().setPort(this.port);
		inv.setOperationName(methodName);


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
		return  (List<Medicao>) ter.getResult();

	}

}
