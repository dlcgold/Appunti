
public class Brano {
	private String titolo;
	private int durataInSecondi;
	
	public String getTitolo() {
		return titolo;
	}
	public int getDurataInSecondi() {
		return durataInSecondi;
	}
	
	public Brano(String titolo, int durataInSecondi) {
		//super();
		this.titolo = titolo;
		this.durataInSecondi = durataInSecondi;
	}
	
	@Override
	public String toString() {
		return "Brano [titolo=" + titolo + ", durataInSecondi=" + durataInSecondi + "]";
	}
	
	public boolean equals(Brano altroBrano) {
		if(altroBrano==null)
			return false;
		if(altroBrano==this)
			return true;
		if(this.titolo.equalsIgnoreCase(altroBrano.titolo))
			return true;
		return false;
	}
}
