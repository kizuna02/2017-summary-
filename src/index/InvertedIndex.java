/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package index;

import io.DocSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;
import score.TermScoringFun;
import tokenizer.Tokenizer;

/**
 *
 * @author lijiaw13
 */
public class InvertedIndex extends Index {

    private HashMap<String, HashMap<Integer, Integer>> _index; //tokens , doc ID -> token frequencies of this document
    private HashMap<String, Integer> _docFreq;   //token, num of docs (needed by the TF-IDF calculation)

    /**
     *
     * @param doc_source
     * @param tokenizer
     * @param scoring
     */
    public InvertedIndex(DocSource doc_source, Tokenizer tokenizer, TermScoringFun scoring) {
        super(doc_source, tokenizer, scoring);
        this._index = new HashMap<>();
        this._docFreq = new HashMap<>();
    }

    @Override
    public void buildIndex() {// Index all files in DocSource

        TreeSet<String> freq = new TreeSet<>();
        for (int i = 0; i < this._docSource.getNumDocs(); i++) {
            String temp = this._docSource.getDoc(i);
            ArrayList<String> tokens = this._tokenizer.tokenize(temp);

            for (String token : tokens) {
                HashMap<Integer, Integer> inner = new HashMap<>();
                if (!this._index.containsKey(token)) {
                    inner.put(i, 1);
                    this._index.put(token, inner);

                } else {
                    if(!this._index.get(token).containsKey(i)){
                         this._index.get(token).put(i, 1);
                    }else{
                    int a = this._index.get(token).get(i) + 1;
                    this._index.get(token).put(i, a);
                    inner = this._index.get(token);
                    _index.put(token, inner);
                }
                }

                if (!this._docFreq.containsKey(token)) {
                    this._docFreq.put(token, 1);
                    freq.add(token);
                } else if (this._docFreq.containsKey(token) && (!freq.contains(token))) {
                    int b = this._docFreq.get(token) + 1;
                    this._docFreq.put(token, b);
                    freq.add(token);
                }

            }
            freq.clear();
            //   tokens.clear();
        }

    }

                
    @Override
    public int getDocumentFreq(String term) {
        if (this._docFreq.containsKey(term.toLowerCase())) {
            return this._docFreq.get(term.toLowerCase());
        } else {
            return 1;
        }
    }

    /**
     *
     * @param query
     * @return
     */
    @Override
    public ArrayList<DocScore> getSortedSearchResults(String query) {

        TreeSet<DocScore> results = new TreeSet<>();

        ArrayList<String> queryTerms = this._tokenizer.tokenize(query);

        double sumScore;
        for (int i = 0; i < this._docSource.getNumDocs(); i++) {
            sumScore = 0;

            for (String s : queryTerms) {
                if (this._index.keySet().contains(s) && (this._index.get(s).get(i) != null)) {
                    sumScore += this._scoring.scoreToken(s, this._index.get(s).get(i));//term, term's doc freq
                }
            }
            if (sumScore != 0) {
                SortedDocScore a = new SortedDocScore(sumScore, i, this._docSource.getDoc(i));
                results.add(a);
            }

        }

        return new ArrayList<>(results);
    }

    @Override
    public String toString() {

        return this._index.toString();
    }

}
