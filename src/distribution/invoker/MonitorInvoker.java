package distribution.invoker;

import infrastructure.serverrequesthandler.ServerRequestHandler;
import infrastructure.serverrequesthandler.ServerRequestHandlerType;
import infrastructure.serverrequesthandler.http.HTTPServerRequestHandler;
import infrastructure.serverrequesthandler.tcp.TCPServerRequestHandler;
import infrastructure.serverrequesthandler.udp.UDPServerRequestHandler;

import java.io.IOException;

import aplication.Medicao;
import distribution.Message;
import distribution.MessageBody;
import distribution.MessageHeader;
import distribution.ReplyBody;
import distribution.ReplyHeader;
import distribution.Termination;
import distribution.clientproxy.ClientProxy;
import distribution.marshaller.Marshaller;

public class MonitorInvoker extends AbstractInvoker {

	public MonitorInvoker() {
		super();
	}

	public MonitorInvoker(ServerRequestHandlerType type) {
		if (type.equals(ServerRequestHandlerType.HTTP)) {
			this.serverRequestHandler = new HTTPServerRequestHandler();
		} else if (type.equals(ServerRequestHandlerType.TCP)) {
			this.serverRequestHandler = new TCPServerRequestHandler();
		} else if (type.equals(ServerRequestHandlerType.UDP)) {
			this.serverRequestHandler = new UDPServerRequestHandler();
		}
	}

	private ServerRequestHandler serverRequestHandler;

	public void invoke(ClientProxy clientProxy) throws IOException, Throwable {

		byte[] msgToBeUnmarshaled = null;
		byte[] marshalledMsg = null;

		Message unmarshaledMsg = new Message();
		Marshaller marshaller = new Marshaller();
		Termination ter = new Termination();
		Medicao remoteObj = new Medicao();

		while (true) {
			msgToBeUnmarshaled = serverRequestHandler.receive();

			unmarshaledMsg = marshaller.unmarshall(msgToBeUnmarshaled);
			Message _add_msgToBeMarshalled;

			switch (unmarshaledMsg.getBody().getRequestHeader().getOperation()) {
			case "getValue":
				ter.setResult(remoteObj.getValue());
				_add_msgToBeMarshalled = new Message(new MessageHeader(
						"protocolo", 0, 0), new MessageBody(null, null,
						new ReplyHeader("", 0, 0), new ReplyBody(
								ter.getResult())));

				// Marshalling the response
				marshalledMsg = marshaller.marshall(_add_msgToBeMarshalled);

				// sending response
				serverRequestHandler.send(marshalledMsg);
				break;

			case "setValue":
				// Ask Nelson how to do when it's a set method
				break;

			case "getGrandeza":
				ter.setResult(remoteObj.getGrandeza());
				_add_msgToBeMarshalled = new Message(new MessageHeader(
						"protocolo", 0, 0), new MessageBody(null, null,
						new ReplyHeader("", 0, 0), new ReplyBody(
								ter.getResult())));

				// Marshalling the response
				marshalledMsg = marshaller.marshall(_add_msgToBeMarshalled);

				// sending response
				serverRequestHandler.send(marshalledMsg);
				break;

			case "setGrandeza":
				// Ask Nelson how to do when it's a set method
				break;

			case "getUnidade":
				ter.setResult(remoteObj.getUnidade());
				_add_msgToBeMarshalled = new Message(new MessageHeader(
						"protocolo", 0, 0), new MessageBody(null, null,
						new ReplyHeader("", 0, 0), new ReplyBody(
								ter.getResult())));

				// Marshalling the response
				marshalledMsg = marshaller.marshall(_add_msgToBeMarshalled);

				// sending response
				serverRequestHandler.send(marshalledMsg);
				break;

			case "setUnidade":
				// Ask Nelson how to do when it's a set method
				break;
			}
		}

	}
}