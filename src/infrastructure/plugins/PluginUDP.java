package infrastructure.plugins;

import java.io.IOException;
import java.net.ConnectException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import aplication.exceptions.ServerNotFoundException;
import distribution.Message;

public class PluginUDP extends Plugin {

	private DatagramSocket socket = null;
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
	public void send(byte[] msg) throws UnknownHostException, IOException, ServerNotFoundException {

		DatagramPacket req = null;

		try{
			byte[] msgSize = String.valueOf(msg.length).getBytes();

			// se for cliente, abre conexao socket
			if (!isServer) {
				socket = new DatagramSocket();
				InetAddress serv = InetAddress.getByName(host);

				// envia o tamanho da mensagem
				DatagramPacket req2 = new DatagramPacket(msgSize, msgSize.length, serv, port);
				socket.send(req2);

				// caso seja cliente, vai enviar pra o ip e a porta passado
				req = new DatagramPacket(msg, msg.length, serv, port);

			} else {
				//envia o tamanho da mensagem
				DatagramPacket req2 = new DatagramPacket(msgSize, msgSize.length,
						recievePackege.getAddress(), recievePackege.getPort());

				socket.send(req2);
				// caso seja servidor, vai enviar o packege de volta ao endereco
				// recebido
				req = new DatagramPacket(msg, msg.length, recievePackege.getAddress(), recievePackege.getPort());
			}

			// envia datagrama contendo a mensagem
			socket.send(req);


			// Servidor fecha a conexao aqui
			if (isServer)
				socket.close();
		}catch(ConnectException e){
			throw new ServerNotFoundException();
		}catch(IOException e){
			System.err.println(e.getMessage() + "\n" + e.getStackTrace());
		}

	}

	@Override
	public byte[] receive() throws IOException, ServerNotFoundException {

		// cria um socket UDP
		if (isServer)
			socket = new DatagramSocket(port);

		byte[] buffer = new byte[10000];
		byte[] msg = null;

		try{
			// cria datagrama para receber request do cliente
			recievePackege = new DatagramPacket(buffer, buffer.length);
			socket.receive(recievePackege);
			String message = new String(recievePackege.getData());


			sentMessageSize = trimForInt(message);
			buffer = new byte[sentMessageSize];
			
			recievePackege = new DatagramPacket(buffer, buffer.length);
			socket.receive(recievePackege);

			// envia resposta
			msg = recievePackege.getData();

			// cliente fecha a conexao aqui
			if (!isServer)
				socket.close();
		}catch(ConnectException e){
			throw new ServerNotFoundException();
		}catch(IOException e){
			System.err.println(e.getMessage() + "\n" + e.getStackTrace());
		}
		return msg;
	}

	public int trimForInt (String message) {
		StringBuffer sb = new StringBuffer();

		char[] caracteres = message.toCharArray();

		for (Character caracter : caracteres) {
			if (Character.isDigit(caracter)) {
				sb.append(caracter);
			}
		}

		return Integer.valueOf(sb.toString());
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
