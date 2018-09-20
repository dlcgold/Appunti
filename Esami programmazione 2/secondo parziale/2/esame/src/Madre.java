
public class Madre extends Persona {
	public String tipoParto;
	public static final String NATURALE="naturale";
	public static final String CESAREO="cesareo";
	
	public Madre(Braccialetto braccialetto, String tipoParto) throws OperazioneNonConsentitaException {
		super(braccialetto);
		this.tipoParto = tipoParto;
		if(tipoParto!=NATURALE && tipoParto!=CESAREO)
			throw new OperazioneNonConsentitaException("NOPE");
	}
	
	public double allatta(Bambino bimbo) throws OperazioneNonConsentitaException {
		if(!this.confrontaBraccialetto(bimbo))
			throw new OperazioneNonConsentitaException();
		double latte=0;
		if(tipoParto.equalsIgnoreCase(NATURALE))
			latte=(bimbo.getPesoAllaNascita()*bimbo.getLunghezza())/5;
		else
			latte=(bimbo.getPesoAllaNascita()*bimbo.getLunghezza())/10;
		bimbo.incrementaPeso(latte);
		return latte;
	}
	public double allatta(Nido nido, Integer lettino) throws OperazioneNonConsentitaException {
		Bambino bimbo = nido.getBambino(lettino);
		return allatta(bimbo);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString()+" "+tipoParto;
	}
	
	
	
}
