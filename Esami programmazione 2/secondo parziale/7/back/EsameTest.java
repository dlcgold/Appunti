
import static org.junit.Assert.*;

import org.junit.Test;


public class EsameTest {
	

	@Test
	public void testMossaOrizontale() {
		Mossa m = new MossaRettilineaOrizzontale(10);
		assertEquals(10, m.getPassi());

		PuntoXY p1 = new PuntoXY(1, 1);
		PuntoXY p2 = m.esegui(p1);
		assertEquals(11, p2.getX());
		assertEquals(1, p2.getY());
	}


	@Test
	public void testMossaVeritcale() {
		Mossa m = new MossaRettilineaVerticale(10);
		assertEquals(10, m.getPassi());

		PuntoXY p1 = new PuntoXY(1, 1);
		PuntoXY p2 = m.esegui(p1);
		assertEquals(1, p2.getX());
		assertEquals(11, p2.getY());
	}

	@Test
	public void testMossaEccezione() {
		try {
			Mossa m = new MossaRettilineaOrizzontale(0);
			fail();
		} catch (RobotException e) {
			//Ok
		}

		try {
			Mossa m = new MossaRettilineaVerticale(0);
			fail();
		} catch (RobotException e) {
			//Ok
		}		
	}

	@Test
	public void testMappaPosizioneOk() {
		PuntoXY obiettivo = new PuntoXY(3, 3);
		Mappa m = new Mappa(-10, 10, -20, 20, obiettivo);
		
		assertFalse(m.posizioneValida(new PuntoXY(11, 1)));
		assertFalse(m.posizioneValida(new PuntoXY(-11, 1)));
		assertFalse(m.posizioneValida(new PuntoXY(1, 21)));
		assertFalse(m.posizioneValida(new PuntoXY(1, -21)));
		
		assertTrue(m.posizioneValida(new PuntoXY(10, 1)));
		assertTrue(m.posizioneValida(new PuntoXY(-10, 1)));
		assertTrue(m.posizioneValida(new PuntoXY(1, 20)));
		assertTrue(m.posizioneValida(new PuntoXY(1, -20)));
	}

	@Test
	public void testMappaArrivoOk() {
		PuntoXY obiettivo = new PuntoXY(3, 3);
		Mappa m = new Mappa(-10, 10, -20, 20, obiettivo);
		
		assertFalse(m.obiettivoRaggiunto(new PuntoXY(0, 3)));
		assertFalse(m.obiettivoRaggiunto(new PuntoXY(3, 0)));

		assertTrue(m.obiettivoRaggiunto(new PuntoXY(3, 3)));
	}

	@Test
	public void testMappaEccezioni() {
		PuntoXY o = new PuntoXY(3, 3);
		try {
			Mappa m = new Mappa(0, 5, 0, 5, null);
			fail();
		} catch (RobotException e) {
			//Ok
		}

		try {
			Mappa m = new Mappa(0, 2, 0, 5, o); //o.x > max
			fail();
		} catch (RobotException e) {
			//Ok
		}

		try {
			Mappa m = new Mappa(4, 5, 0, 5, o); //o.x < min
			fail();
		} catch (RobotException e) {
			//Ok
		}

		try {
			Mappa m = new Mappa(0, 5, 0, 2, o); //o.y > max
			fail();
		} catch (RobotException e) {
			//Ok
		}

		try {
			Mappa m = new Mappa(0, 5, 4, 5, o); //o.y < min
			fail();
		} catch (RobotException e) {
			//Ok
		}
	}
	
	@Test
	public void testRobot() {
		Mappa m = new Mappa(-10, 10, -20, 20, new PuntoXY(3, 3));
		RobotProgrammabile r = new RobotProgrammabile(m);
		
		assertEquals(0, r.getPosizione().getX());
		assertEquals(0, r.getPosizione().getY());
		assertEquals(0, r.numeroMosse());
	}
	


	@Test
	public void testRobotMossaFuoriMappa() {
		Mappa m = new Mappa(-10, 10, -20, 20, new PuntoXY(3, 3));
		RobotProgrammabile r = new RobotProgrammabile(m);

		r.aggiungiMossa(new MossaRettilineaOrizzontale(2));
		r.aggiungiMossa(new MossaRettilineaVerticale(30));
		try {
			r.eseguiProgramma();
			fail();
		} catch (RobotException e) {
			//Ok
		}
	}
	
	@Test
	public void testRobotArrivoSbagliato() {
		Mappa m = new Mappa(-10, 10, -20, 20, new PuntoXY(3, 3));
		RobotProgrammabile r = new RobotProgrammabile(m);
		
		r.aggiungiMossa(new MossaRettilineaOrizzontale(2));
		r.aggiungiMossa(new MossaRettilineaVerticale(3));

		assertFalse(r.eseguiProgramma());
	}

	@Test
	public void testRobotArrivoGiusto() {
		Mappa m = new Mappa(-10, 10, -20, 20, new PuntoXY(3, 3));
		RobotProgrammabile r = new RobotProgrammabile(m);
		
		r.aggiungiMossa(new MossaRettilineaOrizzontale(3));
		r.aggiungiMossa(new MossaRettilineaVerticale(3));

		assertTrue(r.eseguiProgramma());
	}

	@Test
	public void testRobotSenzaSupervisore() {
		try {
			RobotProgrammabile r = new RobotProgrammabile(null);
			fail();
		} catch (RobotException e) {
			//Ok
		}
	}

	@Test
	public void testRobotFuoriMappa() {
		Mappa m = new Mappa(1, 10, 2, 20, new PuntoXY(10, 20));
		try {
			RobotProgrammabile r = new RobotProgrammabile(m);
			fail();
		} catch (RobotException e) {
			//Ok
		}
	}

}
