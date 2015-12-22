package aplication.server;

import java.rmi.Remote;
import java.util.ArrayList;
import java.util.Date;
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
		medicao.setDataMedicao(new Date());
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
		if(lastPos >= 5){
			ArrayList<Medicao> lista = new ArrayList<>();
			for(Medicao m : medicoes.subList(lastPos-5, lastPos)){
				lista.add(m);
			}
			return lista;
		} else {
			throw new InsufficientMedicoesException(medicoes.size());
		}
	}

}
