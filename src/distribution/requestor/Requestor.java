package distribution.requestor;

import infrastructure.clientrequesthandler.ClientRequestHandler;

import java.io.IOException;
import java.net.UnknownHostException;

import distribution.Invocation;
import distribution.Message;
import distribution.MessageBody;
import distribution.MessageHeader;
import distribution.RequestBody;
import distribution.RequestHeader;
import distribution.Termination;
import distribution.marshaller.Marshaller;

public class Requestor implements IRequestor {
	
	public Requestor() {
		super();
	}

	private ClientRequestHandler crh;

	@Override
	public Termination invoke(Invocation inv) throws UnknownHostException,
			IOException, Throwable {
		
		//o tipo do CRH dependera do arquivo de configuracao 
		ClientRequestHandler crh = new ClientRequestHandler(inv.getClientProxy().getHost(), inv.getClientProxy().getPort());
		Marshaller marshaller = new Marshaller();
		Termination termination= new Termination();
		byte[] msgMarshalled;
		byte[] msgToBeUnMarshalled;
		
		Message msgUnMarshalled = new Message();
		RequestHeader requestHeader = new RequestHeader("", 0, true, 0, inv.getOperationName());
		RequestBody requestBody = new RequestBody(inv.getParameters());
		MessageHeader messageHeader = new MessageHeader("MIOP", 0, false, 0, 0);
		MessageBody messageBody = new MessageBody(requestHeader, requestBody, null, null);
		Message msgToBeMarshalled = new Message(messageHeader, messageBody);
		
		//marshall message
		msgMarshalled = marshaller.marshall(msgToBeMarshalled);
		
		////send marshalled message
		crh.send(msgMarshalled);
		
		//receive reply message
		msgToBeUnMarshalled = crh.receive();
		
		//unmarshall reply message
		msgUnMarshalled = marshaller.unmarshall(msgToBeUnMarshalled);
		
		//return result to Client Proxy
		termination.setCodeResult(msgUnMarshalled.getHeader().getMessageType());
		termination.setResult(msgUnMarshalled.getBody().getReplyBody().getOperationResult());
		
		return termination;
	}

	public ClientRequestHandler getCrh() {
		return crh;
	}

	public void setCrh(ClientRequestHandler crh) {
		this.crh = crh;
	}

}
