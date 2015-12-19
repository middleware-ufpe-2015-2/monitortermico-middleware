package distribution.pooling;

import aplication.server.MonitorImpl;


public class MedicaoPool extends ObjectPool<MonitorImpl> {

	MonitorImpl medicaoImpl;

	public MedicaoPool(int tamanho) {
		super(tamanho);
	}

	@Override
	public MonitorImpl criarObjeto() {
		return medicaoImpl = new MonitorImpl(); 
	}
}