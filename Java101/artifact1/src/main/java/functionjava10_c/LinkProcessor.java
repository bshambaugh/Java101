package functionjava10_c;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;

public class LinkProcessor {
	
	   /*
  * Apache commons code provides many overloaded methods to generate md5 hash. It contains
  * md5 method which can accept String, byte[] or InputStream and can return hash as 16 element byte
  * array or 32 character hex String.
  */
 public static String md5ApacheCommonsCodec(String content){
     return DigestUtils.md5Hex(content);
    
 }
	
	
	// http://tutorials.jenkov.com/java-networking/url-urlconnection.html
	// https://stackoverflow.com/questions/5867975/reading-websites-contents-into-string
	// https://stackoverflow.com/questions/309424/read-convert-an-inputstream-to-a-string
 
    //  code from https://crunchify.com/how-to-retry-operation-n-number-of-times-in-java/
	public static void loadPage(String myURL) throws IOException {
		
		// create a list to hold the contents of the result...
		List<String> listA = new ArrayList<String>();
		
		//  source: https://crunchify.com/how-to-retry-operation-n-number-of-times-in-java/
		// Execute this Program as it is once to get correct response.
		 
		// Change URL to
		// https://crunchify.com/wp-content/uploads/code/json.sample2.txt to
		// see retry logic in action..
	//	String myURL = "https://cdn.crunchify.com/wp-content/uploads/code/json.sample.txt";
		CrunchifyRetryOnExceptionStrategy retry = new CrunchifyRetryOnExceptionStrategy();
		while (retry.shouldRetry()) {
			try {
				System.out.println("Requested URL:" + myURL);
				StringBuilder sb = new StringBuilder();
				URLConnection urlConn = null;
				InputStreamReader in = null;
				URL url = new URL(myURL);
				urlConn = url.openConnection();
				if (urlConn != null)
					urlConn.setReadTimeout(60 * 1000);
				if (urlConn != null && urlConn.getInputStream() != null) {
					in = new InputStreamReader(urlConn.getInputStream(),
							Charset.defaultCharset());
					// Limit the size to 10000 bytes
					BufferedReader bufferedReader = new BufferedReader(in,10000);
					if (bufferedReader != null) {
						int cp;
						while ((cp = bufferedReader.read()) != -1) {
							sb.append((char) cp);
						}
						bufferedReader.close();
					}
				}
				// figure out how to return this string to the method...
				System.out.println(sb.toString());
				System.out.println("MD5 message created by Apache commons codec : " + md5ApacheCommonsCodec(sb.toString()));
				in.close();
				break;

			} catch (Exception e) {
				try {
					System.out.println("in catch.....");
					retry.errorOccured();
				} catch (RuntimeException e1) {
					throw new RuntimeException("Exception while calling URL:"
							+ myURL, e);
				} catch (Exception e1) {
					throw new RuntimeException(e1);
				}
			}
		}
	}

}
