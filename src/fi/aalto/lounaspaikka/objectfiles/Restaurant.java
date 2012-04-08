package fi.aalto.lounaspaikka.objectfiles;

public class Restaurant 
{
/**
 * Basic Restaurant info as string. Detailed data in the objects
 */
	
	
	public String name;
	public int restaurantId;
	public String url="";
	public String info="";
	public String updated="";
	public String campus;
	public Menu weeksmenu = new Menu();
	public Location location = new Location();
	public Hours isopen = new Hours();

}

