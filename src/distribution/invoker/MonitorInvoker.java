package distribution.invoker;

import infrastructure.serverrequesthandler.ServerRequestHandler;

import java.io.IOException;
import java.lang.reflect.Method;

import utilsconf.UtilsConf;
import aplication.Medicao;
import distribution.Message;
import distribution.MessageBody;
import distribution.MessageHeader;
import distribution.ReplyBody;
import distribution.ReplyHeader;
import distribution.Termination;
import distribution.clientproxy.ClientProxy;
import distribution.marshaller.Marshaller;
import distribution.pooling.MedicaoPool;
import distribution.pooling.exception.TamanhoPoolException;

public class MonitorInvoker extends AbstractInvoker {

	private MedicaoPool medicaoPool;
	public MonitorInvoker() {
		super();
		// Cria o pool de objetos de Medicao
		medicaoPool = new MedicaoPool(UtilsConf.TAM_POOL);
	}

	private ServerRequestHandler serverRequestHandler;

	public void invoke(ClientProxy clientProxy) throws IOException, Throwable {

		byte[] msgToBeUnmarshaled = null;
		byte[] marshalledMsg = null;

		Message unmarshaledMsg = new Message();
		Marshaller marshaller = new Marshaller();
		Termination ter = new Termination();

		while (true) {
			msgToBeUnmarshaled = serverRequestHandler.receive();

			unmarshaledMsg = marshaller.unmarshall(msgToBeUnmarshaled);
			
			Medicao remoteObj = null;
			boolean encontrou = false;
			int qtdTentativas = 0;
			long tempoTentativa = 2000L;
			while (!encontrou) {
				try {
					// Obtém o Objeto Remoto
					remoteObj = this.medicaoPool.obterObjeto();
					encontrou = true;
				} catch(TamanhoPoolException tpe) {
					qtdTentativas = qtdTentativas + 1;
					if (qtdTentativas == UtilsConf.QTD_MAX_TENTATIVAS){
						encontrou = true;
					}
					tempoTentativa = tempoTentativa * qtdTentativas; 
					Thread.sleep(tempoTentativa);					
				}
			}
			
			if(qtdTentativas == UtilsConf.QTD_MAX_TENTATIVAS) {
				ServerRequestHandler srh = new ServerRequestHandler(clientProxy.getPort());
				byte[] msgMarshalled = null;
				MessageHeader messageHeader = new MessageHeader("protocolo", 0, false, UtilsConf.COD_ERRO_POOL, 0); 
				MessageBody messageBody = new MessageBody(null, null, 
						new ReplyHeader("", 0, 0), 
						new ReplyBody(UtilsConf.MSG_ERRO_POOL));
				
				Marshaller mrsh = new Marshaller();
				
				Message _add_msgToBeMarshalled = new Message(messageHeader, messageBody);
				msgMarshalled = mrsh.marshall(_add_msgToBeMarshalled);
				srh.send(msgMarshalled);
			} else {
			
				Message _add_msgToBeMarshalled;
				String operation = unmarshaledMsg.getBody().getRequestHeader().getOperation();
				Method method = remoteObj.getClass().getMethod(operation, null);
	
				//Verificar com Nelson se o uso do Reflection ta correto;
				try{
					Object res = method.invoke(remoteObj, null);			
					
					if(operation.contains("set")){
						_add_msgToBeMarshalled = new Message(new MessageHeader(
							"protocolo", 0, false, UtilsConf.COD_SUCESSO, 0), new MessageBody(null, null,
							new ReplyHeader("", 0, 0), new ReplyBody(
									"Set Succeeded")));
					}else{
						ter.setResult(res);
						_add_msgToBeMarshalled = new Message(new MessageHeader(
							"protocolo", 0, false, UtilsConf.COD_SUCESSO, 0), new MessageBody(null, null,
							new ReplyHeader("", 0, 0), new ReplyBody(
									ter.getResult())));
					}
	
					// Marshalling the response
					marshalledMsg = marshaller.marshall(_add_msgToBeMarshalled);
	
					// sending response
					serverRequestHandler.send(marshalledMsg);
	
				}catch(Exception e){
					_add_msgToBeMarshalled = new Message(new MessageHeader(
							"protocolo", 0, false, 1, 0), new MessageBody(null, null,
							new ReplyHeader("", 0, 0), new ReplyBody(
									e.getMessage())));
				}
			}
		}

	}
}