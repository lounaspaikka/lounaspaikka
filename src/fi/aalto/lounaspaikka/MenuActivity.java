package fi.aalto.lounaspaikka;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import fi.aalto.lounaspaikka.objectfiles.ObjectsContainer;
import fi.aalto.lounaspaikka.objectfiles.Restaurant;

public class MenuActivity extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		//testing if the data is transfered through activities
		Restaurant restraurant = ObjectsContainer.restaurants.get(0);
		Log.w("AndroidCourse",restraurant.name);
	}
}
