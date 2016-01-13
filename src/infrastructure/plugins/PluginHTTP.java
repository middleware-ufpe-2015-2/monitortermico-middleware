package infrastructure.plugins;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;

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

	@Override
	public void send(byte[] msg) throws UnknownHostException, IOException {

		try {
			if (!isServer) {

				URL url = new URL("http://" + host + ":" + port);
				con = (HttpURLConnection) url.openConnection();

				con.setDoOutput(true);
				con.setDoInput(true);
				con.setRequestMethod("POST");
				con.setUseCaches(false);

				con.setRequestProperty("Content-length", String.valueOf(msg.length));
				con.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");

				outToServer = con.getOutputStream();
				outToServer.write(msg);

			} else {

				String header = "HTTP/1.1 200 OK\r\n";
				header = header + ("Content-Length: " + String.valueOf(msg.length) + "\r\n");
				header = header + ("\r\n");
				header = header + (msg.toString());
				outToClient.write(header);

				outToClient.close();
				in.close();
				clientSocket.close();
			}
		} catch (IOException e) {
			System.err.println(e.getMessage() + "\n" + e.getStackTrace());
		}
	}

	@Override
	public byte[] receive() throws IOException {
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

				msg = new String(content).getBytes();
				
			} else {
				in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String temp;
				String conteudo = null;

				while ((temp = in.readLine()) != null)
					conteudo = conteudo + temp;

				msg = conteudo.getBytes();

				outToServer.close();
				in.close();
				con.disconnect();
			}
		} catch (IOException e) {
			System.err.println(e.getMessage() + "\n" + e.getStackTrace());
		}
		return msg;
	}

}
