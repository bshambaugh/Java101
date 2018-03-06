package functionjava10_c;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApplyRegexFunction extends LoadFile<String,List<String>> {

	public ApplyRegexFunction(String linkfileFile) {
		super(linkfileFile);
	}

// so this apply function could also take a regular expression...
	// actually apply must take a string
	public List<String> apply(String t) {
		// TODO Auto-generated method stubi	
		// regexChecker(t, linkfile);
		List<String> astringList = regexChecker(t, linkfile);
	    return astringList;
	}
	
	/// rewrite this code so that it outputs a string...or an array string...
	public static List<String> regexChecker(String theRegex, String str2Check) {
		
		Pattern checkRegex = Pattern.compile(theRegex);
		Matcher regexMatcher = checkRegex.matcher(str2Check);
		
		List<String> listA = new ArrayList<String>();
		
		while(regexMatcher.find()) {
			if(regexMatcher.group().length() != 0) {

				listA.add(regexMatcher.group().trim());
			}
		}
		
	   return listA;	
		
	}

}

// Based on something like::
// https://github.com/researchstudio-sat/webofneeds/blob/master/webofneeds/won-core/src/main/java/won/protocol/util/DatasetSelectionBySparqlFunction.java

