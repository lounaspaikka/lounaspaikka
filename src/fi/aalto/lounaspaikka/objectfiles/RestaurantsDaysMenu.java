package fi.aalto.lounaspaikka.objectfiles;

import java.util.ArrayList;
/**
 * 
 * Menus are supposed to be stored in arrays list in following order monday (index 0)
 * , tuesday (index 1), etc... Sunday meal is stored or copied as blank even though opening and closing
 * times are not transmited
 * @author oappi
 *
 */
public class RestaurantsDaysMenu 
{
public ArrayList<Meal> daysmenu = new ArrayList<Meal>();
}
