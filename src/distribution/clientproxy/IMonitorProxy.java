package distribution.clientproxy;

import aplication.Medicao;
import aplication.TipoGrandeza;

public interface IMonitorProxy {
	
	
	public Medicao getMedicao(TipoGrandeza tipo);

	public void setmedicao(Medicao m) ;

}
