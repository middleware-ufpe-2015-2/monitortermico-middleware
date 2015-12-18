package distribution.pooling;

import java.util.concurrent.ConcurrentLinkedQueue;

import utilsconf.UtilsConf;
import distribution.pooling.exception.TamanhoPoolException;

public abstract class ObjectPool<T> {

	private ConcurrentLinkedQueue<T> pool;
	
	/**
	 * Cria o pool informando o n�mero de objetos a serem criados.
	 */
	public ObjectPool(final int tamanho) {
		pool = new ConcurrentLinkedQueue<T>();
		for (int i = 0; i < tamanho; i++) {
			pool.add(criarObjeto());
		}
	}

	protected abstract T criarObjeto();

	/**
	 * Obt�m o pr�ximo objeto do pool. 
	 * Se for tentado obter um objeto e n�o houver algum presente no pool, uma exce��o � lan�ada.
	 */
	public T obterObjeto() throws TamanhoPoolException {
		T objeto;
		
		if ((objeto = pool.poll()) == null) {
			throw new TamanhoPoolException(UtilsConf.MSG_ERRO_POOL);
		} else {
			return objeto;
		}
	}

	public void retornarObjeto(T objeto) {
		if (objeto == null) {
			return;
		}
		this.pool.offer(objeto);
	}
}