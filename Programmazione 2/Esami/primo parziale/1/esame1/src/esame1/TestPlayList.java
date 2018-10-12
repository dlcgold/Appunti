package esame1;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestPlayList {

	@Test
	public void testBrano(){
		Brano b = new Brano("Unchained Melody", 4);
		
		assertEquals("Unchained Melody", b.getTitolo());
		
		assertEquals(4, b.getDurataInSecondi());
	}

	@Test
	public void testBranoEquals(){
		Brano b1 = new Brano("Unchained Melody", 4);
		Brano b2 = new Brano("Wonderful Tonight", 10);
		Brano b3 = new Brano("Unchained Melody", 10);
		
		assertFalse(b1.equals(null));
		
		assertFalse(b1.equals(b2));
		
		assertTrue(b1.equals(b1));
		
		assertTrue(b1.equals(b3));
	}

	@Test
	public void testAggiungiBrano(){
		PlayList pl = new PlayList("Love songs", 2);

		assertTrue(pl.aggiungiBrano(new Brano("Unchained Melody", 4)));
		
		assertTrue(pl.aggiungiBrano(new Brano("Wonderful Tonight", 10)));
		
		assertFalse(pl.aggiungiBrano(new Brano("Are You Lonesome Tonight?", 10)));
	}

	@Test
	public void testAggiungiBranoNoDuplicati(){
		PlayList pl = new PlayList("Love songs", 2);
		
		assertTrue(pl.aggiungiBrano(new Brano("Unchained Melody", 4)));
		
		assertFalse(pl.aggiungiBrano("Unchained Melody", 4));
	}

	@Test
	public void testAggiungiBranoBis(){
		PlayList pl = new PlayList("Love songs", 2);
		
		assertTrue(pl.aggiungiBrano("Unchained Melody", 4));
		
		assertTrue(pl.aggiungiBrano("Wonderful Tonight", 10));
		
		assertFalse(pl.aggiungiBrano("Are You Lonesome Tonight?", 10));
	}

	@Test
	public void testPosizioneBrano(){
		PlayList pl = new PlayList("Love songs", 2);
		pl.aggiungiBrano("Unchained Melody", 4);
		pl.aggiungiBrano("Wonderful Tonight", 10);
		
		assertTrue(pl.posizioneBrano(new Brano("Unchained Melody", 4)) >= 0);
		
		assertTrue(pl.posizioneBrano(new Brano("Wonderful Tonight", 10)) >= 0);
		
		assertTrue(pl.posizioneBrano(new Brano("Are You Lonesome Tonight?", 10)) < 0);
	}


	@Test
	public void testRimuoviBrano(){
		PlayList pl = new PlayList("Love songs", 2);
		pl.aggiungiBrano(new Brano("Wonderful Tonight", 10));
		Brano b1 = pl.rimuoviBrano(new Brano("Unchained Melody", 4));

		assertNull(b1);
		
		pl.aggiungiBrano(new Brano("Unchained Melody", 4));
		Brano b2 = pl.rimuoviBrano(new Brano("Unchained Melody", 4));
		
		assertTrue(b2.equals(new Brano("Unchained Melody", 4)));
	
		Brano b3 = pl.rimuoviBrano(new Brano("Unchained Melody", 4));
		
		assertNull(b3);

	}
	
	@Test
	public void testInterazioneFraAggiungiERimuovi(){
		PlayList pl = new PlayList("Love songs", 2);
		
		assertTrue(pl.aggiungiBrano(new Brano("Unchained Melody", 4)));

		assertTrue(pl.aggiungiBrano(new Brano("Wonderful Tonight", 10)));

		assertNotNull(pl.rimuoviBrano(new Brano("Unchained Melody", 4)));

		assertTrue(pl.aggiungiBrano(new Brano("Are You Lonesome Tonight?", 10)));

		assertNotNull(pl.rimuoviBrano(new Brano("Wonderful Tonight", 4)));

		assertTrue(pl.aggiungiBrano(new Brano("One", 12)));

		assertFalse(pl.aggiungiBrano(new Brano("All You Need Is Love", 7)));
	}

	
	@Test
	public void testPlay(){
		PlayList pl = new PlayList("Love songs", 4);
		pl.aggiungiBrano(new Brano("Unchained Melody", 4));

		pl.aggiungiBrano(new Brano("Are You Lonesome Tonight?", 10));

		pl.aggiungiBrano(new Brano("One", 12));
		
		pl.aggiungiBrano(new Brano("All You Need Is Love", 7));
		
		assertEquals(pl.play(),33);
	}
	
    
}