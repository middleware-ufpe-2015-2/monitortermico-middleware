package infrastructure.qosobserver;

import java.io.IOException;

public interface IQosObserver {
	
	public void tempo1() throws IOException, InterruptedException;
	
	public void tempo2(double t1) throws IOException, InterruptedException, ClassNotFoundException;

}
