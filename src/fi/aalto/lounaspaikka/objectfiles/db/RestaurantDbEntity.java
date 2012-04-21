package fi.aalto.lounaspaikka.objectfiles.db;

import java.util.Date;

public class RestaurantDbEntity {
	String restaurantData;
	public String getRestaurantData() {
		return restaurantData;
	}
	public void setRestaurantData(String restaurantData) {
		this.restaurantData = restaurantData;
	}
	public Date getDate() {
		return date;
	}
	
	Date date;
	public void setDate(Date date) {
		this.date = date;
	}
	
}
