package fi.aalto.lounaspaikka;
import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


import fi.aalto.lounaspaikka.SeparatedListAdapter;
import fi.aalto.lounaspaikka.objectfiles.ObjectsContainer;
import fi.aalto.lounaspaikka.objectfiles.Restaurant;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import fi.aalto.lounaspaikka.translate.GoogleTranslater;

public class MenuActivity extends Activity{
	public final static String ITEM_TITLE = "title";
	public final static String ITEM_CAPTION = "caption";
	
	ArrayList<ArrayList<String>> meals = new ArrayList<ArrayList<String>>();
	ArrayList<String> restaurantNames = new ArrayList<String>();

	private SeparatedListAdapter adapter;

	private ListView journalListView;

	public Map<String, ?> createItem(String title, String caption)
		{
			Map<String, String> item = new HashMap<String, String>();
			item.put(ITEM_TITLE, title);
			item.put(ITEM_CAPTION, caption);
			return item;
		}

	@Override
	public void onCreate(Bundle icicle)
		{
			super.onCreate(icicle);

			setContentView(R.layout.menu);
			
			Calendar today = Calendar.getInstance();
			int day = today.get(Calendar.DAY_OF_WEEK)+3;

			int openday=day-2;
			if (openday==-1 ) {
				openday =6;
				}
			
			loadRestaurantData(day);
		
			adapter = new SeparatedListAdapter(this);
			ArrayAdapter<String> listadapter ; 

			for (int i = 0; i < restaurantNames.size(); i++)
				{
					listadapter = new ArrayAdapter<String>(this, R.layout.list_item, meals.get(i));
					adapter.addSection(restaurantNames.get(i), listadapter);
				}

			journalListView = (ListView) this.findViewById(R.id.list_journal);

			journalListView.setAdapter(adapter);

		}
		public void loadRestaurantData(int day){
			int openday=day-2;
			if (openday==-1 ) {
				openday =6;
				}
			
			for (int i=0;i<ObjectsContainer.restaurants.size();i++){
				String opens="closed";
				String closes="";
				try {
				 opens  = ObjectsContainer.restaurants.get(i).isopen.listOfDays.get(openday).opens + "-";
				 closes = ObjectsContainer.restaurants.get(i).isopen.listOfDays.get(openday).closes;
				} catch (Throwable t) {
				// maybe warning later on
				}
				
				if (ObjectsContainer.restaurants.get(i).campus.equals(CampusActivity.nowCampus)){
	        		if (day==1)
					{
	        			restaurantNames.add(ObjectsContainer.restaurants.get(i).name + " "+ opens+closes );
	        			ArrayList<String> mealTmp = new ArrayList<String>();
	        			int mealsSize = ObjectsContainer.restaurants.get(i).weeksmenu.sunday.daysmenu.size();
	        			if( mealsSize == 0){
	        				mealTmp.add("No menu for today. Restaurant is closed.");
        					meals.add(mealTmp);
	        			}
	        			else {
	        				for (int j=0;j<mealsSize;j++){
	        					mealTmp.add(ObjectsContainer.restaurants.get(i).weeksmenu.sunday.daysmenu.get(j).meal);
	        				}
	        				GoogleTranslater.TranslateArray(mealTmp);
	        				meals.add(mealTmp);
	        			}
	        			
					}
					else if (day==2) 
					{
						ArrayList<String> mealTmp = new ArrayList<String>();
						restaurantNames.add(ObjectsContainer.restaurants.get(i).name);
						int mealsSize = ObjectsContainer.restaurants.get(i).weeksmenu.monday.daysmenu.size();
						if(mealsSize == 0){
							mealTmp.add("No menu for today. Restaurant is closed.");
        					meals.add(mealTmp);
						}else{
							for (int j=0;j<mealsSize;j++){
								mealTmp.add(ObjectsContainer.restaurants.get(i).weeksmenu.monday.daysmenu.get(j).meal);
		        			}
							GoogleTranslater.TranslateArray(mealTmp);
							meals.add(mealTmp);
						}
					}
					else if (day==3) 
					{
						ArrayList<String> mealTmp = new ArrayList<String>();
						restaurantNames.add(ObjectsContainer.restaurants.get(i).name);
						int mealsSize = ObjectsContainer.restaurants.get(i).weeksmenu.tuesday.daysmenu.size();
						if( mealsSize == 0){
							mealTmp.add("No menu for today. Restaurant is closed.");
        					meals.add(mealTmp);
						}else{
							for (int j=0;j<mealsSize;j++){
								mealTmp.add(ObjectsContainer.restaurants.get(i).weeksmenu.tuesday.daysmenu.get(j).meal);
							}
							GoogleTranslater.TranslateArray(mealTmp);
							meals.add(mealTmp);
						}
					}
					else if (day==4) 
					{
						ArrayList<String> mealTmp = new ArrayList<String>();
						restaurantNames.add(ObjectsContainer.restaurants.get(i).name);
						int mealsSize = ObjectsContainer.restaurants.get(i).weeksmenu.wednesday.daysmenu.size();
						if( mealsSize== 0) {
							mealTmp.add("No menu for today. Restaurant is closed.");
        					meals.add(mealTmp);
						}else {
							for (int j=0;j<mealsSize;j++){
								mealTmp.add(ObjectsContainer.restaurants.get(i).weeksmenu.wednesday.daysmenu.get(j).meal);
							}
							GoogleTranslater.TranslateArray(mealTmp);
							meals.add(mealTmp);
						}
					}
					else if (day==5) 
					{
						ArrayList<String> mealTmp = new ArrayList<String>();
						restaurantNames.add(ObjectsContainer.restaurants.get(i).name);
						int mealsSize = ObjectsContainer.restaurants.get(i).weeksmenu.thursday.daysmenu.size();
						if(mealsSize == 0) {
							mealTmp.add("No menu for today. Restaurant is closed.");
        					meals.add(mealTmp);
						}else {
							for (int j=0;j<mealsSize;j++){
								mealTmp.add(ObjectsContainer.restaurants.get(i).weeksmenu.thursday.daysmenu.get(j).meal);
							}
							GoogleTranslater.TranslateArray(mealTmp);
							meals.add(mealTmp);
						}
					}
					else if (day==6) 
					{
						ArrayList<String> mealTmp = new ArrayList<String>();
						restaurantNames.add(ObjectsContainer.restaurants.get(i).name);
						int mealsSize = ObjectsContainer.restaurants.get(i).weeksmenu.friday.daysmenu.size();
						if( mealsSize == 0){
							mealTmp.add("No menu for today. Restaurant is closed.");
        					meals.add(mealTmp);
						}
					    else {
							for (int j=0;j<mealsSize;j++){
								mealTmp.add(ObjectsContainer.restaurants.get(i).weeksmenu.friday.daysmenu.get(j).meal);
		        											
							}
							GoogleTranslater.TranslateArray(mealTmp);
							meals.add(mealTmp);	
						}
					}
					else if (day==7) 
					{
						ArrayList<String> mealTmp = new ArrayList<String>();
						restaurantNames.add(ObjectsContainer.restaurants.get(i).name);
						int mealsSize = ObjectsContainer.restaurants.get(i).weeksmenu.saturday.daysmenu.size();
						if(mealsSize ==0){
							mealTmp.add("No menu for today. Restaurant is closed.");
        					meals.add(mealTmp);
						}else {
							for (int j=0;j<mealsSize;j++){
								mealTmp.add(ObjectsContainer.restaurants.get(i).weeksmenu.saturday.daysmenu.get(j).meal);
							}
							GoogleTranslater.TranslateArray(mealTmp);
							meals.add(mealTmp);
						}
					}
	        	}
	        }
		}

}
