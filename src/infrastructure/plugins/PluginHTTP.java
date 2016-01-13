package infrastructure.plugins;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import aplication.exceptions.ServerNotFoundException;

public class PluginHTTP extends Plugin {
	
	public PluginHTTP(String host, int port, boolean isServer) {
		super();
		this.isServer = isServer;
		this.host = host;
		this.port = port;
	}

	private boolean isServer;
	private String host = null;
	private int port;

	private Socket clientSocket;
	private ServerSocket welcomeSocket = null;
	private HttpURLConnection con = null;

	OutputStream outToServer=null;
	private BufferedWriter outToClient = null;
	BufferedReader in = null;
	
	Encoding64 objBase64 = new Encoding64();

	@Override
	public void send(byte[] msg) throws UnknownHostException, IOException, ServerNotFoundException {

		try {
			
			String msgAuxiliar;
			
			if (!isServer) {
				
				byte[] auxiliar = null;
				
				URL url = new URL("http://" + host + ":" + port);
				con = (HttpURLConnection) url.openConnection();

				con.setDoOutput(true);
				con.setDoInput(true);
				con.setRequestMethod("POST");
				con.setUseCaches(false);

				auxiliar  = objBase64.encode64(msg);
				con.setRequestProperty("Content-length", String.valueOf(auxiliar .toString().getBytes().length));
				con.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");

				outToServer = con.getOutputStream();
				//manda para o servidor os bytes da mensagem em 64
				outToServer.write(objBase64.encode64(msg));

			} else {
				msgAuxiliar = objBase64.parsString(msg);
				String header = "HTTP/1.1 200 OK\r\n";
				header = header + ("Content-Length: " + String.valueOf(msgAuxiliar.length()) + "\r\n");
				header = header + ("\r\n");
				header = header + (msgAuxiliar); //passa os bytes para uma string 64
				outToClient.write(header);

				outToClient.close();
				in.close();
				clientSocket.close();
				welcomeSocket.close();				
			}
		}catch(ConnectException e){
			throw new ServerNotFoundException();
		}catch (IOException e) {
			System.err.println(e.getMessage() + "\n" + e.getStackTrace());
		}
	}

	@Override
	public byte[] receive() throws IOException, ServerNotFoundException {
		byte[] msg = null;
		try {
			if (isServer) {

				welcomeSocket = new ServerSocket(port);
				clientSocket = welcomeSocket.accept();
				in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				outToClient = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

				int contentLength = 0;

				while (true) {
					final String line = in.readLine();
					final String contentLengthStr = "Content-Length: ";
					if (line.startsWith(contentLengthStr)) {
						contentLength = Integer.parseInt(line.substring(contentLengthStr.length()));
					}

					if (line.length() == 0) {
						break;
					}
				}

				final char[] content = new char[contentLength];
				in.read(content);
				
				msg = objBase64.decode64(String.valueOf(content));
				
			} else {
				in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String temp;
				String conteudo=null;

				while ((temp = in.readLine()) != null)
					conteudo = temp;

				msg = objBase64.decode64(conteudo);

				outToServer.close();
				in.close();
				con.disconnect();
			}
		}catch(ConnectException e){
			throw new ServerNotFoundException();
		}catch (IOException e) {
			System.err.println(e.getMessage() + "\n" + e.getStackTrace());
		}
		return msg;
	}

}
