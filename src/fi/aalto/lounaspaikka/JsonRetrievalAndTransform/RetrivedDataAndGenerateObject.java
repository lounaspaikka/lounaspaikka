package fi.aalto.lounaspaikka.JsonRetrievalAndTransform;

import java.util.ArrayList;

import fi.aalto.lounaspaikka.objectfiles.ObjectsContainer;
import fi.aalto.lounaspaikka.objectfiles.Restaurant;

public class RetrivedDataAndGenerateObject 
{
	private ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
	
	private String jsonData;
	
	public String getJsonData() {
		return jsonData;
	}



	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}



	public 	 RetrivedDataAndGenerateObject() 
	{
	}
	
	public void fechData(){
		jsonretrive json = new jsonretrive(); 
		json.fetchdata();
		jsonData = json.jsondata;
	}
	
	public void generateObjects(){
		jsontoobjects objectize = new  jsontoobjects(jsonData); 
		objectize.turnInToObject();	
		this.restaurants=objectize.restaurants;
	}
	
	
	public ArrayList<Restaurant>  getrestaurantarray() 
	{
		return this.restaurants;	
	}

	public void Storetomem() {
		ObjectsContainer.restaurants = restaurants;	
		
	}
	
	
}