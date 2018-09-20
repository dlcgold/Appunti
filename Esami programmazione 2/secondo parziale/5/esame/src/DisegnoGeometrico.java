import java.util.ArrayList;

public class DisegnoGeometrico {
	private ArrayList<Poligono> poligoni;

	public DisegnoGeometrico() {
		super();
		this.poligoni = new ArrayList<Poligono>();
	}
	
	public void aggiungiPoligono(Poligono poligono) {
		poligoni.add(poligono);
	}
	
	public double calcolaAreaTotale() throws NoPoligoniExpcetion {
		double somma=0;
		if(poligoni.isEmpty())
			throw new NoPoligoniExpcetion();
		for(Poligono i: poligoni) {
			somma+=i.calcolaArea();
		}
		return somma;
	}
}
