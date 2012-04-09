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
		//Restaurant restraurant = ObjectsContainer.restaurants.get(0);
	//can now be asked with just:
		Log.w("AndroidCourse",ObjectsContainer.restaurants.get(0).name);
		//to get menu of restaurant nro 0 monday menu
		int counter=0;
		while (ObjectsContainer.restaurants.get(0).weeksmenu.monday.daysmenu.size()>counter){
		Log.w("AndroidCourse",ObjectsContainer.restaurants.get(0).weeksmenu.monday.daysmenu.get(counter).meal);
	
		counter++;
		}
	}
}
