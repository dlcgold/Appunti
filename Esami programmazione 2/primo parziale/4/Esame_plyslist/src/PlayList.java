
public class PlayList {
	private String titolo;
	private Brano[] brani;
	
	PlayList(String titolo, int numeroBrani){
		this.titolo = titolo;
		if(numeroBrani>1)
			brani = new Brano [numeroBrani];
		else
			brani = new Brano [1];
	}
	
	PlayList(String titolo) {
		this(titolo,20);
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	
	public void setBrani(Brano[] brani) {
		this.brani = brani;
	}

	public int posizioneBrano(Brano brano) {
		if(brano == null)
			return -1;
		for(int i=0; i< brani.length; i++) {
			if(brani[i]!=null && brani[i].equals(brano))
				return i;
		}
		return -1;
	}
	
	public boolean aggiungiBrano(Brano brano) {
		if(brano==null)
			return false;
		for(int i=0; i<brani.length;i++) {
			if(brani[i]!=null && brani[i].equals(brano))
				return false;
		}
		for(int i=0; i<brani.length;i++) {
			if(brani[i]==null) {
				brani[i]=brano;
				return true;
			}
		}
		return false;
	}
	
	public boolean aggiungiBrano(String titolo, int durataInSecondi) {
		return aggiungiBrano(new Brano(titolo, durataInSecondi));
	}
	
	public Brano rimuoviBrano(Brano brano) {
		Brano tmp = null;
		if(brano == null)
			return brano;
		for(int i=0; i<brani.length;i++) {
			if(brani[i]!=null && brani[i].equals(brano)) {
				tmp=brano;
				brani[i]=null;
				return tmp;
			}
		}
		return null;
	}
	
	public int play() {
		int conta=0;
		for(int i=0; i< brani.length; i++) {
			if(brani[i]!=null) {
				System.out.println(brani[i].getTitolo());
				conta+=brani[i].getDurataInSecondi();
			}
		}
		return conta;
	}
	
}
