package aplication;

import java.util.List;

import aplication.exceptions.InsufficientMedicoesException;


public interface IMonitor {
	
	public Medicao getMedicao(TipoGrandeza tipo) throws Throwable;
	public void setmedicao(Medicao m);
	public Medicao getMedicaoAnterior();
	public List<Medicao> getCincoUltimasMedicoes() throws InsufficientMedicoesException;

}
