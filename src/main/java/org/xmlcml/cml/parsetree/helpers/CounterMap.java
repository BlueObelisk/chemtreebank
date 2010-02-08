/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.xmlcml.cml.parsetree.helpers;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author pm286
 */
public class CounterMap<T> {
    private Map<T, Integer> counterMap;
    public CounterMap() {
        counterMap = new HashMap<T, Integer>();
    }
    public void add(T t) {
        Integer ii = ensureAndGet(t);
        counterMap.put(t, new Integer(ii+1));
    }

    public void add(T t, Integer count) {
        Integer ii = ensureAndGet(t);
        counterMap.put(t, new Integer(ii+count));
    }

    public Set<T> keySet() {
        return counterMap.keySet();
    }

    public Integer getCount(T t) {
        return counterMap.get(t);
    }

    public int size() {
        return counterMap.size();
    }

    private Integer ensureAndGet(T t) {
        Integer ii = counterMap.get(t);
        if (ii == null) {
            ii = new Integer(0);
        }
        return ii;
    }

}
