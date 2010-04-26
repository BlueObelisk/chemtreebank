/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.xmlcml.cml.parsetree.helpers;


/**
 *
 * @author pm286
 */
 public class ListMap<K, T> {
//    private Map<K, List<T>> listMap;
//    public ListMap() {
//        listMap = new HashMap<K, List<T>>();
//    }
//    public void add(K k, T t) {
//        List<T> list = ensureAndGet(k);
//        list.add(t);
//    }
//
//    public void add(K k , List<T> listx) {
//        for (T t : listx) {
//            add(k, t);
//        }
//     }

//    public void add(ListMap<K, T> listMap) {
//        for (K k : listMap.keySet()) {
//            add(k, listMap.get(k));
//        }
//     }
//
//    public List<T> get(K k) {
//        return listMap.get(k);
//    }
//
//    public Set<K> keySet() {
//        return listMap.keySet();
//    }

//    public int getCount(K k) {
//        List<T> list = listMap.get(k);
//        return (list == null) ? 0 : list.size();
//    }
//
//    public int size() {
//        return listMap.size();
//    }

//    public void print() {
//        for (K hash : this.keySet()) {
//            List<T> nodeList = listMap.get(hash);
//            for (T t : nodeList) {
//                System.out.println(hash+": "+t.toString());
//            }
//        }
//    }
//
//    public void printWithCounts() {
//        K[] keys = (K[])listMap.keySet().toArray();
//        Arrays.sort(keys);
//        for (K key : keys) {
//            System.out.println(">>>>> "+key);
//            List<T> nodeList = listMap.get(key);
//            ListMap<String, T> listMapx = new ListMap<String, T>();
//            for (T t : nodeList) {
//                String ts = getString(t);
//                listMapx.add(ts, t);
//            }
//            listMapx.printFrequencies();
//        }
//    }
//
//    public void printFrequencies() {
//        for (K key : listMap.keySet()) {
//            System.out.println(""+key+": "+listMap.get(key).size());
//        }
//    }
//
//    private List<T> ensureAndGet(K k) {
//        List<T> list = listMap.get(k);
//        if (list == null) {
//            list = new ArrayList<T>();
//            listMap.put(k, list);
//        }
//        return list;
//    }
//
//    //FIXME HORRIBLE
//    // something needs subclassing for this
//    public static String getString(Object t) {
//        String s = t.getClass().getName();
//        if (t instanceof String) {
//            s = (String) t;
//        } else if (t instanceof Element) {
//            Element copy = new Element((Element)t);
//            Nodes texts = copy.query("//text()");
//            for (int i = 0; i < texts.size(); i++) {
//                texts.get(i).detach();
//            }
//            s = copy.toXML();
//        } else if (t instanceof Element) {
//            // skipped
//            s = ((Element)t).toXML();
//        }
//        return s;
//    }
}
