package org.xmlcml.cml.parsetree;

import java.util.List;

public class DocumentFixture {

    POSDocument document = null;
    POSSentence sentence = null;
	List<POSElement> sentenceList = null;
	String rootdir = "org/xmlcml/cml/parsetree";
	String indir = rootdir+"/in";
	String refdir = rootdir+"/ref";
	
	public DocumentFixture() {
		makeFiles();
	}
	private void makeFiles() {
		String filename = indir+"/file1.xml";
		document = readDocument(filename);
		sentenceList = document.getSentenceList();
		sentence = (POSSentence) sentenceList.get(0);
	}
	POSDocument readDocument(String filename) {
		POSDocument document1 = null;
		try {
			document1 = POSDocument.parseDocument(this.getClass().getClassLoader().getResourceAsStream(filename));
		} catch (Exception e) {
			throw new RuntimeException("Cannot read "+filename, e);
        }
		return document1;
	}
	
}
