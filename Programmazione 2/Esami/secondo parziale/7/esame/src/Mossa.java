

public abstract class Mossa {
	private int passi;

	public Mossa(int passi) {
		if(passi==0)
			throw new RobotException();
		this.passi = passi;
	}

	public int getPassi() {
		return passi;
	}
	
	public abstract PuntoXY esegui(PuntoXY posizione);
	
}
