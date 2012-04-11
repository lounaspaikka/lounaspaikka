package fi.aalto.lounaspaikka.JsonRetrievalAndTransform;


import java.util.ArrayList;



import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import fi.aalto.lounaspaikka.objectfiles.Hoursday;
import fi.aalto.lounaspaikka.objectfiles.Meal;
import fi.aalto.lounaspaikka.objectfiles.Restaurant;





public class jsontoobjects 
{
	private String jsonstring;
	public ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();

	public jsontoobjects(String jsonstring) 
	{
		this.jsonstring=jsonstring;
	}


	@SuppressWarnings("unused")
	public void turnInToObject()
	{

		GsonBuilder gsonBuilder = new GsonBuilder();



		JsonParser jsonParser = new JsonParser();

		JsonObject jsonobject= (JsonObject) jsonParser.parse(jsonstring)
				.getAsJsonObject().get("LounasaikaResponse");

		String status= jsonobject
				.getAsJsonObject().get("status").getAsString();



		if (status.equals("OK")) 
		{

			jsonobject = jsonobject.getAsJsonObject().get("result")
					.getAsJsonObject();   //moving object near to campusa array
			String updated =  jsonobject.get("updated").getAsString();
			int numberOfcampuses= jsonobject.getAsJsonArray("campus").size();
			int campuscounter=0;
			String campus="";
			int restaurantid=0;
			while (numberOfcampuses>campuscounter) 
			{ 
				campus = jsonobject.getAsJsonArray("campus").get(campuscounter).getAsJsonObject().get("name").getAsString();
				JsonObject campusobject =jsonobject.getAsJsonArray("campus").get(campuscounter).getAsJsonObject();
				int restaurantsincampus= campusobject.getAsJsonArray("restaurant").size();
				int restaurantcounter=0;
				while (restaurantsincampus>restaurantcounter) 
				{
					JsonObject restaurantobject = campusobject.getAsJsonArray("restaurant").get(restaurantcounter).getAsJsonObject();
					Restaurant restaurant = new Restaurant();
					restaurant.restaurantId=restaurantid;
					restaurantid++;
					restaurant.name=restaurantobject.get("name").getAsString();
					restaurant.url=restaurantobject.get("url").getAsString();
					restaurant.info=restaurantobject.get("info").getAsString();
					restaurant.campus=campus;
					restaurant.updated=updated;
					JsonObject locationobject = campusobject.getAsJsonArray("restaurant").get(restaurantcounter).getAsJsonObject().get("location").getAsJsonObject();
					restaurant.location.address=locationobject.get("address").getAsString();
					restaurant.location.lat=locationobject.get("lat").getAsString();
					restaurant.location.lng=locationobject.get("lng").getAsString();
					restaurant.location.distance=locationobject.get("distance").getAsString();
					//here we gather data about restaurant opening and closing hours	
					JsonObject openinghours =  campusobject.getAsJsonArray("restaurant").get(restaurantcounter).getAsJsonObject().get("opening_hours").getAsJsonObject();
					restaurant.isopen.listOfDays.add(0, timesopen(openinghours,"monday"));
					restaurant.isopen.listOfDays.add(0, timesopen(openinghours,"tuesday"));					
					restaurant.isopen.listOfDays.add(0, timesopen(openinghours,"wednesday"));
					restaurant.isopen.listOfDays.add(0, timesopen(openinghours,"thursday"));
					restaurant.isopen.listOfDays.add(0, timesopen(openinghours,"friday"));
					restaurant.isopen.listOfDays.add(0, timesopen(openinghours,"saturday"));
					//feed doesn't provide sunday times
					//Here we start to gather menu to restaurant object
					JsonObject menuoftheweek = campusobject.getAsJsonArray("restaurant").get(restaurantcounter).getAsJsonObject().get("menu").getAsJsonObject();
					restaurant = getmenutorestaurant(menuoftheweek, restaurant, "monday" );
					restaurant = getmenutorestaurant(menuoftheweek, restaurant, "tuesday" );
					restaurant = getmenutorestaurant(menuoftheweek, restaurant, "wednesday" );
					restaurant = getmenutorestaurant(menuoftheweek, restaurant, "thursday" );
					restaurant = getmenutorestaurant(menuoftheweek, restaurant, "friday" );
					restaurant = getmenutorestaurant(menuoftheweek, restaurant, "saturday" );
					restaurant = getmenutorestaurant(menuoftheweek, restaurant, "sunday" );
					//adds restaurant to array
					restaurants.add(restaurant);   
					restaurantcounter++;
				}


				campuscounter++;
			}
		} // we might want to alert user that status of json file isn't OK and can't be read

	}

