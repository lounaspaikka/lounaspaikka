package fi.aalto.lounaspaikka;



import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import fi.aalto.lounaspaikka.JsonRetrievalAndTransform.RetrivedDataAndGenerateObject;
import fi.aalto.lounaspaikka.objectfiles.ObjectsContainer;
import fi.aalto.lounaspaikka.objectfiles.Restaurant;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;



public class LounaspaikkaActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		RetrivedDataAndGenerateObject data = new  RetrivedDataAndGenerateObject();
		data.Storetomem();
		for(int i=0; i<ObjectsContainer.restaurants.size(); i++) {
			Restaurant res = ObjectsContainer.restaurants.get(i);
			System.out.println("Reastaurant: " + res.name);
		}
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

		   public void run() {
			   Intent intent = new Intent(LounaspaikkaActivity.this, DashboardActivity.class);
			  // intent.putExtra("parse", new MyParcelable("1","Mike","6"));
			   LounaspaikkaActivity.this.startActivity(intent);
		   }

		}, 2000);
//		 Intent intent = new Intent(this, DashboardActivity.class);
//		 this.startActivity(intent);

	}
}


