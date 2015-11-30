public class Invoker implements IInvoker{

	public void invoke(ClientProxy clientProxy) throws IOException, Throwable {
		
		ServerRequesqHandler srh = new ServerRequesqHandler(clientProxy.getPort());

		byte[] msgToBeUnmarshaled = null;
		byte[] marshalledMsg = null;

		//who is implementing this? and where?
		Message unmarshaledMsg = new Message();
		Marshaller marshaller = new Marshaller()
		//Ask the prof what it is
		//To be implemented
		Termination ter = new Termination();
		//Medidor Termico is the application
		Medidor remoteObj = new AppImpl()

		while(true){
			msgToBeUnmarshaled = srh.receive();

			unmarshaledMsg = marshaller.unmarshall(msgToBeUnmarshaled);

			switch(unmarshaledMsg.getBody().getRequestHandler().getOperation()){
				case "getValue":
					ter.setResult(remoteObj.getValue());
					Message _add_msgToBeMarshalled = new Message(new MessageHeader("protocolo", 0, 0), new MessageBody(null, null, new ReplyHeader("", 0, 0), new ReplyBody(ter.getResult())));

					//Marshalling the response
					marshalledMsg = marshaller.marshall(_add_msgToBeMarshalled);

					//sending response
					srh.send(marshalledMsg);
					break;

				case "setValue":
					//Ask Nelson how to do when it's a set method
					break;

				case "getGrandeza":
					ter.setResult(remoteObj.getGrandeza());
					Message _add_msgToBeMarshalled = new Message(new MessageHeader("protocolo", 0, 0), new MessageBody(null, null, new ReplyHeader("", 0, 0), new ReplyBody(ter.getResult())));

					//Marshalling the response
					marshalledMsg = marshaller.marshall(_add_msgToBeMarshalled);

					//sending response
					srh.send(marshalledMsg);
					break;

				case "setGrandeza":
					//Ask Nelson how to do when it's a set method
					break;

				case "getUnidade":
					ter.setResult(remoteObj.getUnidade());
					Message _add_msgToBeMarshalled = new Message(new MessageHeader("protocolo", 0, 0), new MessageBody(null, null, new ReplyHeader("", 0, 0), new ReplyBody(ter.getResult())));

					//Marshalling the response
					marshalledMsg = marshaller.marshall(_add_msgToBeMarshalled);

					//sending response
					srh.send(marshalledMsg);
					break;

				case "setUnidade":
					//Ask Nelson how to do when it's a set method
					break;
			}	
		}

	}
}