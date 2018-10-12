
public class Menu {
	private String nomeRistorante;
	private Piatto[] piatti;
	
	public Menu(String ristorante, int numeroPiatti) {
		this.nomeRistorante = ristorante;
		if(numeroPiatti>1)
			piatti = new Piatto [numeroPiatti];
		else
			piatti = new Piatto [30];
	}
	
	public Menu(String ristorante) {
		this(ristorante,30);
	}
	
	public String getNomeRistorante() {
		return nomeRistorante;
	}

	public void setNomeRistorante(String nomeRistorante) {
		this.nomeRistorante = nomeRistorante;
	}

	public boolean aggiungiPiatto(Piatto piatto) {
		if(piatto==null)
			return false;
		for(int i=0;i<piatti.length;i++) {
			if(piatti[i]!=null && piatti[i].equals(piatto))
				return false;
		}
		for(int i=0;i<piatti.length;i++) {
			if(piatti[i]==null) {
				piatti[i]=piatto;
				return true;
			}
		}
		return false;
	}
	
	public Piatto eliminaPiatto(Piatto piatto) {
		Piatto altro=null;
		for(int i=0; i<piatti.length;i++) {
			if(piatti[i]!=null && piatti[i].equals(piatto)) {
				altro = piatto;
				piatti[i]=null;
				return altro;
			}	
		}
		return null;
	}
	
	public Piatto[] dammiTuttiIPiatti(String categoria) {
		int conta = 0;
		for(int i=0; i<piatti.length;i++) {
			if(piatti[i]!=null && piatti[i].getCategoria()==categoria)
				conta++;
		}
		Piatto [] tuttiIPiatti = new Piatto [conta];
		for(int i=0; i<tuttiIPiatti.length;i++) {
			for(int o=0; o<piatti.length;o++) {
				if(piatti[o]!=null && piatti[o].getCategoria()==categoria)
					tuttiIPiatti[i]=piatti[o];
			}
		}
		return tuttiIPiatti;
	}
	
	public Piatto[] dammiTuttiIPrimi() {
		int conta = 0;
		for(int i=0; i<piatti.length;i++) {
			if(piatti[i]!=null && piatti[i].getCategoria()=="primo")
				conta++;
		}
		if(conta==0)
			return null;
		Piatto [] tuttiIPiatti = new Piatto [conta];
		for(int i=0; i<tuttiIPiatti.length;i++) {
			for(int o=0; o<piatti.length;o++) {
				if(piatti[o]!=null && piatti[o].getCategoria()=="primo")
					tuttiIPiatti[i]=piatti[o];
			}
		}
		return tuttiIPiatti;
	}
}
