package aplication;

import java.util.List;

import aplication.exceptions.InsufficientMedicoesException;
import aplication.exceptions.ServerNotFoundException;
import distribution.pooling.exception.TamanhoPoolException;


public interface IMonitor {
	
	public Medicao getMedicao(TipoGrandeza tipo) throws ServerNotFoundException, TamanhoPoolException;
	public void setmedicao(Medicao m) throws ServerNotFoundException, TamanhoPoolException;
	public Medicao getMedicaoAnterior() throws ServerNotFoundException, TamanhoPoolException;
	public List<Medicao> getCincoUltimasMedicoes() throws InsufficientMedicoesException, ServerNotFoundException, TamanhoPoolException;

}
