package business;

import java.io.Serializable;
import java.time.LocalDate;

public class MatriculaForaDoPrazo extends Exception implements Serializable{

	private static final long serialVersionUID = 1L;
	private LocalDate prazo;
	private boolean tipo;

	public MatriculaForaDoPrazo(LocalDate prazo, boolean tipo) {
		super();
		this.prazo = prazo;
		this.tipo = tipo;
	}
	
	public String getMassege() {
		if(this.tipo == true) {
			return ("O prazo para realizar a matricula venceu em "+ this.prazo);
		}
		else
		{
			return ("O prazo para realizar a matricula inicia em "+ this.prazo);
		}
		
	}
	
}
