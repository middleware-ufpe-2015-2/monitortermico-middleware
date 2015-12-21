package aplication.server;

import java.rmi.Remote;
import java.util.ArrayList;
import java.util.List;

import aplication.IMonitor;
import aplication.Medicao;
import aplication.TipoGrandeza;
import aplication.exceptions.InsufficientMedicoesException;

public class MonitorImpl implements IMonitor, Remote {
	
	private Medicao medicao;
	private List<Medicao> medicoes = new ArrayList<Medicao>();

	@Override
	public Medicao getMedicao(TipoGrandeza tipo) {
		this.medicao = new Medicao();
		if(TipoGrandeza.TEMPERATURA.equals(tipo)){
			medicao.setGrandeza("Temperatura");
			medicao.setUnidade("Celsius");
		} else {
			medicao.setGrandeza("Umidade");
			medicao.setUnidade("Kg/m³");
		}
		medicao.setValue((float) (Math.random()*100));
		this.medicoes.add(medicao);
		return this.medicao;
	}

	@Override
	public void setmedicao(Medicao m) {
		this.medicao = m;
	}

	@Override
	public Medicao getMedicaoAnterior() {
		return this.medicao;
	}

	@Override
	public List<Medicao> getCincoUltimasMedicoes() throws InsufficientMedicoesException {
		int lastPos = medicoes.size();
		if(lastPos > 5){
			return medicoes.subList(lastPos-1, lastPos-5);
		} else {
			throw new InsufficientMedicoesException(medicoes.size());
		}
	}

}
