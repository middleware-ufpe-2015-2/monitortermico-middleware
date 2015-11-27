package distribution.requestor;

import java.io.IOException;
import java.net.UnknownHostException;

import distribution.Invocation;
import distribution.Termination;

public interface IRequestor {
	
	public Termination invoke(Invocation inv) throws UnknownHostException, IOException, Throwable;

}
