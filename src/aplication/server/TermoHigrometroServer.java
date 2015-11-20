package aplication.server;

import java.math.BigDecimal;

import aplication.ITermoHigrometro;
import aplication.model.Medicao;

public class TermoHigrometroServer implements ITermoHigrometro {

	@Override
	public Medicao sendTemperatureUmidityData() {
		Medicao m = new Medicao();
		m.setTemp(new BigDecimal(Math.random()*100));
		m.setUmid(new BigDecimal(Math.random()*100));
		return m;
	}

}
