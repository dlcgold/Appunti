import static org.junit.Assert.*;

import org.junit.Test;


public class TestPoligoni {

	@Test
	public void testRettangolo() {
		Poligono p = new Rettangolo(10.0, 20.0);
		assertEquals(200.0, p.calcolaArea(), 0.001);
	}
	
	@Test
	public void testRettangoliUguali() {
		Poligono p1 = new Rettangolo(10.0, 20.0);
		Poligono p2 = new Rettangolo(10.0, 20.0);
		Poligono p3 = new Rettangolo(2.0, 100.0);
		assertEquals(p1, p2);
		assertEquals(p1, p3);
	}

	@Test
	public void testRettangoliDiversi() {
		Poligono p1 = new Rettangolo(10.0, 20.0);
		Poligono p2 = new Rettangolo(7.0, 20.0);
		assertNotEquals(p1, p2);
		assertNotEquals(p1, new Object());
	}
	
	@Test
	public void testRettangoliUgualiSoglie() {
		Poligono p1 = new Rettangolo(10.0, 20.0);
		Poligono p2 = new Rettangolo(10.0, 20.0000999);
		Poligono p3 = new Rettangolo(10.0, 20.0001);
		assertEquals(p1, p2);
		assertEquals(p2, p1);
		assertNotEquals(p1, p3);
		assertNotEquals(p3, p1);
	}

	@Test
	public void testQuadrato() {
		Poligono p = new Quadrato(10.0);
		assertEquals(100.0, p.calcolaArea(), 0.001);
	}

	@Test
	public void testQuadratiUguaglianza() {
		Poligono p1 = new Quadrato(10.0);
		Poligono p2 = new Quadrato(10.0);
		Poligono p3 = new Quadrato(20.0);
		Poligono p4 = new Quadrato(10.00001);
		assertEquals(p1, p2);
		assertNotEquals(p1, p3);
		assertEquals(p1, p4);
		assertEquals(p4, p1);
	}

	@Test
	public void testDisegnoNoPoligoni() {
		DisegnoGeometrico dg = new DisegnoGeometrico();		
		try{
			dg.calcolaAreaTotale();
			fail();
		}catch(NoPoligoniExpcetion e) {		
			//OK
		}
	}

	@Test
	public void testDisegnoUnPoligono() {
		DisegnoGeometrico dg = new DisegnoGeometrico();
		dg.aggiungiPoligono(new Rettangolo(10.0, 20.0));		
		try{
			assertEquals(200.0, dg.calcolaAreaTotale(), 0.001);
		}catch(NoPoligoniExpcetion e) {
			fail();			
		}
	}
	
	@Test
	public void testDisegnoMoltiPoligoni() {
		DisegnoGeometrico dg = new DisegnoGeometrico();
		dg.aggiungiPoligono(new Rettangolo(10.0, 20.0));		
		dg.aggiungiPoligono(new Quadrato(30.0));		
		dg.aggiungiPoligono(new Rettangolo(5.0, 8.0));		
		try{
			assertEquals(1140.0, dg.calcolaAreaTotale(), 0.001);
		}catch(NoPoligoniExpcetion e) {
			fail();
		}
	}
}
