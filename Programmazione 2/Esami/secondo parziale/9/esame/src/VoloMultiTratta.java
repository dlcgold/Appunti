import java.util.List;

public class VoloMultiTratta extends Volo {
	private List<VoloDiretto> tratte;
	
	private static  String estraiAreoportoPartenza(List<VoloDiretto> tratte) throws VoloNonValidoException {
		if(tratte==null || tratte.size()<2)
			throw new VoloNonValidoException();
		return tratte.get(0).getAeroportoPartenza();
	}

	private static  String estraiAreoportoDestinazione(List<VoloDiretto> tratte){
		return tratte.get(tratte.size()-1).getAeroportoArrivo();
	}

	
	public VoloMultiTratta( List<VoloDiretto> tratte) throws VoloNonValidoException {
		super(estraiAreoportoPartenza(tratte), estraiAreoportoDestinazione(tratte));
		for(int i=0; i<tratte.size()-1;i++)
			if(tratte.get(i).getAeroportoArrivo()!=tratte.get(i+1).getAeroportoPartenza())
				throw new VoloNonValidoException();
		this.tratte = tratte;
	}
	
	

	@Override
	public int getNumeroTratte() {
		return tratte.size();
	}

	@Override
	public int getDurataInMinuti() {
		int conta=0;
		for(VoloDiretto i: tratte ) {
			if(i!=null)
				conta+=i.getDurataInMinuti();
		}
		return conta;
	}
	

}
