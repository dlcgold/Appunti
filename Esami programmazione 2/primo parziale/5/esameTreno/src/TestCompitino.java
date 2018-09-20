import static org.junit.Assert.*;

import org.junit.Test;


public class TestCompitino {

	@Test
	public void testNuovoPasseggero() {
		Passeggero p = new Passeggero("Bicocca", true);
		assertEquals("Bicocca", p.getDestinazione());
		assertTrue(p.isInPosessoDiBiglietto());
	}

	@Test
	public void testPasseggeroCapolinea() {
		Passeggero p = new Passeggero(null);
		assertEquals("CAPOLINEA", p.getDestinazione());
		assertTrue(p.isInPosessoDiBiglietto());
	}

	@Test
	public void testPasseggeroToString() {
		Passeggero p = new Passeggero("Bicocca", true);
		
assertTrue(p.toString().contains(p.getDestinazione()));
		assertTrue(p.toString().contains("" + 
p.isInPosessoDiBiglietto()));
	}
	
	@Test
	public void testMetroSalitaPasseggeri() {
		TrenoMetro m = new TrenoMetro();
		boolean salito = m.salitaPasseggero("Bicocca", 
true);
		assertTrue(salito);
		salito = m.salitaPasseggero("Bignami", false);
		assertTrue(salito);
		salito = m.salitaPasseggero("Zara", true);
		assertTrue(salito);
		assertEquals(3, m.numeroPasseggeri());
	}

	@Test
	public void testMetroTrenoPieno() {
		TrenoMetro m = new TrenoMetro();
		for(int i = 0; i < 50; i++) {
			m.salitaPasseggero("Bicocca", true);
		}
		assertEquals(50, m.numeroPasseggeri());
		boolean salito = m.salitaPasseggero("Bignami", 
true);
		assertFalse(salito);
	}
	
	@Test
	public void testMetroNessunoScende() {
		TrenoMetro m = new TrenoMetro();
		m.salitaPasseggero("Bicocca", true);
		m.salitaPasseggero("Bignami", false);
		m.salitaPasseggero("Bicocca", false);
		m.salitaPasseggero("Bicocca", false);
		
		Passeggero[] daMultare = m.discesaPasseggeri(new 
String("Zara"));
		assertEquals(4, m.numeroPasseggeri());
		assertEquals(0, daMultare.length);
	}

	@Test
	public void testMetroDiscesaPasseggeriNoMulte() {
		TrenoMetro m = new TrenoMetro();
		m.salitaPasseggero("Bicocca", true);
		m.salitaPasseggero("Bignami", false);
		m.salitaPasseggero("Bicocca", true);
		m.salitaPasseggero("Bicocca", true);
		
		Passeggero[] daMultare = m.discesaPasseggeri(new 
String("Bicocca"));
		assertEquals(1, m.numeroPasseggeri());
		assertEquals(0, daMultare.length);
	}

	@Test
	public void testMetroDiscesaPasseggeriAlcuneMulte() {
		TrenoMetro m = new TrenoMetro();
		m.salitaPasseggero("Bicocca", true);
		m.salitaPasseggero("Bignami", false);
		m.salitaPasseggero("Bicocca", false);
		m.salitaPasseggero("Bicocca", false);
		
		Passeggero[] daMultare = m.discesaPasseggeri(new 
String("Bicocca"));
		assertEquals(1, m.numeroPasseggeri());
		assertEquals(2, daMultare.length);
		assertEquals("Bicocca", 
daMultare[0].getDestinazione());
		assertEquals("Bicocca", 
daMultare[1].getDestinazione());		
	}
}

