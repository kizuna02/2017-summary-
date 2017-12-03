/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package score;

import index.Index;

/**
 *
 * @author lijiaw13
 */
public class TFIDFScoringFun implements TermScoringFun{
   private Index index;
   
    @Override
    public void init(Index s) {//get N and docFreq
   index = s;
   
    
    }

    @Override
    public double scoreToken(String term, int freq) {//single token
      double result = 0;
    //  double N = index.getDocSource().getNumDocs();
      //result = Math.log10(1.0 + (double)termfreq)*Math.log10(N / (double)index.getDocumentFreq(term));
        result = Math.log10(1.0 + (double)freq)*Math.log10((double)this.index.getDocSource().getNumDocs()/(double)this.index.getDocumentFreq(term));
        
       return result;
    }
    
}
