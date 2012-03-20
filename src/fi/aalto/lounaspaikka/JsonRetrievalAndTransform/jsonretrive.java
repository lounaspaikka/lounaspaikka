package fi.aalto.lounaspaikka.JsonRetrievalAndTransform;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;

public class jsonretrive {
	public String jsondata;

	public void fetchdata() 
	{
		/**
		 * This is main method to fetch data and storing it to classes
		 * 
		 */
		try {
			String JsonAsString = Jsonfromurl();
			JsonAsString.getBytes("UTF-8");
			jsondata=JsonAsString;
			System.out.println("breakpointer");
		} catch (UnsupportedEncodingException e) {
			System.exit(-1);
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(-1);
		}
	}

	private String Jsonfromurl() throws UnsupportedEncodingException, IOException 
	{
		/**
		 * pulls data from lounasaika.net's api. to string.
		 * 
		 * 
		 */
		URL lounasaika = new URL("http://www.lounasaika.net/api/json?key=development321");
		BufferedReader in = new BufferedReader(
				new InputStreamReader(lounasaika.openStream()));
		String inputLine;
		String wholejsonstring="";
		while ((inputLine = in.readLine()) != null)
		{
			wholejsonstring=wholejsonstring+ inputLine + System.getProperty("line.separator");
		}
		in.close();
		return wholejsonstring;
	}




}

