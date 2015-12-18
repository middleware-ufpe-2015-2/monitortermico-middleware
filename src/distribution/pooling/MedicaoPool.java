package distribution.pooling;

import aplication.Medicao;


public class MedicaoPool extends ObjectPool<Medicao> {

	Medicao medicao;

	public MedicaoPool(int tamanho) {
		super(tamanho);
	}

	@Override
	public Medicao criarObjeto() {
		return medicao = new Medicao(); 
	}
}