package distribution.invoker;

import infrastructure.qosobserver.QosObserver;
import infrastructure.serverrequesthandler.ServerRequestHandler;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Calendar;

import aplication.Medicao;
import distribution.Message;
import distribution.MessageBody;
import distribution.MessageHeader;
import distribution.ReplyBody;
import distribution.ReplyHeader;
import distribution.Termination;
import distribution.clientproxy.ClientProxy;
import distribution.invoker.AbstractInvoker;
import distribution.marshaller.Marshaller;


public class MonitorInvoker extends AbstractInvoker {

	public MonitorInvoker() {
		super();
	}

	private ServerRequestHandler serverRequestHandler;

	public void invoke(ClientProxy clientProxy) throws IOException, Throwable {

		byte[] msgToBeUnmarshaled = null;
		byte[] marshalledMsg = null;

		Message unmarshaledMsg = new Message();
		Marshaller marshaller = new Marshaller();
		Termination ter = new Termination();
		Medicao remoteObj = new Medicao();
		Calendar hora_inicial = Calendar.getInstance();

		while (true) {
			msgToBeUnmarshaled = serverRequestHandler.receive();

			unmarshaledMsg = marshaller.unmarshall(msgToBeUnmarshaled);
			Message _add_msgToBeMarshalled;
			
			String operation = unmarshaledMsg.getBody().getRequestHeader().getOperation();
			Method method = remoteObj.getClass().getMethod(operation, null);
			
			QosObserver qosobserver = new QosObserver(); 

			//inicia a contagem do tempo do qos observer
			hora_inicial = qosobserver.tempo1();

			try{
				Object res = method.invoke(remoteObj, null);	
				ReplyBody replyBody = new ReplyBody();
				if(operation.contains("set")){
					
					replyBody.setOperationResult("Set Suceeded");
					_add_msgToBeMarshalled = new Message(new MessageHeader(
						"protocolo", 0, false, 0, 0), new MessageBody(null, null,
						new ReplyHeader("", 0, 0), replyBody));
				}else{
					ter.setResult(res);
					replyBody.setOperationResult(ter.getResult());
					_add_msgToBeMarshalled = new Message(new MessageHeader(
						"protocolo", 0, false, 0, 0), new MessageBody(null, null,
						new ReplyHeader("", 0, 0), replyBody));
				}

				// Marshalling the response
				marshalledMsg = marshaller.marshall(_add_msgToBeMarshalled);

				// sending response
				serverRequestHandler.send(marshalledMsg);
				qosobserver.tempo2(hora_inicial);

			}catch(Exception e){
				ReplyBody replyBody = new ReplyBody();
				replyBody.setOperationResult(e.getMessage());
				_add_msgToBeMarshalled = new Message(new MessageHeader(
						"protocolo", 0, false, 1, 0), new MessageBody(null, null,
						new ReplyHeader("", 0, 0), replyBody));
			}
		}

	}
}