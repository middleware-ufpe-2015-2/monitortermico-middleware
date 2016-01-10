package aplication;

import java.util.List;

import aplication.exceptions.InsufficientMedicoesException;
import aplication.exceptions.ServerNotFoundException;


public interface IMonitor {
	
	public Medicao getMedicao(TipoGrandeza tipo) throws ServerNotFoundException;
	public void setmedicao(Medicao m) throws ServerNotFoundException;
	public Medicao getMedicaoAnterior() throws ServerNotFoundException;
	public List<Medicao> getCincoUltimasMedicoes() throws InsufficientMedicoesException, ServerNotFoundException;

}
