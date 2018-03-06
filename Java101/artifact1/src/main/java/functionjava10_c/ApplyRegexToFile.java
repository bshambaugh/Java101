package functionjava10_c;

public class ApplyRegexToFile {
	
	private static ApplyRegexFunction LoadedFile;
	
	public static ApplyRegexFunction getLoadedFile(String input) {

	   LoadedFile = new ApplyRegexFunction(input);
		return LoadedFile;
	}
	
}

// Based on something like:
// https://github.com/researchstudio-sat/webofneeds/blob/master/webofneeds/won-utils/won-utils-conversation/src/main/java/won/protocol/highlevel/HighlevelFunctionFactory.java
