/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.xmlcml.cml.parsetree;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import nu.xom.Element;

import org.junit.Assert;
import org.junit.Test;
import org.xmlcml.cml.parsetree.helpers.CounterMap;
import org.xmlcml.cml.parsetree.helpers.ListMap;


/**
 *
 * @author pm286
 */
public class SentenceAnalyzerTest {

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

    @Test
    public void testSentence() throws Exception {
    	POSSentence sentence = createSentence();
        Assert.assertNotNull(sentence);
        Assert.assertEquals("Nodes", 4, sentence.getChildElements().size());
    }

	private POSSentence createSentence() {
		POSDocument document = documents[0];
        POSSentence sentence = (POSSentence) document.getSentenceList().get(0);
		return sentence;
	}
    
//
//    @Test
//    public void testIndexHash() throws Exception {
//        Element sentenceElement = new Builder().build(new FileInputStream(filenames[0])).getRootElement();
//        Sentence sentence = new Sentence(sentenceElement);
//        Assert.assertEquals("hash", -838885842, sentence.hashCodeFromSelfAndDescendantNodeNames());
//    }
//    /*
    @Test
    public void testGetNodeIndex() throws Exception {
    	SentenceAnalyzer sentenceAnalyzer = new SentenceAnalyzer();
    	POSSentence posSentence = createSentence();
        CounterMap<POSElement> counterMap = sentenceAnalyzer.getNodeIndex(posSentence);
        System.out.println("CC "+counterMap.size());
        for (POSElement pn : counterMap.keySet()) {
            Integer count = counterMap.getCount(pn);
            System.out.println("I "+count+" "+pn);
        }
    }
//     */
//    /*
//    @Test
//    public void testGetNodeHashIndex() throws Exception {
//        String filename = "src/test/resources/parsetrees/syntax00.xml";
//        Element sentenceElement = new Builder().build(new FileInputStream(filename)).getRootElement();
//        Sentence sentence = new Sentence(sentenceElement);
//        CounterMap<Integer> counterMap = sentence.getNodeHashIndex();
//        System.out.println("CC "+counterMap.size());
//        for (Integer ii : counterMap.keySet()) {
//            Integer count = counterMap.getCount(ii);
//            System.out.println("I "+count+"/"+ii);
//        }
//    }
//     */
//    @Test
//    @Ignore
//    public void testGetIndexOfNodesByHashCodeFromSelfAndDescendantNodeNames() throws Exception {
//        printSentenceListMap(filenames[0]);
//    }
//
//    @Test
//    @Ignore
//    public void testGetIndexOfNodesByHashCodeFromSelfAndDescendantNodeNames1() throws Exception {
//        ListMap<Integer, POSElement> listMap = new ListMap<Integer, POSElement>();
//        for (Sentence sentence : sentences) {
//            listMap.add(sentence.getIndexOfNodesByHashCodeFromSelfAndDescendantNodeNames());
//        }
//        listMap.printWithCounts();
//    }
//
    @Test
    public void testMapLeafContentToNodeName() {
    	SentenceAnalyzer sentenceAnalyzer = new SentenceAnalyzer();
    	POSSentence posSentence = createSentence();
        ListMap<String, String> listMap = sentenceAnalyzer.mapLeafContentToNodeName(posSentence);
        listMap.printWithCounts();
    }

    @Test
    public void testMapLeafContentToNodeNameAllSentences() {
        SentenceAnalyzer sentenceAnalyzer = new SentenceAnalyzer();
        List<POSElement> sentenceList = getSentenceList();
        ListMap<String, String> listMap = sentenceAnalyzer.mapLeafContentToNodeName(sentenceList);
        listMap.printWithCounts();
    }
    
    @Test
    public void testMapLeafContentToNodeNameAllDocuments() {
        SentenceAnalyzer sentenceAnalyzer = new SentenceAnalyzer();
        List<POSElement> documentList = getDocumentList();
        ListMap<String, String> listMap = sentenceAnalyzer.mapLeafContentToNodeName(documentList);
        Assert.assertNotNull(listMap);
        listMap.printWithCounts();
    }
    
    @Test
    public void testMapNonLeafContentToNodeNameSingleSentence() {
    	SentenceAnalyzer sentenceAnalyzer = new SentenceAnalyzer();
    	POSSentence posSentence = createSentence();
        ListMap<String, Element> listMap = sentenceAnalyzer.mapNonLeafContentToNodeName(posSentence);
        Assert.assertNotNull(listMap);
        listMap.printWithCounts();
    }

    @Test
    public void testMapNonLeafContentToNodeNameAllSentences() {
    	SentenceAnalyzer sentenceAnalyzer = new SentenceAnalyzer();
        List<POSElement> sentenceList = getSentenceList();
        ListMap<String, Element> listMap = sentenceAnalyzer.mapNonLeafContentToNodeName(sentenceList);
        Assert.assertNotNull(listMap);
        listMap.printWithCounts();
    }

    @Test
    public void testMapNonLeafContentToNodeNameAllDocuments() {
    	SentenceAnalyzer sentenceAnalyzer = new SentenceAnalyzer();
        List<POSElement> documentList = getDocumentList();
        ListMap<String, Element> listMap = sentenceAnalyzer.mapNonLeafContentToNodeName(documentList);
        Assert.assertNotNull(listMap);
        listMap.printWithCounts();
    }

    
//    private ListMap<Integer, POSElement> getListMapForSentence(String filename) throws IOException, ParsingException {
//
//    	POSDocument document = POSDocument.parseDocument(filename);
//        List<POSSentence> sentences = document.getSentenceList();
//        for (POSSentence sentence : sentences) {
//        	ListMap<Integer, POSElement> listMap = document.getIndexOfNodesByHashCodeFromSelfAndDescendantNodeNames();
//        return listMap;
//    }
//
//
//    private ListMap<Integer, POSElement>  printSentenceListMap(String filename) throws ParsingException, IOException {
//        ListMap<Integer, POSElement> listMap = getListMapForSentence(filename);
//        listMap.print();
//        return listMap;
//    }
}
