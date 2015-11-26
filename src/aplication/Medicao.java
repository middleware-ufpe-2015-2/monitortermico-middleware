package aplication;


public class Medicao {
	
	private float value;
	private String grandeza;
	private String unidade;
	
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
