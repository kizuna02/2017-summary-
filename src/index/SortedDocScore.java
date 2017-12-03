/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package index;

import java.util.TreeSet;

/**
 *
 * @author lijiaw13
 */
public class SortedDocScore extends index.DocScore implements Comparable{

    public SortedDocScore(double score, int doc_id, String content) {
        super(score, doc_id, content);
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof SortedDocScore) {
            SortedDocScore s = (SortedDocScore) o;
            if (this._score > s._score) {
                return -1;
            } else if (this._score < s._score) {
                return 1;
            } else {
                return this._content.compareTo(s._content);
            }

        } else {
            return 0;
        }

    }
   

}
