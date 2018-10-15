
public class Aeroporti {

	private static final String aeroporti[] = {"LIN", "SFO", "FCO", "MXP"};
	
	public static boolean aeroportoValido(String aeroporto) {
		for(int i = 0; i < aeroporti.length; i++){
			if(aeroporti[i].equals(aeroporto)) return true;
		}
		return false;
	}

}
