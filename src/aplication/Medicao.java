package aplication;

import java.io.Serializable;

import distribution.clientproxy.ErroMiddleware;


public class Medicao implements Serializable {
	
	private static final long serialVersionUID = 1653576778172104059L;
	
	private float value;
	private String grandeza;
	private String unidade;
	private ErroMiddleware erro;
	
	public ErroMiddleware getErro() {
		return erro;
	}
	public void setErro(ErroMiddleware erro) {
		this.erro = erro;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	public String getGrandeza() {
		return grandeza;
	}
	public void setGrandeza(String grandeza) {
		this.grandeza = grandeza;
	}
	public String getUnidade() {
		return unidade;
	}
	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}
	
	public Medicao() {
		super();
	}
	public Medicao(float value, String grandeza, String unidade) {
		super();
		this.value = value;
		this.grandeza = grandeza;
		this.unidade = unidade;
	}

}
