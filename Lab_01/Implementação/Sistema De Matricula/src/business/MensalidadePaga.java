package business;

import java.io.Serializable;
import java.time.LocalDate;

public class MensalidadePaga extends Exception implements Serializable{

	private static final long serialVersionUID = 3L;
	private LocalDate data;
	public MensalidadePaga(LocalDate dataPagamento) {
		this.data=dataPagamento;
	}
	
	public String getMassege() {
		return ("A mensalidade deste mes j� foi paga no dia " + this.data);
	}
}
