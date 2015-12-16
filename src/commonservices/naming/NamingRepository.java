package commonservices.naming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

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
	
	
	/**
	 * Serviço de Bind 
	 * @param nomeServico
	 * @param AOR
	 */
	public void bind(String nomeServico, ClientProxy AOR)
	{
		namingRecord.put(nomeServico, AOR);
	}
	
	/**
	 * serviço de lookup
	 * @param nomeServico
	 * @return
	 */
	
	public ClientProxy lookup(String nomeServico)
	{
		ClientProxy retornoLook = new ClientProxy();
		
		if (this.namingRecord.containsKey(nomeServico))
		{
			retornoLook = (ClientProxy)this.namingRecord.get(nomeServico);
		}
		else
		{
			retornoLook.setHost("servico nao existe");
			
			retornoLook.setObjectId(000000);
		}
		
		return retornoLook;
	}
	
	/**
	 * Listamgem dos serviços existentes no serviço de nomes
	 */
	public ArrayList<String> list()
	{
		ArrayList<String> retorList = new ArrayList();
		
		Set<String> x = this.namingRecord.keySet();
		
		Iterator<String> it = x.iterator(); 
		
		while(it.hasNext())
		{
			String atri = it.next();
			
			retorList.add(atri);
		}
		
		return retorList;
	}
	
	/**
	 * Teste da classe
	 * 
	public static void main(String[] args)
	{
		NamingRepository repositorio = new NamingRepository();
		
		ClientProxy proxy = new ClientProxy();
		
		ClientProxy proxy2 = new ClientProxy();
		
		ClientProxy proxy3 = new ClientProxy();
		
		proxy.setHost("local");
		
		proxy.setObjectId(2222);
		
		proxy.setPort(65655);
		
		proxy2.setHost("local");
		
		proxy2.setObjectId(2223);
		
		proxy2.setPort(65656);
		
		proxy3.setHost("local");
		
		proxy3.setObjectId(2224);
		
		proxy3.setPort(6565);
		
		repositorio.setNamingRecord("Conversor", proxy);
		
		repositorio.bind("calculador", proxy);
		
		repositorio.bind("converso", proxy2);
		
		repositorio.bind("matematica", proxy3);
		
		repositorio.list();
		
		repositorio.lookup("calculadora");
		
		repositorio.lookup("calculador");
		
	}
	*/

}
