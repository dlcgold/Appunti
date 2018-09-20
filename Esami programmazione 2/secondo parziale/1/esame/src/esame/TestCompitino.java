package esame;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestCompitino {

	// ============ TEST LIBRO ============

	@Test
	public void testCreazioneLibro() throws MonografiaInvalidaException {
		//creazione libro corretto
		Libro l = new Libro("Uno, nessuno e centomila", "Luigi Pirandello", 264);
		
		//controllo attributi di libro
		assertEquals("Uno, nessuno e centomila", l.geTitolo());
		assertEquals("Luigi Pirandello", l.getAutore());
		assertEquals(264, l.numPagine());

		//verifica di toString
		String str = l.toString();
		assertTrue(str.indexOf("nessuno") >= 0);
		assertTrue(str.indexOf("Luigi") >= 0);
		assertTrue(str.indexOf("264") >= 0);

		//verifica di equals
		//libri uguali
		Libro l1 = new Libro("Uno, nessuno e centomila", "Luigi Pirandello", 112);
		assertTrue(l.equals(l1));

		//libri differenti
		Libro l2 = new Libro("Uno, nessuno e centomila", "Luigi", 264);
		assertFalse(l.equals(l2));
	}

	@Test
	public void testCreazioneLibroNonValido() {

		try { // Titolo vuoto
			Libro l = new Libro("", "Luigi Pirandello", 264);
			fail();
		} catch (MonografiaInvalidaException e) {
			/* OK */}

		try { // Autore null
			Libro l = new Libro("Uno, nessuno e centomila", null, 264);
			fail();
		} catch (MonografiaInvalidaException e) {
			/* OK */}

		try { // 0 pagine
			Libro l = new Libro("Uno, nessuno e centomila", "Luigi Pirandello", 0);
			fail();
		} catch (MonografiaInvalidaException e) {
			/* OK */}
	}

	// ============ TEST SERIE ============
	@Test
	public void testCreazioneSerieSemplice() throws MonografiaInvalidaException {
		Libro l1 = new Libro("La Compagnia dell'Anello", "JRR Tolkien", 670);
		Libro l2 = new Libro("Le Due Torri", "JRR Tolkien", 535);
		Libro l3 = new Libro("Il Ritorno del Re", "JRR Tolkien", 670);
		Libro[] libri = { l1, l2, l3 };

		//creazione di una serie valida
		Serie s = new Serie(libri, "Il Signore degli Anelli", "JRR Tolkien");

		//controllo metodi 
		assertEquals("JRR Tolkien", s.getAutore());
		assertEquals("Il Signore degli Anelli", s.geTitolo());

		assertEquals(1875, s.numPagine());

		assertEquals(3, s.getNumMonografie());
		assertEquals(3, s.contaLibriNonSerie());

		//controllo toString
		String str = s.toString();
		assertTrue(str.indexOf("Compagnia") > 0);
		assertTrue(str.indexOf("Torri") > 0);
		assertTrue(str.indexOf("Ritorno") > 0);
	}

	@Test
	public void testSerieNidificate() throws MonografiaInvalidaException {
		Libro l1 = new Libro("La Compagnia dell'Anello", "JRR Tolkien", 670);
		Libro l2 = new Libro("Le Due Torri", "JRR Tolkien", 535);
		Libro l3 = new Libro("Il Ritorno del Re", "JRR Tolkien", 670);
		Libro[] libri1 = { l1, l2, l3};

		//creazione serie
		Serie s1 = new Serie(libri1, "Il Signore degli Anelli", "JRR Tolkien");
		
		Libro l= new Libro("Lo Hobbit", "JRR Tolkien", 350);
		Monografia[] libri2 = { l, s1};

		//creazione serie che contiene un libro e una seria
		Serie s2 = new Serie (libri2, "Saga della Terra di Mezzo","JRR Tolkien");
		
		//controllo valori ritornati dai metodi
		assertEquals("JRR Tolkien", s2.getAutore());

		assertEquals("Saga della Terra di Mezzo", s2.geTitolo());

		assertEquals(2225, s2.numPagine());

		assertEquals(2, s2.getNumMonografie());
		assertEquals(1, s2.contaLibriNonSerie());

		//controllo toString
		String str = s2.toString();
		assertTrue(str.indexOf("Mezzo") > 0);
		assertTrue(str.indexOf("Compagnia") > 0);
		assertTrue(str.indexOf("Torri") > 0);
		assertTrue(str.indexOf("Ritorno") > 0);
	}
	
	@Test
	public void aggiungiMonografiaASerie() throws MonografiaInvalidaException {
		Libro l1 = new Libro("La Compagnia dell'Anello", "JRR Tolkien", 670);
		Libro l2 = new Libro("Le Due Torri", "JRR Tolkien", 535);
		Libro l3 = new Libro("Il Ritorno del Re", "JRR Tolkien", 670);
		Libro[] libri = {l1};
		
		//creazione serie con un libro
		Serie s1 = new Serie(libri, "Il Signore degli Anelli", "JRR Tolkien");
		
		//controllo metodi
		assertEquals("JRR Tolkien", s1.getAutore());

		assertEquals("Il Signore degli Anelli", s1.geTitolo());

		assertEquals(670, s1.numPagine());

		assertEquals(1, s1.getNumMonografie());
		assertEquals(1, s1.contaLibriNonSerie());

		//aggiunta di 2 monografie
		s1.aggiungiMonografia(l2);
		s1.aggiungiMonografia(l3);
		
		//controllo metodi
		assertEquals("JRR Tolkien", s1.getAutore());

		assertEquals("Il Signore degli Anelli", s1.geTitolo());

		assertEquals(1875, s1.numPagine());

		assertEquals(3, s1.getNumMonografie());
		assertEquals(3, s1.contaLibriNonSerie());

		//controllo toString
		String str = s1.toString();
		assertTrue(str.indexOf("Compagnia") > 0);
		assertTrue(str.indexOf("Torri") > 0);
		assertTrue(str.indexOf("Ritorno") > 0);
	}
	
	@Test
	public void testSerieConDuplicati() throws MonografiaInvalidaException {
		Libro l1 = new Libro("La Compagnia dell'Anello", "JRR Tolkien", 670);
		Libro l2 = new Libro("Le Due Torri", "JRR Tolkien", 535);
		Libro l3 = new Libro("Il Ritorno del Re", "JRR Tolkien", 670);
		Libro l4 = new Libro("Le Due Torri", "JRR Tolkien", 510);
		
		Monografia[] libri = { l1, l2, l3, l4 };

		//creazione serie da elenco con libri duplicati
		Serie s = new Serie(libri, "Il Signore degli Anelli","JRR Tolkien");

		//controllo metodi
		assertEquals("JRR Tolkien", s.getAutore());

		assertEquals("Il Signore degli Anelli", s.geTitolo());

		assertEquals(1875, s.numPagine());

		assertEquals(3, s.getNumMonografie());
		assertEquals(3, s.contaLibriNonSerie());
		
		//creazione di libro uguale alla serie esistente
		Libro libroDuplicato = new Libro("Il Signore degli Anelli", "JRR Tolkien", 1200);
		Monografia[] libri2 = {libroDuplicato};
		
		Serie serieConDuplicati = new Serie(libri2, "Il Signore degli Anelli","JRR Tolkien");
		//aggiunta della serie duplicata
		serieConDuplicati.aggiungiMonografia(serieConDuplicati);
		
		//contollo metodi
		assertEquals(1200, serieConDuplicati.numPagine());

		assertEquals(1, serieConDuplicati.getNumMonografie());
		assertEquals(1, serieConDuplicati.contaLibriNonSerie());
		
		//controllo toString
		String str = serieConDuplicati.toString();
		assertTrue(str.indexOf("Signore") > 0);
		assertTrue(str.indexOf("Anelli") > 0);
		assertTrue(str.indexOf("Tolkien") > 0);
	}
	
	@Test
	public void testCreazioneSerieNonValida() throws MonografiaInvalidaException {
		Libro l1 = new Libro("La Compagnia dell'Anello", "JRR Tolkien",670);
		Libro l2 = new Libro("Il Gioco del Trono", "GRR Martin",694);
		Libro l3 = new Libro("Il Ritorno del Re", "JRR Tolkien",670);
	
		Libro[] libri = {l1, l2, l3};
		
		Serie s = null;
		try{ // elenco monografie null
			s = new Serie(null,"Il Signore degli Anelli","JRR Tolkien");
			fail();
		} catch(MonografiaInvalidaException e) {/*OK*/}
		
		try{ // aggiunta monografia errata
			s = new Serie(new Libro[0],"Il Signore degli Anelli","JRR Tolkien");
			s.aggiungiMonografia(l2);
			fail();
		} catch(MonografiaInvalidaException e) {/*OK*/}
		
		try{ // serie non coerente
			Serie s2 = new Serie(libri,"Il Signore degli Anelli","JRR Tolkien");
			fail();
		} catch(MonografiaInvalidaException e) {/*OK*/}
		
	}
	
}	