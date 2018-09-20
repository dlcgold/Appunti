
public class SimmetriaRispettoAOrigine extends TrasformazioneGeometrica {

	@Override
	protected Punto trasforma(Punto originale) {
		Punto a = new Punto(originale.getEtichetta(),-originale.getX(),-originale.getY());
		return a;
	}

}
