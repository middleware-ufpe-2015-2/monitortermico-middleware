package commonservices.naming;

import java.io.IOException;
import java.io.Serializable;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;

import distribution.Invocation;
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
				
		Invocation invocacao = new Invocation();
		ArrayList<Object> mapa = new ArrayList<Object>();
		class Local{
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
		
		invocacao.setPortNumber(clientProxy.getPort());
		
		/**
		 * Configuração do objetoId enviado ao objeto remoto por meio da configura
		 * ção do parâmetro 'ObjectId' do objeto invocação. O lado servidor do midleware
		 * invocará o método 'bind' de NamingProxy  
		 */
		
		invocacao.setObjectId(clientProxy.getObjectId());
		
		/**
		 * Configuração do endereço host do servidor enviado pelo lado servidor da camada
		 * de aplicação ao namingProxy
		 */
		
		invocacao.setIpAdress(clientProxy.getHost());

		/**
		 * introdução ao objeto ArrayList 'mapa' de alguns parâmetros contidos no 'ClientProxy',
		 * como o nome do método e do objeto 'clientProxy'
		 */
		
		mapa.add(clientProxy);
		mapa.add(nomeMetodo);
		invocacao.setParameters(mapa);
		invocacao.setOperationName(nomeMetodo);
		
		/**
		 * A invocação é repassada ao resquestor, para então serializar a mensagem pelo marshall
		 * e ser enviada pela reade. 
		 */
		requisicao.invoke(invocacao);
		
		
	}

	@Override
	public ClientProxy lookup(String serviceName) throws UnknownHostException,
			IOException, Throwable {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> list() throws UnknownHostException, IOException,
			Throwable {
		// TODO Auto-generated method stub
		return null;
	}

}
