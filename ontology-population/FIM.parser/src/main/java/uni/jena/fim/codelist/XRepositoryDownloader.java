package uni.jena.fim.codelist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class XRepositoryDownloader {
	private static final String baseURL = "https://www.xrepository.de/api/version_codeliste/";
		
	public static final String 	JSON = "json", 
								EXCEL="excel", 
								MARKDOWN="markdown", 
								MAGICDRAW="magicdraw", 
								XML="genericode";
	
	
	public static Codelist getCodelist(String urnVersion) throws IOException {
		URL url = getURL(urnVersion, XML);
		
		Codelist cl = new Codelist(url);
		
		return cl;
	}	
	
	public static String getCodelist(String urnVersion, String format) throws IOException {
		URL url = getURL(urnVersion, format);
			
		StringBuilder result = new StringBuilder();
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(conn.getInputStream()))) {
			for (String line; (line = reader.readLine()) != null; ) {
				result.append(line + "\n");
			}
		}
		
		return result.toString();
	}	
	
	
	public static URL getURL(String urnVersion, String format) throws MalformedURLException {
		String url = baseURL + urnVersion + "/"+ format;
		
		return new URL(url);
	}
	
}
