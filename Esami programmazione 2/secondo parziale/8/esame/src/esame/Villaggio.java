package esame;

import java.util.List;
import java.util.ArrayList;

public class Villaggio extends AreaAbitabile {

	private List<AreaAbitabile> abitazioni;
	
	public Villaggio(String comune, List<AreaAbitabile> abitazioni) throws AreaAbitabileException {
		super(comune);
		this.abitazioni= new ArrayList<AreaAbitabile>();
		addAreaAbitabile(abitazioni);
	}
	
	public void addAreaAbitabile(AreaAbitabile abitazione) throws AreaAbitabileException {
		if(abitazione==null)
			throw new AreaAbitabileException();
		else {
			if(!abitazione.getComune().equals(comune))
				throw new AreaAbitabileException();
			else
				abitazioni.add(abitazione);
		}
	}
	public void addAreaAbitabile(List<AreaAbitabile> abitazioni) throws AreaAbitabileException {
		if(abitazioni==null)
			throw new AreaAbitabileException();
		else {
			for(AreaAbitabile i: abitazioni) {
				addAreaAbitabile(i);
			}
		}
		
	}
	
	

	@Override
	public int getSuperficieTotale() {
		int conta=0;
		for(AreaAbitabile i: abitazioni) {
			if(i!=null)
				conta+=i.getSuperficieTotale();
		}
		return conta;
	}
	public int numCaseIndipendenti() {
		int conta=0;
		for(AreaAbitabile i: abitazioni) {
			if(i!=null) {
				if(i instanceof CasaIndipendente ) 
					conta++;
				if(i instanceof Villaggio) {
					conta+=((Villaggio) i).numCaseIndipendenti();
				}
			}
		}
		return conta;
	}

	
	
	@Override
	public String toString() {
		String s=super.toString();
		for(AreaAbitabile i: abitazioni) {
			s+=i.toString();
		}
		return s;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Villaggio other = (Villaggio) obj;
		if (abitazioni == null) {
			if (other.abitazioni != null)
				return false;
		} else if (!abitazioni.equals(other.abitazioni))
			return false;
		return true;
	}
	


}
