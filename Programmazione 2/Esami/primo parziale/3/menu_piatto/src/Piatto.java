
public class Piatto {
	public static final String PRIMO = "primo";
	public static final String SECONDO = "secondo";
	public static final String DOLCE = "dolce";
	private String categoria;
	private String nome;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCategoria() {
		return categoria;
	}
	public Piatto(String categoria, String nome) {
		//super();
		this.categoria = categoria;
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return "Piatto [categoria=" + categoria + ", nome=" + nome + "]";
	}
	
	public boolean equals(Piatto altroPiatto) {
		if(altroPiatto==null)
			return false;
		if(this == altroPiatto)
            return true;
		if(this.categoria.equals(altroPiatto.categoria) && this.nome.equals(altroPiatto.nome))
			return true;
		return false;				
	}
	
}
