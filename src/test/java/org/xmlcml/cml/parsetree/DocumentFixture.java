package org.xmlcml.cml.parsetree;

import java.util.ArrayList;
import java.util.List;

public class DocumentFixture {

	String[] filenames = null;
    POSDocument[] documents = null;
	public DocumentFixture() {
		makeFiles();
	}
	private void makeFiles() {
		if (filenames == null) {
			filenames = new String[] {
	        "parsetrees/target/file1.xml",
	        "parsetrees/target/file2.xml",
	        "parsetrees/target/file3.xml",
	        "parsetrees/target/file4.xml",
	        "parsetrees/target/file5.xml",
	        "parsetrees/target/file6.xml",
	        "parsetrees/target/file7.xml",
	        "parsetrees/target/file8.xml",
	        "parsetrees/target/file9.xml",
			};

		    documents = new POSDocument[filenames.length];
	        for (int i = 0; i < filenames.length; i++) {
	            try {
					documents[i] = POSDocument.parseDocument(this.getClass().getClassLoader().getResourceAsStream(filenames[i]));
				} catch (Exception e) {
					throw new RuntimeException("Cannot read "+filenames[i]);
				}
	        }
		}
	}
	
    public List<POSElement> getDocumentList() {
    	List<POSElement> documentList = new ArrayList<POSElement>();
    	for (POSElement document : documents) {
    		documentList.add((POSDocument)document);
    	}
    	return documentList;
    }
    
    public List<POSElement> getSentenceList() {
    	List<POSElement> sentenceList = new ArrayList<POSElement>();
    	for (POSElement document : documents) {
    		List<POSElement> sentenceListx = ((POSDocument)document).getSentenceList();
    		for (POSElement sentence : sentenceListx) {
    			sentenceList.add((POSSentence)sentence);
    		}
    	}
    	return sentenceList;
    }

}
