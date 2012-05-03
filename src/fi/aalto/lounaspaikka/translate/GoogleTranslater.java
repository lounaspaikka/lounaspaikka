package fi.aalto.lounaspaikka.translate;

import com.google.api.GoogleAPI;
import com.google.api.GoogleAPIException;
import com.google.api.translate.Language;
import com.google.api.translate.Translate;

public class GoogleTranslater {

	public static String Translate(String input) {
		if (input.length() == 0){
			return "";
		}
		String output = input;
		
		GoogleAPI.setHttpReferrer("http://localhost");
        GoogleAPI.setKey("KEY_HERE");
		try {
			output = Translate.DEFAULT.execute(input, Language.FINNISH, Language.ENGLISH);
		} catch (GoogleAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return output;
	}
}
