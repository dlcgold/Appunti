import java.util.HashSet;
import java.util.Set;

public class Grafico {

	private Set<Punto> punti;
	
	Grafico(Punto primoPunto){
		punti=new HashSet<Punto>();
		if(primoPunto!=null)
			punti.add(primoPunto);
	}
	public void aggiungiPunto(Punto punto) throws GraficoException {
		for(Punto i: punti) {
			if(i.equals(punto))
				throw new GraficoException();
		}
		if(punto!=null)
			punti.add(punto);
	}
	public void visualizza() {
		for(Punto i: punti)
			System.out.println(i.toString()+" ");
	}
	
	public void trasforma(TrasformazioneGeometrica trasformazione) {
		punti=trasformazione.trasforma(punti);
	}
	
	public int getNumeroPunti() {
		int somma=0;
		for(Punto i: punti)
			somma++;
		return somma;
	}
}
