                                                                                                                                                   package distribution.invoker;

import infrastructure.qosobserver.IQosObserver;
import infrastructure.qosobserver.QosObserver;
import infrastructure.serverrequesthandler.ServerRequestHandler;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.GregorianCalendar;

import utilsconf.UtilsConf;
import aplication.server.MonitorImpl;
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
		this.serverRequestHandler = new ServerRequestHandler(clientProxy.getPort());

		Message unmarshaledMsg = new Message();
		Marshaller marshaller = new Marshaller();
		Termination ter = new Termination();
		Calendar hora_inicial = new GregorianCalendar();
		IQosObserver qosobserver = new QosObserver();

		while (true) {
			msgToBeUnmarshaled = serverRequestHandler.receive();

			unmarshaledMsg = marshaller.unmarshall(msgToBeUnmarshaled);
			
			MonitorImpl remoteObj = null;
			boolean encontrou = false;
			int qtdTentativas = 0;
			long tempoTentativa = 200L;
			
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
				Class<?>[] parameterTypes = new Class<?>[unmarshaledMsg.getBody().getRequestBody().getParameters().size()];
				for(int x = 0; x < parameterTypes.length; x++){
					parameterTypes[x] = unmarshaledMsg.getBody().getRequestBody().getParameters().get(x).getClass();
				}
				Method method = remoteObj.getClass().getMethod(operation, parameterTypes);
	
				hora_inicial = qosobserver.tempo1();
				try{
					Object res = null;
					if(unmarshaledMsg.getBody().getRequestBody().getParameters().size() > 0){
						res = method.invoke(remoteObj, unmarshaledMsg.getBody().getRequestBody().getParameters().get(0));
					} else {
						res = method.invoke(remoteObj);
					}
					
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
					medicaoPool.retornarObjeto(remoteObj);
					qosobserver.tempo2(hora_inicial);
					
				}catch(Exception e){
					_add_msgToBeMarshalled = new Message(new MessageHeader(
							"protocolo", 0, false, 1, 0), new MessageBody(null, null,
							new ReplyHeader("", 0, 0), new ReplyBody(
									e.getMessage())));
					medicaoPool.retornarObjeto(remoteObj);

					// Marshalling the response
					marshalledMsg = marshaller.marshall(_add_msgToBeMarshalled);
	
					// sending response
					serverRequestHandler.send(marshalledMsg);					
				}
			}
		}

	}
}
