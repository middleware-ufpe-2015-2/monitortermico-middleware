package infrastructure.plugins;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class PluginUDP extends Plugin {

	private DatagramSocket s = null;
	private DatagramPacket recievePackege = null;
	
	private boolean isServer;
	private int port;
	private String host;
	
	private int sentMessageSize;
	private int receiveMessageSize;

	public PluginUDP(String host, int port, boolean isServer) throws IOException {
		super();
		this.host = host;
		this.port = port;
		this.isServer = isServer;
	}

	@Override
	public void send(byte[] msg) throws UnknownHostException, IOException {
		DatagramPacket req = null;
		
		//se for cliente, abre conexao socket
		if (!isServer) {
			s = new DatagramSocket();
			InetAddress serv = InetAddress.getByName(host);
			
			//caso seja cliente, vai enviar pra o ip e a porta passado
			req = new DatagramPacket(msg, msg.length, serv, port);
		} else {
			
			//caso seja servidor, vai enviar o packege de volta ao endereco recebido 
			req = new DatagramPacket(msg, msg.length, recievePackege.getAddress(), recievePackege.getPort());
		}

		// envia datagrama contendo a mensagem 
		s.send(req);

		// s.setSoTimeout(10000); // timeout em ms

		//Servidor fecha a conexao aqui 
		if (isServer)
			s.close();

	}

	@Override
	public byte[] receive() throws IOException {
		// cria um socket UDP
		if (isServer)
			s = new DatagramSocket(port);
		
		byte[] buffer = new byte[10000];
		byte[] msg = null;

		// cria datagrama para receber request do cliente
		recievePackege = new DatagramPacket(buffer, buffer.length);
		s.receive(recievePackege);

		// envia resposta
		msg = recievePackege.getData();
		
		//cliente fecha a conexao aqui 
		if (!isServer)
			s.close();
		return msg;
	}

	public int getSentMessageSize() {
		return sentMessageSize;
	}

	public void setSentMessageSize(int sentMessageSize) {
		this.sentMessageSize = sentMessageSize;
	}

	public int getReceiveMessageSize() {
		return receiveMessageSize;
	}

	public void setReceiveMessageSize(int receiveMessageSize) {
		this.receiveMessageSize = receiveMessageSize;
	}

}
