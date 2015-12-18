package commonservices.naming;

import distribution.Message;
import distribution.Termination;
import distribution.marshaller.Marshaller;


public class NamingInvoker {

	/**
	 * No caso, na camada de aplicação, o serviço de nomes é que fará o papel do servidor.
	 * Então, é necessária a invocação de um serverrequesthandler. A linha ficará comentada,
	 * pois ServerRequestHandler é uma classe abstrata e não pode ser instanciada. Gostaria ]
	 * de deixar o registro do por que o ServerRequestHandler ser abstrato, já que, em tese, 
	 * não teria vários tipos de ServerRequestHandler diferentes.
	 */

	//ServerRequestHandler srhNaInv = new ServerRequestHandler();

	/**
	 * Mensagens que o invoker enviará para o marshall para ser serializada e desserializada
	 */

	byte [] msgParaMarshall;
	byte [] msgDoMarshall;

	/**
	 * Mensagem desserializada pelo marshall
	 */

	Message msgDoSrh = new Message();

	/**
	 * Istanciação de um repositório
	 */
	NamingRepository SerNomes = new NamingRepository();

	/**
	 * Instanciação do Marshall
	 */
	Marshaller marNaInv = new Marshaller();

	/**
	 * Mesagem de retorno
	 * 
	 *  * 
	 */
	Termination terNamiInv = new Termination();


	while (true){

		/**
		 * Recebendo do ServerRequestHandler. Ficará comentado até a resolução do 
		 * problema do serverRequestHandler como abstrato
		 */

		//msgParaMarshall = srhNaInv.receive();

		/**
		 * Mandando a mensagem para o marshall. Essa parte também ficará comentada 
		 * até a solução do serverRequestHandler como abstrato.
		 */

		//msgDoMarSrh = marNaInv.unmarshall(msgParaMarshall);

		/**
		 * receberá o cabeçalho da mensagem e verificará qual operação realizar, reti-
		 * rando os espaços em branco usando o método 'trim()'. O cabeçalho da mensagem
		 * indicará se o serviço a ser feito pelo 'nameInvoker' será de 'bind' ou de 'lookup'.
		 *  Também ficará comentado ao problema do ServerRequestHandler estar abstrato e não 
		 *  poder ser instanciado.
		 */

		//switch (msgDoMarSrh.getBody().getRequestHeader().getOperation.trim())
		//{
		//case "bind":

		/**
		 * O índice 0 do método 'get(0)' de 'getParameters' diz que se deve pegar o 
		 * elemento de índice 0 do arrayList que retorna a partir da chamada do méto-
		 * 'getParameters'. Esse elemento provavelmente é a chave do registro.
		 */

		//String chave = msgDoMarSrh.geyBody().getRequestBody().getParameters.get(0).toString();

		/**
		 *  O índice 1 do método 'get(1)' de 'getParameters' diz que se deve pegar o 
		 * elemento de índice 1 do arrayList que retorna a partir da chamada do méto-
		 * 'getParameters'. Esse elemento é valor do registro. 
		 */

		//String valor = msgDoMarSrh.geyBody().getRequestBody().getParameters.get(1);

		/**
		 * Adicionando ao repositorio e instanciando o termination
		 */

		//this.SerNomes(chave,valor);
		//this.terNamiInv.setResult("");

		/**
		 * Envio da resposta ao marshall
		 */

		//Message MensagemParaMarshall_bind = new Message(new MessageHeader(
		//		"protocolo", 0, 0), new MessageBody(null,
		//		null, new ReplyHeader("", 0, 0), new ReplyBody(
		//				this.terNamiInv.getResult())));

		/**
		 * Mensagem para o marshall e resposta serializada
		 */

		//this.msgDoMarshall = this.marNaInv.marshall(MensagemParaMarshall_reply)

		/**
		 * envio pelo ServerResquestHandler
		 */

		//this.srhNaInv.send(this.msgDoMarshall);
		//break

		//case "lookup":

		/**
		 * receberá a chave
		 */

		//String chave = msgDoMarSrh.geyBody().getRequestBody().getParameters.get(0).toString();

		/**
		 * Envio da resposta ao marshall
		 */

		//Message MensagemParaMarshall_lookup = new Message(new MessageHeader(
		//		"protocolo", 0, 0), new MessageBody(null,
		//		null, new ReplyHeader("", 0, 0), new ReplyBody(
		//				this.terNamiInv.getResult())));

		/**
		 * Resposta do marshall
		 */

		//this.msgDoMarshall = this.marNaInv.marshall(MensagemParaMarshall_reply);

		/**
		 * Envio do serverResquestHandler
		 */

		//this.srhNaInv.send(this.msgDoMarshall);
		//break;

		//case "list":

		/**
		 * O termination é setado com o retorno do método 'list()' do servidor de nomes
		 * 'SerNomes'
		 */

		//this.terNamiInv.setResult(this.SerNomes.list());

		/**
		 * configurando a mensagem de retorno
		 */

		//Message MensagemParaMarshall_list = new Message(new MessageHeader(
		//		"protocolo", 0, 0), new MessageBody(null,
		//		null, new ReplyHeader("", 0, 0), new ReplyBody(
		//				this.terNamiInv.getResult())));

		/**
		 * serializando com o marshall e atribuinto à variável devida
		 */

		//this.msgDoMarshall = this.marNaInv.marshall(MensagemParaMarshall_list);

		/**
		 * Enviando ao SRH
		 */

		//this.srhNaInv.send(this.msgDoMarshall);
		//break;

		//}


	}

}

