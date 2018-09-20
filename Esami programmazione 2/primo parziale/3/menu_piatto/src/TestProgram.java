import static org.junit.Assert.*;

import org.junit.Test;

public class TestProgram {
	
	@Test
	public void testPiatto(){
		Piatto b = new Piatto(Piatto.PRIMO, "Norma");
		
		assertEquals("primo", b.getCategoria());
		assertEquals("Norma", b.getNome());
	}

	@Test
	public void testMenuAggiunta(){
		Menu m = new Menu("il mio ristorante");
		assertTrue(m.aggiungiPiatto(new Piatto(Piatto.PRIMO, "Norma")));		
	}

	@Test
	public void testMenuAggiuntaNoDuplicati(){
		Menu m = new Menu("il mio ristorante");
		assertTrue(m.aggiungiPiatto(new Piatto(Piatto.PRIMO, "Norma")));
		assertFalse(m.aggiungiPiatto(new Piatto(Piatto.PRIMO, "Norma")));
	}

	@Test
	public void testRimozione(){
		Piatto p1 = new Piatto(Piatto.PRIMO, "Norma");
		Menu m = new Menu("il mio ristorante");
		m.aggiungiPiatto(p1);
		m.eliminaPiatto(new Piatto(Piatto.PRIMO, "Norma"));
		assertTrue(m.aggiungiPiatto(new Piatto(Piatto.PRIMO, "Norma")));
	}
	
	@Test
	public void testMenuDammiTuttiIPiatti(){
		Menu m = new Menu("il mio ristorante");
		m.aggiungiPiatto(new Piatto(Piatto.PRIMO, "Norma"));
		m.aggiungiPiatto(new Piatto(Piatto.SECONDO, "Filetto"));
		m.aggiungiPiatto(new Piatto(Piatto.PRIMO, "ravioli di magro"));
		m.aggiungiPiatto(new Piatto(Piatto.DOLCE, "fragole"));
		
		Piatto esito [] = m.dammiTuttiIPiatti(Piatto.PRIMO);
		assertEquals(2, esito.length);
		esito = m.dammiTuttiIPiatti(Piatto.SECONDO);
		assertEquals(1, esito.length);
		esito = m.dammiTuttiIPiatti(Piatto.DOLCE);
		assertEquals(1, esito.length);
	}
	
	//Da implementare
	@Test
	public void testMenudammiTuttiIPrimi(){
		Menu m = new Menu("il mio ristorante");
		m.aggiungiPiatto(new Piatto(Piatto.PRIMO, "Norma"));
		m.aggiungiPiatto(new Piatto(Piatto.SECONDO, "Filetto"));
		m.aggiungiPiatto(new Piatto(Piatto.PRIMO, "ravioli di magro"));
		Piatto esito [] = m.dammiTuttiIPrimi();
		assertEquals(2, esito.length);
	}
}

