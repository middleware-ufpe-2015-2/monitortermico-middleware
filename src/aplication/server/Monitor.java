package aplication.server;

import aplication.Medicao;

import commonservices.IMonitor;
import commonservices.TipoGrandeza;

public class Monitor implements IMonitor {
	
	private Medicao medicao;

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
		return this.medicao;
	}

	@Override
	public void setmedicao(Medicao m) {
		this.medicao = m;
	}

}
