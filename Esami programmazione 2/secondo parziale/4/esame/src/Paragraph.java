
public class Paragraph extends DocumentItem {

	private String text;
	
	public Paragraph(String testo) {
		if(testo==null)
			throw new NonValidDocumentException();
		this.text = testo;
	}

	@Override
	public String getText() {
		return text;
	}

	@Override
	public void update(DocumentItem other) throws ModifyingLockedDocumentException {
		if(this.isLocked())
			throw new ModifyingLockedDocumentException();
		this.text=other.getText();
	}
}
