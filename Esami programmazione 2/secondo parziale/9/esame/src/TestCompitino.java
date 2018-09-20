
import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;

import org.junit.Test;

import junit.framework.TestCase;


public class TestCompitino {

//==============  TEST VOLO DIRETTO ============
	/** Verifica la corretta istanziazione di un volo diretto */
	@Test
	public void testVoloDiretto() throws VoloNonValidoException{
		Volo v = new VoloDiretto("LIN", "FCO", 45);
		assertEquals("FCO", v.getAeroportoArrivo());
		assertEquals("LIN", v.getAeroportoPartenza());
		assertEquals(45, v.getDurataInMinuti());
		assertEquals(1, v.getNumeroTratte(), 1);
	}
	
	/** Verifica la gestione degli errori in fase di costruzione di un volo diretto */
	@Test
	public void testGestioneErroriVoloDiretto() {
		Volo v;
		try {
			v = new VoloDiretto("AAA", "FCO", 45);
			fail();
		} catch (VoloNonValidoException e) { /*OK - Aeroporto partenza non esistente*/}
		try {
			v = new VoloDiretto("FCO", null, 45);
			fail();
		} catch (VoloNonValidoException e) { /*OK - Aeroporto d'arrivo null*/}
		try {
			v = new VoloDiretto("LIN", "FCO", 0);
			fail();
		} catch (VoloNonValidoException e) { /*durata 0*/}
	}

	/** Verifica il toString per un volo diretto */
	@Test
	public void testToStringVoloDiretto() throws VoloNonValidoException {
		Volo v = new VoloDiretto("LIN", "FCO", 45);
		assertTrue(v.toString().indexOf("FCO") >= 0);
		assertTrue(v.toString().indexOf("LIN") >= 0);
		assertTrue(v.toString().indexOf("45") >= 0);
		assertTrue(v.toString().indexOf("1") >= 0);
	}

//==============  TEST VOLO MULTITRATTA ============
	
	/** Verifica la corretta istanziazione di un volo multi tratta */
	@Test
	public void testVoloMultiTratta() throws VoloNonValidoException{
		ArrayList<VoloDiretto> vv = new ArrayList<VoloDiretto>();
		
		vv.add(new VoloDiretto("MXP", "LIN", 20));
		vv.add(new VoloDiretto("LIN", "FCO", 60));
		vv.add(new VoloDiretto("FCO", "SFO", 665));
		
		VoloMultiTratta v = new VoloMultiTratta(vv);
		assertEquals("SFO", v.getAeroportoArrivo());
		assertEquals("MXP", v.getAeroportoPartenza());
		assertEquals(745, v.getDurataInMinuti());
		assertEquals(3, v.getNumeroTratte());
	}

	/** Verifica la gestione degli errori in fase di costruzione di un volo multi tratta */
	@Test
	public void testGestioneErroriVoloMultiTratta() throws VoloNonValidoException {
		VoloDiretto v1 = new VoloDiretto("MXP", "LIN", 60);
		VoloDiretto v2 = new VoloDiretto("LIN", "FCO", 60);
		
		ArrayList<VoloDiretto> vv1 = new ArrayList<VoloDiretto>();
		vv1.add(v2);
		vv1.add(v1);
		
		VoloMultiTratta v;
		try {
			v = new VoloMultiTratta(vv1);
			fail();
		} catch (VoloNonValidoException e) { /*OK - ordine sbagliato*/}
		
		vv1.clear();
		vv1.add(v1);
		vv1.add(v2);
		vv1.add(v2);
		try {
			v = new VoloMultiTratta(vv1);
			fail();
		} catch (VoloNonValidoException e) { /*OK - ordine sbagliato*/}
	}

	/** Verifica il toString per un volo multi tratta */
	@Test
	public void testToStringVoloMultiTratta() throws VoloNonValidoException{
		ArrayList<VoloDiretto> vv1 = new ArrayList<VoloDiretto>();
		vv1.add(new VoloDiretto("LIN", "FCO", 60));
		vv1.add(new VoloDiretto("FCO", "SFO", 665));
		VoloMultiTratta v = new VoloMultiTratta(vv1);
		assertTrue(v.toString().indexOf("SFO") >= 0);
		assertTrue(v.toString().indexOf("LIN") >= 0);
		assertTrue(v.toString().indexOf("725") >= 0);
		assertTrue(v.toString().indexOf("2") >= 0);
	}
	
	//==============  TEST REGISTRO ============
	
