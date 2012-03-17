package fi.aalto.lounaspaikka.JsonRetrievalAndTransform;

import java.util.ArrayList;

import fi.aalto.lounaspaikka.objectfiles.Restaurant;

public class RetrivedDataAndGenerateObject 
{
	private ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
	public 	 RetrivedDataAndGenerateObject() 
	{
		jsonretrive json = new jsonretrive(); 
		json.fetchdata();
		jsontoobjects objectize = new  jsontoobjects(json.jsondata); 
		objectize.turnInToObject();	
		this.restaurants=objectize.restaurants;
	}


	public ArrayList<Restaurant>  getrestaurantarray() 
	{
		return this.restaurants;	
	}


}