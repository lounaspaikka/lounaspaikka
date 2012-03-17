package fi.aalto.lounaspaikka;



import java.util.ArrayList;

import fi.aalto.lounaspaikka.JsonRetrievalAndTransform.RetrivedDataAndGenerateObject;
import fi.aalto.lounaspaikka.objectfiles.Restaurant;
import android.app.Activity;
import android.os.Bundle;



public class LounaspaikkaActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		RetrivedDataAndGenerateObject data = new  RetrivedDataAndGenerateObject();
		ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
		restaurants=data.getrestaurantarray();

	}
}


