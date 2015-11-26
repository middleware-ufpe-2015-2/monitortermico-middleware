package commonservices;

import aplication.Medicao;

public interface IMonitor {
	
	public Medicao getMedicao(TipoGrandeza tipo);
	public void setmedicao(Medicao m);

}
