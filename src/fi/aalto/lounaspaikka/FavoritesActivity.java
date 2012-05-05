package fi.aalto.lounaspaikka;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import fi.aalto.lounaspaikka.objectfiles.ObjectsContainer;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class FavoritesActivity extends Activity{
	public final static String ITEM_TITLE = "List with favorites";
	public final static String ITEM_CAPTION = "Favorite Restaurants";
	
	ArrayList<ArrayList<String>> meals = new ArrayList<ArrayList<String>>();
	ArrayList<String> restaurantNames = new ArrayList<String>();
	ArrayList<String> fav = new ArrayList<String>();
	
	// Adapter for ListView Contents
	private SeparatedListAdapter adapter;

	// ListView Contents
	private ListView journalListView;

	public Map<String, ?> createItem(String title, String caption)
		{
			Map<String, String> item = new HashMap<String, String>();
			item.put(ITEM_TITLE, title);
			item.put(ITEM_CAPTION, caption);
			return item;
		}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		    setContentView(R.layout.menu);

		    Calendar today = Calendar.getInstance();
			int day = today.get(Calendar.DAY_OF_WEEK);

			int openday=day-2;
			if (openday==-1 ) {
				openday =6;
				}
			LoadFav();
			
			loadRestaurantData(day);
			
			// Create the ListView Adapter
			adapter = new SeparatedListAdapter(this);
			ArrayAdapter<String> listadapter ; 

			// Add Sections
			for (int i = 0; i < restaurantNames.size(); i++)
				{
					listadapter = new ArrayAdapter<String>(this, R.layout.list_item, meals.get(i));
					adapter.addSection(restaurantNames.get(i), listadapter);
				}

			// Get a reference to the ListView holder
			journalListView = (ListView) this.findViewById(R.id.list_journal);

			// Set the adapter on the ListView holder
			journalListView.setAdapter(adapter);

	}
	 public void onListItemClick(ListView parent, View v, int position,long id) {        	
		 LoadFav();
	 }
	 public void loadRestaurantData(int day){
		 ArrayList<String> mealTmp;
		 for (int i=0;i<ObjectsContainer.restaurants.size();i++){
				for(int k=0; k<fav.size();k++){
				   if (ObjectsContainer.restaurants.get(i).name.equals(fav.get(k))){
	        		if (day==1)
					{
	        			restaurantNames.add(ObjectsContainer.restaurants.get(i).name);
	        			mealTmp = new ArrayList<String>();
	        			int mealsSize = ObjectsContainer.restaurants.get(i).weeksmenu.sunday.daysmenu.size();
	        			if(mealsSize == 0){
	        				mealTmp.add("");
     					meals.add(mealTmp);
	        			}
	        			else {
	        				for (int j=0;j<mealsSize;j++){
	        					mealTmp.add(ObjectsContainer.restaurants.get(i).weeksmenu.sunday.daysmenu.get(j).meal);
	        				}
	        				meals.add(mealTmp);
	        			 }
	        			
					}
					else if (day==2) 
					{
						mealTmp = new ArrayList<String>();
						restaurantNames.add(ObjectsContainer.restaurants.get(i).name);
						int mealsSize = ObjectsContainer.restaurants.get(i).weeksmenu.monday.daysmenu.size();
						if(mealsSize == 0){
							mealTmp.add("");
     					meals.add(mealTmp);
						}else{
							for (int j=0;j<mealsSize;j++){
								mealTmp.add(ObjectsContainer.restaurants.get(i).weeksmenu.monday.daysmenu.get(j).meal);
		        			}
							meals.add(mealTmp);
						}
					}
					else if (day==3) 
					{
						mealTmp = new ArrayList<String>();
						restaurantNames.add(ObjectsContainer.restaurants.get(i).name);
						int mealsSize = ObjectsContainer.restaurants.get(i).weeksmenu.tuesday.daysmenu.size();
						if( mealsSize == 0){
							mealTmp.add("");
     					meals.add(mealTmp);
						}else{
							for (int j=0;j<mealsSize;j++){
								mealTmp.add(ObjectsContainer.restaurants.get(i).weeksmenu.tuesday.daysmenu.get(j).meal);
							}
							meals.add(mealTmp);
						}
					}
					else if (day==4) 
					{
						mealTmp = new ArrayList<String>();
						restaurantNames.add(ObjectsContainer.restaurants.get(i).name);
						int mealsSize = ObjectsContainer.restaurants.get(i).weeksmenu.wednesday.daysmenu.size();
						if( mealsSize== 0) {
							mealTmp.add("");
     					meals.add(mealTmp);
						}else {
							for (int j=0;j<mealsSize;j++){
								mealTmp.add(ObjectsContainer.restaurants.get(i).weeksmenu.wednesday.daysmenu.get(j).meal);
							}
							meals.add(mealTmp);
						}
					}
					else if (day==5) 
					{
						mealTmp = new ArrayList<String>();
						restaurantNames.add(ObjectsContainer.restaurants.get(i).name);
						int mealsSize = ObjectsContainer.restaurants.get(i).weeksmenu.thursday.daysmenu.size();
						if(mealsSize == 0) {
							mealTmp.add("");
     					meals.add(mealTmp);
						}else {
							for (int j=0;j<mealsSize;j++){
								mealTmp.add(ObjectsContainer.restaurants.get(i).weeksmenu.thursday.daysmenu.get(j).meal);
							}
							meals.add(mealTmp);
						}
					}
					else if (day==6) 
					{
						mealTmp = new ArrayList<String>();
						restaurantNames.add(ObjectsContainer.restaurants.get(i).name);
						int mealsSize = ObjectsContainer.restaurants.get(i).weeksmenu.friday.daysmenu.size();
						if( mealsSize == 0){
							mealTmp.add("");
     					meals.add(mealTmp);
						}
					    else {
							for (int j=0;j<mealsSize;j++){
								mealTmp.add(ObjectsContainer.restaurants.get(i).weeksmenu.friday.daysmenu.get(j).meal);
		        											
							}
							meals.add(mealTmp);	
						}
						
					}
					else if (day==7) 
					{
						mealTmp = new ArrayList<String>();
						restaurantNames.add(ObjectsContainer.restaurants.get(i).name);
						int mealsSize = ObjectsContainer.restaurants.get(i).weeksmenu.saturday.daysmenu.size();
						if(mealsSize ==0){
							mealTmp.add("");
     					meals.add(mealTmp);
						}else {
							for (int j=0;j<mealsSize;j++){
								mealTmp.add(ObjectsContainer.restaurants.get(i).weeksmenu.saturday.daysmenu.get(j).meal);
							}
							meals.add(mealTmp);
						}
					}
	        	}
	        }
		}
	 }
	 public void LoadFav(){
			FileInputStream fIn = null;

	        InputStreamReader isr = null;

	        try{
	        fIn = openFileInput("fav.txt");
	        isr = new InputStreamReader(fIn);
	        BufferedReader reader = new BufferedReader(isr);
	        String line = null;
	       
	        while ((line = reader.readLine()) != null) {
	        	fav.add(line);       	
	        }
	        isr.close();
	        //Toast.makeText(getBaseContext(), "Favourite Loaded",Toast.LENGTH_SHORT).show();
	        fIn.close();
	        
	        
	        }catch(IOException e){

	        e.printStackTrace(System.err);

	        }
		
		}
}
