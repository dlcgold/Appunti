package esame1;

public class PlayList {
	private String titolo;
	private Brano[] brani;
	
	public PlayList(String titolo, int numeroBrani) {
		this.titolo=titolo;
		if(numeroBrani<1)
			this.brani= new Brano[1];
		else 
			this.brani= new Brano[numeroBrani];
	}
	
	public PlayList(String titolo) {
		this(titolo,20);
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public int posizioneBrano(Brano brano){
		if(brano==null)
			return -1;
		for(int i=0; i<this.brani.length;i++) {
			if(brani[i]!=null && this.brani[i].equals(brano)){
				return i;
			}
		}
		return(-1);
	}
	
	public boolean aggiungiBrano(Brano brano) {
		if(brano==null)
			return false;
		for(int i=0;i<this.brani.length;i++) {
			if(brani[i]!=null && brano.equals(this.brani[i]))
				return false;
		}
		for(int i=0; i<this.brani.length;i++) {
			if(brani[i]==null){
				brani[i]=brano;
				return true;
			}
		}
		return false;
	}
	
	public boolean aggiungiBrano(String titolo, int durataInSecondi) {
		Brano brano = new Brano(titolo, durataInSecondi);
		return(aggiungiBrano(brano));
	}
	
	public Brano rimuoviBrano(Brano brano) {
		if(brano==null)
			return null;
		for(int i=0; i<this.brani.length;i++) {
			if(brani[i]!=null && brani[i].equals(brano)){
				Brano branoS = brani[i];
				brani[i]=null;
				return branoS;
			}
		}
		return null;
	}
	
	public int play() {
		int somma = 0;
		for(int i=0; i<this.brani.length;i++) {
			if(brani[i]!=null) {
				System.out.println(this.brani[i].getTitolo());
				somma+=this.brani[i].getDurataInSecondi();
			}
		}
		return somma;
	}
	
}
