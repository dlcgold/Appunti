package esame1;

public class Brano {
	private String titolo;
	private int durataInSecondi;
	
	public Brano(String titolo, int durataInSecondi) {
		super();
		this.titolo = titolo;
		this.durataInSecondi = durataInSecondi;
	}

	@Override
	public String toString() {
		return "Brano [titolo=" + titolo + ", durataInSecondi=" + durataInSecondi + "]";
	}
	
	public boolean equals(Brano brano) {
		if(brano==null)
			return false;
		if(brano==this)
			return true;
		if(brano.titolo.equalsIgnoreCase(this.titolo))
			return true;
		return false;
	}

	public String getTitolo() {
		return titolo;
	}

	public int getDurataInSecondi() {
		return durataInSecondi;
	}
	
	
}
