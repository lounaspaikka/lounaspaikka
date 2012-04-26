package fi.aalto.lounaspaikka.filters;

import java.util.ArrayList;
import java.util.Calendar;

import fi.aalto.lounaspaikka.objectfiles.Meal;
import fi.aalto.lounaspaikka.objectfiles.ObjectsContainer;

public class checkFoodswithFilter {


	public ArrayList<Meal> foodarray = new ArrayList<Meal>();
	private ArrayList<filterArrayObject> filters = new ArrayList<filterArrayObject>();

	public void generatereviewdata(){
		getfiltersarrayList();
		generateArrayList();	//food with points
		arrangeList(); 			//arrange by scores top scores to top
	}

	private void arrangeList() { // not the fastest way change it if time
		int arraysize = foodarray.size();
		int foodcounter=0;
		while (arraysize>foodcounter) 
		{
			int location=foodcounter-1;
			int newlocation=foodcounter;
			while (location >=0) {
				if (foodarray.get(location).reviewvalue< foodarray.get(foodcounter).reviewvalue) {
					newlocation=location;	
					}
				location--;
			}
			if (newlocation != foodcounter) {
				Meal meal = new Meal();
				meal = foodarray.get(foodcounter);
				foodarray.remove(foodcounter);
				foodarray.add(newlocation,meal);
			}
			foodcounter++;
		}
	}
	private void getfiltersarrayList() {
		filters=ObjectsContainer.filter;
	}
	private void generateArrayList() {
		Calendar today = Calendar.getInstance();
		int day = today.get(Calendar.DAY_OF_WEEK);
		//sunday is first day saturday 7th we move it to monday 0....
		day=day-2;
		if (day==-1 ) {
			day =6;
		}


		int restaurantc = ObjectsContainer.restaurants.size();
		int counter=0;
		while (restaurantc>counter) 
		{
			String restaurantname = ObjectsContainer.restaurants.get(counter).name;
			int mealcount=getdaysmenusize(counter,day);
			int innercount=0;
			while (mealcount>innercount)
			{
				Meal meal = new Meal();
				meal=getmeal(counter,innercount,day);
				meal.restaurantname=restaurantname;
				meal=pointmeal(meal);
				foodarray.add(meal);
			innercount++;
			}
			counter++;
		}
	}

	private Meal getmeal(int counter, int innercount, int day) {
		Meal meal = new Meal();
		if (day==0) {
		meal=ObjectsContainer.restaurants.get(counter).weeksmenu.monday.daysmenu.get(innercount);
		}
		else if (day==1) {
			meal=ObjectsContainer.restaurants.get(counter).weeksmenu.tuesday.daysmenu.get(innercount);
		}
		else if (day==2) {
			meal=ObjectsContainer.restaurants.get(counter).weeksmenu.wednesday.daysmenu.get(innercount);
		}
		else if (day==3) {
			meal=ObjectsContainer.restaurants.get(counter).weeksmenu.thursday.daysmenu.get(innercount);
		}
		else if (day==4) {
			meal=ObjectsContainer.restaurants.get(counter).weeksmenu.friday.daysmenu.get(innercount);
		}
		else if (day==5) {
			meal=ObjectsContainer.restaurants.get(counter).weeksmenu.saturday.daysmenu.get(innercount);
		}
		else if (day==6) {
			meal=ObjectsContainer.restaurants.get(counter).weeksmenu.sunday.daysmenu.get(innercount);
		}
		return meal;
	}

	private int getdaysmenusize(int counter, int day) {
		int menusize=0;
		if (day==0) {
			menusize=ObjectsContainer.restaurants.get(counter).weeksmenu.monday.daysmenu.size();
		}
		else if (day==1) {
			menusize=ObjectsContainer.restaurants.get(counter).weeksmenu.tuesday.daysmenu.size();
		}
		else if (day==2) {
			menusize=ObjectsContainer.restaurants.get(counter).weeksmenu.wednesday.daysmenu.size();
		}
		else if (day==3) {
			menusize=ObjectsContainer.restaurants.get(counter).weeksmenu.thursday.daysmenu.size();
		}
		else if (day==4) {
			menusize=ObjectsContainer.restaurants.get(counter).weeksmenu.friday.daysmenu.size();
		}
		else if (day==5) {
			menusize=ObjectsContainer.restaurants.get(counter).weeksmenu.saturday.daysmenu.size();
		}
		else if (day==6) {
			menusize=ObjectsContainer.restaurants.get(counter).weeksmenu.sunday.daysmenu.size();
		}

		return menusize;
	}

	private Meal pointmeal(Meal meal) {
		meal.reviewvalue=0;	
		int filtercount = filters.size();
		int filtercounter=0;
		while (filtercount>filtercounter) 
		{
			if (meal.meal.contains(filters.get(filtercounter).filter)) 
			{
				meal.reviewvalue = meal.reviewvalue + filters.get(filtercounter).filterweight;
				if (meal.reviewvalue>100) {
					meal.reviewvalue=100;
				} 
				else if (meal.reviewvalue<-100) 
				{
					meal.reviewvalue=-100;
				}
			}
			filtercounter++;
		}
		return meal;
	}
}