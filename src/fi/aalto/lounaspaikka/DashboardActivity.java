package fi.aalto.lounaspaikka;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;


public class DashboardActivity extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard);
		 
		GridView gridview = (GridView) findViewById(R.id.gridview);
	    gridview.setAdapter(new ImageAdapter(this));

	    gridview.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
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
	        		  intent = new Intent(DashboardActivity.this, MyMapActivity.class);
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
	public class ImageAdapter extends BaseAdapter{
		private Context mContext;

	    public ImageAdapter(Context c) {
	        mContext = c;
	    }

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mThumbIds.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			View v;
			if(convertView==null){
				LayoutInflater li = getLayoutInflater();
				v = li.inflate(R.layout.icon, null);
				TextView tv = (TextView)v.findViewById(R.id.icon_text);
				tv.setText(iconsStrings[position]);
				
				ImageView iv = (ImageView)v.findViewById(R.id.icon_image);
				iv.setImageResource(mThumbIds[position]);

			}
			else
			{
				v = convertView;
			}
			return v;
	    }
		 private Integer[] mThumbIds = {
		            R.drawable.restaurant_icon, R.drawable.favorites_icon,
		            R.drawable.menu_icon, R.drawable.map_icon,
		            R.drawable.reviews_icon, R.drawable.aalto_icon,
		          
		    };
		 private String[] iconsStrings = {
		            "Restaurants", "Favorites",
		            "Todays Menu", "Map",
		            "Reviews", "Choose Campus",
		          
		    };

	}
}
