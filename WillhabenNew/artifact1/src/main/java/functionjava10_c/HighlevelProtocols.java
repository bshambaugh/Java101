package functionjava10_c;

import java.util.List;

public class HighlevelProtocols {
	
	private static String urlRegularExpression = "http[s]*://[A-Za-z0-9+&@#/%?=~\\-_|!:,.;]+";
		
	public static List<String> linkList (String inputFile) {
		List<String> linkList = ApplyRegexToFile.getLoadedFile(inputFile).apply(urlRegularExpression);
		return linkList;
	}
		
}

// Modified:::
// https://github.com/researchstudio-sat/webofneeds/blob/master/webofneeds/won-utils/won-utils-conversation/src/main/java/won/protocol/highlevel/HighlevelProtocols.java






