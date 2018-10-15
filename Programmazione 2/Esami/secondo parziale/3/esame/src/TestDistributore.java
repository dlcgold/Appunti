import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Test;

public class TestDistributore {

	@Test
	public void testProdottoQuantitaNegativa() {
		Toast t = new Toast(3, false, -2);
		assertEquals(3, t.getPrezzoUnitario());
		assertEquals(0, t.getQuantita());
	}

	@Test
	public void testProdottoPiccolo() {
		Toast t = new Toast(3, false, 2);
		assertEquals(3, t.getPrezzoUnitario());
		assertEquals(2, t.getQuantita());
		t.incrementaQuantita(3);
		t.decrementaQuantita(4);
		assertEquals(1, t.getQuantita());
	}

	@Test
	public void testProdottoGrande() {
		Toast t = new Toast(3, true, 2);
		assertEquals(6, t.getPrezzoUnitario());
		assertEquals(2, t.getQuantita());
		t.incrementaQuantita(3);
		t.decrementaQuantita(4);
		assertEquals(1, t.getQuantita());
	}

	@Test
	public void testCreazioneDistributore() {
		HashSet<Prodotto> prodotti = new HashSet<Prodotto>();

		Toast t1 = new Toast(2, true, 4);
		Toast t2 = new Toast(3, true, 2);
		Toast t3 = new Toast(1, false, 1);

		prodotti.add(t1);
		prodotti.add(t2);
		prodotti.add(t3);

		DistributoreAutomatico da = new DistributoreAutomatico(prodotti);

		try {
			assertTrue(prodotti.contains(da.getProdotto(0)));
			assertTrue(prodotti.contains(da.getProdotto(1)));
			assertTrue(prodotti.contains(da.getProdotto(2)));
		} catch (Exception e) {
			fail();
		}

		try {
			da.getProdotto(3);
			fail();
		} catch (PosizioneNonValidaException e) {
			// correct
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testIncrementaQuantitaProdotto() {
		HashSet<Prodotto> prodotti = new HashSet<Prodotto>();

		Toast t1 = new Toast(2, true, 4);
		Toast t2 = new Toast(3, true, 2);
		Toast t3 = new Toast(1, false, 1);

		prodotti.add(t1);
		prodotti.add(t2);
		prodotti.add(t3);

		DistributoreAutomatico da = new DistributoreAutomatico(prodotti);

		//incrementa di 2 le quantità di tutti i prodotti
		for (int i = 0; i < 3; i++) {
			try {
				int qt = da.getProdotto(i).getQuantita();
				da.incrementaQuantitaProdotto(i, 2);
				int newQt = da.getProdotto(i).getQuantita();
				assertEquals(qt + 2, newQt);
			} catch (Exception e) {
				fail();
			}
		}

		try {
			da.incrementaQuantitaProdotto(3, 2);
			fail();
		} catch (Exception e) {
			// correct
		}
	}

	@Test
	public void testAcquista() {
		HashSet<Prodotto> prodotti = new HashSet<Prodotto>();

		Toast t1 = new Toast(2, true, 4);

		prodotti.add(t1);

		DistributoreAutomatico da = new DistributoreAutomatico(prodotti);

		try {
			int resto = da.acquista(0, 5);
			assertEquals(1, resto);

			int qt = da.getProdotto(0).getQuantita();
			assertEquals(3, qt);
			
			resto = da.acquista(0, 4);
			qt = da.getProdotto(0).getQuantita();
			assertEquals(2, qt);
			
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testAcquistaValoreInsufficiente() {
		HashSet<Prodotto> prodotti = new HashSet<Prodotto>();

		Toast t1 = new Toast(2, true, 4);

		prodotti.add(t1);

		DistributoreAutomatico da = new DistributoreAutomatico(prodotti);

		try {
			da.acquista(0, 3);
			fail();
		}catch(ProdottoNonAcquistabileException e) {
			assertTrue(e.getMessage().contains("valore insufficiente"));
		}catch(Exception e) {
			fail();
		}
	}

	@Test
	public void testAcquistaPosizioneNonValida() {
		HashSet<Prodotto> prodotti = new HashSet<Prodotto>();

		Toast t1 = new Toast(2, true, 4);
		Toast t2 = new Toast(1, false, 2);
		
		prodotti.add(t1);
		prodotti.add(t2);

		DistributoreAutomatico da = new DistributoreAutomatico(prodotti);

		try {
			da.acquista(2, 3);
			fail();
		}catch(PosizioneNonValidaException e) {
			//correct
		}catch(Exception e) {
			fail();
		}
	}

	@Test
	public void testQuantitaInsufficiente() {
		HashSet<Prodotto> prodotti = new HashSet<Prodotto>();

		Toast t1 = new Toast(2, true, 0);
		
		prodotti.add(t1);

		DistributoreAutomatico da = new DistributoreAutomatico(prodotti);

		try {
			da.acquista(0, 8);
			fail();
		}catch(ProdottoNonAcquistabileException e) {
			assertTrue(e.getMessage().contains("quantita insufficiente"));
		}catch(Exception e) {
			fail();
		}
	}

}
