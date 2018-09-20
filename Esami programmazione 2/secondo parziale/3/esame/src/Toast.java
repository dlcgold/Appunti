
public class Toast extends Prodotto {

	private int costoBase;
	private boolean grande;

	public Toast( int costoBase, boolean grande, int quantita) {
		super(quantita);
		this.costoBase = costoBase;
		this.grande = grande;
	}	

	@Override
	public int getPrezzoUnitario() {
		if(grande)
			return costoBase*2;
		else
			return costoBase;

	}
}
