package aplication.model;

import java.math.BigDecimal;

public class Medicao {
	
	private BigDecimal temp;
	private BigDecimal umid;
	
	public BigDecimal getTemp() {
		return temp;
	}
	public void setTemp(BigDecimal temp) {
		this.temp = temp;
	}
	public BigDecimal getUmid() {
		return umid;
	}
	public void setUmid(BigDecimal umid) {
		this.umid = umid;
	}
	public Medicao() {
		super();
	}
	public Medicao(BigDecimal temp, BigDecimal umid) {
		super();
		this.temp = temp;
		this.umid = umid;
	}
	@Override
	public String toString() {
		return "Medicao [temp=" + temp + ", umid=" + umid + "]";
	}
}
