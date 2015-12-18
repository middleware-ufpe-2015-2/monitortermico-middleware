package infrastructure.plugins;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class PluginTCP extends Plugin {
	private boolean isServer;
	private String host = null;
	private int port;
	
	private Socket clientSocket;
	private ServerSocket welcomeSocket = null;
	
	private DataOutputStream out=null;
	private DataInputStream in=null;
		
	private int sentMessageSize;
	private int receiveMessageSize;
	

	public PluginTCP(String host, int port, boolean isServer) throws IOException {
		super();
		this.host = host;
		this.port = port;
		this.isServer = isServer;
	}

	public void send(byte[] msg) throws IOException {
		
		//se for cliente, abre conexao socket
		if (!isServer) {
			clientSocket = new Socket(this.host, this.port);
			out = new DataOutputStream(clientSocket.getOutputStream());
			in = new DataInputStream(clientSocket.getInputStream());
		}
		
		//prepera os dados para o envio 
		sentMessageSize = msg.length;
		out.writeInt(sentMessageSize);
		out.write(msg, 0, sentMessageSize);
		out.flush(); //forca a escrita no stream 
		
		//quando for servidor, fecha os streams e o socket aqui 
		if(isServer) {
			clientSocket.close();
			welcomeSocket.close();
			out.close();
			in.close();
		}
		return;
	}

	public byte[] receive() throws IOException {
		
		//se for servidor, cria um servidor socket 
		if (isServer) {
			welcomeSocket = new ServerSocket(port);
			clientSocket = welcomeSocket.accept(); 
			out = new DataOutputStream(clientSocket.getOutputStream());
			in = new DataInputStream(clientSocket.getInputStream());
		}

		//preprara para receber os dados 
		byte[] msg = null;		
		receiveMessageSize = in.readInt();
		msg = new byte[receiveMessageSize];
		in.read(msg, 0, receiveMessageSize);
		
		//quando for cliente, fecha os streams e o socket aqui 
		if (!isServer) {
			clientSocket.close();
			out.close();
			in.close();
		}

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
