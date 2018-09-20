import java.util.Date;

import junit.framework.TestCase;


public class TestEsame extends TestCase {

	
/*** Test classe Nido	
	/** Verifica la corretta aggiunta di un bambino al Nido */
	public void testAggiungiBambino(){
		Nido nido = new Nido();		
		try{
			assertTrue(nido.aggiungiBambino(1, new Bambino(new Braccialetto(21), 1.5, 48, 7)));			
			assertTrue(nido.aggiungiBambino(15, new Bambino(new Braccialetto(25), 1.5, 47, 5)));
			assertFalse(nido.aggiungiBambino(1, new Bambino(new Braccialetto(47), 1.6, 46, 6)));
		} catch(OperazioneNonConsentitaException e){	
			fail();
		}		
	}
	
	/** Verifica la corretta dimissione di un bambino dal nido */
	public void testDimettiBambino(){
		try{
			Persona nonno = new Persona(new Braccialetto(1)){};//creazione classe derivata da Persona
			
			Nido nido = new Nido();
			
			Bambino b1 = new Bambino(new Braccialetto(1), 2.5, 49, 8);
			Bambino b2 = new Bambino(new Braccialetto(1), 2.5, 49, 5);
			Bambino b3 = new Bambino(new Braccialetto(2), 2.5, 49, 8);		
			
			nido.aggiungiBambino(1, b1);
			nido.aggiungiBambino(2, b2);
			nido.aggiungiBambino(3, b3);
			
			assertEquals(b1, nido.dimettiBambino(1, nonno));
			assertNull(nido.dimettiBambino(2, nonno));
			assertNull(nido.dimettiBambino(4, nonno));
			
		} catch(OperazioneNonConsentitaException e){			
			fail();
		}
	}
	
	
	/** Verifica la corretta restituzione del numero di letto dato il braccialetto */
	public void testGetLettino(){
		Nido nido = new Nido();		
		try {
			nido.aggiungiBambino(1, new Bambino(new Braccialetto(21), 1.5, 48, 7));
			nido.aggiungiBambino(15, new Bambino(new Braccialetto(25), 1.5, 47, 5));			
			assertEquals(1, nido.getLettino(new Braccialetto(21)));
			assertEquals(15, nido.getLettino(new Braccialetto(25)));
		} catch (OperazioneNonConsentitaException e) {
			fail();
		}		
		
	}	
	
	/** Verifica la corretta restituzione del bambino dato il lettino */
	public void testGetBambino(){
		Nido nido = new Nido();		
		Bambino b1;
		try {
			b1 = new Bambino(new Braccialetto(21), 2.5, 49, 8);
			Bambino b2 = new Bambino(new Braccialetto(25), 2.5, 49, 5);
			nido.aggiungiBambino(1, b1);		
			nido.aggiungiBambino(2, b2);
			
			assertEquals(b1, nido.getBambino(1));
			assertEquals(b2, nido.getBambino(2));
		} catch (OperazioneNonConsentitaException e) {
			fail();
		}
	}

	
/*** Test classe Madre		
	
	/** Verifica la corretta istanziazione di una Madre */
	public void testCreazioneMadre() {
		Madre madre;
		try {
			madre = new Madre(null, Madre.CESAREO);
			fail();
		} catch (OperazioneNonConsentitaException e) { /*OK*/}
		try {
			madre = new Madre(new Braccialetto(1), "Artificiale");
			fail();
		} catch (OperazioneNonConsentitaException e) { /*OK*/}		
	}	
	
	
	/** Verifica la corretta esecuzione del metodo allatta(Bambino) */
	public void testAllattaBambino(){
		Nido nido = new Nido();
		Braccialetto b1 = new Braccialetto(1);
		Braccialetto b2 = new Braccialetto(2);
		Bambino bimbo1;
		Bambino bimbo2;
		Madre mamma;
		Madre mamma1;
		try {			
			bimbo1 = new Bambino(b1, 1.5, 48, 7);
			bimbo2 = new Bambino(b2, 2.5, 49, 5);
			
			nido.aggiungiBambino(1, bimbo1);
			nido.aggiungiBambino(2, bimbo2);

			//Test quantit� latte cesareo
			mamma = new Madre(new Braccialetto(1), Madre.CESAREO);				
			double ql = mamma.allatta(bimbo1);		
			assertEquals(7.2, ql, 0.5);
			assertEquals(8.7, bimbo1.getPesoAttuale(), 0.5);
			
			//Test quantit� latte naturale
			mamma1 = new Madre(new Braccialetto(2), Madre.NATURALE);		
			ql = mamma1.allatta(bimbo2);
			assertEquals(24.5, ql, 0.5);
			assertEquals(27, bimbo2.getPesoAttuale(), 0.5);
		} catch (OperazioneNonConsentitaException e) {			
			fail();
		}
		
		//Test allattamento di un bambino che non � figlio proprio
		try{
			bimbo1 = new Bambino(b1, 1.5, 48, 7);
			mamma1 = new Madre(new Braccialetto(2), Madre.NATURALE);			
			double ql = mamma1.allatta(bimbo1);
			fail();			
		}catch (OperazioneNonConsentitaException e) {} /* OK */
	}

	
	/** Verifica la corretta esecuzione del metodo allatta(Lettino) */
	public void testAllattaNidoLettino(){
		Nido nido = new Nido();
		Braccialetto b1 = new Braccialetto(1);
		Braccialetto b2 = new Braccialetto(2);
		Bambino bimbo1;
		Bambino bimbo2;
		Madre mamma;
		Madre mamma1;
		
		try {
			bimbo1 = new Bambino(b1, 1.5, 48, 7);
			bimbo2 = new Bambino(b2, 2.5, 49, 5);
			
			nido.aggiungiBambino(1, bimbo1);
			nido.aggiungiBambino(2, bimbo2);
	
			//Test allattamento figlio proprio
			mamma = new Madre(new Braccialetto(1), Madre.CESAREO);				
			double ql = mamma.allatta(nido, 1);		
			assertEquals(7.2, ql, 0.5);
		} catch (OperazioneNonConsentitaException e) {			
			e.printStackTrace();
		}
		
		//Test allattamento di un bambino che non � figlio proprio
		try{
			mamma = new Madre(new Braccialetto(1), Madre.CESAREO);	
			double ql = mamma.allatta(nido, 2);
			assertEquals(-1, ql, 0.5);	
			fail();			
		} catch (OperazioneNonConsentitaException e) {} /* OK */
	}
}
