import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class Test {

	@org.junit.Test
	public void testNuovoPunto() {
		Punto p = new Punto("1a misurazione", 2, 5);
		assertEquals(2, p.getX());
		assertEquals(5, p.getY());
		assertEquals("1a misurazione", p.getEtichetta());
		
		p = new Punto(null, 2, 5); 
		assertEquals("UNDEF", p.getEtichetta());

		p = new Punto("", 2, 5); 
		assertEquals("UNDEF", p.getEtichetta());
	}

	@org.junit.Test
	public void testPuntoEquals() {
		Punto p1 = new Punto("1a misurazione", 2, 5);
		Punto p2 = new Punto("2a misurazione", 2, 5);
		Punto p3 = new Punto("3a misurazione", 1, 5);
		assertTrue(p1.equals(p2));
		assertFalse(p1.equals(p3));
		assertFalse(p1.equals(null));
	}
	
	@org.junit.Test
	public void testTraslazione() {
		TrasformazioneGeometrica t = new Traslazione(4, 2);
		Punto p1 = new Punto("1a misurazione", 2, 5);
		Punto p2 = t.trasforma(p1);
		assertEquals(6, p2.getX());
		assertEquals(7, p2.getY());
		assertEquals("1a misurazione", p2.getEtichetta());
	}

	@org.junit.Test
	public void testIngrandimentoValido() throws GraficoException {
		TrasformazioneGeometrica t = new SimmetriaRispettoAOrigine();
		Punto p1 = new Punto("1a misurazione", 2, 5);
		Punto p2 = t.trasforma(p1);
		assertEquals(-2, p2.getX());
		assertEquals(-5, p2.getY());
		assertEquals("1a misurazione", p2.getEtichetta());
	}
	
	@org.junit.Test
	public void testNuovoGrafico() {
	    Punto p1 = new Punto("1a misurazione", 2, 5);
		Grafico g1 = new Grafico(p1);
		assertEquals(1, g1.getNumeroPunti());

		Grafico g2 = new Grafico(null);
		assertEquals(0, g2.getNumeroPunti());
}
	
	@org.junit.Test
	public void testAggiungiPuntoNonDuplicato() throws GraficoException {
	    Punto p1 = new Punto("1a misurazione", 2, 5);
	    Punto p2 = new Punto("2a misurazione", 3, 8);
		Grafico g = new Grafico(p1);
		g.aggiungiPunto(p2);
		assertEquals(2, g.getNumeroPunti());
	}

	@org.junit.Test
	public void testAggiungiPuntoNull() throws GraficoException {
	    Punto p1 = new Punto("1a misurazione", 2, 5);
		Grafico g = new Grafico(p1);
		g.aggiungiPunto(null);
		assertEquals(1, g.getNumeroPunti());
	}

	@org.junit.Test
	public void testAggiungiPuntoDuplicato() {
	    Punto p1 = new Punto("1a misurazione", 2, 5);
	    Punto p2 = new Punto("2a misurazione", 2, 5);
		Grafico g = new Grafico(p1);
		try {
			g.aggiungiPunto(p2);
			fail("Eccezione non sollevata");
		} catch (GraficoException e) {
			//OK: eccezione sollevata
		}
	}
	
	@org.junit.Test
	public void testGraficoVisualizza() throws GraficoException {
		//ridirigge su stream l'output su console per poi poterlo verificare nel test
		final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outStream));

	    Punto p1 = new Punto("1a misurazione", 2, 5);
	    Punto p2 = new Punto("2a misurazione", 3, 8);
		Grafico g = new Grafico(p1);
		g.aggiungiPunto(p2);
		g.visualizza();
		
		String output = outStream.toString();
		assertTrue(output.contains(p1.toString()));
		assertTrue(output.contains(p2.toString()));
	}

	@org.junit.Test
	public void testGraficoTrasforma() throws GraficoException {
		//ridirigge su stream l'output su console per poi poterlo verificare nel test
		final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outStream));

	    Punto p1 = new Punto("1a misurazione", 2, 5);
	    Punto p2 = new Punto("2a misurazione", 3, 8);
		Grafico g = new Grafico(p1);
		g.aggiungiPunto(p2);
		TrasformazioneGeometrica t = new Traslazione(4, 2);
		g.trasforma(t);
		g.visualizza();
		
		String output = outStream.toString();
		assertTrue(output.contains(t.trasforma(p1).toString()));
		assertTrue(output.contains(t.trasforma(p2).toString()));
	}

}
