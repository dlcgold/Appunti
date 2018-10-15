
public class Studente {
	private String cognome;
	private int matricola;
	
	public String getCognome() {
		return cognome;
	}
	public int getMatricola() {
		return matricola;
	}
	
	public Studente(String cognome, int matricola) {
		this.cognome=cognome;
		this.matricola=matricola;
	}
	@Override
	public String toString() {
		return "Studente [cognome=" + cognome + ", matricola=" + matricola + "]";
	}
	
	public boolean equals(Studente altro) {
		if(altro==null)
			return false;
		if(this.matricola==altro.getMatricola())
			return true;
		return false;
	}
}
