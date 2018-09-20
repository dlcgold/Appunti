
public class MossaRettilineaOrizzontale extends Mossa {

	
	public MossaRettilineaOrizzontale(int passi) {
		super(passi);
	}

	@Override
	public PuntoXY esegui(PuntoXY posizione) {
		PuntoXY posizioneFinale=new PuntoXY(posizione.getX()+this.getPassi(),posizione.getY());
		return posizioneFinale;
	}

}
