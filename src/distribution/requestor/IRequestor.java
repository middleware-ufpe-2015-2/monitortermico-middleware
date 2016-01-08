package distribution.requestor;

import java.io.IOException;
import java.net.UnknownHostException;

import aplication.exceptions.ServerNotFoundException;
import distribution.Invocation;
import distribution.Termination;

public interface IRequestor {
	
	public Termination invoke(Invocation inv) throws ServerNotFoundException;

}
