package infrastructure.plugins;

import java.io.IOException;
import java.net.UnknownHostException;

public abstract class Plugin  {	
	
	public abstract void send(byte[] msg) throws UnknownHostException, IOException;

	public abstract byte[] receive() throws IOException;
}
