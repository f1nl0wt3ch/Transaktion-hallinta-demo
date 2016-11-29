package kohdeluokat;

import java.util.Date;

public class Lainaus {

	private int numero;
	private Date lainausPvm;
	private Asiakas lainaaja;
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public Date getLainausPvm() {
		return lainausPvm;
	}
	public void setLainausPvm(Date lainausPvm) {
		this.lainausPvm = lainausPvm;
	}
	public Asiakas getLainaaja() {
		return lainaaja;
	}
	public void setLainaaja(Asiakas lainaaja) {
		this.lainaaja = lainaaja;
	}
	
}
