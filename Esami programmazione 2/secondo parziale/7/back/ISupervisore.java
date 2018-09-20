

public interface ISupervisore {

	/** 
	 * Decide se la posizione in input e' valida 
	 *  
	 * @param posizione: la posizione da valutare
	 * @return true, se la posizione e' valida; 
	 *         false, in caso contrario.
	 */
	boolean posizioneValida(PuntoXY posizione);

	/**
	 * Decide se la posizione in input corrisponde
	 * all'obiettivo
	 *  
	 * @param posizione: la posizione da valutare
	 * @return true, se la posizione corrisponde all'obiettivo; 
	 *         false, in caso contrario.
	 */
	boolean obiettivoRaggiunto(PuntoXY posizione);

}