	/** Verifica il metodo aggiungiVolo(codice, Volo) della classe RegistroVoli */
	@Test
	public void testRegistroAgggiungiVolo() throws CodiceVoloNonValidoException,VoloNonValidoException{
		
		VoloDiretto v = new VoloDiretto("MXP", "LIN", 20); 
		
		ArrayList<VoloDiretto> vv1 = new ArrayList<VoloDiretto>();
		vv1.add(new VoloDiretto("MXP", "LIN", 20));
		vv1.add(new VoloDiretto("LIN", "FCO", 60));
		vv1.add(new VoloDiretto("FCO", "SFO", 665));

		VoloMultiTratta vv = new VoloMultiTratta(vv1);
		
		RegistroVoli reg = new RegistroVoli();
		try{
			reg.aggiungiVolo("AB1", vv); 
			fail();
		}catch(CodiceVoloNonValidoException e) {/* OK -codice non ha 5 caratteri*/}
			
			
		reg.aggiungiVolo("P2A01", vv); //aggiunge un volo diretto 
		reg.aggiungiVolo("P2A02", v);  //aggiunge un volo multi tratta
	}	

	/** Verifica il metodo aggiungiVolo(codice, aeroporto...) della classe RegistroVoli */
	@Test
	public void testRegistroAggiungiNuovoVoloDiretto() throws CodiceVoloNonValidoException,VoloNonValidoException{
		RegistroVoli reg = new RegistroVoli();
		try{
			reg.aggiungiVolo("P2A03", "LIN", "AAA", 660);  //aeroporto arrivo non esistente
		}catch(VoloNonValidoException e) {/* OK - aeroporto non esistente*/}

		reg.aggiungiVolo("P2A03", "LIN", "FCO", 660);  //aggiunge un altro volo diretto	
	}	
	
	/** Verifica il metodo getListaCodiciDeiVoli della classe RegistroVoli */
	@Test
	public void testRegistroGetListaCodiciDeiVoli() throws CodiceVoloNonValidoException,VoloNonValidoException{
		VoloDiretto v1 = new VoloDiretto("MXP", "LIN", 20);
		RegistroVoli reg = new RegistroVoli();
		reg.aggiungiVolo("P2A01", v1);
		reg.aggiungiVolo("P2A03", "LIN", "FCO", 660);
		String codici[] = reg.getListaCodiciDeiVoli();
		assertEquals(2, codici.length);
		String s = codici[0] + codici[1];
		assertTrue(s.indexOf("P2A01") >= 0);
		assertTrue(s.indexOf("P2A03") >= 0);
	}	
	
	/** Verifica il metodo getVolo della classe RegistroVoli */
	@Test
	public void testRegistroGetVolo() throws CodiceVoloNonValidoException,VoloNonValidoException{
		VoloDiretto v1 = new VoloDiretto("MXP", "LIN", 20);
		RegistroVoli reg = new RegistroVoli();
		reg.aggiungiVolo("P2A01", v1);
		reg.aggiungiVolo("P2A03", "LIN", "FCO", 660);
		Volo v = reg.getVolo("P2A03");
		assertEquals(660, v.getDurataInMinuti());
		v = reg.getVolo("P2A02");
		assertNull(v);
	}	
	
	/** Verifico il metodo di creazione file
	 * @throws VoloNonValidoException 
	 * @throws CodiceVoloNonValidoException 
	 * @throws IOException */
	@Test
	public void testCreazioneFile() throws VoloNonValidoException, CodiceVoloNonValidoException, IOException {
		VoloDiretto v1 = new VoloDiretto("MXP", "LIN", 20); 
		VoloDiretto v2 = new VoloDiretto("FCO", "SFO", 665); 
		
		ArrayList<VoloDiretto> vv1 = new ArrayList<VoloDiretto>();
		vv1.add(new VoloDiretto("MXP", "LIN", 20));
		vv1.add(new VoloDiretto("LIN", "FCO", 60));
		vv1.add(new VoloDiretto("FCO", "SFO", 665));

		VoloMultiTratta vv = new VoloMultiTratta(vv1);
		
		RegistroVoli reg = new RegistroVoli();
		reg.aggiungiVolo("P2A01", v1);
		reg.aggiungiVolo("P2A08", vv);
		reg.aggiungiVolo("P2A03", v2);
		
		String nomeFile ="elencoVoli.txt";
		reg.dumpSuFile(nomeFile);
		
		FileInputStream fin = new FileInputStream(nomeFile);
		Scanner in = new Scanner(fin);
		
		String str ="";
		while(in.hasNextLine()) {
			str += in.nextLine();
		}
			
		assertTrue(str.indexOf("FCO")>0);
		assertTrue(str.indexOf("745")>0);
		assertTrue(str.indexOf("665")>0);
		in.close();
		
	}
}
