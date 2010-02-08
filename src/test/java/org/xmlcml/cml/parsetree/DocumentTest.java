/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.xmlcml.cml.parsetree;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import junit.framework.Assert;
import nu.xom.ParsingException;
import nu.xom.ValidityException;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.xmlcml.cml.base.CMLUtil;


/**
 *
 * @author pm286
 */
public class DocumentTest {
	private static Logger LOG = Logger.getLogger(DocumentTest.class);

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

	DocumentFixture fixture;
	@Before
	public void setUp() {
		if (fixture == null) {
			fixture = new DocumentFixture();
		}
	}
    

    @Test
    public void testSentence0() throws Exception {
        POSDocument document = POSDocument.parseDocument(
        		this.getClass().getClassLoader().getResourceAsStream(fixture.filenames[0]));
        Assert.assertNotNull(document);
        CMLUtil.debug(document, "DOC "+fixture.filenames[0]);
    }

    @Test
    public void testSentence1() throws Exception {
        POSDocument document = POSDocument.parseDocument(
        		this.getClass().getClassLoader().getResourceAsStream(fixture.filenames[1]));
        Assert.assertNotNull(document);
        CMLUtil.debug(document, "DOC "+fixture.filenames[1]);
    }

    @Test
    public void testSentenceAllNew() throws Exception {
    	for (int i = 0; i < fixture.filenames.length; i++) {
       		File outfile = new File("target", fixture.filenames[i]);
    		System.out.println(outfile.getAbsolutePath());
       		new File(outfile.getParent()).mkdirs();
       	    try {
    			parseAndWrite(outfile.getAbsolutePath());
    		} catch(Exception e) {
    			LOG.error("Cannot write file: "+outfile);
    		}
	    }
    }
    

}
