
public class Traslazione extends TrasformazioneGeometrica {
	
	private int deltaX;
	private int deltaY;
	
	
	public Traslazione(int deltaX, int deltaY) {
		super();
		this.deltaX = deltaX;
		this.deltaY = deltaY;
	}


	@Override
	protected Punto trasforma(Punto originale) {
		String b= originale.getEtichetta();
		Punto a = new Punto(b,deltaX+originale.getX(), deltaY+originale.getY());
		return a;
		
	}

}
