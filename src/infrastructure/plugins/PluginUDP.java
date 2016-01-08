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

//		try{
			byte[] msgSize = String.valueOf(msg.length).getBytes();

			// se for cliente, abre conexao socket
			if (!isServer) {
				s = new DatagramSocket();
				InetAddress serv = InetAddress.getByName(host);

				// envia o tamanho da mensagem
				DatagramPacket req2 = new DatagramPacket(String.valueOf(msg.length).getBytes(), msgSize.length, serv, port);
				s.send(req2);

				// caso seja cliente, vai enviar pra o ip e a porta passado
				req = new DatagramPacket(msg, msg.length, serv, port);

			} else {
				//envia o tamanho da mensagem
				DatagramPacket req2 = new DatagramPacket(String.valueOf(msg.length).getBytes(), msgSize.length,
						recievePackege.getAddress(), recievePackege.getPort());

				s.send(req2);
				// caso seja servidor, vai enviar o packege de volta ao endereco
				// recebido
				req = new DatagramPacket(msg, msg.length, recievePackege.getAddress(), recievePackege.getPort());
			}

			// envia datagrama contendo a mensagem
			s.send(req);

			// s.setSoTimeout(10000); // timeout em ms

			// Servidor fecha a conexao aqui
			if (isServer)
				s.close();
//		}catch(IOException e){
//			System.err.println(e.getMessage() + "\n" + e.getStackTrace());
//		}

	}

	@Override
	public byte[] receive() throws IOException {

		// cria um socket UDP
		if (isServer)
			s = new DatagramSocket(port);

		byte[] buffer = new byte[10000];
		byte[] msg = null;

//		try{
			// cria datagrama para receber request do cliente
			recievePackege = new DatagramPacket(buffer, buffer.length);
			s.receive(recievePackege);
			String message = new String(recievePackege.getData());


			sentMessageSize = trimForInt(message);
			buffer = new byte[sentMessageSize];

			s.receive(recievePackege);

			// envia resposta
			msg = recievePackege.getData();

			// cliente fecha a conexao aqui
			if (!isServer)
				s.close();
//		}catch(IOException e){
//			System.err.println(e.getMessage() + "\n" + e.getStackTrace());
//		}
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
