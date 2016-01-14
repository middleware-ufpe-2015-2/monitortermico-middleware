package infrastructure.plugins;

import java.io.IOException;
import java.net.UnknownHostException;

import aplication.exceptions.ServerNotFoundException;
import distribution.pooling.exception.TamanhoPoolException;

public abstract class Plugin  {	
	
	public abstract void send(byte[] msg) throws UnknownHostException, IOException, ServerNotFoundException;

	public abstract byte[] receive() throws IOException, ServerNotFoundException, TamanhoPoolException;
}
