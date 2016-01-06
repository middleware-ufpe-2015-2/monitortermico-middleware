package aplication;

import java.util.List;

import aplication.exceptions.InsufficientMedicoesException;


public interface IMonitor {
	
	public Object getMedicao(TipoGrandeza tipo);
	public void setmedicao(Medicao m);
	public Medicao getMedicaoAnterior();
	public List<Medicao> getCincoUltimasMedicoes() throws InsufficientMedicoesException;

}
