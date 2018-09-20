package esame;

public abstract class AreaAbitabile {
	protected String comune;

	public AreaAbitabile(String comune) throws AreaAbitabileException {
		if(comune==null)
			throw new AreaAbitabileException();
		else
			this.comune = comune;
	}

	public String getComune() {
		return comune;
	}
	
	public abstract int getSuperficieTotale();

	@Override
	public String toString() {
		return comune + " " + getSuperficieTotale();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AreaAbitabile other = (AreaAbitabile) obj;
		if (comune == null) {
			if (other.comune != null)
				return false;
		} 
		else { // se comune not null
			if (!comune.equals(other.comune)){
				return false;
			}
		}
		if(getSuperficieTotale()!=other.getSuperficieTotale())
			return false;
		else 
			return true;
	}

	
	 
	
	
}
