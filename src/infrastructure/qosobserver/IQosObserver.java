package infrastructure.qosobserver;

import java.io.IOException;
import java.util.Calendar;

public interface IQosObserver {
	
	public Calendar tempo1() throws IOException, InterruptedException;
	
	public void tempo2(Calendar t1) throws IOException, InterruptedException, ClassNotFoundException;

}
