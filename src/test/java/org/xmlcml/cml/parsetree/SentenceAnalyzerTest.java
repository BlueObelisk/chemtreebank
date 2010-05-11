/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.xmlcml.cml.parsetree;

import nu.xom.Element;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xmlcml.cml.parsetree.helpers.CounterMap;
import org.xmlcml.cml.parsetree.helpers.TreeBankUtil;
import org.xmlcml.cml.testutil.JumboTestUtils;

import com.google.common.collect.ListMultimap;


/**
 *
 * @author pm286
 */
public class SentenceAnalyzerTest {


	DocumentFixture fixture;
	
	@Before
	public void setUp() {
		if (fixture == null) {
			fixture = new DocumentFixture();
		}
	}
    
    @Test
    @Ignore
    public void testSentence() throws Exception {
        Assert.assertEquals("Nodes", 4, fixture.sentence.getChildElements().size());
		Element ref = JumboTestUtils.parseValidFile(fixture.refdir+"/file1.xml");
		JumboTestUtils.assertEqualsIncludingFloat("document", ref, fixture.document, true, 0.000001);
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
        CounterMap<POSElement> counterMap = sentenceAnalyzer.getNodeIndex(fixture.sentence);
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
//        printSentenceListMultimap(filenames[0]);
//    }
//
//    @Test
//    @Ignore
//    public void testGetIndexOfNodesByHashCodeFromSelfAndDescendantNodeNames1() throws Exception {
//        ListMultimap<Integer, POSElement> listMap = new ListMultimap<Integer, POSElement>();
//        for (Sentence sentence : sentences) {
//            listMap.add(sentence.getIndexOfNodesByHashCodeFromSelfAndDescendantNodeNames());
//        }
//        listMap.printWithCounts();
//    }
//
    @Test
    public void testMapLeafContentToNodeName() {
    	SentenceAnalyzer sentenceAnalyzer = new SentenceAnalyzer();
        ListMultimap<String, String> listMap = sentenceAnalyzer.mapLeafContentToNodeName(fixture.sentence);
        TreeBankUtil.printWithCounts(listMap);
    }

    @Test
    public void testMapLeafContentToNodeNameAllSentences() {
        SentenceAnalyzer sentenceAnalyzer = new SentenceAnalyzer();
        ListMultimap<String, String> listMap = sentenceAnalyzer.mapLeafContentToNodeName(fixture.sentenceList);
        TreeBankUtil.printWithCounts(listMap);
    }
    
    @Test
    public void testMapNonLeafContentToNodeNameSingleSentence() {
    	SentenceAnalyzer sentenceAnalyzer = new SentenceAnalyzer();
        ListMultimap<String, Element> listMap = sentenceAnalyzer.mapNonLeafContentToNodeName(fixture.sentence);
        Assert.assertNotNull(listMap);
        TreeBankUtil.printWithCounts(listMap);
    }

    @Test
    public void testMapNonLeafContentToNodeNameAllSentences() {
    	SentenceAnalyzer sentenceAnalyzer = new SentenceAnalyzer();
        ListMultimap<String, Element> listMap = sentenceAnalyzer.mapNonLeafContentToNodeName(fixture.sentenceList);
        Assert.assertNotNull(listMap);
        TreeBankUtil.printWithCounts(listMap);
    }

    
//    private ListMultimap<Integer, POSElement> getListMultimapForSentence(String filename) throws IOException, ParsingException {
//
//    	POSDocument document = POSDocument.parseDocument(filename);
//        List<POSSentence> sentences = document.getSentenceList();
//        for (POSSentence sentence : sentences) {
//        	ListMultimap<Integer, POSElement> listMap = document.getIndexOfNodesByHashCodeFromSelfAndDescendantNodeNames();
//        return listMap;
//    }
//
//
//    private ListMultimap<Integer, POSElement>  printSentenceListMultimap(String filename) throws ParsingException, IOException {
//        ListMultimap<Integer, POSElement> listMap = getListMultimapForSentence(filename);
//        listMap.print();
//        return listMap;
//    }
}
