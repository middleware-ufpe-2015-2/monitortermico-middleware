package aplication;

import java.util.List;


public interface IMonitor {
	
	public Medicao getMedicao(TipoGrandeza tipo) throws Throwable;
	public void setmedicao(Medicao m);
	public Medicao getMedicaoAnterior();
	public List<Medicao> getCincoUltimasMedicoes();

}
