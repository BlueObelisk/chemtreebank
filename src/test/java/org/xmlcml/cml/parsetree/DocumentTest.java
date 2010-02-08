/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.xmlcml.cml.parsetree;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nu.xom.ParsingException;
import nu.xom.ValidityException;

import org.junit.Before;
import org.junit.Test;
import org.xmlcml.cml.base.CMLUtil;


/**
 *
 * @author pm286
 */
public class DocumentTest {

	String[] filenames = null;
    POSDocument[] documents = null;
	private void makeFiles() {
		if (filenames == null) {
			filenames = new String[] {
	        "src/test/resources/parsetrees/target/file1.xml",
	        "src/test/resources/parsetrees/target/file2.xml",
	        "src/test/resources/parsetrees/target/file3.xml",
	        "src/test/resources/parsetrees/target/file4.xml",
	        "src/test/resources/parsetrees/target/file5.xml",
	        "src/test/resources/parsetrees/target/file6.xml",
	        "src/test/resources/parsetrees/target/file7.xml",
	        "src/test/resources/parsetrees/target/file8.xml",
	        "src/test/resources/parsetrees/target/file9.xml",
			};

		    documents = new POSDocument[filenames.length];
	        for (int i = 0; i < filenames.length; i++) {
	            try {
					documents[i] = POSDocument.parseDocument(new FileInputStream(filenames[i]));
				} catch (FileNotFoundException e) {
					throw new RuntimeException("Cannot read "+filenames[i]);
				}
	        }
		}
	}

	private void parseAndWrite(String filename) throws ParsingException, ValidityException,
			IOException, FileNotFoundException {
		POSDocument document = POSDocument.parseDocument(filename);
		File infile = new File(filename);
		File outputDir = new File(new File("target"), infile.getParent());
		outputDir.mkdirs();
		File outputFile = new File(outputDir, infile.getName());
		FileOutputStream fos = new FileOutputStream(outputFile);
		CMLUtil.debug(document, fos, 4);
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


	@Before
	public void setUp() {
		makeFiles();
	}

    @Test
    public void testSentence0() throws Exception {
        POSDocument document = POSDocument.parseDocument(filenames[0]);
        CMLUtil.debug(document, "DOC "+filenames[0]);
    }

    @Test
    public void testSentence1() throws Exception {
        POSDocument document = POSDocument.parseDocument(filenames[1]);
        CMLUtil.debug(document, "DOC "+filenames[1]);
    }

    @Test
    public void testSentenceAllNew() throws Exception {
    	for (int i = 0; i < filenames.length; i++) {
    		System.out.println(filenames[i]);
            parseAndWrite(filenames[i]);
	    }
    }
    

}
