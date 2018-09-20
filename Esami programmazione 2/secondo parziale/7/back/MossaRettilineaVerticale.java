
public class MossaRettilineaVerticale extends Mossa {

	MossaRettilineaVerticale(int passi){
		super(passi);
	}
	@Override
	public PuntoXY esegui(PuntoXY posizione) {
		PuntoXY posizioneFinale=new PuntoXY(posizione.getX(), posizione.getY()+this.getPassi());
		return posizioneFinale;
	}
	
}
