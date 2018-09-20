import java.util.HashSet;
import java.util.Set;

public abstract class TrasformazioneGeometrica {
	
	protected abstract Punto trasforma(Punto originale);
	public Set<Punto> trasforma(Set<Punto> original){
		HashSet<Punto> trasformati = new HashSet<Punto>();
		for(Punto i: original)
			trasformati.add(trasforma(i));
		return trasformati;
	}
	
	
}
