package infrastructure.plugins;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ProtocolType {
	private String name;
	private static final String caminho = "protocolChoice.txt";
	private Plugin protocol;

	public ProtocolType() throws IOException {
		name = identificaProtocol();
	}

	public Plugin getProtocol(String host, int port) throws IOException {

		if (name.equalsIgnoreCase("TCP")) {
			protocol = new PluginTCP(host, port, false);
		} else if (name.equalsIgnoreCase("UDP")) {
			protocol = new PluginUDP(host, port, false);
		} else if (name.equalsIgnoreCase("HTTP")) {
			protocol = new PluginHTTP(host, port, false);
		}

		return protocol;
	}

	public Plugin getProtocol(int port) throws IOException {

		if (name.equalsIgnoreCase("TCP")) {
			protocol = new PluginTCP(null, port, true);
		} else if (name.equalsIgnoreCase("UDP")) {
			protocol = new PluginUDP(null, port, true);
		} else if (name.equalsIgnoreCase("HTTP")) {
			protocol = new PluginHTTP(null, port, true);
		}

		return protocol;
	}

	private String identificaProtocol() throws IOException {

		BufferedReader file = new BufferedReader(new FileReader(caminho));
		String protocolo = file.readLine().toLowerCase();
		file.close();

		return protocolo;
	}

	public String getName() {
		return name;
	}

}
