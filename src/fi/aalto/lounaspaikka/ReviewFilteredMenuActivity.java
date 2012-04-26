package fi.aalto.lounaspaikka;

import java.util.ArrayList;
import java.util.Calendar;

import fi.aalto.lounaspaikka.filters.checkFoodswithFilter;
import fi.aalto.lounaspaikka.filters.filterArrayObject;
import fi.aalto.lounaspaikka.objectfiles.Meal;
import fi.aalto.lounaspaikka.objectfiles.ObjectsContainer;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class ReviewFilteredMenuActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.review_menu);
	//
		filterArrayObject filterr= new filterArrayObject();
		filterr.filter="Porsaanleike";
		filterr.filterweight=50;
		ObjectsContainer.filter.add(filterr);
		//		
		
		checkFoodswithFilter pointfoods = new checkFoodswithFilter();
		pointfoods.generatereviewdata(); //Porsaanleike
	
		ArrayList<Meal> mealList = new ArrayList<Meal>();
		mealList=pointfoods.foodarray;
		int mealsize= mealList.size();
		int counter=0;
		String reviewList="";
		while (mealsize>counter) 
		{
			reviewList = reviewList + "Meal" + mealList.get(counter).meal + "\n";
			reviewList = reviewList + "Restaurant" + mealList.get(counter).restaurantname + "\n";
			reviewList = reviewList + "Weight" + mealList.get(counter).reviewvalue + "\n";
			reviewList = reviewList + "\n";
			counter++;
		}
		TextView tv;
		tv = (TextView)findViewById(R.id.textView1);

		tv.setMovementMethod(new ScrollingMovementMethod());
		tv.setText(reviewList); 
	}
}



