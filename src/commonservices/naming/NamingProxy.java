package commonservices.naming;

import java.io.IOException;
import java.io.Serializable;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;

import distribution.Invocation;
import distribution.Termination;
import distribution.clientproxy.ClientProxy;
import distribution.requestor.Requestor;

public class NamingProxy extends ClientProxy implements Serializable, INaming {

	private static final long serialVersionUID = -8846996576561055585L;
	
	public NamingProxy(String host, int port) {
		// TODO Auto-generated constructor stub
		this.host = host;
		this.port=port;
	}

	@Override
	public void bind(String serviceName, ClientProxy clientProxy)
			throws UnknownHostException, IOException, Throwable {
		// TODO Auto-generated method stub
		
		/**
		 * Aqui, deu-se a entender por 'MonitorServer' que um objeto do 
		 * tipo 'NamingProxy' irá receber um objeto do tipo 'MonitorProxy', 
		 * e não um objeto do tipo 'CalculatorProxy', como nas implementa-
		 * ções exemplo postas pelo professor
		 **/
				
		/**
		 * Processo de invocação
		 */
		Invocation invocBind = new Invocation();
		
		/**
		 * Arraylist que conterá os parâmetro
		 */
		ArrayList<Object> mapa = new ArrayList<Object>();
				
		class Local
		{
			
		}
		;
		// Recuperação do nome do próprio método
		String nomeMetodo = Local.class.getEnclosingMethod().getName();
		
		Requestor requisicao = new Requestor();
				
		/**
		 * Configuração da porta em que o servidor irá trabalhar por meio
		 * da configuração do parêmetro 'porta' de invocação a ser realizada
		 * pelo servidar na camada de aplicação.
		 */
		
//		invocBind.setPortNumber(clientProxy.getPort());
		
		/**
		 * Configuração do objetoId enviado ao objeto remoto por meio da configura
		 * ção do parâmetro 'ObjectId' do objeto invocação. O lado servidor do midleware
		 * invocará o método 'bind' de NamingProxy  
		 */
		
//		invocBind.setObjectId(clientProxy.getObjectId());
		
		/**
		 * Configuração do endereço host do servidor enviado pelo lado servidor da camada
		 * de aplicação ao namingProxy, nome da operação
		 */
		
		invocBind.setOperationName(nomeMetodo);
		invocBind.setClientProxy(clientProxy);
//		invocBind.setIpAdress(clientProxy.getHost());

		/**
		 * introdução ao objeto ArrayList 'mapa' de alguns parâmetros contidos no 'ClientProxy',
		 * como o nome do método e do objeto 'clientProxy'
		 */
		
		mapa.add(nomeMetodo);
		mapa.add(clientProxy);
		invocBind.setParameters(mapa);
		
		
		/**
		 * A invocação é repassada ao resquestor, para então serializar a mensagem pelo marshall
		 * e ser enviada pela reade. 
		 */
		requisicao.invoke(invocBind);
	}

	@Override
	public Termination lookup(String serviceName) throws UnknownHostException,
			IOException, Throwable {
		// TODO Auto-generated method stub
		
		
	/**
	 * O retorno será pensado como uma AOR. Como o termination está pensado co
	 * mo o termination está codificado para retornar um objeto geral, esse objeto 
	 * será considerado um AOR. Tomar-se-á em conta que o lookup
	 * necessita apenas de setar o nome de serviço ('lookup') no invocation e o 
	 * nome do tipo de serviço a ser buscado no servidor ('no caso, conversão').
	 * Ao final, o invocation será repassado ao requestor.
	 */
		Termination AOR = new Termination();
		
	/**
	 * Instanciação da invocação que será trabalhada pelo requestor
	 */
		Invocation invocLookup = new Invocation();
		
	/**
	 * Isntanciação do requestor que irá receber a requisição
	 */
		
		Requestor requisi = new Requestor();
	
	/**
	 *Estrutura de dado que irá ser enviado ao requestor
	 */
		
		ArrayList<Object> parametros = new ArrayList();
		
	/**
	 * Preparação para o reflexion
	 */
		class Local{
		}
		;
	/**
	 * envia o nome do método ao requestor
	 */
		String nomeMetod = Local.class.getEnclosingMethod().getName();
		parametros.add(nomeMetod);
	/**
	 * Configuração dos parâmetros retornados 
	 */
		invocLookup.setParameters(parametros);
		invocLookup.setOperationName(serviceName);
		parametros.add(invocLookup);
		
	/** 
	 * Envia a requisição ao requestor. Na chamada da linha 146 está nula porque
	 * o requestor não está implementado
	 */
		
		AOR = requisi.invoke(invocLookup);
		
		return AOR;
	}

	@Override
	public ArrayList<String> list() throws UnknownHostException, IOException,
			Throwable {
		
		/**
		 * Instanciação da invocação
		 */
		Invocation invList = new Invocation();
		
		/**
		 * Instanciação do resultado
		 */
		Termination terList = new Termination();
		
		/**
		 *Instanciação do retorno 
		 */
		ArrayList resultado = new ArrayList();
		
		/**
		 * Instanciação do Requestos
		 */
		
		Requestor requiList = new Requestor();
		
		/**
		 * Classe local
		 */
		class Local{
			
		}
		;
		
		/**
		 * Reflexão. Invoca o próprio nome do método
		 */
		String nomList = Local.class.getEnclosingMethod().getName();
		
		/**
		 * configuração da invocação
		 */
		invList.setOperationName(nomList);
		
		/**
		 * Envio da invocação ao requestor e atribuição do retorno ao
		 * termination 'terList'
		 */
		
		terList = requiList.invoke(invList);
		
		/**
		 * Obtenção do ArrayList listando os serviços a partir do Termination 'terList'
		 */
		
		resultado = (ArrayList)terList.getResult();
				  
		 /**
		 * retorno do resultado
		 */
		return resultado;
	}
	
	
	/** Para testar os métodos, descomente os códigos abaixo
	 * 
	 *
	 
	public static void main(String args[]) throws UnknownHostException, IOException, Throwable
	{
		ClientProxy cliente = new ClientProxy();
		
		NamingProxy nomes = new NamingProxy("localHost",1313);
		
		nomes.bind("transdutor", cliente);
		
		nomes.bind("Calculadora",cliente);
		
		String  transdutor = "conversao";
		
		nomes.lookup(transdutor);
	}
	*/

}
