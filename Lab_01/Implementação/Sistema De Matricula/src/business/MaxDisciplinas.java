package business;

public class MaxDisciplinas extends Exception {

	private static final long serialVersionUID = 2L;
	private boolean obrigatorias;
	private int num;
	
	public MaxDisciplinas(boolean obrigatorias, int num) {
		super();
		this.obrigatorias = obrigatorias;
		this.num = num;
	}
	
	public String getMassege() {
		if(obrigatorias==true) {
			return ("Voc� alcan�ou limite de "+ this.num + "disciplinas obrigat�rias cadastradas.");
		}
		else {
			return ("Voc� alcan�ou limite de "+ this.num + "disciplinas optativas cadastradas.");
		}
		
	}

}
