package distribution.invoker;

import java.io.IOException;

import distribution.clientproxy.ClientProxy;

public interface IInvoker {
	public void invoke(ClientProxy clientProxy) throws IOException, Throwable;
}
	