package fi.aalto.lounaspaikka.translate;

import java.util.ArrayList;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;


public class GoogleTranslater {

	public static String Translate(String input) {
		String baseUrl = "http://translate.patana.info/translate.php?target=en&source=fi&string=";
		String url = baseUrl + java.net.URLEncoder.encode(input);
		
		String output = input;
		
        try {
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet(url);
                // Get the response
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                output = client.execute(request, responseHandler);
        }
        catch (Exception e) {
            System.out.println("Translation error occured."+e.toString());
        }
        return output;
	}

	public static void TranslateArray(ArrayList<String> mealTmp) {
		// TODO Auto-generated method stub
		String input = "";
		for (String s : mealTmp)
		{
		    input += s + "|";
		}
		String output =Translate(input);
		String[] outputParts= output.split("[|]");
		
		mealTmp.clear();
		for (int i=0;i<outputParts.length;i++){
			mealTmp.add(outputParts[i]);
		}
	}

}
