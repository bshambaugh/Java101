package functionjava10_c;

import java.util.List;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Test {
	
	public static void main(String[] args) {
		
       String inputFile = "input.txt";
  
       /*
      String currentDir = System.getProperty("user.dir");
       if (0 < args.length) {
          inputFile = currentDir+'/'+args[0];
       } else {
    	   System.err.println("Invalid arguments count:" + args.length);
    	}
   */

//   if(inputFile != null) {
		List<String> allURLs = HighlevelProtocols.linkList(inputFile);
		List<String> validURLs = new ArrayList<String>();
		
		// create another list here that holds the valid links
		
      for(String o : allURLs){
    	  System.out.println(o);  
    	  System.out.println(IsValidLink.validLink(o));
    	  if(IsValidLink.validLink(o)) {
    		  validURLs.add(o);
    		  load(o);
    	  }
       }
 //  }
      

}
	
public static void load(String myURL) {
    try {
		LinkProcessor.loadPage(myURL);
	 } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
 		
}