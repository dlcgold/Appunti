import java.util.Collection;
import java.util.HashMap;

public class Nido {
	HashMap<Integer,Bambino> camerata;

	public Nido() {
		this.camerata = new HashMap<Integer,Bambino>();
	}
	
	public boolean aggiungiBambino(Integer lettino, Bambino bimbo) {
		if(!camerata.containsValue(bimbo) && !camerata.containsKey(lettino)) {
			camerata.put(lettino,bimbo);
			return true;
		}
		else return false;
	}
	
	public Bambino dimettiBambino(Integer lettino, Persona richiedente) {
		Bambino bimbo = camerata.get(lettino);
		if(bimbo!=null && richiedente.confrontaBraccialetto(bimbo) && bimbo.dimettibile())
			return bimbo;
		return null;
	}
	
	public Collection<Integer> getLettiniOccupati(){
		return camerata.keySet();
	}
	
	public Bambino getBambino(int lettino) {
		return camerata.get(lettino);
	}
	
	public int getLettino(Braccialetto braccialetto) {
		Collection<Integer> occupati=camerata.keySet();
		for(int lettino: occupati) {
			Bambino bimbo = getBambino(lettino);
			if(bimbo.getBraccialetto().equals(braccialetto))
				return lettino;
		}
		return -1;
	}
	
	
	
}
