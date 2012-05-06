package fi.aalto.lounaspaikka.objectfiles;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;

public class Restaurant implements   Serializable
{
/**
 * Basic Restaurant info as string. Detailed data in the objects
 */
	
	
	public String name;
	public transient int restaurantId;
	public transient String url="";
	public transient String info="";
	public transient String updated="";
	public transient String campus;
	public transient Menu weeksmenu = new Menu();
	public Location location = new Location();
	public transient Hours isopen = new Hours();
	
 
}

