/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokenizer;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IndexingTokenizer implements tokenizer.Tokenizer {


   @Override
	public ArrayList<String> tokenize(String s) {

		// Note: can see following page for special characters in Java Regex:
		//       https://docs.oracle.com/javase/6/docs/api/java/util/regex/Pattern.html
		
		ArrayList<String> ret = new ArrayList<String>();
		Pattern p = Pattern.compile("\\w[-\\w-]*");
		Matcher m = p.matcher(s);
		while (m.find()) {
			ret.add(m.group().toLowerCase());
			// Equivalently we could also say: ret.add(s.substring(m.start(), m.end()));
        }
		return ret;
	}
}
