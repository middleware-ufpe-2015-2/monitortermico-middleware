package commonservices.naming;

import java.util.ArrayList;
import java.util.HashMap;

import distribution.clientproxy.ClientProxy;

public class NamingRepository {
	
	private HashMap<String,ClientProxy> namingRecord = new HashMap<String,ClientProxy>();
	
	public NamingRepository(){
		
	}
	
	/**
	 * O seguinte método adicionará um serviço ao serviço de nomes. Aqui, toma-se em conta que o lado 
	 * servidor solicitou um pedido de 'Bind'. Esse pedido de 'Bind' é enviado ao requestor, ao marshall,
	 * ao clientReqestHandler, serverRequestHandler (O serviço de nomes, neste caso, fazendo o papel do 
	 * lado servidor), marshall, invoker, que então invocará a classe 'NamingRepository'.
	 * @return
	 */
	
	public HashMap<String,ClientProxy> getNamingRecord() {
		return namingRecord;
	}

	
	/**
	 * O seguinte método adicionará uma AOR ao 'NamingRepository'. Aqui, o AOR está pensado como um objeto
	 *  'ClientProxy', já que existe subsídios por parte do invocation em setar 'ip','porta', 'objetoId', 
	 *  'OperationName','parâmetros'. Todas as configurações acima de 'invocation' podem ser fetias a partir
	 *  de um objeto 'ClienteProxy', que possui como atributos 'host', 'porta', 'ObjetoID'.
	 * @param nomeServico
	 * @param AOR
	 */
	public void setNamingRecord(String nomeServico,ClientProxy AOR) {
		namingRecord.put(nomeServico,AOR);
	}

}
