import junit.framework.TestCase;

public class TestDocumentItems extends TestCase {
	
	//Verifica l'impossibilita' di creare un paragrafo senza testo
	public void testNonValidParagraph() {
		try {
			DocumentItem par = new Paragraph(null);
			fail();
		} catch(NonValidDocumentException e){/*pass*/}
	}

	//Verifica la corretta creazione del paragrafo
	public void testNewParagraph() {
		DocumentItem par = new Paragraph("some text");
		assertFalse(par.isLocked());
		assertEquals("some text", par.getText());
	}

	//Verifica il conteggio sul caratteri e l'update di un paragrafo
	public void testUseParagraph() throws Exception {
		DocumentItem par = new Paragraph("some text");
		assertEquals(9, par.charactersCount());
		
		par.update(new Paragraph("some more text"));
		assertEquals("some more text", par.getText());
		assertEquals(14, par.charactersCount());
	}

	//Verifica la gestione degli errori quando si modifica un paragrafo in stato di lock
	public void testLockedParagraph() {
		DocumentItem par = new Paragraph("some text");
		try {
			par.setLock(true);
			par.update(new Paragraph("some more text"));
			fail();
		} catch (ModifyingLockedDocumentException e) {/*pass*/}
		
		assertEquals("some text", par.getText());
	}

	//Verifica l'impossibilita' di creare un documento senza testo
	public void testNonValidDocument() {
		try {
			DocumentItem doc = new Document(null);
			fail();
		} catch(NonValidDocumentException e){/*pass*/}
	}
 
	//Verifica la corretta creazione di documenti
	public void testNewDocument() {
		DocumentItem doc = new Document(new Paragraph("some text"));
		assertFalse(doc.isLocked());
		assertEquals("some text", doc.getText());

		DocumentItem docBis = new Document(doc);
		assertEquals("some text", docBis.getText());
	}

	//Verifica l'aggiunta incrementale di paragrafi
	public void testDocumentUpdate() throws Exception {
		DocumentItem doc = new Document(new Paragraph("some text "));
		doc.update(new Paragraph("other text"));
		assertEquals("some text other text", doc.getText());

		DocumentItem docBis = new Document(doc);
		docBis.update(new Paragraph(" yet different text"));
		assertEquals("some text other text yet different text", docBis.getText());
	}

	//Verifica la creazioni di un documento con molte sezioni
	public void testDocumentMultipleParts() throws Exception {
		Document section0 = new Document(new Paragraph("Part0 "));
		Paragraph section1 = new Paragraph("Part1 ");
		Document section2 = new Document(new Paragraph("Part2a "));
		section2.update(new Paragraph("Part2b "));
		
		Document doc = new Document(section0);
		doc.update(section1);
		doc.update(section2);
		
		assertEquals("Part0 Part1 Part2a Part2b ", doc.getText());
	}
	
	//Verifica l'update delle sezioni di un documento
	public void testUpdateMultipleParts() throws Exception {
		Paragraph section0 = new Paragraph("Part0 ");
		Document section1 = new Document(new Paragraph("Part1 "));
		
		Document doc = new Document(section0);
		doc.update(section1);
		
		doc.update(new Paragraph("NewPart0 "), 0);
		doc.update(new Paragraph("NewPart1 "), 1);
		
		assertEquals("NewPart0 Part1 NewPart1 ", doc.getText());
	}
	
	//Verifica la gestione degli errori quando si modifica un documento in stato di lock
	public void testLockedDocument() {
		DocumentItem doc = new Document(new Paragraph("some text"));
		try {
			doc.setLock(true);
			doc.update(new Paragraph("some more text"));
			fail();
		} catch (ModifyingLockedDocumentException e) {/*pass*/}
		
		assertEquals("some text", doc.getText());
	}

	//Verifica la gestione degli errori quando si modifica un documento che ha alcune sezioni in stato di lock
	public void testDocumentWithLockedParts() throws Exception {
		Paragraph section0 = new Paragraph("Part0 ");
		Document section1 = new Document(new Paragraph("Part1 "));
		section1.setLock(true);
		
		Document doc = new Document(section0);
		doc.update(section1);
		
		doc.update(new Paragraph("NewPart0 "), 0);
		try {
			doc.update(new Paragraph("NewPart1 "), 1);
			fail();
		} catch (ModifyingLockedDocumentException e) {/*pass*/}
		
		assertEquals("NewPart0 Part1 ", doc.getText());
	}
	
}
