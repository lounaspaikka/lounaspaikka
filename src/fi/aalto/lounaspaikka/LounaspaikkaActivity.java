package fi.aalto.lounaspaikka;

import android.app.Activity;
import android.os.Bundle;



public class LounaspaikkaActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
 jsonretrive json = new jsonretrive(); 
	json.fetchdata();
	
	}

}
