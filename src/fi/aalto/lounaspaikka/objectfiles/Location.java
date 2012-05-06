package fi.aalto.lounaspaikka.objectfiles;

import java.io.Serializable;

public class Location implements Serializable
{
	/**
	 * Describes where restaurant is located some restaurants give lat and lng others just address
	 */

	public String address;
	public double lat;
	public double lng;
	public String distance;
	
}
