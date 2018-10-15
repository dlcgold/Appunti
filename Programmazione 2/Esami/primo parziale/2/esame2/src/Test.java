import static org.junit.Assert.*;

//no istanziazione, no equals su stringhe, no overloading

public class Test {

	@org.junit.Test
	public void testCreazioneStudente() {
		Studente s = new Studente("Rossi", 1234);
		assertEquals("Rossi", s.getCognome());
		assertEquals(1234, s.getMatricola());
	}

	@org.junit.Test
	public void testEquals() {
		Studente s1 = new Studente("Rossi", 1234);
		Studente s2 = new Studente(new String("Rossi"), 1234);
		assertTrue(s1.equals(s2));
		s2 = new Studente(new String("Verdi"), 1234);
		assertTrue(s1.equals(s2));
		s2 = new Studente(new String("Verdi"), 1235);
		assertFalse(s1.equals(s2));
	}
	
	@org.junit.Test
	public void testCreazioneAgenda() {
		AgendaGiornaliera ag = new AgendaGiornaliera("Pippo", 10, 4);
		assertEquals("Pippo", ag.getDocente());
		assertEquals(10, ag.getGiorno());
		assertEquals(4, ag.getNumeroAppuntamentiGiornalieri());
		
		ag = new AgendaGiornaliera("Pippo", 10);
		assertEquals("Pippo", ag.getDocente());
		assertEquals(10, ag.getGiorno());
		assertEquals(6, ag.getNumeroAppuntamentiGiornalieri());
	}
	
	
	@org.junit.Test
	public void testPrenotaNoSlotSenzaAnnulla() {
		AgendaGiornaliera ag = new AgendaGiornaliera("Pippo", 10, 4);
		ag.prenota(new Studente("Rossi", 1234));
		assertFalse(ag.isLibero(0));
		//prenoto solo il primo slot
		assertTrue(ag.isLibero(1));
		
		//non prenoto se studenete è null
		ag.prenota(null);
		assertTrue(ag.isLibero(1));
		
		//non prenoto lo stesso studente
		ag.prenota(new Studente("Verdi", 1234));
		assertTrue(ag.isLibero(1));
		
		//non prenoto se ho riempito l'agenda
		ag.prenota(new Studente("Verdi", 1235));   //1
		ag.prenota(new Studente("Bianchi", 1236)); //2
		ag.prenota(new Studente("Blu", 1237));     //3
		assertFalse(ag.prenota(new Studente("Gialli", 1238)));
	}
	
	
	//implica aver codificato il metodo annulla
	//prenoto la prima posizione libera
	@org.junit.Test
	public void testPrenotaNoSlotConAnnulla() {
		AgendaGiornaliera ag = new AgendaGiornaliera("Pippo", 10, 4);
		ag.prenota(new Studente("Rossi", 1234));   //0
		ag.prenota(new Studente("Verdi", 1235));   //1
		ag.prenota(new Studente("Bianchi", 1236)); //2
		ag.annulla(new Studente("Verdi", 1235));   //1
		assertTrue(ag.isLibero(1));
		ag.prenota(new Studente("Blu", 1237));
		assertFalse(ag.isLibero(1));
	}
	
	
	@org.junit.Test
	public void testGetStudente() {
		AgendaGiornaliera ag = new AgendaGiornaliera("Pippo", 10, 4);
		ag.prenota(new Studente("Rossi", 1234));   //0
		ag.prenota(new Studente("Verdi", 1235));   //1
		assertTrue(new Studente("Rossi", 1234).equals(ag.getStudente(0)));
	}
	
	@org.junit.Test
	public void testisLibero() {
		AgendaGiornaliera ag = new AgendaGiornaliera("Pippo", 10, 4);
		ag.prenota(new Studente("Rossi", 1234));   //0
		assertTrue(ag.isLibero(2));
		assertTrue(ag.isLibero(1));
		assertFalse(ag.isLibero(0));
		assertFalse(ag.isLibero(-1));
		assertFalse(ag.isLibero(4));
	}
	
	
	@org.junit.Test
	public void testPrenotaConSlotSenzaAnnulla() {
		AgendaGiornaliera ag = new AgendaGiornaliera("Pippo", 10, 4);
		ag.prenota(new Studente("Rossi", 1234), 3);
		assertTrue(ag.isLibero(0));
		assertTrue(ag.isLibero(1));
		assertTrue(ag.isLibero(2));
		assertFalse(ag.isLibero(3));
		
		//non prenoto sullo stesso slot
		ag.prenota(new Studente("Verdi", 1235), 3);
		assertTrue(new Studente("Rossi", 1234).equals(ag.getStudente(3)));
		
		//non prenoto se studente è null
		ag.prenota(null, 0);
		assertTrue(ag.isLibero(0));
		
		//non prenoto lo stesso studeete
		ag.prenota(new Studente("Rossi", 1234), 0);
		assertTrue(ag.isLibero(0));
		
		//non prenoto se ho riempito l'agenda
		ag.prenota(new Studente("Verdi", 1235));   //0
		ag.prenota(new Studente("Bianchi", 1236)); //1
		ag.prenota(new Studente("Blu", 1237));     //2
		assertFalse(ag.prenota(new Studente("Gialli", 1238)));
		
	}
	
	@org.junit.Test
	public void testAnnulla() {
		AgendaGiornaliera ag = new AgendaGiornaliera("Pippo", 10, 4);
		ag.prenota(new Studente("Rossi", 1234), 3);
		assertTrue(ag.annulla(new Studente("Rossi", 1234)));
		assertFalse(ag.annulla(new Studente("Verdi", 1235)));
		assertFalse(ag.annulla(null));
	}
}
