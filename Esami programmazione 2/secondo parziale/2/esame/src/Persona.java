
public abstract class Persona {
	private Braccialetto braccialetto;

	public Persona(Braccialetto braccialetto) throws OperazioneNonConsentitaException {
		if(braccialetto==null)
			throw new OperazioneNonConsentitaException("NOPE");
		this.braccialetto = braccialetto;
	}

	public Braccialetto getBraccialetto() {
		return braccialetto;
	}
	
	public boolean confrontaBraccialetto(Persona altraPersona) {
		return braccialetto.equals(altraPersona.getBraccialetto());
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return braccialetto.toString();
	}
	
	
	
}
