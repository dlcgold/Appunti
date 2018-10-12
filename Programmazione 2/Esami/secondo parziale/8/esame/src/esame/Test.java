package esame;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class Test {

	@org.junit.Test
	public void testCasaIndipendente() {
		CasaIndipendente casa = null;
		try {
			casa = new CasaIndipendente("Milano", "Viale Sarca", 20);
		} catch (AreaAbitabileException e) {
			fail();
		}
		String txt = casa.toString();

		assertTrue(txt.indexOf("Milano") > -1);
		assertTrue(txt.indexOf("Viale Sarca") > -1);
		assertTrue(txt.indexOf("20") > -1);

		assertTrue(casa.getSuperficieTotale() == 20);
		
		assertEquals(casa.getComune(),"Milano");
		assertEquals(casa.getIndirizzo(), "Viale Sarca");

	}

	@org.junit.Test
	public void testCasaIndipendenteException() {
		CasaIndipendente casa = null;
		try {
			casa = new CasaIndipendente(null, "Viale Sarca", 20);
			fail();
		} catch (AreaAbitabileException e) {

		}

		try {
			casa = new CasaIndipendente("Milano", null, 20);
			fail();
		} catch (AreaAbitabileException e) {

		}

		try {
			casa = new CasaIndipendente("Milano", "Viale Sarca", -10);
			fail();
		} catch (AreaAbitabileException e) {

		}
	}

	@org.junit.Test
	public void testCasaConfronti() {
		CasaIndipendente casa1 = null;
		try {
			casa1 = new CasaIndipendente("Milano", "Viale Sarca", 20);
		} catch (AreaAbitabileException e) {
			fail();
		}

		CasaIndipendente casa2 = null;
		try {
			casa2 = new CasaIndipendente("Milano", "Viale Sarca", 20);
		} catch (AreaAbitabileException e) {
			fail();
		}

		assertTrue(casa1.equals(casa2));
		assertFalse(casa1.equals(new Object()));
		assertFalse(casa1.equals(null));

		try {
			casa2 = new CasaIndipendente("Roma", "Viale Sarca", 20);
		} catch (AreaAbitabileException e) {
			fail();
		}
		assertFalse(casa1.equals(casa2));

		try {
			casa2 = new CasaIndipendente("Milano", "Viale Sarca", 10);
		} catch (AreaAbitabileException e) {
			fail();
		}
		assertFalse(casa1.equals(casa2));
	}

	@org.junit.Test
	public void testCreazioneVillaggio() {
		List<AreaAbitabile> area = null;
		try {
			area = new ArrayList<AreaAbitabile>();
			area.add(new CasaIndipendente("Milano", "Viale Sarca 10", 20));
			area.add(new CasaIndipendente("Milano", "Viale Fulvio Testi 130", 12));
			area.add(new CasaIndipendente("Milano", "Viale Sarca 12", 12));

			Villaggio v = new Villaggio("Milano", area);

			assertTrue(v.getComune().equals("Milano"));
			assertTrue(v.getSuperficieTotale() == 44);
		} catch (AreaAbitabileException e) {
			fail();
		}

	}

	@org.junit.Test
	public void testCreazioneVillaggioException() {
		List<AreaAbitabile> area = null;
		try {
			area = new ArrayList<AreaAbitabile>();
			area.add(new CasaIndipendente("Milano", "Viale Sarca 10", 20));
			area.add(new CasaIndipendente("Milano", "Viale Fulvio Testi 130", 12));
			area.add(new CasaIndipendente("Milano", "Viale Sarca 12", 12));
		} catch (AreaAbitabileException e) {
			fail();
		}

		Villaggio v = null;
		try {
			v = new Villaggio(null, area);
			fail();
		} catch (AreaAbitabileException e) {
		}

		try {
			v = new Villaggio("Milano", area);
		} catch (AreaAbitabileException e) {
			fail();
		}

		try {
			v.addAreaAbitabile(new CasaIndipendente("Roma", "Viale Sarca 10", 20));
			fail();
		} catch (AreaAbitabileException e) {
		}

		List<AreaAbitabile> area2 = null;
		Villaggio v2 = null;
		try {
			area2 = new ArrayList<AreaAbitabile>();
			area2.add(new CasaIndipendente("Roma", "Viale Sarca 10", 20));
			area2.add(new CasaIndipendente("Roma", "Viale Fulvio Testi 130", 12));
			area2.add(new CasaIndipendente("Roma", "Viale Sarca 12", 12));

			v2 = new Villaggio("Roma", area2);
		} catch (AreaAbitabileException e) {
			fail();
		}

		try {
			v.addAreaAbitabile(v2);
			fail();
		} catch (AreaAbitabileException e) {
		}

	
	}

	@org.junit.Test
	public void testContaCaseVillaggio() {
		List<AreaAbitabile> area = null;
		try {
			area = new ArrayList<AreaAbitabile>();
			area.add(new CasaIndipendente("Milano", "Viale Sarca 10", 20));
			area.add(new CasaIndipendente("Milano", "Viale Fulvio Testi 130", 12));
			area.add(new CasaIndipendente("Milano", "Viale Sarca 12", 12));
		} catch (AreaAbitabileException e) {
			fail();
		}

		Villaggio v = null;

		try {
			v = new Villaggio("Milano", area);
		} catch (AreaAbitabileException e) {
			fail();
		}

		assertTrue(v.numCaseIndipendenti() == 3);

		List<AreaAbitabile> area2 = null;
		Villaggio v2 = null;
		try {
			area2 = new ArrayList<AreaAbitabile>();
			area2.add(new CasaIndipendente("Milano", "Viale Monza 10", 20));
			area2.add(new CasaIndipendente("Milano", "Viale Monza 8", 12));
			area2.add(new CasaIndipendente("Milano", "Viale Monza 12", 12));

			v2 = new Villaggio("Milano", area2);
			v.addAreaAbitabile(v2);
		} catch (AreaAbitabileException e) {
			fail();
		}

		assertTrue(v.numCaseIndipendenti() == 6);

		try {
			v.addAreaAbitabile(new CasaIndipendente("Milano", "Viale Fulvio Testi 16", 20));
		} catch (AreaAbitabileException e) {
			fail();
		}
		
		assertTrue(v.numCaseIndipendenti() == 7);
		assertTrue(v.getSuperficieTotale()==108);
		
		List<AreaAbitabile> area3 = null;
		try {
			area3 = new ArrayList<AreaAbitabile>();
			area3.add(new CasaIndipendente("Milano", "Viale Ripamonti 10", 20));
			area3.add(new CasaIndipendente("Milano", "Viale Ripamonti 8", 12));
			area3.add(new CasaIndipendente("Milano", "Viale Ripamonti 12", 12));
		} catch (AreaAbitabileException e) {
			fail();
		}
		
		try {
			v.addAreaAbitabile(area3);
		} catch (AreaAbitabileException e) {
			fail();
			
			assertTrue(v.getSuperficieTotale()==152);
		}
	}
	
	@org.junit.Test
	public void testVillaggioToString() {
		List<AreaAbitabile> area = null;
		try {
			area = new ArrayList<AreaAbitabile>();
			area.add(new CasaIndipendente("Milano", "Viale Sarca 10", 20));
			area.add(new CasaIndipendente("Milano", "Viale Fulvio Testi 130", 12));
			area.add(new CasaIndipendente("Milano", "Viale Sarca 12", 12));
		} catch (AreaAbitabileException e) {
			fail();
		}

		Villaggio v = null;

		try {
			v = new Villaggio("Milano", area);
		} catch (AreaAbitabileException e) {
			fail();
		}

		String txt = v.toString();
		assertTrue(txt.indexOf("Viale Sarca 12")>-1);
		assertTrue(txt.indexOf("20")>-1);
		
		List<AreaAbitabile> area2 = null;
		Villaggio v2 = null;
		try {
			area2 = new ArrayList<AreaAbitabile>();
			area2.add(new CasaIndipendente("Milano", "Viale Monza 10", 20));
			area2.add(new CasaIndipendente("Milano", "Viale Monza 8", 12));
			area2.add(new CasaIndipendente("Milano", "Viale Monza 12", 12));

			v2 = new Villaggio("Milano", area2);
			v.addAreaAbitabile(v2);
		} catch (AreaAbitabileException e) {
			fail();
		}

		txt = v.toString();
		assertTrue(txt.indexOf("Viale Monza 10")>-1);
		
		
		try {
			v.addAreaAbitabile(new CasaIndipendente("Milano", "Viale Fulvio Testi 16", 20));
		} catch (AreaAbitabileException e) {
			fail();
		}
		
		txt = v.toString();
		assertTrue(txt.indexOf("Viale Fulvio Testi 16")>-1);
	
	}

}
