
public class Rettangolo implements Poligono {
	private double base;
	private double altezza;
	
	public Rettangolo(double base, double altezza) {
		super();
		this.base = base;
		this.altezza = altezza;
	}

	@Override
	public double calcolaArea() {
		return this.base*this.altezza;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rettangolo other = (Rettangolo) obj;
		if ((this.base*this.altezza)!=(other.altezza*other.base) && Math.abs(this.base*this.altezza-other.altezza*other.base)>0.001)
			return false;
		return true;
	}
	
	

}
