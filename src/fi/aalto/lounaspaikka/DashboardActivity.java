package fi.aalto.lounaspaikka;

import fi.aalto.lounaspaikka.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;


public class DashboardActivity extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard);
		 
//		Bundle data = getIntent().getExtras();
//		MyParcelable student = data.getParcelable("parse");
//		
		
		GridView gridview = (GridView) findViewById(R.id.gridview);
	    gridview.setAdapter(new ImageAdapter(this));

	    gridview.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	           // Toast.makeText(SecondPageActivity.this, "" + position, Toast.LENGTH_SHORT).show();
	        	Intent intent=null;
	        	switch(position){

	        	  case 0:
	        	       intent = new Intent(DashboardActivity.this, RestaurantsActivity.class);
	        	       DashboardActivity.this.startActivity(intent);
	        	       break;
	        	  case 1: 
	        		   intent = new Intent(DashboardActivity.this, FavoritesActivity.class);
	        	       DashboardActivity.this.startActivity(intent);
	        	       break;
	        	  case 2: /** AlerDialog when click on Exit */
	        		   intent = new Intent(DashboardActivity.this, MenuActivity.class);
	        	       DashboardActivity.this.startActivity(intent);
	        	       break;
	        	  case 3: /** AlerDialog when click on Exit */
	        		  intent = new Intent(DashboardActivity.this, MapActivity.class);
	        	       DashboardActivity.this.startActivity(intent);
	        	       break;
	        	  case 4: /** AlerDialog when click on Exit */
	        		  intent = new Intent(DashboardActivity.this, ReviewsActivity.class);
	        	       DashboardActivity.this.startActivity(intent);
	        	       break;
	        	  case 5: /** AlerDialog when click on Exit */
	        		  intent = new Intent(DashboardActivity.this, CampusActivity.class);
	        	       DashboardActivity.this.startActivity(intent);
	        	       break;
	        	  }
	        }
	    });
		
	}
}
