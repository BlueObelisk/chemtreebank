/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.xmlcml.cml.converters.dictionary;

import junit.framework.TestCase;

import org.junit.Ignore;
import org.junit.Test;
import org.xmlcml.cml.element.CMLDictionary;

/**
 *
 * @author Weerapong
 */
public class DictionaryCollectionTest extends TestCase {
    
    public DictionaryCollectionTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of loadDictionary method, of class DictionaryCollection.
     */
    @Test
    public void testLoadDictionary() {
        System.out.println("loadDictionary");
        DictionaryCollection instance = new DictionaryCollection();
        System.out.println("Dict : " + DictionaryCollection.CompChemLocation);
        instance.loadDictionary(DictionaryCollection.CompChemLocation);
        System.out.println("Dict : " + DictionaryCollection.MoleculeLocation);
        instance.loadDictionary(DictionaryCollection.MoleculeLocation);
        System.out.println("Dict : " + DictionaryCollection.PropertyLocation);
        instance.loadDictionary(DictionaryCollection.PropertyLocation);
        //instance.loadDictionary(DictionaryCollection.UnitsLocation);
    }

    /**
     * Test of getDictionary method, of class DictionaryCollection.
     */
    @Test
    @Ignore
    public void testGetDictionary() {
        System.out.println("getDictionary");
        String namespace = "";
        DictionaryCollection instance = new DictionaryCollection();
        CMLDictionary expResult = null;
        CMLDictionary result = instance.getDictionary(namespace);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

}