	private Hoursday timesopen(JsonObject openinghours, String day) 
	{
		Hoursday isopen = new Hoursday();
		isopen.opens = openinghours.get(day).getAsJsonObject().get("opening_time").getAsString();
		isopen.closes = openinghours.get(day).getAsJsonObject().get("closing_time").getAsString();
		return isopen;
	}


	private Restaurant getmenutorestaurant(JsonObject menuoftheweek, Restaurant restaurant, String day) 
	{
		int meals = 0;
		try {  // try catch for array elements that are empty.
			meals =menuoftheweek.get(day).getAsJsonObject().getAsJsonArray("meal").size();
		} catch (Throwable t)  {
		}

		int count=0;
		while (meals>count)
		{
			Meal meal = new Meal();
			meal.meal= menuoftheweek.get(day).getAsJsonObject().getAsJsonArray("meal").get(count).getAsString().replace("\r\n", "").replace("\n", "");
			if (day.equals("monday")){
				restaurant.weeksmenu.monday.daysmenu.add(meal);
			} else if (day.equals("tuesday")){
				restaurant.weeksmenu.tuesday.daysmenu.add(meal);
			} else if (day.equals("wednesday")){
				restaurant.weeksmenu.wednesday.daysmenu.add(meal);
			} else if (day.equals("thursday")){
				restaurant.weeksmenu.thursday.daysmenu.add( meal);
			} else if (day.equals("friday")){
				restaurant.weeksmenu.friday.daysmenu.add( meal);
			}else if (day.equals("saturday")){
				restaurant.weeksmenu.saturday.daysmenu.add( meal);
			} else if (day.equals("sunday")){
				restaurant.weeksmenu.sunday.daysmenu.add( meal);
			}
			count++;

		}
		return restaurant;
	}
}


// below is pojo where for which i inteded gson to build object turned out that it was
// easier to do it manually because errors in it were next to impossible to find.
/*
class LounasaikaResponse 
{

	private String status;

	public String getStatus(){
		return this.status;
	}
	class result 
	{
		private String updated;
		List<campus> campmus;
	}
	class campus 
	{
		String name;
		List<restaurant> restaurant;
	}

	class restaurant 
	{
		String name;
		String url;
		String info;
		List<location> location;
		List<opening_hours> opening_hours;
		List<menu> menu;
	}

	class location 
	{
		String address;
		String lat;
		String lng;
		String distance;
	}

	class opening_hours 
	{
		List<monday> monday;
		List<tuesday> tuesday;
		List<wednesday> wednesday;
		List<thursday> thursday;
		List<friday> friday;
		List<saturday> saturday;

		class monday 
		{
			String opening_time;
			String closing_time;

		}

		class tuesday 
		{
			String opening_time;
			String closing_time;
		}

		class wednesday 
		{
			String opening_time;
			String closing_time;
		}

		class thursday 
		{
			String opening_time;
			String closing_time;
		}
		class friday 
		{
			String opening_time;
			String closing_time;
		}
		class saturday 
		{
			String opening_time;
			String closing_time;
		}
	}

	class menu 
	{
		List<monday> monday;
		List<tuesday> tuesday;
		List<wednesday> wednesday;
		List<thursday> thursday;
		List<friday> friday;
		List<saturday> saturday;
		List<sunday> sunday;

		class monday 
		{
			String meal;
		}

		class tuesday 
		{
			String meal;
		}

		class wednesday 
		{
			String meal;
		}

		class thursday 
		{
			String meal;
		}

		class friday 
		{
			String meal;
		}

		class saturday {
			String meal;
		}

		class sunday 
		{
			String meal;
		}
	}
}


 */